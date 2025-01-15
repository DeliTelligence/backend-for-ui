package com.DeliTelligenceBackEndService.entitymodel;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "TBL_STANDARD_WEIGHT_PRODUCT")
@NoArgsConstructor
@AllArgsConstructor

public class StandardWeightProduct {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "STANDARD_WEIGHT_PRODUCT_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID standardWeightId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "standard-standardWeight")
    @JoinColumn(name = "STANDARD_WEIGHT_ID")
    private StandardWeight standardWeight;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference(value = "product-standardWeight")
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;

    @Column(name = "STANDARD_WEIGHT")
    private double standardWeightValue;
}
