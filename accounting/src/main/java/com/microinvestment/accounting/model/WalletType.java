package com.microinvestment.accounting.model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "wallet_type")
public class WalletType {

    @Id
    private String id;

    @NotBlank
    private String name;
    @Enumerated(value = EnumType.STRING)
    private EntityStatus entityStatus;

    private String createdBy;
    private String ownerId;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    private String amountLimit;

    public WalletType(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.entityStatus = EntityStatus.ACTIVE;
        this.dateCreated = LocalDateTime.now();
    }

    public WalletType() {

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(LocalDateTime dateCreated) {
        this.dateCreated = dateCreated;
    }

    public LocalDateTime getDateUpdated() {
        return dateUpdated;
    }

    public void setDateUpdated(LocalDateTime dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    public String getAmountLimit() {
        return amountLimit;
    }

    public void setAmountLimit(String limit) {
        this.amountLimit = limit;
    }

    @PreUpdate
    public void preUpdate() {
        dateUpdated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "WalletType{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", entityStatus=" + entityStatus +
                ", createdBy='" + createdBy + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                '}';
    }
}
