package com.capgemini.flight_booking.checkin_service.exception;

public class AlreadyCheckedInException extends RuntimeException {
    public AlreadyCheckedInException(String message) {
        super(message);
    }
}
