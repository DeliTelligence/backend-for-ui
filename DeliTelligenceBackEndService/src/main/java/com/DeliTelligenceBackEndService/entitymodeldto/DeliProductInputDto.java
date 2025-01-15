package com.DeliTelligenceBackEndService.entitymodeldto;

import com.DeliTelligenceBackEndService.enumformodel.PortionType;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class DeliProductInputDto {
    private List<ProductInputDto> productInputDtos;
    private ProductInputDto productInputDto;
    private Float combinedWeight;
    private PortionType portionType;

}
