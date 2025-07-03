package com.microinvestment.accounting.dto;

import com.microinvestment.accounting.model.Account;
import com.microinvestment.accounting.model.EntityStatus;
import com.microinvestment.accounting.model.WalletType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

import java.time.LocalDateTime;

public class WalletDto {

    private String name;
    private double balance;
    private String walletTypeId;
    private String accountId;
    private String createdBy;
    private String ownerId;
    private EntityStatus entityStatus;
    private String amountLimit;

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

    public String getWalletTypeId() {
        return walletTypeId;
    }

    public void setWalletTypeId(String walletTypeId) {
        this.walletTypeId = walletTypeId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
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

    public void setAmountLimit(String amountLimit) {
        this.amountLimit = amountLimit;
    }

    @Override
    public String toString() {
        return "WalletDto{" +
                "name='" + name + '\'' +
                ", balance=" + balance +
                ", walletTypeId='" + walletTypeId + '\'' +
                ", accountId='" + accountId + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", ownerId='" + ownerId + '\'' +
                ", entityStatus=" + entityStatus +
                ", amountLimit='" + amountLimit + '\'' +
                '}';
    }
}
