package com.DeliTelligenceBackEndService.entitymodel.mapper;


import com.DeliTelligenceBackEndService.entitymodel.StandardWeight;
import com.DeliTelligenceBackEndService.entitymodeldto.StandardWeightUpdateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.productcreatedto.StandardWeightCreateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.StandardWeightDto;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface StandardWeightMapper {

    StandardWeightDto toStandardWeightDto(StandardWeight standardWeight);

    StandardWeight toStandardWeight(StandardWeightCreateDto standardWeightCreateDto);

    StandardWeight toStandardWeight(StandardWeightUpdateDto standardWeightUpdateDto);

}
