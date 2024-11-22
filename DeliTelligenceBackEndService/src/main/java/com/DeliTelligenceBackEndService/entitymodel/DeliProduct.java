package com.DeliTelligenceBackEndService.entitymodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.UUID;

@Data
@Entity
@Table(name = "TBL_DELI_PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
public class DeliProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MADE_DELI_PRODUCT_ID", insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @ManyToOne
    @JsonBackReference("product-deliProduct")
    @JoinColumn(name = "DELI_PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    private Product deliProduct;

    @ManyToOne
    @JsonBackReference("product-productUsed")
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column(name = "COMBINED_WEIGHT", nullable = false)
    private Float combinedWeight;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALE_ID", unique = true)
    @JsonBackReference
    private DeliSale sale;

    @Column(name = "DELI_PRODUCT_PRICE", nullable = false)
    private Float deliProductPrice;

    @Column(name = "DELI_PRODUCT_QUANTITY", nullable = false)
    private Integer deliProductQuantity;

    @Column(name = "WEIGH_TO_PRICE", nullable = false)
    private boolean weightToPrice;
}