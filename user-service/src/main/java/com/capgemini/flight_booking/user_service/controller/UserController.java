package com.capgemini.flight_booking.user_service.controller;

import static com.capgemini.flight_booking.user_service.constants.UserConstants.*;
import com.capgemini.flight_booking.user_service.dto.ResponseDto;
import com.capgemini.flight_booking.user_service.dto.UserProfileDto;
import com.capgemini.flight_booking.user_service.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * APIs
 * POST /users : Register a new user
 * POST /users/login : Authenticate a user
 * GET /users/{userId} : Get user details
 * PUT /users/{userId} : Update user details
 * DELETE /users/{userId} : Delete a user account
 */
@Validated
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Slf4j
@Profile({"dev", "prod"})
@Tag(name = "User API", description = "Do CURD operations on users")
public class UserController {

    //user service bean
    private final IUserService userService;


    /**
     * Creates a new user and adds to the database
     * @param userProfileDto contains user details from the frontend
     * @return ResponseDto can be either success or failure based on the request
     */
    @Operation(summary = "Register a new user", description = "Creates a new user and adds to the database")
    @PostMapping
    public ResponseEntity<ResponseDto> createUser(@Valid @RequestBody UserProfileDto userProfileDto) {
        log.trace("Inside createUser");
        log.debug("Invoking userService.createUser with {}", userProfileDto);
        userService.createUser(userProfileDto);
        log.debug("User created successfully");
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ResponseDto(STATUS_201, MESSAGE_201));
    }


    /**
     * Returns the details of the user
     * @param userName represents a unique user
     * @return UserProfileDto, if the user exists with that userId
     */
    @Operation(summary = "Get user details", description = "Returns the details of the user")
    @GetMapping("/{userName}")
    public ResponseEntity<UserProfileDto> getUser(@PathVariable String userName) {
        log.trace("Inside getUser");
        log.debug("Invoking userService.getUser with {}", userName);
        UserProfileDto userProfileDto = userService.getUser(userName);
        log.debug("User details fetched successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(userProfileDto);
    }


    /**
     * Updates the details of the user
     * @param userName represents a unique user
     * @param userProfileDto contains user details from the frontend
     * @return ResponseDto based on the result of the request
     */
    @Operation(summary = "Update user details", description = "Updates the details of the user")
    @PutMapping("/{userName}")
    public ResponseEntity<ResponseDto> updateUser(@PathVariable String userName, @RequestBody UserProfileDto userProfileDto) {
        log.trace("Inside updateUser");
        log.debug("Invoking userService.updateUser with {}, {}", userName, userProfileDto);
        userService.updateUser(userName, userProfileDto);
        log.debug("User details updated successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(STATUS_200, MESSAGE_200));
    }


    /**
     * Deletes the user details from the database
     * @param userName represents a unique user
     * @return ResponseDto based on the result of the request
     */
    @Operation(summary = "Delete a user account", description = "Deletes the user details from the database")
    @DeleteMapping("/{userName}")
    public ResponseEntity<ResponseDto> deleteUser(@PathVariable String userName) {
        log.trace("Inside deleteUser");
        log.debug("Invoking userService.deleteUser with {}", userName);
        userService.deleteUser(userName);
        log.debug("User details deleted successfully");
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(new ResponseDto(STATUS_200, MESSAGE_200));
    }
}
