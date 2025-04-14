package com.capgemini.flight_booking.search_service.service;

import com.capgemini.flight_booking.search_service.dto.FlightDto;
import com.capgemini.flight_booking.search_service.dto.mapper.Mapper;
import com.capgemini.flight_booking.search_service.entity.FlightEntity;
import com.capgemini.flight_booking.search_service.repository.FlightRepository;
import com.capgemini.flight_booking.search_service.service.impl.FlightSearchServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.List;
import java.util.Optional;

class FlightSearchServiceTest {

    @InjectMocks
    private FlightSearchServiceImpl flightSearchService;

    @Mock
    private FlightRepository flightRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getFlightsTest(){
        FlightEntity entity = new FlightEntity(1L, "airline", "from", "to", null, null, null, 0.0, 0);

        when(flightRepository
                .getFlightsByFromAndTo(ArgumentMatchers.anyString(), ArgumentMatchers.anyString()))
                .thenReturn(List.of(entity));

        List<FlightDto> result = flightSearchService.getFlights("from", "to");

        assertNotNull(result);
    }

    @Test
    void getAllFlightsTest(){
        List<FlightEntity> flights = List.of(new FlightEntity(1L, "airline", "from", "to", null, null, null, 0.0, 0));
        when(flightRepository.findAll()).thenReturn(flights);

        List<FlightDto> result = flightSearchService.getAllFlights();
        assertNotNull(result);
    }

    @Test
    void getFlightByIdTest(){
        FlightEntity entity = new FlightEntity(1L, "airline", "from", "to", null, null, null, 0.0, 0);
        when(flightRepository.findById(ArgumentMatchers.anyLong())).thenReturn(java.util.Optional.of(entity));

        FlightDto result = flightSearchService.getFlightById(1L);
        assertNotNull(result);
    }

    @Test
    void updateSeatsTest() {
        FlightEntity entity = new FlightEntity(1L, "airline", "from", "to", null, null, null, 0.0, 3);
        int prevSeats = entity.getSeatsAvailable();
        when(flightRepository.findById(1L)).thenReturn(Optional.of(entity));
        flightSearchService.updateSeats(Mapper.mapToFlightDto(entity));

        assertEquals(prevSeats-1, flightRepository.findById(1L).get().getSeatsAvailable());
    }
}
