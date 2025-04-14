package com.capgemini.flight_booking.search_service.repository;


import com.capgemini.flight_booking.search_service.entity.FlightEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface FlightRepository extends JpaRepository<FlightEntity, Long> {
    @Query("SELECT f FROM FlightEntity f WHERE f.departureAirport = ?1 AND f.arrivalAirport = ?2")
    List<FlightEntity> getFlightsByFromAndTo(String from, String to);

}
