package com.capgemini.flight_booking.checkin_service.dto.mapper;

import com.capgemini.flight_booking.checkin_service.dto.CheckInDto;
import com.capgemini.flight_booking.checkin_service.entity.CheckInEntity;

public class MapToCheckInDto {

    private MapToCheckInDto() {}

    public static CheckInDto mapToCheckInDto(CheckInEntity checkInEntity) {
        return new CheckInDto(
                checkInEntity.getCheckInId(),
                checkInEntity.isCheckInStatus(),
                checkInEntity.getSeatNumber(),
                checkInEntity.getPnr()
        );
    }
}
