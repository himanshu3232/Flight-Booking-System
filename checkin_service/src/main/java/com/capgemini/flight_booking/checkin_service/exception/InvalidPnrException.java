package com.capgemini.flight_booking.checkin_service.exception;

public class InvalidPnrException extends RuntimeException {
    public InvalidPnrException(String message) {
        super(message);
    }
}
