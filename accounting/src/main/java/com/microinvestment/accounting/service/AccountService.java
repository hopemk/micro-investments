package com.microinvestment.accounting.service;

import com.microinvestment.accounting.model.Account;
import com.microinvestment.accounting.model.Wallet;
import java.util.List;

public interface AccountService {
    List<Account> getAllPersons();
    List<Account> getAllAccountsByOwnerId(String ownerId);
    Account getPersonById(String id);
    Account createPerson(Account account);
    Account updatePerson(String id, Account accountDetails);
    void deletePerson(String id);
    Wallet createWalletForPerson(String personId, String walletName, double initialBalance);
//    List<Wallet> getPersonWallets(String personId);
//    double getPersonTotalBalance(String personId);
}