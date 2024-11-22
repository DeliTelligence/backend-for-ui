package com.DeliTelligenceBackEndService.entitymodeldto;

import com.DeliTelligenceBackEndService.enumformodel.SaleType;
import lombok.Data;

import java.util.UUID;

@Data
public class DeliSaleInputDto {
    private UUID employeeId;
    private Float salePrice;
    private Float saleWeight;
    private Float wastePerSale;
    private Float wastPerSaleValue;
    private Float differenceWeight;
    private SaleType saleType;
    private Integer saleQuantity;
}