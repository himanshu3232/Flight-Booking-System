package com.capgemini.flight_booking.booking_service.dto.mapper;


import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.entity.BookingEntity;

public class Mapper {

    private Mapper() {
    }

    public static BookingRequestDto mapToBookingRequestDto(BookingEntity bookingEntity) {
        return new BookingRequestDto(
                bookingEntity.getFlightId(),
                bookingEntity.getPassengerName(),
                bookingEntity.getPassengerEmail(),
                bookingEntity.getPassengerAge(),
                bookingEntity.getPaidAmount()
        );
    }


    public static BookingEntity mapToBookingEntity(BookingRequestDto bookingRequestDto) {
        return new BookingEntity(
                bookingRequestDto.flightId(),
                bookingRequestDto.passengerName(),
                bookingRequestDto.passengerEmail(),
                bookingRequestDto.passengerAge(),
                bookingRequestDto.paidAmount()
        );

    }
}
