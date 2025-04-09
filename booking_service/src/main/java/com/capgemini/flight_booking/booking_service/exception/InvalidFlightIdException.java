package com.capgemini.flight_booking.booking_service.exception;

public class InvalidFlightIdException extends RuntimeException {
    public InvalidFlightIdException(String message) {
        super(message);
    }
}
