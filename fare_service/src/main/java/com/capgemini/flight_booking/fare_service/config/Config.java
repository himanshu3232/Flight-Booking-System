package com.capgemini.flight_booking.fare_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Config {
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
