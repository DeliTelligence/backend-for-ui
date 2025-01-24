package com.DeliTelligenceBackEndService.entitymodel.mapper;


import com.DeliTelligenceBackEndService.entitymodel.StandardWeight;
import com.DeliTelligenceBackEndService.entitymodeldto.StandardWeightDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StandardWeightMapper {

    StandardWeightDto toStandardWeightDto(StandardWeight standardWeight);

    StandardWeight toStandardWeight(StandardWeightDto standardWeightDto);

//    default List<String> mapRelatedProducts(StandardWeight standardWeight) {
//        if (standardWeight.getStandardWeightProducts() == null) {
//            return null;
//        }
//        return standardWeight.getStandardWeightProducts().stream()
//                .map(swp -> swp.getProduct().getProductName())
//                .collect(Collectors.toList());
//    }
}
