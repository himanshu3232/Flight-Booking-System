package com.capgemini.flight_booking.checkin_service.dto;

public record CheckInDto(
        long checkInId,
        boolean checkInStatus,
        String seatNumber,
        String pnr
) {}
