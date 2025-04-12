package com.capgemini.flight_booking.checkin_service.dto;

import org.springframework.http.HttpStatus;

/**
 *
 * @param status http status code
 * @param message error message
 */
public record ExceptionResponseDto(
        HttpStatus status,
        String message
) {}
