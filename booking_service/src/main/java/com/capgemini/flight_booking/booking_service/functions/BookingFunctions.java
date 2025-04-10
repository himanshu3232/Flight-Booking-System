package com.capgemini.flight_booking.booking_service.functions;

import com.capgemini.flight_booking.booking_service.dto.FlightDto;
import com.capgemini.flight_booking.booking_service.service.IBookingService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;
import java.util.function.Function;

@Configuration
public class BookingFunctions {

    @Bean
    public Function<FlightDto, FlightDto> updateSeats(){
        return flightDto -> flightDto;
    }

    @Bean
    public Consumer<String> checkIn(IBookingService bookingService){
        return bookingService::checkIn;
    }
}
