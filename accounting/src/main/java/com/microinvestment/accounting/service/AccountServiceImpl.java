package com.microinvestment.accounting.service;

import com.microinvestment.accounting.exception.ResourceNotFoundException;
import com.microinvestment.accounting.model.Account;
import com.microinvestment.accounting.model.EntityStatus;
import com.microinvestment.accounting.model.Wallet;
import com.microinvestment.accounting.repository.AccountRepository;
import com.microinvestment.accounting.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    
    private final AccountRepository accountRepository;
    private final WalletRepository walletRepository;
    
    @Autowired
    public AccountServiceImpl(AccountRepository accountRepository, WalletRepository walletRepository) {
        this.accountRepository = accountRepository;
        this.walletRepository = walletRepository;
    }
    
    @Override
    public List<Account> getAllPersons() {
        return accountRepository.findAll();
    }

    @Override
    public List<Account> getAllAccountsByOwnerId(String ownerId) {
        return accountRepository.findByOwnerIdAndEntityStatusNot(ownerId, EntityStatus.DELETED);
    }

    @Override
    public Account getPersonById(String id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Account not found with id: " + id));
    }
    
    @Override
    public Account createPerson(Account account) {
        return accountRepository.save(account);
    }
    
    @Override
    public Account updatePerson(String id, Account accountDetails) {
        Account account = getPersonById(id);
        account.setName(accountDetails.getName());
        return accountRepository.save(account);
    }
    
    @Override
    public void deletePerson(String id) {
        Account account = getPersonById(id);
        accountRepository.delete(account);
    }
    
    @Override
    @Transactional
    public Wallet createWalletForPerson(String personId, String walletName, double initialBalance) {
        Account account = getPersonById(personId);
        Wallet wallet = new Wallet(walletName, initialBalance, account);
        accountRepository.save(account);
        return wallet;
    }
    
//    @Override
//    public List<Wallet> getPersonWallets(String personId) {
//        Account account = getPersonById(personId);
//        return account.getWallets();
//    }
//
//    @Override
//    public double getPersonTotalBalance(String personId) {
//        Account account = getPersonById(personId);
//        return account.getTotalBalance();
//    }
}