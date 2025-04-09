package com.capgemini.flight_booking.booking_service.service;


import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.dto.ResponseDto;
import com.capgemini.flight_booking.booking_service.exception.InvalidFlightIdException;
import com.capgemini.flight_booking.booking_service.exception.NoBookingsFoundException;
import com.capgemini.flight_booking.booking_service.exception.NoSeatsAvailableException;
import com.capgemini.flight_booking.booking_service.exception.PaymentFailedException;

public interface IBookingService {

    ResponseDto bookFlight(BookingRequestDto bookingRequestDTO) throws PaymentFailedException, InvalidFlightIdException, NoSeatsAvailableException;
    BookingRequestDto getBookingByPnr(String pnr) throws NoBookingsFoundException;
    void cancelBooking(String pnr);
}
