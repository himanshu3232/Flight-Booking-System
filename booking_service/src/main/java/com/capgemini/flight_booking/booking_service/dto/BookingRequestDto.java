package com.capgemini.flight_booking.booking_service.dto;

import jakarta.validation.constraints.*;

/**
 *
 * @param flightId
 * @param passengerName
 * @param passengerEmail
 * @param passengerAge
 * @param paidAmount
 */
public record BookingRequestDto (
        @Min(1)
        long flightId,
        @NotEmpty
        String passengerName,
        @Email
        String passengerEmail,
        @Min(0) @Max(120)
        int passengerAge,
        @Min(0) @Max(20000)
        double paidAmount
) {}