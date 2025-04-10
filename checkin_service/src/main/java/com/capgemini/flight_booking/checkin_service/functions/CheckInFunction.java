package com.capgemini.flight_booking.checkin_service.functions;

import com.capgemini.flight_booking.checkin_service.dto.CheckInDto;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Function;

@Configuration
public class CheckInFunction {

    @Bean
    public Function<CheckInDto, CheckInDto> checkIn(){
        return checkInDto -> checkInDto;

    }
}
