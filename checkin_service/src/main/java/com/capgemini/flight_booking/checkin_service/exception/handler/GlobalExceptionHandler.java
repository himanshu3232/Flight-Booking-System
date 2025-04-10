package com.capgemini.flight_booking.checkin_service.exception.handler;

import com.capgemini.flight_booking.checkin_service.dto.ExceptionResponseDto;
import com.capgemini.flight_booking.checkin_service.exception.AlreadyCheckedInException;
import com.capgemini.flight_booking.checkin_service.exception.InvalidPnrException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponseDto> handleException (Exception e){
        return ResponseEntity
                .status(500)
                .body(new ExceptionResponseDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    @ExceptionHandler(AlreadyCheckedInException.class)
    public ResponseEntity<ExceptionResponseDto> handleAlreadyCheckedInException (AlreadyCheckedInException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponseDto(HttpStatus.BAD_REQUEST, e.getMessage()));
    }


    @ExceptionHandler(InvalidPnrException.class)
    public ResponseEntity<ExceptionResponseDto> handleInvalidPnrException (InvalidPnrException e){
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionResponseDto(HttpStatus.BAD_REQUEST, e.getMessage()));
    }
}
