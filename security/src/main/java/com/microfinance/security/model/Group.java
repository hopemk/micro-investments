package com.microfinance.security.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * Represents a group in the system that can contain multiple users.
 */
@Entity
@Table(name = "groups")
public class Group {
    @Id
    private String id;

    @NotBlank
    @Size(max = 50)
    @Column(unique = true)
    private String name;

    @Size(max = 200)
    private String description;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "group_roles",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    /**
     * Default constructor required by JPA.
     */
    public Group() {
        this.id = UUID.randomUUID().toString();
    }

    /**
     * Creates a new group with the given name.
     *
     * @param name The name of the group
     */
    public Group(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
    }

    /**
     * Creates a new group with the given name and description.
     *
     * @param name The name of the group
     * @param description The description of the group
     */
    public Group(String name, String description) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}