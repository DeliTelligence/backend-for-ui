package com.DeliTelligenceBackEndService.entitymodel.repository;

import com.DeliTelligenceBackEndService.entitymodel.StandardWeight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface StandardWeightRepository extends JpaRepository<StandardWeight, UUID> {
}
