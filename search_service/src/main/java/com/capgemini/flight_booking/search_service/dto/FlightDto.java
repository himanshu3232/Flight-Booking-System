package com.capgemini.flight_booking.search_service.dto;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 *
 * @param flightId unique flight id
 * @param airline airline name
 * @param departureAirport departure airport
 * @param arrivalAirport arrival airport
 * @param departureDate departure date
 * @param arrivalDate arrival date
 * @param duration duration of flight
 * @param price price of flight
 * @param seatsAvailable number of seats available
 */
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