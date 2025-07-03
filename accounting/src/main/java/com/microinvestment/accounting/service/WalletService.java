package com.microinvestment.accounting.service;

import com.microinvestment.accounting.dto.WalletDto;
import com.microinvestment.accounting.model.Wallet;
import java.util.List;

public interface WalletService {
    List<Wallet> getAllWallets();
    List<Wallet> getAllWalletsByOwnerId (String ownerId);
    Wallet getWalletById(String id);
    Wallet updateWallet(String id, Wallet walletDetails);
    void deleteWallet(String id);
    Wallet deposit(String walletId, double amount);
    Wallet withdraw(String walletId, double amount);
    List<Wallet> getWalletsByOwner(String ownerId);
    Wallet transfer(String sourceWalletId, String destinationWalletId, double amount);
    Wallet createWallet(WalletDto walletDto);
}
