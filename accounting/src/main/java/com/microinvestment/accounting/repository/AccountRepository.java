package com.microinvestment.accounting.repository;

import com.microinvestment.accounting.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    // Spring Data JPA will automatically implement basic CRUD operations
}