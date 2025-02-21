package com.DeliTelligenceBackEndService.entitymodeldto.delisaledto;

import lombok.Data;

import java.util.List;

@Data
public class DailySaleDto {
    private Float salePercentage;
    private String saleCategoryTime;
    private Float saleAmount;
}
