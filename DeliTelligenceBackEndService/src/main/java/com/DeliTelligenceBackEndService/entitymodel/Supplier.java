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
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "supplier_name", nullable = false, length = 200)
    private String supplierName;

    @Column(name = "supplier_location", nullable = false, length = 200)
    private String supplierLocation;

    @OneToMany(mappedBy = "supplier", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("supplier-purchaseOrder")
    private List<PurchaseOrder> orders;

}