package com.capgemini.flight_booking.checkin_service.controller;

import com.capgemini.flight_booking.checkin_service.dto.CheckInDto;
import com.capgemini.flight_booking.checkin_service.service.ICheckInService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/checkin")
@Tag(name = "CheckIn", description = "CheckIn REST APIs")
public class CheckInController {

    private final ICheckInService checkInService;

    @Operation(summary = "Check-in a booking", description = "Check-in a booking by passing PNR details")
    @PostMapping
    public ResponseEntity<CheckInDto> checkIn(@RequestParam String pnr) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(checkInService.checkIn(pnr));
    }
}
