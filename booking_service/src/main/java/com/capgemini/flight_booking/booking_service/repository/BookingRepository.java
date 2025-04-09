package com.capgemini.flight_booking.booking_service.repository;

import com.capgemini.flight_booking.booking_service.entity.BookingEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface BookingRepository extends JpaRepository<BookingEntity, String> {
    Optional<BookingEntity> findByPnr(String pnr);

    @Transactional
    @Modifying
    void deleteByPnr(String pnr);
}
