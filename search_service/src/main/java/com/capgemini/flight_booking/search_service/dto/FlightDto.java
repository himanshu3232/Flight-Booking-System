package com.capgemini.flight_booking.search_service.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record FlightDto (
        long flightId,
        String airline,
        String departureAirport,
        String arrivalAirport,
        LocalDate departureDate,
        LocalDate arrivalDate,
        LocalTime duration,
        double price,
        int seatsAvailable
) {}