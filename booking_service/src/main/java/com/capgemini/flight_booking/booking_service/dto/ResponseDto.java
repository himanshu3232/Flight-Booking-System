package com.capgemini.flight_booking.booking_service.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 *
 * @param status status code
 * @param response string response message
 * @param time time of response
 */
public record ResponseDto(
        HttpStatus status,
        String response,
        LocalDateTime time
) {}
