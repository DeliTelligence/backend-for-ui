package com.DeliTelligenceBackEndService.entitymodel.repository;

import com.DeliTelligenceBackEndService.entitymodel.FoodAllergen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface FoodAllergenRepository extends JpaRepository<FoodAllergen, UUID> {

}