package com.capgemini.flight_booking.fare_service.dto;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public record ResponseDto (
        HttpStatus status,
        String response,
        LocalDateTime time
){}
