package com.capgemini.flight_booking.checkin_service.dto;

import org.springframework.http.HttpStatus;

public record ExceptionResponseDto(
        HttpStatus status,
        String message
) {}
