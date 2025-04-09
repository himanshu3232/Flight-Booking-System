package com.capgemini.flight_booking.user_service.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

/**
 *  Represents User in the system
 *  This entity stores user details such as userId, username, email, password-hash and other metadata
 */

@Entity
@Getter @Setter @ToString
@NoArgsConstructor
@Table(name = "user_profiles")
public class UserProfileEntity extends BaseEntity{


    /**
     * All args Constructor
     * @param userName represents a unique user
     * @param passwordHash hashed password using BCrypt
     * @param email email of the user
     * @param firstName first name of the user
     * @param lastName last name of the user
     * @param phoneNumber phone number of the user
     * @param address address of the user
     */
    public UserProfileEntity(String userName, String passwordHash, String email, String firstName, String lastName, String phoneNumber, String address) {
        this.userName = userName;
        this.passwordHash = passwordHash;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.address = address;
    }


    /**
     * Id is auto-generated and represents unique user profile
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    /**
     * The username must range from 4-20 characters and must start with a letter.
     * It can also contain special characters like @ and _
     * The username must also be unique.
     */
    @Column(name = "user_name", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z][a-zA-Z0-9@_]{3,19}$", message = "Username should be alphanumeric, ranging from 4-20 characters and start with a letter")
    private String userName;



    /**
     * Hashed password using BCrypt
     * Contains a non-empty string
     */
    @Column(name = "password", nullable = false)
    @NotEmpty(message = "Password cannot be empty")
    private String passwordHash;



    /**
     * The email must be unique and valid.
     * The email represents a unique identifier for the user in case of account recovery
     */
    @Column(name = "email", nullable = false, unique = true)
    @Email(message = "Invalid email address")
    @NotNull
    private String email;


    /**
     * First name must be 3-15 characters and is case-insensitive
     */
    @Pattern(regexp = "[A-Za-z]{3,15}", message = "First name should only contain alphabets, ranging from 3-15 characters")
    private String firstName;



    /**
     * Last name must be 3-15 characters and is case-insensitive
     */
    @Pattern(regexp = "[A-Za-z]{3,15}", message = "Last name should only contain alphabets, ranging from 3-15 characters")
    private String lastName;



    /**
     * Accepts phone number with or without country code
     * Must be 10 digits
     * Must be unique and non-empty
     */
    @Pattern(regexp = "^(\\+\\d{1,3}[- ]?)?\\d{10}$", message = "Invalid phone number")
    @Column(name = "phone_number", nullable = false, unique = true)
    private String phoneNumber;



    /**
     * Address must be 5-100 characters
     */
    @Pattern(regexp = "[A-Za-z0-9\\s]{5,100}", message = "Address should only contain alphabets, numbers and spaces, ranging from 5-100 characters")
    private String address;
}
