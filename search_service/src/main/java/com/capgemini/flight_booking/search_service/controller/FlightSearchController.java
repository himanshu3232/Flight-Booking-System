package com.capgemini.flight_booking.search_service.controller;

import com.capgemini.flight_booking.search_service.dto.FlightDto;
import com.capgemini.flight_booking.search_service.service.IFlightSearchService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


/**
 * Exposes the Flight Search REST APIs
 * Allows you to search for flights
 * Allows you to get flight details by id
 * Get all flights available
 */
@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
@Tag(name = "Flight Search", description = "Search for flights")
public class FlightSearchController {

    private final IFlightSearchService flightSearchService;

    /**
     * Checks if the flight is available
     * @param from origin
     * @param to destination
     * @return list of flights
     */
    @Operation(summary = "Search for flights", description = "Search for flights by passing from and to")
    @GetMapping("/search")
    public ResponseEntity<List<FlightDto>> searchFlights(@RequestParam String from, @RequestParam String to) {
        return ResponseEntity.ok(flightSearchService.getFlights(from, to));
    }

    /**
     * Get flight summary by passing the ID
     * @param flightId unique flight id
     * @return flight details
     */
    @Operation(summary = "Get flight details", description = "Get flight details by passing flightId")
    @GetMapping
    public ResponseEntity<FlightDto> getFlightById(@RequestParam long flightId) {
        return ResponseEntity.ok(flightSearchService.getFlightById(flightId));
    }

    /**
     * Fetch the list of all the flights available
     * @return list of all available flights
     */
    @Operation(summary = "Get all available flights", description = "Get all available flights")
    @GetMapping("/all")
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return ResponseEntity.ok(flightSearchService.getAllFlights());
    }
}
