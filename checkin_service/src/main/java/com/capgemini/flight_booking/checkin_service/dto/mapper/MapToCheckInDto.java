package com.capgemini.flight_booking.checkin_service.dto.mapper;

import com.capgemini.flight_booking.checkin_service.dto.CheckInDto;
import com.capgemini.flight_booking.checkin_service.entity.CheckInEntity;

/**
 * Maps the CheckInEntity to the CheckInDto
 */
public class MapToCheckInDto {

    private MapToCheckInDto() {}

    /**
     *
     * @param checkInEntity check-in entity
     * @return check-in dto
     */
    public static CheckInDto mapToCheckInDto(CheckInEntity checkInEntity) {
        return new CheckInDto(
                checkInEntity.getCheckInId(),
                checkInEntity.getCheckInStatus(),
                checkInEntity.getSeatNumber(),
                checkInEntity.getPnr()
        );
    }
}
