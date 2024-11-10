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
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference(value = "inventory-adjustment")
    @JoinColumn(name = "inventory_id", nullable = false)
    private Inventory inventory;

    @Column(name = "adjustment_type", nullable = false, length = 200)
    private String adjustmentType;

    @Column(name = "reason", nullable = false, length = 1000)
    private String reason;

    @Column(name = "date_of_adjustment", nullable = false)
    private LocalDate dateOfAdjustment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PURCHASE_ORDER_DETAIL_ID", referencedColumnName = "PURCHASE_ORDER_DETAIL_ID")
    private PurchaseOrderDetail purchaseOrderDetail;


}