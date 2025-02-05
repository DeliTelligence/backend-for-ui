package com.DeliTelligenceBackEndService.entitymodeldto.delisaledto;

import com.DeliTelligenceBackEndService.enumformodel.SaleType;
import lombok.Data;

import java.util.UUID;

@Data
public class DeliSaleInputDto {
    private UUID employeeId;
    private DeliProductInputDto deliProductInputDto;
    private Float salePrice;
    private Float saleWeight;
    private Float wastePerSaleValue;
    private Float differenceWeight;
    private SaleType saleType;
    private int quantity;
    private boolean handMade;
}