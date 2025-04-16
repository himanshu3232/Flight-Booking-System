package com.capgemini.flight_booking.booking_service.dto;

public record EmailDetails(BookingRequestDto bookingRequestDto, String msgBody, String subject) {
}
