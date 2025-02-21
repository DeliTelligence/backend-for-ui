package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.entitymodel.*;
import com.DeliTelligenceBackEndService.entitymodeldto.StandardWeightProductUpdateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.productcreatedto.StandardWeightProductCreateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.StandardWeightProductDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {StandardWeightMapper.class})
public interface StandardWeightProductMapper {


    StandardWeightProductDto toStandardWeightProductDto(StandardWeightProduct standardWeightProduct);

    StandardWeightProduct toStandardWeightProduct(StandardWeightProductUpdateDto standardWeightProductDto);

    StandardWeightProduct toStandardWeightProduct(StandardWeightProductCreateDto dto);


}