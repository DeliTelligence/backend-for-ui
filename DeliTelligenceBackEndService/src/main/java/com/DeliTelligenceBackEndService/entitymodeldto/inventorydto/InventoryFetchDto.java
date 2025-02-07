package com.DeliTelligenceBackEndService.entitymodeldto.inventorydto;

import com.DeliTelligenceBackEndService.entitymodeldto.ProductFetchDto;
import lombok.Data;

import java.util.List;

@Data
public class InventoryFetchDto {
    private List<ProductFetchDto> products;
    private float totalWeight;
    private float saladPortion;
    private float fillingPortion;
    private String location;
    private float inventoryValue;
}
