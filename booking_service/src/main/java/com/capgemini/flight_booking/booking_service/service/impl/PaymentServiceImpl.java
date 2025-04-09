package com.capgemini.flight_booking.booking_service.service.impl;

import com.capgemini.flight_booking.booking_service.client.FlightAvailabilityClient;
import com.capgemini.flight_booking.booking_service.dto.BookingRequestDto;
import com.capgemini.flight_booking.booking_service.dto.FlightDto;
import com.capgemini.flight_booking.booking_service.entity.PaymentEntity;
import com.capgemini.flight_booking.booking_service.enums.PaymentStatus;
import com.capgemini.flight_booking.booking_service.exception.InvalidFlightIdException;
import com.capgemini.flight_booking.booking_service.exception.InvalidPaymentAmountException;
import com.capgemini.flight_booking.booking_service.exception.InvalidPaymentIdException;
import com.capgemini.flight_booking.booking_service.repository.PaymentRepository;
import com.capgemini.flight_booking.booking_service.service.IPaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements IPaymentService {

    private final PaymentRepository paymentRepository;
    private final FlightAvailabilityClient flightAvailabilityClient;


    /**
     * @param bookingRequestDto the booking request made by client
     */
    @Override
    public long processPayment(BookingRequestDto bookingRequestDto, String bookingId) {
        FlightDto flightDto = flightAvailabilityClient
                .getFlightAvailabilityById(bookingRequestDto.flightId());

        if(flightDto == null){
            throw new InvalidFlightIdException("Invalid flight ID: " + bookingRequestDto.flightId());
        }

        double price = flightDto.price();

        if(price != bookingRequestDto.paidAmount()){
            throw new InvalidPaymentAmountException("Invalid payment amount");
        }

        PaymentEntity paymentEntity = new PaymentEntity(bookingId, bookingRequestDto.paidAmount());
        paymentEntity.setPaymentStatus(PaymentStatus.SUCCESS);
        PaymentEntity savedPaymentEntity = paymentRepository.save(paymentEntity);
        return savedPaymentEntity.getPaymentId();
    }

    /**
     * @param paymentId unique paymentId for each unique payment
     * @return get payment status
     */
    @Override
    public PaymentStatus getPaymentStatus(long paymentId) {
        PaymentEntity paymentEntity = paymentRepository.findById(paymentId)
                .orElseThrow(() -> new InvalidPaymentIdException("Invalid payment ID: " + paymentId));

        return paymentEntity.getPaymentStatus();
    }
}
