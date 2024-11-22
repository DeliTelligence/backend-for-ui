package com.DeliTelligenceBackEndService.entitymodel.repository;

import com.DeliTelligenceBackEndService.entitymodel.Product;
import com.DeliTelligenceBackEndService.enumformodel.ProductType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    List<Product> findByProductType(ProductType productType);

    Optional<Product> getProductByProductName(String productName);
}
