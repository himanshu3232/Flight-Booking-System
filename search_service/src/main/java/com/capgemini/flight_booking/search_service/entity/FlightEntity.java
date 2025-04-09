package com.capgemini.flight_booking.search_service.entity;


import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@Table(name = "flights")
public class FlightEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long flightId;

    @Column(name = "airline", nullable = false)
    private String airline;

    @Column(name = "departure_airport", nullable = false)
    private String departureAirport;

    @Column(name = "arrival_airport", nullable = false)
    private String arrivalAirport;

    @Column(name = "departure_date", nullable = false)
    private LocalDate departureDate;

    @Column(name = "arrival_date", nullable = false)
    private LocalDate arrivalDate;

    @Column(name = "duration", nullable = false)
    private LocalTime duration;

    @Column(name = "price", nullable = false)
    private double price;

    @Column(name = "seats_available", nullable = false)
    private int seatsAvailable;

}

