package com.capgemini.flight_booking.user_service.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;


/**
 * Represents User in the system
 * This entity stores user details such as userId, username, email, password-hash and other metadata
 */
@EntityListeners(AuditingEntityListener.class)
@Getter @Setter @ToString
@MappedSuperclass
public class BaseEntity {

    /**
     * Contains details about when the user first registered
     */
    @Column(name = "created_at", nullable = false, updatable = false)
    @CreatedDate
    private LocalDateTime createdAt;


    /**
     * Contains details about when the user was last updated
     */
    @Column(name = "updated_at", insertable = false)
    @LastModifiedDate
    private LocalDateTime updatedAt;
}
