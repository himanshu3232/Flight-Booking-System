package com.capgemini.flight_booking.booking_service.service;


import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.enums.PaymentStatus;

public interface IPaymentService {
    long processPayment(BookingRequestDto bookingRequestDto, String bookingId);
    PaymentStatus getPaymentStatus(long paymentId);
}
