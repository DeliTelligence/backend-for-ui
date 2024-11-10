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

    @Column(name = "inventory_total_boxes", nullable = false)
    private Integer inventoryTotalBoxes;

    @Column(name = "inventory_value", nullable = false)
    private Float inventoryValue;

    @Column(name = "inventory_expiration_date", nullable = false)
    private LocalDate inventoryExpirationDate;

    @Column(name = "location", nullable = false, length = 200)
    private String location;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @JsonBackReference(value = "product-inventory")
    private Product product;

    @OneToMany(mappedBy = "inventory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("inventory-adjustment")
    private List<InventoryAdjustment> adjustments;

}