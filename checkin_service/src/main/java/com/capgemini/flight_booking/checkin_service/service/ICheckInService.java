package com.capgemini.flight_booking.checkin_service.service;

import com.capgemini.flight_booking.checkin_service.dto.CheckInDto;


public interface ICheckInService {

    /**
     * Generates a random seat number
     * @param pnr unique booking pnr code
     * @return a random seat number
     */
    String generateSeatNumber(String pnr);

    /**
     * Marks a booking as checked in
     * @param pnr unique booking pnr code
     * @return CheckInDto response containing seat number, pnr and check-in id
     */
    CheckInDto checkIn(String pnr);
}
