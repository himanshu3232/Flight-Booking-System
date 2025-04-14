package com.capgemini.flight_booking.booking_service.service;

import com.capgemini.flight_booking.booking_service.client.FlightAvailabilityClient;
import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.dto.FlightDto;
import com.capgemini.flight_booking.booking_service.dto.mapper.Mapper;
import com.capgemini.flight_booking.booking_service.entity.BookingEntity;
import com.capgemini.flight_booking.booking_service.entity.PaymentEntity;
import com.capgemini.flight_booking.booking_service.repository.PaymentRepository;
import com.capgemini.flight_booking.booking_service.service.impl.PaymentServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PaymentServiceTest {

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private FlightAvailabilityClient flightAvailabilityClient;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void processPaymentTest() {
        long flightId = 1L;
        long paymentId = 3L;
        BookingRequestDto requestDto = new BookingRequestDto(flightId, "John Doe", "Rn8b8@example.com", 25, 100.0);
        PaymentEntity paymentEntity = new PaymentEntity("bookingId", 100.0);
        paymentEntity.setPaymentId(paymentId);

        when(flightAvailabilityClient.getFlightAvailabilityById(flightId))
                .thenReturn(Mono.just(new FlightDto(flightId, "airline", "from", "to", null, null, null, 100.0, 0)));

        when(paymentService.processPayment(requestDto, anyString()))
                .thenReturn(paymentId);

        when(paymentRepository.save(paymentEntity))
                .thenReturn(paymentEntity);

        assertEquals(paymentId, paymentService.processPayment(requestDto, "bookingId"));
    }
}
