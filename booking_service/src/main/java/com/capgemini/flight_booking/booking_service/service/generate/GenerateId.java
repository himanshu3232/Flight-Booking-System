package com.capgemini.flight_booking.booking_service.service.generate;

import java.time.LocalTime;

/**
 * Generates unique id for each entity instances
 */
public class GenerateId {
    private GenerateId(){}

    /**
     *
     * @param prefix prefix of id
     * @return unique id
     */
    public static String generateId(String prefix) {
        return String.format("%s@%.7f%d", prefix, Math.random(), LocalTime.now().getNano())
                .substring(0,25);
    }
}
