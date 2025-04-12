package com.capgemini.flight_booking.search_service.service;


import com.capgemini.flight_booking.search_service.dto.FlightDto;
import java.time.LocalDate;
import java.util.List;

public interface IFlightSearchService {
    /**
     *
     * @param from onboarding airport
     * @param to destination airport
     * @param date date of onboarding
     * @return list of flights available from onboarding airport to destination on a particular day
     */
    List<FlightDto> getFlights(String from, String to, LocalDate date);

    /**
     * @return list of all available flights
     */
    List<FlightDto> getAllFlights();

    /**
     * @param flightId unique flightId
     * @return FlightDto of a unique flight
     */
    FlightDto getFlightById(long flightId);

    /**
     * @param flightDto with flight id and number of seats previously available
     */
    void updateSeats(FlightDto flightDto);
}
