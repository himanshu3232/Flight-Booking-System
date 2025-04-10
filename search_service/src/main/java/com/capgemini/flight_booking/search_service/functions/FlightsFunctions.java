package com.capgemini.flight_booking.search_service.functions;

import com.capgemini.flight_booking.search_service.dto.FlightDto;
import com.capgemini.flight_booking.search_service.service.IFlightSearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.function.Consumer;

@Configuration
@Slf4j
public class FlightsFunctions {

    @Bean
    public Consumer<FlightDto> updateSeats(IFlightSearchService flightSearchService){
        return flightSearchService::updateSeats;
    }
}
