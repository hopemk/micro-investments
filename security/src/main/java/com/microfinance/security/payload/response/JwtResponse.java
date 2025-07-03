package com.microfinance.security.payload.response;

import java.util.List;

/**
 * Response payload for JWT authentication.
 */
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private List<String> roles;

    /**
     * Constructor for JwtResponse.
     *
     * @param accessToken The JWT token
     * @param id The user ID
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param email The user's email
     * @param phoneNumber The user's phone number
     * @param roles The user's roles
     */
    public JwtResponse(String accessToken, String id, String firstName, String lastName, String email, String phoneNumber, List<String> roles) {
        this.token = accessToken;
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.roles = roles;
    }

    public String getAccessToken() {
        return token;
    }

    public void setAccessToken(String accessToken) {
        this.token = accessToken;
    }

    public String getTokenType() {
        return type;
    }

    public void setTokenType(String tokenType) {
        this.type = tokenType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public List<String> getRoles() {
        return roles;
    }
}