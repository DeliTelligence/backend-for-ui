package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.custommapper.EditCustomMapping;
import com.DeliTelligenceBackEndService.entitymodel.*;
import com.DeliTelligenceBackEndService.entitymodeldto.StandardWeightProductUpdateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.productcreatedto.StandardWeightProductCreateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.StandardWeightProductDto;
import org.mapstruct.*;

import java.util.Arrays;
import java.util.List;

@Mapper(componentModel = "spring", uses = {StandardWeightMapper.class})
public interface StandardWeightProductMapper {


    StandardWeightProductDto toStandardWeightProductDto(StandardWeightProduct standardWeightProduct);

    @Mapping(target = "standardWeight", source = "standardWeight.standardType")
    StandardWeightProduct toStandardWeightProduct(StandardWeightProductDto standardWeightProductDto);

    StandardWeightProduct toStandardWeightProduct(StandardWeightProductCreateDto dto);


}