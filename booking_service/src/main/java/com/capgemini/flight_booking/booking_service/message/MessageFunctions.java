package com.capgemini.flight_booking.booking_service.message;

import com.capgemini.flight_booking.booking_service.dto.FlightDto;
import com.capgemini.flight_booking.booking_service.dto.ResponseDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Slf4j
@Configuration
public class MessageFunctions {

    @Bean
    public Function<FlightDto, FlightDto> updateSeats(){
        return flightDto -> {
            log.info("Sending updated seats for flight {}", flightDto);
            return flightDto;
        };
    }

    @Bean
    public Function<FlightDto, String> updateSeat(){
        return flightDto -> {
            log.info("Sending okokok {}", flightDto);
            return "Good okokok";
        };
    }
}
