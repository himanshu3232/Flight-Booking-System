package com.capgemini.flight_booking.fare_service.client;

import com.capgemini.flight_booking.fare_service.dto.FlightDto;
import com.capgemini.flight_booking.fare_service.exception.InvalidFlightIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Component
@RequiredArgsConstructor
public class FlightFareClient {
    private final RestTemplate restTemplate;

    public double getFare(long flightId) {
        final String url = "http://localhost:1000/flights";
        URI uri = UriComponentsBuilder
                .fromUriString(url)
                .queryParam("flightId", flightId)
                .build()
                .toUri();
        FlightDto flightDto = restTemplate.getForObject(uri, FlightDto.class);

        if(flightDto == null) {
            throw new InvalidFlightIdException("Invalid flight id");
        }
        return flightDto.price();
    }
}
