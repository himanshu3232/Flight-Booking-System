package com.capgemini.flight_booking.booking_service.dto;

import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.LocalTime;

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