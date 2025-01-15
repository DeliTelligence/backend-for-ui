package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.entitymodel.StandardWeightProduct;
import com.DeliTelligenceBackEndService.entitymodeldto.StandardWeightProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring", uses = {StandardWeightMapper.class})
public interface StandardWeightProductMapper {

    StandardWeightProductDto toStandardWeightProductDto(StandardWeightProduct standardWeightProduct);

    StandardWeightProduct toStandardWeightProduct(StandardWeightProductDto standardWeightProductDto);
}
