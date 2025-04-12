package com.capgemini.flight_booking.fare_service.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 *
 * @param status {@link HttpStatus code}
 * @param response message
 * @param time time of response
 */
public record ResponseDto (
        HttpStatus status,
        String response,
        LocalDateTime time
){}
