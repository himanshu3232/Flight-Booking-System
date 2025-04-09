package com.capgemini.flight_booking.booking_service.exception;

public class InvalidPaymentIdException extends RuntimeException{
    public InvalidPaymentIdException(String message) {
        super(message);
    }
}
