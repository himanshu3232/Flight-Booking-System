package com.capgemini.flight_booking.booking_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * Configures a WebClient for making HTTP requests.
 */
@Configuration
public class WebClientConfig {

    /**
     *
     * @param builder builds a WebClient bean
     * @return  WebClient bean
     */
    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:1000/flights")
                .build();
    }
}