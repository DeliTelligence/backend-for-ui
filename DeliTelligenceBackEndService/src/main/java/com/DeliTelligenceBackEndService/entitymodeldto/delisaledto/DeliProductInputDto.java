package com.DeliTelligenceBackEndService.entitymodeldto.delisaledto;

import com.DeliTelligenceBackEndService.entitymodeldto.ProductInputDto;
import com.DeliTelligenceBackEndService.enumformodel.PortionType;
import lombok.Data;

import java.util.List;

@Data
public class DeliProductInputDto {
    private List<ProductInputDto> productInputDtos;
    private ProductInputDto productInputDto;
    private Float combinedWeight;
    private PortionType portionType;

}
