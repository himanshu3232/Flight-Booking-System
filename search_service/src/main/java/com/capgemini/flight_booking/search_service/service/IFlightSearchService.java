package com.capgemini.flight_booking.search_service.service;


import com.capgemini.flight_booking.search_service.dto.FlightDto;
import java.time.LocalDate;
import java.util.List;

public interface IFlightSearchService {
    List<FlightDto> getFlights(String from, String to, LocalDate date);
    List<FlightDto> getAllFlights();
    FlightDto getFlightById(long flightId);
    void updateSeats(FlightDto flightDto);
}
