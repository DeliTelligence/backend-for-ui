package com.DeliTelligenceBackEndService.entitymodeldto.productcreatedto;

import lombok.Data;

@Data
public class StandardWeightProductCreateDto {
    private double standardWeightValue;
    private StandardWeightCreateDto standardWeight;
}
