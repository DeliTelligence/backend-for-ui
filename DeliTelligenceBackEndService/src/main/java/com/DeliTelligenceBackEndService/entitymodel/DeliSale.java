package com.DeliTelligenceBackEndService.entitymodel;

import com.DeliTelligenceBackEndService.enumformodel.SaleType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @Column(name = "SALE_PRICE", nullable = false)
    private Float salePrice;

    @Column(name = "SALE_DATE", nullable = false)
    private LocalDate saleDate;

    @Column(name = "SALE_WEIGHT", nullable = false)
    private Float saleWeight;

    @Column(name = "SALE_TIME", nullable = false)
    private Instant saleTime;

    @Column(name = "WASTE_PER_SALE", nullable = false)
    private Float wastePerSale;

    @Column(name = "WASTE_PER_SALE_VALUE", nullable = false)
    private Float wastePerSaleValue;

    @Column(name = "DIFFERENCE_WEIGHT", nullable = false)
    private Float differenceWeight;

    @Enumerated(EnumType.STRING)
    @Column(name = "SALE_TYPE", nullable = false, length = 50)
    private SaleType saleType;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @OneToOne(mappedBy = "sale", cascade = CascadeType.ALL)
    @JsonManagedReference
    private DeliProduct deliProduct;

}