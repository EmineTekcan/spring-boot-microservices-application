package com.eminetekcan.inventoryservice.service;

import com.eminetekcan.inventoryservice.entity.Inventory;
import com.eminetekcan.inventoryservice.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    public boolean isInStock(String skuCode){
        Inventory inventory=inventoryRepository.findBySkuCode(skuCode);

        if (inventory !=null)
            return true;
        else
            return false;
    }
}
