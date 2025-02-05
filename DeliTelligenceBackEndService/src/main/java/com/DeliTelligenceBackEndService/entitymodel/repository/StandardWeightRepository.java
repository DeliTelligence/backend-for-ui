package com.DeliTelligenceBackEndService.entitymodel.repository;

import com.DeliTelligenceBackEndService.entitymodel.StandardWeight;
import com.DeliTelligenceBackEndService.enumformodel.StandardType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface StandardWeightRepository extends JpaRepository<StandardWeight, UUID> {
    Optional<StandardWeight> findByStandardType(StandardType standardType);
}
