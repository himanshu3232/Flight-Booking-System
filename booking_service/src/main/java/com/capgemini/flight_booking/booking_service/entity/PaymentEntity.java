package com.capgemini.flight_booking.booking_service.entity;

import com.capgemini.flight_booking.booking_service.enums.PaymentStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "payments")
@EntityListeners(AuditingEntityListener.class)
public class PaymentEntity {

    public PaymentEntity (String bookingId, double amount) {
        this.bookingId = bookingId;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long paymentId;
    @Column(name = "bookingId", nullable = false, unique = true)
    private String bookingId;
    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;
    @CreatedDate
    private LocalDateTime paymentTime;
    private double amount;
}

