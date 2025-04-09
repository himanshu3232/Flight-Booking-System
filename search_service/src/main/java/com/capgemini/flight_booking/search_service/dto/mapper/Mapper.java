package com.capgemini.flight_booking.search_service.dto.mapper;

import com.capgemini.flight_booking.search_service.dto.FlightDto;
import com.capgemini.flight_booking.search_service.entity.FlightEntity;

public class Mapper {

    private Mapper() {}

    public static FlightDto mapToFlightDto(FlightEntity flightEntity) {
        return new FlightDto(
                flightEntity.getFlightId(),
                flightEntity.getAirline(),
                flightEntity.getDepartureAirport(),
                flightEntity.getArrivalAirport(),
                flightEntity.getDepartureDate(),
                flightEntity.getArrivalDate(),
                flightEntity.getDuration(),
                flightEntity.getPrice(),
                flightEntity.getSeatsAvailable()
        );
    }
}
