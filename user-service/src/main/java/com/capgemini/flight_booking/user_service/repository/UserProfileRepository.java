package com.capgemini.flight_booking.user_service.repository;

import com.capgemini.flight_booking.user_service.entity.UserProfileEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;

import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Long> {


    /**
     * Finds user details using the unique user-name
     * @param userName represents a unique user
     * @return UserProfileEntity if found
     */
    Optional<UserProfileEntity> findByUserName(String userName);


    @Transactional
    @Modifying
    void deleteByUserName(String userName);
}
