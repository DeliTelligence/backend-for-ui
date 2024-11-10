package com.DeliTelligenceBackEndService.entitymodel;

import com.DeliTelligenceBackEndService.enumformodel.SaleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_deli_sales")
@NoArgsConstructor
@AllArgsConstructor
public class DeliSale {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "SALE_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "employee-sale")
    @JoinColumn(name = "EMPLOYEE_ID")
    private Employee employee;

    @Column(name = "sale_price", nullable = false)
    private Float salePrice;

    @Column(name = "sale_date", nullable = false)
    private LocalDate saleDate;

    @Column(name = "sale_weight", nullable = false)
    private Float saleWeight;

    @Column(name = "sale_time", nullable = false)
    private Instant saleTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "DELI_PRODUCT_ID", referencedColumnName = "DELI_PRODUCT_ID")
    private DeliProduct deliProduct;

    @Column(name = "waste_per_sale", nullable = false)
    private Float wastePerSale;

    @Column(name = "waste_per_sale_value", nullable = false)
    private Float wastePerSaleValue;

    @Column(name = "difference_weight", nullable = false)
    private Float differenceWeight;

    @Column(name = "sale_type", nullable = false, length = 50)
    private SaleType saleType;

    @Column(name = "quantity")
    private Integer quantity;

}