package com.capgemini.flight_booking.booking_service.service.generate;

import java.time.LocalTime;

public class GenerateId {
    private GenerateId(){}

    public static String generateId(String prefix) {
        return String.format("%s@%.7f%d", prefix, Math.random(), LocalTime.now().getNano())
                .substring(0,25);
    }
}
