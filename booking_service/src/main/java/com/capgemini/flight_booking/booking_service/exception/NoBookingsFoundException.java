package com.capgemini.flight_booking.booking_service.exception;

public class NoBookingsFoundException extends RuntimeException {
    public NoBookingsFoundException(String message) {
        super(message);
    }
}
