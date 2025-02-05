package com.DeliTelligenceBackEndService.entitymodeldto.productcreatedto;

import com.DeliTelligenceBackEndService.enumformodel.ProductType;
import lombok.Data;

import java.util.List;

@Data
public class ProductCreateDto {
    private String productName;
    private String productDescription;
    private Float productPrice;
    private ProductType productType;
    private String productImageDto;
    private List<StandardWeightProductCreateDto> standardWeightProducts;
}
