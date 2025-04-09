package com.capgemini.flight_booking.booking_service.service.impl;

import com.capgemini.flight_booking.booking_service.client.FlightAvailabilityClient;
import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.dto.FlightDto;
import com.capgemini.flight_booking.booking_service.dto.ResponseDto;
import com.capgemini.flight_booking.booking_service.entity.BookingEntity;
import com.capgemini.flight_booking.booking_service.enums.BookingStatus;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.capgemini.flight_booking.booking_service.dto.mapper.Mapper.mapToBookingEntity;
import static com.capgemini.flight_booking.booking_service.dto.mapper.Mapper.mapToBookingRequestDto;

@RequiredArgsConstructor
@Service
@Slf4j
public class BookingServiceImpl implements IBookingService {

    private final BookingRepository bookingRepository;
    private final IPaymentService paymentService;
    private final FlightAvailabilityClient flightAvailabilityClient;
    private final StreamBridge streamBridge;


    @Override
    public ResponseDto bookFlight(BookingRequestDto bookingRequestDTO) throws PaymentFailedException, InvalidFlightIdException, NoSeatsAvailableException {
        BookingEntity bookingEntity = mapToBookingEntity(bookingRequestDTO);
        bookingEntity.setBookingId(GenerateId.generateId("Booking"));
        long paymentId = paymentService.processPayment(bookingRequestDTO, bookingEntity.getBookingId());

        //Check if payment is successful
        if(paymentService.getPaymentStatus(paymentId) != PaymentStatus.SUCCESS){
            throw new PaymentFailedException("Payment failed!");
        }

        FlightDto flightDto = flightAvailabilityClient.getFlightAvailabilityById(bookingRequestDTO.flightId());

        //Check if flight is available
        if(flightDto == null){
            throw new InvalidFlightIdException("Invalid flight ID: " + bookingRequestDTO.flightId());
        }


        //Check if seats are available
        if(flightDto.seatsAvailable() < 1){
            throw new NoSeatsAvailableException("No seats available");
        }

        sendCommunication(flightDto);

        //Generate pnr code
        bookingEntity.setPnr(GeneratePnr.generatePnr(flightDto.airline()));
        bookingEntity.setStatus(BookingStatus.BOOKED);
        bookingEntity.setPaymentId(paymentId);
        bookingRepository.save(bookingEntity);

        return new ResponseDto(HttpStatus.CREATED, "Booking successful with pnr:" + bookingEntity.getPnr(), LocalDateTime.now());
    }

    @Override
    public BookingRequestDto getBookingByPnr(String pnr) throws NoBookingsFoundException {
        BookingEntity bookingEntity = bookingRepository
                .findByPnr(pnr)
                .orElseThrow(() -> new NoBookingsFoundException("No bookings found with the pnr " + pnr));

        return mapToBookingRequestDto(bookingEntity);
    }

    @Override
    public void cancelBooking(String pnr) {
        bookingRepository.deleteByPnr(pnr);
    }


    private void sendCommunication(FlightDto flightDto){
        log.debug("Sending flightDto to RabbitMQ to update seats available {}" , flightDto);
        var result = streamBridge.send("sendCommunication-out-0", flightDto);
        log.debug("Result from RabbitMQ: {}", result);

    }
}
