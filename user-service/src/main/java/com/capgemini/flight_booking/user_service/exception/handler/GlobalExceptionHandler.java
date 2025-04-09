package com.capgemini.flight_booking.user_service.exception.handler;

import com.capgemini.flight_booking.user_service.dto.ErrorResponseDto;
import com.capgemini.flight_booking.user_service.exception.BadRequestException;
import com.capgemini.flight_booking.user_service.exception.UserAlreadyExistsException;
import com.capgemini.flight_booking.user_service.exception.UserNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     *
     * @param ex Validation exception
     * @param headers contains http headers
     * @param status HTTP status code
     * @param request contains details about the request
     * @return error response
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        log.trace("Inside handleMethodArgumentNotValid");
        Map<String, String> map = new HashMap<>();
        ex
                .getBindingResult()
                .getAllErrors()
                .forEach(error ->
                        map.put(((FieldError)error).getField(), error.getDefaultMessage())
                );
        log.debug("Handled MethodArgumentNotValidException, {}", map);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(map);
    }

    /**
     *
     * @param e exception thrown
     * @param webRequest contains information about the request
     * @return error response dto
     */
    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponseDto> handleUserAlreadyExistsException
            (UserAlreadyExistsException e, WebRequest webRequest){
        log.trace("Inside handleUserAlreadyExistsException");
        var response = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                LocalDateTime.now()
        );
        log.debug("Handled UserAlreadyExistsException, {}", response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    /**
     *
     * @param e exception thrown
     * @param webRequest contains information about the request
     * @return error response dto
     */
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ErrorResponseDto> handleUserNotFoundException
            (UserNotFoundException e, WebRequest webRequest){
        log.trace("Inside handleUserNotFoundException");
        var response = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                LocalDateTime.now()
        );
        log.debug("Handled UserNotFoundException, {}", response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto> handleBadRequestException
            (BadRequestException e, WebRequest webRequest){
        log.trace("Inside handleBadRequestException");
        var response = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.BAD_REQUEST,
                e.getMessage(),
                LocalDateTime.now()
        );
        log.debug("Handled BadRequestException, {}", response);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
    }

    /**
     *
     * @param e exception thrown
     * @param webRequest contains information about the request
     * @return error response dto
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponseDto> handleBaseException
    (Exception e, WebRequest webRequest){
        log.trace("Inside handleBaseException");
        var response = new ErrorResponseDto(
                webRequest.getDescription(false),
                HttpStatus.INTERNAL_SERVER_ERROR,
                e.getMessage(),
                LocalDateTime.now()
        );
        log.debug("Handled Exception, {}", response);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
    }

}
