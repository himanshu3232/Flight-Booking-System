package com.capgemini.flight_booking.fare_service.controller;

import com.capgemini.flight_booking.fare_service.client.FlightFareClient;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * Exposes the Fare REST API(s)
 */

@RestController
@RequestMapping("/fare")
@RequiredArgsConstructor
@Tag(name = "Fare Service", description = "Fare Service REST APIs")
public class FareController {

    private final FlightFareClient flightFareClient;

    /**
     * Get the flight fare by hitting the API
     * @param flightId unique flight id
     * @return the flight fare
     */
    @Operation(summary = "Get Fare", description = "Get Fare by passing flightId")
    @GetMapping
    public ResponseEntity<Double> getFare(@RequestParam long flightId) {
        return ResponseEntity.ok(flightFareClient.getFare(flightId));
    }
}
