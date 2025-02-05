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
@Table(name = "TBL_INGREDIENT")
@NoArgsConstructor
@AllArgsConstructor
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "INGREDIENT_ID", insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID ingredientId;

    @ManyToOne
    @JsonBackReference("deliProduct-ingredient")
    @JoinColumn(name = "MADE_DELI_PRODUCT_ID")
    private DeliProduct deliProduct;

    @ManyToOne
    @JsonBackReference("product-ingredient")
    @JoinColumn(name = "PRODUCT_ID")
    private Product product;


}
