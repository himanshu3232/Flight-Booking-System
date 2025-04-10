package com.capgemini.flight_booking.checkin_service.repository;

import com.capgemini.flight_booking.checkin_service.entity.CheckInEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CheckInRepository extends JpaRepository<CheckInEntity, Long> {
    Optional<CheckInEntity> findByPnr(String pnr);
}
