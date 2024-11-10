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
@Table(name = "tbl_deli_product")
@NoArgsConstructor
@AllArgsConstructor
public class DeliProduct {

    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "DELI_PRODUCT_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.VARCHAR)
    private UUID id;

    @Column(name = "theoretical_weight", nullable = false)
    private Float theoreticalWeight;

    @Column(name = "weight_to_price", nullable = false)
    private Boolean weightToPrice = false;

    @OneToOne(mappedBy = "deliProduct")
    private DeliSale sale;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "TBL_DELI_FILLING",
            joinColumns = @JoinColumn(name = "DELI_PRODUCT_ID"),
            inverseJoinColumns = @JoinColumn(name = "PRODUCT_ID")
    )
    private Set<Product> products = new HashSet<Product>();

}