package com.capgemini.flight_booking.booking_service.repository;

import com.capgemini.flight_booking.booking_service.entity.PaymentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<PaymentEntity, Long> {
    Optional<PaymentEntity> findByBookingId(String bookingId);
}
