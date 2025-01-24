package com.DeliTelligenceBackEndService.entitymodeldto;

import com.DeliTelligenceBackEndService.entitymodel.Product;
import lombok.Data;

@Data
public class StandardWeightProductDto {
    private StandardWeightDto standardWeight;
    private double standardWeightValue;


}
