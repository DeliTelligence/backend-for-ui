package com.DeliTelligenceBackEndService.entitymodel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
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

    @Column(name = "product_name", nullable = false, length = 100)
    private String productName;

    @Column(name = "standard_weight", nullable = false)
    private Float standardWeight;

    @Column(name = "product_description", nullable = false)
    private LocalDate productDescription;

    @Column(name = "product_price", nullable = false)
    private Float productPrice;

    @Column(name = "product_image", nullable = false)
    private byte[] productImage;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-inventory")
    private List<Inventory> inventories;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("product-purchaseOrder")
    private List<PurchaseOrderDetail> orderDetails;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<FoodAllergen> productAllergens = new HashSet<FoodAllergen>();

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    private Set<DeliProduct> productFillings = new HashSet<DeliProduct>();


}