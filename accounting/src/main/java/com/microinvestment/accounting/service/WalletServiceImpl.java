package com.microinvestment.accounting.service;

import com.microinvestment.accounting.dto.WalletDto;
import com.microinvestment.accounting.exception.ResourceNotFoundException;
import com.microinvestment.accounting.model.Account;
import com.microinvestment.accounting.model.EntityStatus;
import com.microinvestment.accounting.model.Wallet;
import com.microinvestment.accounting.model.WalletType;
import com.microinvestment.accounting.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class WalletServiceImpl implements WalletService {

    private final WalletRepository walletRepository;
    private final AccountService accountService;
    private final WalletTypeService walletTypeService;

    @Autowired
    public WalletServiceImpl(WalletRepository walletRepository, AccountService accountService, WalletTypeService walletTypeService) {
        this.walletRepository = walletRepository;
        this.accountService = accountService;
        this.walletTypeService = walletTypeService;
    }

    @Override
    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    @Override
    public List<Wallet> getAllWalletsByOwnerId(String ownerId) {
        return walletRepository.findByOwnerIdAndEntityStatusNot(ownerId, EntityStatus.DELETED);
    }

    @Override
    public Wallet getWalletById(String id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Wallet not found with id: " + id));
    }

    @Override
    public Wallet updateWallet(String id, Wallet walletDetails) {
        Wallet wallet = getWalletById(id);
        wallet.setName(walletDetails.getName());
        return walletRepository.save(wallet);
    }

    @Override
    public void deleteWallet(String id) {
        Wallet wallet = getWalletById(id);
        walletRepository.delete(wallet);
    }

    @Override
    @Transactional
    public Wallet deposit(String walletId, double amount) {
        Wallet wallet = getWalletById(walletId);
        if (wallet.getBalance() < 0) {
            throw new IllegalArgumentException("Wallet balance cannot be negative");
        }
        wallet.setBalance(wallet.getBalance() + amount);
        return walletRepository.save(wallet);
    }

    @Override
    @Transactional
    public Wallet withdraw(String walletId, double amount) {
        Wallet wallet = getWalletById(walletId);

        if (wallet.getBalance() < amount) {
            return null;
        }

        //todo
        //call api
        wallet.setBalance(wallet.getBalance() - amount);
        wallet = walletRepository.save(wallet);
        return wallet;
    }

    @Override
    public List<Wallet> getWalletsByOwner(String ownerId) {
        return List.of();
    }

    @Override
    @Transactional
    public Wallet transfer(String sourceWalletId, String destinationWalletId, double amount) {
        Wallet sourceWallet = getWalletById(sourceWalletId);

        if (sourceWallet.getBalance() < amount) {
            return null;
        }
        //Wallet destinationWallet = getWalletById(destinationWalletId);

        sourceWallet.setBalance(sourceWallet.getBalance()- amount);

        walletRepository.save(sourceWallet);

        return sourceWallet;
    }

    @Override
    public Wallet createWallet(WalletDto walletDto) {

        Account account = accountService.getPersonById(walletDto.getAccountId());
        WalletType walletType = walletTypeService.getWalletTypeById(walletDto.getWalletTypeId());

        Wallet wallet = new Wallet(walletDto.getName(), walletDto.getInitialBalance(), account, walletType, walletDto.getAmountLimit());
        wallet = walletRepository.save(wallet);
        return wallet;
    }
}
