package com.capgemini.flight_booking.booking_service.service.impl;

import com.capgemini.flight_booking.booking_service.client.FlightAvailabilityClient;
import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.dto.EmailDetails;
import com.capgemini.flight_booking.booking_service.dto.FlightDto;
import com.capgemini.flight_booking.booking_service.dto.ResponseDto;
import com.capgemini.flight_booking.booking_service.dto.mapper.Mapper;
import com.capgemini.flight_booking.booking_service.entity.BookingEntity;
import com.capgemini.flight_booking.booking_service.enums.BookingStatus;
import com.capgemini.flight_booking.booking_service.enums.CheckInStatus;
import com.capgemini.flight_booking.booking_service.enums.PaymentStatus;
import com.capgemini.flight_booking.booking_service.enums.RequestType;
import com.capgemini.flight_booking.booking_service.exception.*;
import com.capgemini.flight_booking.booking_service.repository.BookingRepository;
import com.capgemini.flight_booking.booking_service.service.IBookingService;
import com.capgemini.flight_booking.booking_service.service.IPaymentService;
import com.capgemini.flight_booking.booking_service.service.generate.GenerateId;
import com.capgemini.flight_booking.booking_service.service.generate.GeneratePnr;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.http.HttpStatus;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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
    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.username}")
    private String sender;


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
        String pnr = GeneratePnr.generatePnr(flightDto.airline());
        bookingEntity.setPnr(pnr);
        bookingEntity.setStatus(BookingStatus.BOOKED);
        bookingEntity.setPaymentId(paymentId);
        bookingEntity.setCheckIn(CheckInStatus.NOT_CHECKED_IN);
        bookingRepository.save(bookingEntity);

        sendConfirmationMail(RequestType.BOOK, bookingRequestDTO, pnr);

        return new ResponseDto(HttpStatus.CREATED, "Booking successful with pnr:" + bookingEntity.getPnr(), LocalDateTime.now(), pnr);
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
            sendConfirmationMail(RequestType.CANCEL, getBookingByPnr(pnr), pnr);
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
        sendConfirmationMail(RequestType.CHECK_IN, Mapper.mapToBookingRequestDto(bookingEntity), pnr);
    }

    /**
     * Sends email notification to the user
     * @param requestType type of the request CANCEL, BOOK or CHECKIN
     * @param bookingRequestDto user details
     */
    @Override
    public void sendConfirmationMail(RequestType requestType, BookingRequestDto bookingRequestDto, String pnr) {
        switch (requestType){
            case RequestType.CANCEL -> sendSimpleMail(
                    new EmailDetails(bookingRequestDto, String.format("Cancel Flight for %s%nYour refund of %.2f has been processed and will be deposited to your account", bookingRequestDto.passengerName(), bookingRequestDto.paidAmount()), "Your booking has been cancelled")
            );
            case RequestType.BOOK -> sendSimpleMail(
                    new EmailDetails(bookingRequestDto, String.format("Booked Flight for %s%nYour pnr is %s", bookingRequestDto.passengerName(), pnr) , "Your flight has been booked")
            );
            case RequestType.CHECK_IN-> sendSimpleMail(
                    new EmailDetails(bookingRequestDto, String.format("Checked In Flight for %s%nYour pnr is %s", bookingRequestDto.passengerName(), pnr), "Your flight has been checked in")
            );
        }
    }

    /**
     * sends the mail to the recipient
     * @param details details of the email
     */
    private void sendSimpleMail(EmailDetails details)
    {
        try {

            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();

            mailMessage.setFrom(sender);
            mailMessage.setTo(details.bookingRequestDto().passengerEmail());
            mailMessage.setText(details.msgBody());
            mailMessage.setSubject(details.subject());

            // Sending the mail
            javaMailSender.send(mailMessage);
        }catch (Exception e) {
            throw new EmailSendingException("Error while sending mail");
        }
    }


    /**
     * Sends a message to update the number of seats available
     * @param flightDto contains flight details
     */
    private void updateSeats(FlightDto flightDto){
        streamBridge.send("updateSeats-out-0", flightDto);
    }
}
