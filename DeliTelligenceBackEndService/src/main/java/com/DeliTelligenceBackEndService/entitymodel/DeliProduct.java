package com.DeliTelligenceBackEndService.entitymodel;

import com.DeliTelligenceBackEndService.enumformodel.PortionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Data
@Entity
@Table(name = "TBL_DELI_PRODUCT")
@NoArgsConstructor
@AllArgsConstructor
public class DeliProduct {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "MADE_DELI_PRODUCT_ID", updatable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID id;

    @ManyToOne
    @JsonBackReference("product-deliProduct")
    @JoinColumn(name = "DELI_PRODUCT_ID", referencedColumnName = "PRODUCT_ID")
    private Product deliProduct;


    @OneToMany(mappedBy = "deliProduct", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("deliProduct-ingredient")
    private List<Ingredient> ingredients;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SALE_ID", unique = true)
    @JsonBackReference("DeliProduct-DeliSale")
    private DeliSale sale;

    @Column(name= "COMBINED_WEIGHT")
    private float combinedWeight;

    @Column(name = "PORTION_TYPE")
    private PortionType portionType;

}