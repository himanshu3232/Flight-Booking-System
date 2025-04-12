package com.capgemini.flight_booking.booking_service.client;

import com.capgemini.flight_booking.booking_service.dto.FlightDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * Client for flight availability
 */
@Component
@RequiredArgsConstructor
public class FlightAvailabilityClient {

    private final WebClient webClient;

    /**
     *
     * @param flightId unique flight id for each flight details
     * @return Mono of flight dto
     */
    public Mono<FlightDto> getFlightAvailabilityById(long flightId) {
        return webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("flightId", flightId)
                        .build()
                )
                .retrieve()
                .bodyToMono(FlightDto.class);
    }
}
