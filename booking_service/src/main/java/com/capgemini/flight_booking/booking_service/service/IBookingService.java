package com.capgemini.flight_booking.booking_service.service;


import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.dto.ResponseDto;
import com.capgemini.flight_booking.booking_service.enums.RequestType;
import com.capgemini.flight_booking.booking_service.exception.InvalidFlightIdException;
import com.capgemini.flight_booking.booking_service.exception.NoBookingsFoundException;
import com.capgemini.flight_booking.booking_service.exception.NoSeatsAvailableException;
import com.capgemini.flight_booking.booking_service.exception.PaymentFailedException;

public interface IBookingService {
    /**
     * Book a flight
     * @param bookingRequestDTO request body by the client
     * @return returns a response
     * @throws PaymentFailedException if payment fails
     * @throws InvalidFlightIdException if flight id is invalid
     * @throws NoSeatsAvailableException if no seats are available
     */
    ResponseDto bookFlight(BookingRequestDto bookingRequestDTO) throws PaymentFailedException, InvalidFlightIdException, NoSeatsAvailableException;

    /**
     * Get booking details by passing in the pnr
     * @param pnr unique pnr code
     * @return returns a response of the booking
     * @throws NoBookingsFoundException if no bookings are found
     */
    BookingRequestDto getBookingByPnr(String pnr) throws NoBookingsFoundException;

    /**
     *  Cancels the booking made by the client
     * @param pnr unique pnr code
     */
    void cancelBooking(String pnr);

    /**
     * Check-in a booking
     * @param pnr unique pnr code
     */
    void checkIn(String pnr);

    /**
     * Sends email notification to the user
     */
    void sendConfirmationMail(RequestType requestType, BookingRequestDto bookingRequestDto, String pnr);
}
