package com.capgemini.flight_booking.user_service.service.impl;

import com.capgemini.flight_booking.user_service.dto.UserProfileDto;
import static com.capgemini.flight_booking.user_service.dto.mapper.Mapper.*;
import static com.capgemini.flight_booking.user_service.constants.UserConstants.*;
import com.capgemini.flight_booking.user_service.entity.UserProfileEntity;
import com.capgemini.flight_booking.user_service.exception.BadRequestException;
import com.capgemini.flight_booking.user_service.exception.UserAlreadyExistsException;
import com.capgemini.flight_booking.user_service.exception.UserNotFoundException;
import com.capgemini.flight_booking.user_service.repository.UserProfileRepository;
import com.capgemini.flight_booking.user_service.service.IUserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * Service layer implementation
 * Manages CRUD operations of users-service microservice
 */
@Slf4j
@Service
@RequiredArgsConstructor
@Profile({"dev", "prod"})
public class UserServiceImpl implements IUserService {

    private final UserProfileRepository userProfileRepo;

    /**
     * Creates a new user
     * @param userProfileDto represents user details
     * @throws UserAlreadyExistsException if a user already exists
     */
    @Override
    public void createUser(UserProfileDto userProfileDto) throws UserAlreadyExistsException {
        if(checkIfUserExists(userProfileDto)) throw new UserAlreadyExistsException(USER_ALREADY_EXISTS);
        log.trace("Map to dto mapper invoked");
        UserProfileEntity userProfileEntity = mapToUserProfileEntity(userProfileDto);
        log.debug("Saving user to repository {}", userProfileEntity);
        userProfileRepo.save(userProfileEntity);
    }


    /**
     * Fetches user details using the unique user-name
     * @param userName represents a unique user
     * @return UserProfileDto if user if found
     * @throws UserNotFoundException if user is not found
     */
    @Override
    public UserProfileDto getUser(String userName) throws UserNotFoundException {
        Optional<UserProfileEntity> user = userProfileRepo.findByUserName(userName);
        log.debug("Found user {}", user);
        return mapToUserProfileDto(user
                .orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND)));
    }



    /**
     * updates user details if the user exists
     * @param userName       a unique user identifier
     * @param userProfileDto represents updated user details
     * @throws UserNotFoundException if user is not found
     */
    @Override
    public void updateUser(String userName, UserProfileDto userProfileDto) throws UserNotFoundException {
        UserProfileEntity user = userProfileRepo.findByUserName(userName).orElseThrow(() -> new UserNotFoundException(USER_NOT_FOUND));
        user.setFirstName(userProfileDto.firstName());
        user.setLastName(userProfileDto.lastName());
        user.setAddress(userProfileDto.address());
        if(!user.getEmail().equals(userProfileDto.email()) || !user.getPhoneNumber().equals(userProfileDto.phoneNumber())) {
            log.error("Cannot update email or phone number");
            throw new BadRequestException("Email or phone number cannot be updated");
        }
        log.debug("Updating user from {} to {}", userProfileDto ,user);
        userProfileRepo.save(user);
    }



    /**
     * Deletes user from the database if user exists
     * @param userName a unique user identifier
     * @throws UserNotFoundException if user is not found
     */
    @Override
    public void deleteUser(String userName) throws UserNotFoundException {
        if(!checkIfUserExists(userName)){
            log.debug("User not found with username: {}", userName);
            throw new UserNotFoundException(USER_NOT_FOUND);
        }
        log.debug("Deleting user with username: {}", userName);
        userProfileRepo.deleteByUserName(userName);
    }


    /**
     * Handles the repetitive task of checking if user already exists
     * @param userProfileDto represents user details from controller
     * @return if user exists
     * @throws BadRequestException if user details are null
     */
    private boolean checkIfUserExists(UserProfileDto userProfileDto) throws BadRequestException {
        if(userProfileDto == null){
            log.trace("User details are null");
            throw new BadRequestException(NULL_USER_DETAILS);
        }
        Optional<?> user = userProfileRepo.findByUserName(userProfileDto.userName());
        return user.isPresent();
    }


    /**
     * Handles the repetitive task of checking if user already exists
     * @param userName represents unique user identifier
     * @return if user exists
     * @throws BadRequestException if user details are null
     */
    private boolean checkIfUserExists(String userName) throws BadRequestException {
        if(userName == null) throw new BadRequestException(NULL_USER_DETAILS);
        Optional<?> user = userProfileRepo.findByUserName(userName);
        return user.isPresent();
    }
}
