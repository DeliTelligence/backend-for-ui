package com.DeliTelligenceBackEndService.entitymodel;

import com.DeliTelligenceBackEndService.enumformodel.OrderStatus;
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
@Table(name = "tbl_purchase_order")
@NoArgsConstructor
@AllArgsConstructor

public class PurchaseOrder {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "PURCHASE_ORDER_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "order_price", nullable = false)
    private Float orderPrice;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonBackReference(value = "supplier-purchaseOrder")
    @JoinColumn(name = "supplier_id", nullable = false)
    private Supplier supplier;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "order_status", nullable = false)
    private OrderStatus orderStatus;

    @OneToMany(mappedBy = "purchaseOrder", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("purchaseOrder-purchaseOrderDetail")
    private List<PurchaseOrderDetail> orderDetails;

}