package com.DeliTelligenceBackEndService.entitymodel;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "tbl_food_allergen")
@NoArgsConstructor
@AllArgsConstructor
public class FoodAllergen {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "ALLERGEN_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @Column(name = "ALLERGEN_DESCRIPTION", length = 1000)
    private String allergenDescription;

    @Column(name = "ALLERGEN_NAME", length = 200)
    private String allergenName;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "TBL_FOOD_ALLERGENS_DETAIL",
            joinColumns = @JoinColumn(name = "ALLERGEN_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    private Set<Product> products = new HashSet<Product>();
}