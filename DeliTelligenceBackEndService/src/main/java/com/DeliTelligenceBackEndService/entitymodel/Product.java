package com.DeliTelligenceBackEndService.entitymodel;

import com.DeliTelligenceBackEndService.enumformodel.ProductType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_product")
@NoArgsConstructor
@AllArgsConstructor

public class Product {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PRODUCT_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 100)
    private String productName;

    @Column(name = "STANDARD_WEIGHT", nullable = false)
    private Float standardWeight;

    @Column(name = "PRODUCT_DESCRIPTION", nullable = false)
    private String productDescription;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private Float productPrice;

    @Column(name = "PRODUCT_IMAGE", nullable = false)
    private byte[] productImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_TYPE", nullable = false)
    private ProductType productType;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-inventory")
    private List<Inventory> inventories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-inventoryAdjustment")
    private List<InventoryAdjustment> adjustments;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<FoodAllergen> productAllergens = new HashSet<FoodAllergen>();

    @OneToMany(mappedBy = "deliProduct")
    @JsonManagedReference("product-deliProduct")
    private List<DeliProduct> deliProducts;

    @OneToMany(mappedBy = "product")
    @JsonManagedReference("product-productUsed")
    private List<DeliProduct> productsUsed;


}