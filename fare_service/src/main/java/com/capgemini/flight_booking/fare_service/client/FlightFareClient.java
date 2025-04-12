package com.capgemini.flight_booking.fare_service.client;

import com.capgemini.flight_booking.fare_service.dto.FlightDto;
import com.capgemini.flight_booking.fare_service.exception.InvalidFlightIdException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Fetches the flight fare from the flight availability service
 */

@Component
@RequiredArgsConstructor
public class FlightFareClient {
    private final WebClient webClient;

    /**
     * Fetches the flight fare
     * @param flightId unique flight id
     * @return the flight fare
     */
    public double getFare(long flightId) {

        Mono<FlightDto> flightDtoMono = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("flightId", flightId)
                        .build()
                ).retrieve()
                .bodyToMono(FlightDto.class);

        FlightDto flightDto = flightDtoMono.block();

        if(flightDto == null) {
            throw new InvalidFlightIdException("Invalid flight id");
        }
        return flightDto.price();
    }
}
