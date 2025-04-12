package com.capgemini.flight_booking.booking_service.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @param flightId unique flight id working as a foreign key
 * @param airline airline name
 * @param departureAirport departure airport
 * @param arrivalAirport arrival airport
 * @param departureDate departure date
 * @param arrivalDate arrival date
 * @param duration duration of flight
 * @param price price of flight
 * @param seatsAvailable seats available
 */
public record FlightDto (
        @Min(1)
        long flightId,
        @NotEmpty
        String airline,
        @NotEmpty
        String departureAirport,
        @NotEmpty
        String arrivalAirport,
        @NotNull
        LocalDate departureDate,
        @NotNull
        LocalDate arrivalDate,
        @NotNull
        LocalTime duration,
        @Min(0) @Max(20000)
        double price,
        @Min(0)
        int seatsAvailable
) {}