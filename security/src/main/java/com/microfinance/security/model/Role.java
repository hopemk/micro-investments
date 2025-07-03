package com.microfinance.security.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.UUID;

/**
 * Represents a role in the system for authorization purposes.
 */
@Entity
@Table(name = "roles")
public class Role {
    @Id
    private String id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    @NotBlank
    @Size(max = 100)
    private String description;

    /**
     * Default constructor required by JPA.
     */
    public Role() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Creates a new role with the given name.
     *
     * @param name The name of the role
     */
    public Role(ERole name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    /**
     * Creates a new role with the given name and description.
     *
     * @param name The name of the role
     * @param description The description of the role
     */
    public Role(ERole name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}