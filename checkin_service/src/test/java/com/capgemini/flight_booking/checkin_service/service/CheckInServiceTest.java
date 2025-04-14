package com.capgemini.flight_booking.checkin_service.service;

import com.capgemini.flight_booking.checkin_service.dto.CheckInDto;
import com.capgemini.flight_booking.checkin_service.repository.CheckInRepository;
import com.capgemini.flight_booking.checkin_service.service.impl.CheckInServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class CheckInServiceTest {

    @InjectMocks
    private CheckInServiceImpl checkInService;

    @Mock
    private CheckInRepository checkInRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void CheckIn(){
        String pnr = "abc";
        when(checkInRepository.findByPnr(pnr))
                .thenReturn(Optional.empty());

        CheckInDto checkInDto = checkInService.checkIn(pnr);
        assertEquals(pnr, checkInDto.pnr());
        assertEquals("seat", checkInDto.seatNumber());
    }
}
