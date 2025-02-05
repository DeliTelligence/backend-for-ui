package com.DeliTelligenceBackEndService.entitymodeldto;

import lombok.Data;

import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Data
public class ProductFetchDto {
    private UUID id;
    private String productName;
    private List<StandardWeightProductDto> standardWeightProducts;
    private float productPrice;
    private String productImageDto;
    private String productType;

}
