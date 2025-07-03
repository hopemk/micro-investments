package com.microfinance.security.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microfinance.security.model.Group;

/**
 * Repository for Group entity.
 */
@Repository
public interface GroupRepository extends JpaRepository<Group, String> {
    /**
     * Find a group by name.
     *
     * @param name The group name to search for
     * @return An Optional containing the group if found
     */
    Optional<Group> findByName(String name);

    /**
     * Check if a group with the given name exists.
     *
     * @param name The name to check
     * @return True if a group with the name exists, false otherwise
     */
    Boolean existsByName(String name);
}