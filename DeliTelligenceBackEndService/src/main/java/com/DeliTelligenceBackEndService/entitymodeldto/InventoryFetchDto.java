package com.DeliTelligenceBackEndService.entitymodeldto;

import lombok.Data;

@Data
public class InventoryFetchDto {
    private String productName;
    private float totalWeight;
    private String location;
    private float inventoryValue;
}
