package com.microfinance.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.microfinance.security.model.User;
import com.microfinance.security.repository.UserRepository;

/**
 * Implementation of UserDetailsService for Spring Security.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    /**
     * Loads a user by username (email).
     *
     * @param email The email of the user
     * @return UserDetails object
     * @throws UsernameNotFoundException if the user is not found
     */
    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("User Not Found with email: " + email));

        return UserDetailsImpl.build(user);
    }
}