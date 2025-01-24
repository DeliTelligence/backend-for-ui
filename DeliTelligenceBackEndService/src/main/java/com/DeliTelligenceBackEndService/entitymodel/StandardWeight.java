package com.DeliTelligenceBackEndService.entitymodel;

import com.DeliTelligenceBackEndService.enumformodel.StandardType;
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
@Table(name = "TBL_STANDARD_WEIGHT")
@NoArgsConstructor
@AllArgsConstructor

public class StandardWeight {
    @Id()
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "STANDARD_WEIGHT_ID",insertable = false, updatable = false)
    @JdbcTypeCode(SqlTypes.UUID)
    private UUID standardWeightId;

    @Enumerated(EnumType.STRING)
    @Column(name = "STANDARD_TYPE", nullable = false)
    private StandardType standardType;

    @OneToMany(mappedBy = "standardWeight", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("standard-standardWeight")
    private List<StandardWeightProduct> standardWeightProducts;

}
