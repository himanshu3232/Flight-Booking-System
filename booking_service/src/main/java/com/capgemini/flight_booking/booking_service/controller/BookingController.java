package com.capgemini.flight_booking.booking_service.controller;

import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.dto.ResponseDto;
import com.capgemini.flight_booking.booking_service.service.IBookingService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/booking")
public class BookingController {

    private final IBookingService bookingService;

    @PostMapping
    public ResponseEntity<ResponseDto> bookFlight(@Valid @RequestBody BookingRequestDto bookingRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.bookFlight(bookingRequestDto));
    }

    @GetMapping
    public ResponseEntity<BookingRequestDto> getBookingByPnr(@RequestParam String pnr) {
        return ResponseEntity.ok(bookingService.getBookingByPnr(pnr));
    }

    @DeleteMapping
    public ResponseEntity<ResponseDto> cancelBooking(@RequestParam String pnr) {
        bookingService.cancelBooking(pnr);
        ResponseDto responseDto = new ResponseDto(HttpStatus.OK, "Booking cancelled successfully", LocalDateTime.now());
        return ResponseEntity.ok(responseDto);
    }
}
