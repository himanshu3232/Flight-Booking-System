package com.capgemini.flight_booking.booking_service.service;

import com.capgemini.flight_booking.booking_service.client.FlightAvailabilityClient;
import com.capgemini.flight_booking.booking_service.repository.BookingRepository;
import com.capgemini.flight_booking.booking_service.service.impl.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class BookingServiceTest {
    @InjectMocks
    private BookingServiceImpl bookingService;

    @Mock
    private BookingRepository bookingRepository;

    @Mock
    private IPaymentService paymentService;

    @Mock
    private FlightAvailabilityClient flightAvailabilityClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void bookFlightTest(){

    }

    @Test
    void getBookingsByPnrTest(){

    }

    @Test
    void cancelBookingTest(){

    }

    @Test
    void checkInTest() {

    }
}
