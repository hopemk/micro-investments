package com.microinvestment.accounting.repository;

import com.microinvestment.accounting.model.EntityStatus;
import com.microinvestment.accounting.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, String> {
    List<Wallet> findByAccountId(String personId);
    Wallet findByAccountIdAndName(String personId, String name);

    List<Wallet> findByOwnerIdAndEntityStatusNot(String ownerId, EntityStatus entityStatus);
}