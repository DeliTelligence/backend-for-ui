package com.DeliTelligenceBackEndService.entitymodel.repository;

import com.DeliTelligenceBackEndService.entitymodel.DeliSale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface DeliSaleRepository extends JpaRepository<DeliSale, UUID> {
    List<DeliSale> findBySaleDate(LocalDate saleDate);
    List<DeliSale> findBySaleDateBetween(LocalDate startDate, LocalDate endDate);
}

