package com.capgemini.flight_booking.user_service.dto.mapper;

import com.capgemini.flight_booking.user_service.dto.UserProfileDto;
import com.capgemini.flight_booking.user_service.entity.UserProfileEntity;

public class Mapper {
    private Mapper(){}

    public static UserProfileDto mapToUserProfileDto(UserProfileEntity userProfileEntity) {
        return new UserProfileDto(
            userProfileEntity.getUserName(),
            userProfileEntity.getPasswordHash(),
            userProfileEntity.getEmail(),
            userProfileEntity.getFirstName(),
            userProfileEntity.getLastName(),
            userProfileEntity.getPhoneNumber(),
            userProfileEntity.getAddress()
        );
    }


    public static UserProfileEntity mapToUserProfileEntity(UserProfileDto userProfileDto) {
        return new UserProfileEntity(
            userProfileDto.userName(),
            userProfileDto.password(),
            userProfileDto.email(),
            userProfileDto.firstName(),
            userProfileDto.lastName(),
            userProfileDto.phoneNumber(),
            userProfileDto.address()
        );
    }
}
