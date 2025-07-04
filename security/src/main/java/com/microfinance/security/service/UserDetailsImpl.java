package com.microfinance.security.service;

import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.microfinance.security.model.User;

/**
 * Implementation of UserDetails interface for Spring Security.
 */
public class UserDetailsImpl implements UserDetails {
    private static final long serialVersionUID = 1L;

    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;

    @JsonIgnore
    private String password;

    private Collection<? extends GrantedAuthority> authorities;

    /**
     * Constructor for UserDetailsImpl.
     *
     * @param id The user ID
     * @param firstName The user's first name
     * @param lastName The user's last name
     * @param email The user's email
     * @param password The user's password
     * @param phoneNumber The user's phone number
     * @param authorities The user's authorities
     */
    public UserDetailsImpl(String id, String firstName, String lastName, String email, String password, 
                          String phoneNumber, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.authorities = authorities;
    }

    /**
     * Builds a UserDetailsImpl from a User entity.
     *
     * @param user The User entity
     * @return A UserDetailsImpl instance
     */
    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getName().name()))
                .collect(Collectors.toList());

        return new UserDetailsImpl(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNumber(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public String getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}