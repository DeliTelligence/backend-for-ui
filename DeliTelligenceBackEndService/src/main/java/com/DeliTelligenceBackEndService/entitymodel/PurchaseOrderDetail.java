package com.DeliTelligenceBackEndService.entitymodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_purchase_order_detail")
@NoArgsConstructor
@AllArgsConstructor

public class PurchaseOrderDetail {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PURCHASE_ORDER_DETAIL_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference(value = "product-purchaseOrder")
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference(value = "purchaseOrder-purchaseOrderDetail")
    @JoinColumn(name = "purchase_order_id", nullable = false)
    private PurchaseOrder purchaseOrder;

    @OneToOne(mappedBy = "purchaseOrderDetail")
    private InventoryAdjustment adjustment;

    @Column(name = "weight_per_box", nullable = false)
    private Float weightPerBox;

    @Column(name = "unit_price", nullable = false)
    private Float unitPrice;

    @Column(name = "quantity_of_box", nullable = false)
    private Integer quantityOfBox;

}