package com.microinvestment.accounting.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

/**
 * Represents a person in the system who can have multiple wallets.
 */
@Entity
@Table(name = "account")
public class Account {
    @Id
    private String id;

    @NotBlank
    private String name;

    private String createdBy;
    private String ownerId;
    private String phoneNumber;
    private LocalDateTime dateCreated;
    private LocalDateTime dateUpdated;
    @Enumerated(value = EnumType.STRING)
    private EntityStatus entityStatus;

//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Wallet> wallets;

    /**
     * Default constructor required by JPA.
     */
    public Account() {
        this.id = UUID.randomUUID().toString();
        //this.wallets = new ArrayList<>();
        this.dateCreated = LocalDateTime.now();

    }

    /**
     * Creates a new person with the given name.
     *
     * @param name The name of the person
     */
    public Account(String name) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        //this.wallets = new ArrayList<>();
    }

    /**
     * Gets the unique identifier for this person.
     *
     * @return The person's ID
     */
    public String getId() {
        return id;
    }

    /**
     * Gets the name of this person.
     *
     * @return The person's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this person.
     *
     * @param name The new name for the person
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets an unmodifiable view of all wallets owned by this person.
     *
     * @return A list of all wallets
     */
//   // public List<Wallet> getWallets() {
//        return Collections.unmodifiableList(wallets);
//    }

    /**
     * Adds a new wallet to this person.
     *
     * @param wallet The wallet to add
     * @return true if the wallet was added successfully
     */
//    //public boolean addWallet(Wallet wallet) {
//        return wallets.add(wallet);
//    }

    /**
     * Removes a wallet from this person.
     *
     * @param wallet The wallet to remove
     * @return true if the wallet was removed successfully
     */
//   // public boolean removeWallet(Wallet wallet) {
//        return wallets.remove(wallet);
//    }

    /**
     * Creates a new wallet with the given name and initial balance and adds it to this person.
     *
     * @param name The name of the wallet
     * @param initialBalance The initial balance for the wallet
     * @return The newly created wallet
     */

    /**
     * Finds a wallet by its ID.
     *
     * @param walletId The ID of the wallet to find
     * @return The wallet if found, null otherwise
     */
//    public Wallet findWalletById(String walletId) {
//        return wallets.stream()
//                .filter(wallet -> wallet.getId().equals(walletId))
//                .findFirst()
//                .orElse(null);
//    }

    /**
     * Finds a wallet by its name.
     *
     * @param walletName The name of the wallet to find
     * @return The wallet if found, null otherwise
     */
//    public Wallet findWalletByName(String walletName) {
//        return wallets.stream()
//                .filter(wallet -> wallet.getName().equals(walletName))
//                .findFirst()
//                .orElse(null);
//    }

    /**
     * Gets the total balance across all wallets owned by this person.
     *
     * @return The total balance
     */
//    public double getTotalBalance() {
//        return wallets.stream()
//                .mapToDouble(Wallet::getBalance)
//                .sum();
//    }

    public void setId(String id) {
        this.id = id;
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

//    public void setWallets(List<Wallet> wallets) {
//        this.wallets = wallets;
//    }

    public EntityStatus getEntityStatus() {
        return entityStatus;
    }

    public void setEntityStatus(EntityStatus entityStatus) {
        this.entityStatus = entityStatus;
    }

    @PreUpdate
    public void preUpdate() {
        dateUpdated = LocalDateTime.now();
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(String ownerId) {
        this.ownerId = ownerId;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", createdBy='" + createdBy + '\'' +
                ", dateCreated=" + dateCreated +
                ", dateUpdated=" + dateUpdated +
                //", wallets=" + wallets +
                '}';
    }
}
