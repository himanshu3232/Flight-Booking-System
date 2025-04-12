package com.capgemini.flight_booking.checkin_service.client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class VerifyPnr {

    private final WebClient webClient;

    public boolean verifyPnr(String pnr) {
        Mono<Object> response = webClient
                .get()
                .uri(uriBuilder -> uriBuilder
                        .path("/booking")
                        .queryParam("pnr", pnr)
                        .build()
                ).retrieve()
                .bodyToMono(Object.class);

        return response.block() != null;
    }
}
