package com.capgemini.flight_booking.fare_service.controller;

import com.capgemini.flight_booking.fare_service.client.FlightFareClient;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/fare")
@RequiredArgsConstructor
public class FareController {

    private final FlightFareClient flightFareClient;
    @GetMapping
    public ResponseEntity<Double> getFare(@RequestParam long flightId) {
        return ResponseEntity.ok(flightFareClient.getFare(flightId));
    }
}
