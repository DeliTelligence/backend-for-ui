package com.DeliTelligenceBackEndService.entitymodel.repository;

import com.DeliTelligenceBackEndService.entitymodel.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface InventoryRepository extends JpaRepository<Inventory, UUID> {

}
