package com.microinvestment.accounting.repository;

import com.microinvestment.accounting.model.EntityStatus;
import com.microinvestment.accounting.model.WalletType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface WalletTypeRepository extends JpaRepository<WalletType, String> {
    List<WalletType> findByEntityStatus(EntityStatus entityStatus);
    WalletType findByName(String name);
    List<WalletType> findByOwnerIdAndEntityStatusNot(String ownerId, EntityStatus entityStatus);
}