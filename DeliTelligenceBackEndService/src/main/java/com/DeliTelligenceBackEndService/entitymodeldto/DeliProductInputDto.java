package com.DeliTelligenceBackEndService.entitymodeldto;

import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class DeliProductInputDto {
    private List<String> products;
    private UUID employeeId;
    private Float combinedWeight;
    private Float deliProductPrice;
    private Integer deliProductQuantity;
    private Boolean weightToPrice;
}
