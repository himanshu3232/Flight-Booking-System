package com.capgemini.flight_booking.booking_service.service.impl;

import com.capgemini.flight_booking.booking_service.client.FlightAvailabilityClient;
import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.dto.FlightDto;
import com.capgemini.flight_booking.booking_service.dto.ResponseDto;
import com.capgemini.flight_booking.booking_service.entity.BookingEntity;
import com.capgemini.flight_booking.booking_service.enums.BookingStatus;
import com.capgemini.flight_booking.booking_service.enums.CheckInStatus;
import com.capgemini.flight_booking.booking_service.enums.PaymentStatus;
import com.capgemini.flight_booking.booking_service.exception.InvalidFlightIdException;
import com.capgemini.flight_booking.booking_service.exception.NoBookingsFoundException;
import com.capgemini.flight_booking.booking_service.exception.NoSeatsAvailableException;
import com.capgemini.flight_booking.booking_service.exception.PaymentFailedException;
import com.capgemini.flight_booking.booking_service.repository.BookingRepository;
import com.capgemini.flight_booking.booking_service.service.IBookingService;
import com.capgemini.flight_booking.booking_service.service.IPaymentService;
import com.capgemini.flight_booking.booking_service.service.generate.GenerateId;
import com.capgemini.flight_booking.booking_service.service.generate.GeneratePnr;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;

import static com.capgemini.flight_booking.booking_service.dto.mapper.Mapper.mapToBookingEntity;
import static com.capgemini.flight_booking.booking_service.dto.mapper.Mapper.mapToBookingRequestDto;

@RequiredArgsConstructor
@Service
public class BookingServiceImpl implements IBookingService {

    private final BookingRepository bookingRepository;
    private final IPaymentService paymentService;
    private final FlightAvailabilityClient flightAvailabilityClient;
    private final StreamBridge streamBridge;


    /**
     * Book a flight
     * @param bookingRequestDTO request body by the client
     * @return returns a response
     * @throws PaymentFailedException if payment fails
     * @throws InvalidFlightIdException if flight id is invalid
     * @throws NoSeatsAvailableException if no seats are available
     */

    @Override
    public ResponseDto bookFlight(BookingRequestDto bookingRequestDTO) throws PaymentFailedException, InvalidFlightIdException, NoSeatsAvailableException {
        BookingEntity bookingEntity = mapToBookingEntity(bookingRequestDTO);
        bookingEntity.setBookingId(GenerateId.generateId("Booking"));
        long paymentId = paymentService.processPayment(bookingRequestDTO, bookingEntity.getBookingId());

        //Check if payment is successful
        if(paymentService.getPaymentStatus(paymentId) != PaymentStatus.SUCCESS){
            throw new PaymentFailedException("Payment failed!");
        }

        FlightDto flightDto = flightAvailabilityClient
                .getFlightAvailabilityById(bookingRequestDTO.flightId())
                .block();

        //Check if flight is available
        if(flightDto == null){
            throw new InvalidFlightIdException("Invalid flight ID: " + bookingRequestDTO.flightId());
        }


        //Check if seats are available
        if(flightDto.seatsAvailable() < 1){
            throw new NoSeatsAvailableException("No seats available");
        }

        //update the number of seats available
        updateSeats(flightDto);

        //Generate pnr code
        bookingEntity.setPnr(GeneratePnr.generatePnr(flightDto.airline()));
        bookingEntity.setStatus(BookingStatus.BOOKED);
        bookingEntity.setPaymentId(paymentId);
        bookingEntity.setCheckIn(CheckInStatus.NOT_CHECKED_IN);
        bookingRepository.save(bookingEntity);

        return new ResponseDto(HttpStatus.CREATED, "Booking successful with pnr:" + bookingEntity.getPnr(), LocalDateTime.now());
    }


    /**
     * Get booking details by passing in the pnr
     * @param pnr unique pnr code
     * @return returns a response of the booking
     * @throws NoBookingsFoundException if no bookings are found
     */

    @Override
    public BookingRequestDto getBookingByPnr(String pnr) throws NoBookingsFoundException {
        BookingEntity bookingEntity = bookingRepository
                .findByPnr(pnr)
                .orElseThrow(() -> new NoBookingsFoundException("No bookings found with the pnr " + pnr));

        return mapToBookingRequestDto(bookingEntity);
    }

    /**
     *  Cancels the booking made by the client
     * @param pnr unique pnr code
     */

    @Override
    public void cancelBooking(String pnr) {
        if(getBookingByPnr(pnr) != null){
            bookingRepository.deleteByPnr(pnr);
        }
    }

    /**
     * Check-in a booking
     * @param pnr unique pnr code
     */
    @Override
    public void checkIn(String pnr) {
        BookingEntity bookingEntity = bookingRepository.findByPnr(pnr).orElseThrow(() -> new NoBookingsFoundException("No bookings found with the pnr " + pnr));
        bookingEntity.setCheckIn(CheckInStatus.CHECKED_IN);
        bookingRepository.save(bookingEntity);
    }


    /**
     * Sends a message to update the number of seats available
     * @param flightDto contains flight details
     */
    private void updateSeats(FlightDto flightDto){
        streamBridge.send("updateSeats-out-0", flightDto);
    }
}
