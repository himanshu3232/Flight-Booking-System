package com.capgemini.flight_booking.booking_service.service.generate;

import java.time.LocalTime;

public class GeneratePnr {
    private GeneratePnr(){}

    public static String generatePnr(String airline){
        String time = LocalTime.now().getNano() + "";
        String pnr = String.format("%s@%.5f@%s", airline, Math.random(), time);
        return pnr.substring(0,20);
    }
}
