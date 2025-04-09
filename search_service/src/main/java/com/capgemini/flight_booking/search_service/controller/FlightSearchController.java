package com.capgemini.flight_booking.search_service.controller;

import com.capgemini.flight_booking.search_service.dto.FlightDto;
import com.capgemini.flight_booking.search_service.service.IFlightSearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.time.LocalDate;
import java.util.List;


@RestController
@RequestMapping("/flights")
@RequiredArgsConstructor
public class FlightSearchController {

    private final IFlightSearchService flightSearchService;

    @GetMapping("/search")
    public ResponseEntity<List<FlightDto>> searchFlights(@RequestParam String from, @RequestParam String to, @RequestParam String date) {
        return ResponseEntity.ok(flightSearchService.getFlights(from, to,  LocalDate.parse(date)));
    }

    @GetMapping
    public ResponseEntity<FlightDto> getFlightById(@RequestParam long flightId) {
        return ResponseEntity.ok(flightSearchService.getFlightById(flightId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<FlightDto>> getAllFlights() {
        return ResponseEntity.ok(flightSearchService.getAllFlights());
    }
}
