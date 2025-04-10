package com.capgemini.flight_booking.checkin_service.service;

import com.capgemini.flight_booking.checkin_service.dto.CheckInDto;

public interface ICheckInService {
    String generateSeatNumber(String pnr);
    CheckInDto checkIn(String pnr);
}
