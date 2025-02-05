package com.DeliTelligenceBackEndService.entitymodeldto;

import com.DeliTelligenceBackEndService.entitymodeldto.productcreatedto.StandardWeightCreateDto;
import lombok.Data;

@Data
public class StandardWeightProductUpdateDto {
    private double standardWeightValue;
    private StandardWeightUpdateDto standardWeight;
}
