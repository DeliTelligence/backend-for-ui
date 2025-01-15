package com.DeliTelligenceBackEndService.entitymodel.repository;

import com.DeliTelligenceBackEndService.entitymodel.StandardWeight;
import com.DeliTelligenceBackEndService.entitymodel.StandardWeightProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StandardWeightProductRepository extends JpaRepository<StandardWeightProduct, UUID> {
}
