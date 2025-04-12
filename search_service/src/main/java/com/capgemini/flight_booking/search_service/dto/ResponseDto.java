package com.capgemini.flight_booking.search_service.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 *
 * @param status {@link HttpStatus} code
 * @param response message
 * @param time of response
 */
public record ResponseDto(
        HttpStatus status,
        String response,
        LocalDateTime time
) {}
