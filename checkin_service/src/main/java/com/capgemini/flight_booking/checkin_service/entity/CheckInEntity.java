package com.capgemini.flight_booking.checkin_service.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "check_in")
@Getter @Setter @ToString
@AllArgsConstructor
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class CheckInEntity {

    public CheckInEntity(String pnr, boolean checkInStatus, String seatNumber) {
        this.pnr = pnr;
        this.checkInStatus = checkInStatus;
        this.seatNumber = seatNumber;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long checkInId;

    @Column(name = "flight_id", nullable = false, unique = true)
    private String pnr;

    @Column(name = "check_in_status", nullable = false)
    private boolean checkInStatus;

    @CreatedDate
    @Column(name = "check_in_time", nullable = false)
    private LocalDateTime checkInTime;

    @Column(name = "seat_number", nullable = false)
    private String seatNumber;
}
