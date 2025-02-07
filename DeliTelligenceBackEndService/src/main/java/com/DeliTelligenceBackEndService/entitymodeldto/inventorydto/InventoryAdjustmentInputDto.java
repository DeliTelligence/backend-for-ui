package com.DeliTelligenceBackEndService.entitymodeldto.inventorydto;

import com.DeliTelligenceBackEndService.enumformodel.AdjustmentType;
import lombok.Data;

@Data
public class InventoryAdjustmentInputDto {
    private String supplierName;
    private String productName;
    private float orderWeight;
    private float costPerBox;
    private AdjustmentType adjustmentType;
    private String reason;

}
