package com.DeliTelligenceBackEndService.entitymodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_inventory_adjustment")
@NoArgsConstructor
@AllArgsConstructor

public class InventoryAdjustment {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ADJUSTMENT_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference(value = "inventory-adjustment")
    @JoinColumn(name = "INVENTORY_ID", nullable = false)
    private Inventory inventory;

    @Column(name = "WEIGHT_ADJUSTMENT", nullable = false, length = 200)
    private float weightPerBox;

    @Column(name = "UNIT_COST", nullable = false, length = 200)
    private float unitCost;

    @Column(name = "ADJUSTMENT_TYPE", nullable = false, length = 200)
    private String adjustmentType;

    @Column(name = "REASON", nullable = false, length = 1000)
    private String reason;

    @Column(name = "DATE_OF_ADJUSTMENT", nullable = false)
    private LocalDate dateOfAdjustment;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value = "purchaseOrder-inventoryAdjustment")
    @JoinColumn(name = "PURCHASE_ORDER_ID", nullable = false)
    private PurchaseOrder purchaseOrder;

    @ManyToOne(cascade = CascadeType.ALL)
    @JsonBackReference(value = "product-inventoryAdjustment")
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;


}