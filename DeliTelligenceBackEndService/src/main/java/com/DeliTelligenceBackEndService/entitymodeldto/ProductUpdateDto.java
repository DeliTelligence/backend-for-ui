package com.DeliTelligenceBackEndService.entitymodeldto;

import com.DeliTelligenceBackEndService.entitymodeldto.productcreatedto.StandardWeightProductCreateDto;
import com.DeliTelligenceBackEndService.enumformodel.ProductType;
import lombok.Data;

import java.util.List;
import java.util.UUID;
@Data
public class ProductUpdateDto {
    private UUID id;
    private String productName;
    private String productDescription;
    private Float productPrice;
    private ProductType productType;
    private String productImageDto;
    private List<StandardWeightProductUpdateDto> standardWeightProducts;
}
