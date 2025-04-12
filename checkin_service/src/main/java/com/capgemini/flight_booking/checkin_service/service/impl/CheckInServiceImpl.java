package com.capgemini.flight_booking.checkin_service.service.impl;

import com.capgemini.flight_booking.checkin_service.client.VerifyPnr;
import com.capgemini.flight_booking.checkin_service.dto.CheckInDto;
import com.capgemini.flight_booking.checkin_service.entity.CheckInEntity;
import com.capgemini.flight_booking.checkin_service.enums.CheckInStatus;
import com.capgemini.flight_booking.checkin_service.exception.AlreadyCheckedInException;
import com.capgemini.flight_booking.checkin_service.exception.InvalidPnrException;
import com.capgemini.flight_booking.checkin_service.repository.CheckInRepository;
import com.capgemini.flight_booking.checkin_service.service.ICheckInService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.stereotype.Service;
import com.capgemini.flight_booking.checkin_service.dto.mapper.MapToCheckInDto;

@Service
@RequiredArgsConstructor
public class CheckInServiceImpl implements ICheckInService {

    private final StreamBridge streamBridge;
    private final CheckInRepository checkInRepository;
    private final VerifyPnr verifyPnr;

    /**
     * Generates a random seat number
     * @param pnr unique booking pnr code
     * @return a random seat number
     */
    @Override
    public String generateSeatNumber(String pnr) {
        int num = (int) (Math.random() * 100);
        return String.format("%d%c", num, (char)((num%26) + 'A') );
    }

    /**
     * Marks a booking as checked in
     * @param pnr unique booking pnr code
     * @return CheckInDto response containing seat number, pnr and check-in id
     */
    @Override
    public CheckInDto checkIn(String pnr) {
        if(checkInRepository.findByPnr(pnr).isPresent()){
            throw new AlreadyCheckedInException("Already checked in with pnr: " + pnr);
        }

        //Check valid pnr
        if(!isValidPnr(pnr)){
            throw new InvalidPnrException("Invalid pnr: " + pnr);
        }


        String seatNumber = generateSeatNumber(pnr);
        CheckInEntity checkInEntity = new CheckInEntity(pnr, CheckInStatus.CHECKED_IN,seatNumber);
        checkInRepository.save(checkInEntity);
        sendCheckInNotification(pnr);

        return MapToCheckInDto.mapToCheckInDto(checkInEntity);
    }



    /**
     * Sends check-in notification to Booking service
     * @param pnr unique code
     */
    private void sendCheckInNotification(String pnr) {
        streamBridge.send("checkIn-out-0", pnr);
    }


    /**
     * Checks if pnr is valid
     * @param pnr unique code
     */
    private boolean isValidPnr(String pnr) {
        return verifyPnr.verifyPnr(pnr);
    }
}
