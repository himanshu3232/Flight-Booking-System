package com.capgemini.flight_booking.booking_service.entity;

import com.capgemini.flight_booking.booking_service.enums.BookingStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "bookings")
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
public class BookingEntity {
    public BookingEntity(long flightId, String passengerName, String passengerEmail, int passengerAge, double paidAmount) {
        this.flightId = flightId;
        this.passengerName = passengerName;
        this.passengerEmail = passengerEmail;
        this.passengerAge = passengerAge;
        this.paidAmount = paidAmount;
    }
    @Id
    private String bookingId;
    private Long paymentId;
    private Long flightId;
    @Column(name = "pnr", nullable = false, unique = true)
    private String pnr;
    private String passengerName;
    private String passengerEmail;
    private int passengerAge;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private BookingStatus status;
    @CreatedDate
    private LocalDateTime bookingTime;
    private double paidAmount;
}


