package com.microfinance.security.controller;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.microfinance.security.payload.request.LoginRequest;
import com.microfinance.security.payload.request.SignupRequest;
import com.microfinance.security.payload.response.JwtResponse;
import com.microfinance.security.payload.response.MessageResponse;
import com.microfinance.security.service.AuthService;

/**
 * Controller for authentication endpoints.
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthService authService;

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param loginRequest The login request
     * @return JWT response with token and user details
     */
    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);
    }

    /**
     * Registers a new user.
     *
     * @param signUpRequest The signup request
     * @return Message response indicating success or failure
     */
    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        MessageResponse response = authService.registerUser(signUpRequest);
        return ResponseEntity.ok(response);
    }
}