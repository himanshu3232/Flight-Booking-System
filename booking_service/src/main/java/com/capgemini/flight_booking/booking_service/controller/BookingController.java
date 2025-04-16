package com.capgemini.flight_booking.booking_service.controller;

import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.dto.ResponseDto;
import com.capgemini.flight_booking.booking_service.service.IBookingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;

/**
 * Controller class for booking service
 * Allows user to book a flight, get booking details and cancel a booking
 */
@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/booking")
@Tag(name = "Booking", description = "Booking REST APIs")
public class BookingController {

    private final IBookingService bookingService;

    /**
     *
     * @param bookingRequestDto request body from client
     * @return response entity that a flight has been booked successfully
     */
    @Operation(summary = "Book a flight", description = "Book a flight by passing BookingRequestDto")
    @PostMapping
    public ResponseEntity<ResponseDto> bookFlight(@Valid @RequestBody BookingRequestDto bookingRequestDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(bookingService.bookFlight(bookingRequestDto));
    }

    /**
     *
     * @param pnr unique pnr code details of the booking
     * @return response entity with booking details
     */
    @Operation(summary = "Get booking details", description = "Get booking details by passing PNR details")
    @GetMapping
    public ResponseEntity<BookingRequestDto> getBookingByPnr(@RequestParam String pnr) {
        return ResponseEntity.ok(bookingService.getBookingByPnr(pnr));
    }

    /**
     *
     * @param pnr unique pnr code details of the booking
     * @return response entity with success message
     */
    @Operation(summary = "Cancel a booking", description = "Cancel a booking by passing PNR details")
    @DeleteMapping
    public ResponseEntity<ResponseDto> cancelBooking(@RequestParam String pnr) {
        bookingService.cancelBooking(pnr);
        ResponseDto responseDto = new ResponseDto(HttpStatus.OK, "Booking cancelled successfully", LocalDateTime.now(), pnr);
        return ResponseEntity.ok(responseDto);
    }
}
