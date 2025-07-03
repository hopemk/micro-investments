package com.microinvestment.accounting.repository;

import com.microinvestment.accounting.model.Account;
import com.microinvestment.accounting.model.EntityStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    List<Account> findByOwnerIdAndEntityStatusNot(String ownerId, EntityStatus entityStatus);
    // Spring Data JPA will automatically implement basic CRUD operations
}