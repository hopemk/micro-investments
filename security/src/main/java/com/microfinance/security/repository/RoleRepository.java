package com.microfinance.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microfinance.security.model.ERole;
import com.microfinance.security.model.Role;

/**
 * Repository for Role entity.
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    /**
     * Find a role by name.
     *
     * @param name The role name to search for
     * @return An Optional containing the role if found
     */
    Optional<Role> findByName(ERole name);
}