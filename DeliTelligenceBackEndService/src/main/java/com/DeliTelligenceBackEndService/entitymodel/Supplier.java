package com.DeliTelligenceBackEndService.entitymodel;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.List;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_supplier")
@NoArgsConstructor
@AllArgsConstructor

public class Supplier {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "SUPPLIER_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(name = "SUPPLIER_NAME", nullable = false, length = 200)
    private String supplierName;

    @Column(name = "SUPPLIER_LOCATION", nullable = false, length = 500)
    private String supplierLocation;

    @Column(name = "SUPPLIER_NUMBER", nullable = false, length = 200)
    private String supplierNumber;

    @Column(name = "SUPPLIER_WEBSITE", nullable = false, length = 200)
    private String supplierWebsite;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("supplier-adjustments")
    private List<InventoryAdjustment> adjustments;

}