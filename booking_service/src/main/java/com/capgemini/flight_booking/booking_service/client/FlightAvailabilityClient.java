package com.capgemini.flight_booking.booking_service.client;

import com.capgemini.flight_booking.booking_service.dto.FlightDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class FlightAvailabilityClient {
    private final RestTemplate restTemplate;

    public FlightDto getFlightAvailabilityById(long flightId) {
        final String flightAvailabilityUrl = "http://localhost:1000/flights";
        URI uri = UriComponentsBuilder
                .fromUriString(flightAvailabilityUrl)
                .queryParam("flightId", flightId)
                .build()
                .toUri();
        return restTemplate.getForObject(uri , FlightDto.class);
    }
}
