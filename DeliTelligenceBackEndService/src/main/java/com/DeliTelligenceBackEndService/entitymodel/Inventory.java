package com.DeliTelligenceBackEndService.entitymodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_inventory")
@NoArgsConstructor
@AllArgsConstructor

public class Inventory {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "INVENTORY_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "INVENTORY_TOTAL_BOXES", nullable = false)
    private Integer inventoryTotalBoxes;

    @Column(name = "INVENTORY_VALUE", nullable = false)
    private Float inventoryValue;

    @Column(name = "INVENTORY_EXPIRATION_DATE", nullable = false)
    private LocalDate inventoryExpirationDate;

    @Column(name = "LOCATION", nullable = false, length = 200)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    @JsonBackReference(value = "product-inventory")
    private Product product;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("inventory-adjustment")
    private List<InventoryAdjustment> adjustments;

}