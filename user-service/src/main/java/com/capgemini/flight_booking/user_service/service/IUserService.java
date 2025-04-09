package com.capgemini.flight_booking.user_service.service;

import com.capgemini.flight_booking.user_service.dto.UserProfileDto;
import com.capgemini.flight_booking.user_service.exception.UserAlreadyExistsException;
import com.capgemini.flight_booking.user_service.exception.UserNotFoundException;

public interface IUserService {

    /**
     * Creates a new user
     * @param userProfileDto represents user details
     * @throws UserAlreadyExistsException if a user already exists
     */
    void createUser(UserProfileDto userProfileDto) throws UserAlreadyExistsException;


    /**
     * Fetches user details using the unique user-name
     * @param userName represents a unique user
     * @return UserProfileDto if user if found
     * @throws UserNotFoundException if user is not found
     */
    UserProfileDto getUser(String userName) throws UserNotFoundException;


    /**
     * updates user details if the user exists
     * @param userName a unique user identifier
     * @param userProfileDto represents updated user details
     * @throws UserNotFoundException if user is not found
     */
    void updateUser(String userName, UserProfileDto userProfileDto) throws UserNotFoundException;



    /**
     * Deletes user from the database if user exists
     * @param userName a unique user identifier
     * @throws UserNotFoundException if user is not found
     */
    void deleteUser(String userName) throws UserNotFoundException;
}
