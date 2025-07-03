package com.microinvestment.accounting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Represents a wallet that holds a balance for a person.
 */
@Entity
@Table(name = "wallets")
public class Wallet {

    @Id
    private String id;

    @NotBlank
    private String name;

    private double balance;

    @ManyToOne
    @JoinColumn(name = "wallet_type_id")
    private WalletType walletType;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    private String createdBy;
    private String ownerId;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    @Enumerated(value = EnumType.STRING)
    private EntityStatus entityStatus;
    private String amountLimit;

    public Wallet() {
        this.id = UUID.randomUUID().toString();
    }

    public Wallet(String name, double initialBalance, Account account) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.balance = initialBalance >= 0 ? initialBalance : 0;
        this.account = account;
        this.dateCreated = LocalDateTime.now();
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

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public WalletType getWalletType() {
        return walletType;
    }

    public void setWalletType(WalletType walletType) {
        this.walletType = walletType;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
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
        return "Wallet{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", balance=" + balance +
                ", walletType=" + walletType +
                ", account=" + account +
                '}';
    }
}
