package com.capgemini.flight_booking.booking_service.dto.mapper;


import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.entity.BookingEntity;

/**
 * Maps the BookingRequestDto to the BookingEntity and vice-versa
 */
public class Mapper {

    private Mapper() {
    }

    /**
     *
     * @param bookingEntity booking entity for each unique booking
     * @return booking request dto
     */
    public static BookingRequestDto mapToBookingRequestDto(BookingEntity bookingEntity) {
        return new BookingRequestDto(
                bookingEntity.getFlightId(),
                bookingEntity.getPassengerName(),
                bookingEntity.getPassengerEmail(),
                bookingEntity.getPassengerAge(),
                bookingEntity.getPaidAmount()
        );
    }


    /**
     *
     * @param bookingRequestDto booking request made by client
     * @return booking entity
     */
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
