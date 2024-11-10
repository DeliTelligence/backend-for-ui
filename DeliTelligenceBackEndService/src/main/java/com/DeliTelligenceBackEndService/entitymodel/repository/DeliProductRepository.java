package com.DeliTelligenceBackEndService.entitymodel.repository;

import com.DeliTelligenceBackEndService.entitymodel.DeliProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeliProductRepository extends JpaRepository<DeliProduct, UUID> {

}
