package com.microfinance.security.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.microfinance.security.jwt.JwtUtils;
import com.microfinance.security.model.ERole;
import com.microfinance.security.model.Role;
import com.microfinance.security.model.User;
import com.microfinance.security.payload.request.LoginRequest;
import com.microfinance.security.payload.request.SignupRequest;
import com.microfinance.security.payload.response.JwtResponse;
import com.microfinance.security.payload.response.MessageResponse;
import com.microfinance.security.repository.RoleRepository;
import com.microfinance.security.repository.UserRepository;

/**
 * Service for authentication operations.
 */
@Service
public class AuthService {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    /**
     * Authenticates a user and returns a JWT token.
     *
     * @param loginRequest The login request containing email and password
     * @return JWT response with token and user details
     */
    public JwtResponse authenticateUser(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);
        
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new JwtResponse(jwt, 
                         userDetails.getId(), 
                         userDetails.getFirstName(),
                         userDetails.getLastName(),
                         userDetails.getEmail(),
                         userDetails.getPhoneNumber(),
                         roles);
    }

    /**
     * Registers a new user.
     *
     * @param signUpRequest The signup request containing user details
     * @return Message response indicating success or failure
     */
    public MessageResponse registerUser(SignupRequest signUpRequest) {
        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return new MessageResponse("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getFirstName(), 
                             signUpRequest.getLastName(),
                             signUpRequest.getEmail(),
                             encoder.encode(signUpRequest.getPassword()),
                             signUpRequest.getPhoneNumber());

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                case "admin":
                    Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(adminRole);
                    break;
                case "mod":
                    Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(modRole);
                    break;
                default:
                    Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                            .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                    roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return new MessageResponse("User registered successfully!");
    }
}