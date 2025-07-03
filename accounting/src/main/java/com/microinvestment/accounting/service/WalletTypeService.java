package com.microinvestment.accounting.service;

import com.microinvestment.accounting.model.EntityStatus;
import com.microinvestment.accounting.model.WalletType;
import java.util.List;

public interface WalletTypeService {
    List<WalletType> getAllWalletTypes();
    List<WalletType> getWalletTypesByStatus(EntityStatus status);
    WalletType getWalletTypeById(String id);
    WalletType getWalletTypeByName(String name);
    WalletType createWalletType(WalletType walletType);
    WalletType updateWalletType(String id, WalletType walletTypeDetails);
    void deleteWalletType(String id);
    WalletType activateWalletType(String id);
    WalletType deactivateWalletType(String id);
}