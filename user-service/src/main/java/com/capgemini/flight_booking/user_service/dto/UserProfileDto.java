package com.capgemini.flight_booking.user_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

/**
 * Abstracted User details
 * @param userName
 * @param password
 * @param email
 * @param firstName
 * @param lastName
 * @param phoneNumber
 * @param address
 */
public record UserProfileDto (
        @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9@_]{3,19}$", message = "Username should be alphanumeric, ranging from 4-20 characters and start with a letter")
        String userName,
        @NotEmpty(message = "Password cannot be empty")
        String password,
        @Email(message = "Invalid email address")
        String email,
        @Pattern(regexp = "[A-Za-z]{3,15}", message = "First name should only contain alphabets, ranging from 3-15 characters")
        String firstName,
        @Pattern(regexp = "[A-Za-z]{3,15}", message = "Last name should only contain alphabets, ranging from 3-15 characters")
        String lastName,
        @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number")
        String phoneNumber,
        @Pattern(regexp = "[A-Za-z0-9\\s]{5,100}", message = "Address should only contain alphabets, numbers and spaces, ranging from 5-100 characters")
        String address
){}
