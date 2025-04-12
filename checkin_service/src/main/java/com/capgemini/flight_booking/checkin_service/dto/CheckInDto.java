package com.capgemini.flight_booking.checkin_service.dto;

import com.capgemini.flight_booking.checkin_service.enums.CheckInStatus;

/**
 *
 * @param checkInId id of the check-in
 * @param checkInStatus check-in status enum
 * @param seatNumber seat number
 * @param pnr unique booking pnr code
 */
public record CheckInDto(
        long checkInId,
        CheckInStatus checkInStatus,
        String seatNumber,
        String pnr
) {}
