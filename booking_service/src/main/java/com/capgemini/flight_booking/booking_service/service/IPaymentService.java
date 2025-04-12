package com.capgemini.flight_booking.booking_service.service;


import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.enums.PaymentStatus;

public interface IPaymentService {

    /**
     * Processes the payment and returns the payment id
     * @param bookingRequestDto request made by the client
     * @param bookingId unique booking id
     * @return payment id
     */
    long processPayment(BookingRequestDto bookingRequestDto, String bookingId);

    /**
     * Check the payment status by passing in the payment id
     * @param paymentId unique payment id
     * @return payment status enum
     */
    PaymentStatus getPaymentStatus(long paymentId);
}
