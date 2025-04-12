package com.capgemini.flight_booking.booking_service.dto;

import jakarta.validation.constraints.*;

/**
 *
 * @param flightId foreign key of flights table
 * @param passengerName passenger name
 * @param passengerEmail passenger email
 * @param passengerAge passenger age
 * @param paidAmount paid amount
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