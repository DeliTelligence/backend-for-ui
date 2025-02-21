package com.DeliTelligenceBackEndService.entitymodel;

import com.DeliTelligenceBackEndService.enumformodel.ProductType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "PRODUCT_ID", insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(name = "PRODUCT_NAME", nullable = false, length = 100)
    private String productName;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-standardWeight")
    private List<StandardWeightProduct> standardWeightProducts;

    @Column(name = "PRODUCT_DESCRIPTION", nullable = false)
    private String productDescription;

    @Column(name = "PRODUCT_PRICE", nullable = false)
    private Float productPrice;

    @Column(name = "PRODUCT_IMAGE", nullable = false)
    private byte[] productImage;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRODUCT_TYPE", nullable = false)
    private ProductType productType;

    @Column(name = "PRODUCT_DELETED", nullable = false)
    private Boolean productDeleted;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "product-inventory")
    @JoinColumn(name = "INVENTORY_ID")
    private Inventory inventory;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-inventoryAdjustment")
    private List<InventoryAdjustment> adjustments;


    @OneToMany(mappedBy = "deliProduct")
    @JsonManagedReference("product-deliProduct")
    private List<DeliProduct> deliProducts;


    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-ingredient")
    private List<Ingredient> ingredients;


}