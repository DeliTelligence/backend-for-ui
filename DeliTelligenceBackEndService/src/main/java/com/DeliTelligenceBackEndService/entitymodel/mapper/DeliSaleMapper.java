package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.DailySaleDto;
import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.DeliSaleFetchDto;
import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.QuantitySaleSplitDto;
import com.DeliTelligenceBackEndService.service.EmployeeService;
import org.mapstruct.*;


import com.DeliTelligenceBackEndService.entitymodel.DeliSale;
import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.DeliSaleInputDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {EmployeeMapper.class, DeliProductMapper.class})
public interface DeliSaleMapper {

    @Mapping(target = "employee", source = "employeeId")
    @Mapping(target = "deliProduct", source = "deliProductInputDto")
    @Mapping(target = "saleDate", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "saleTime", expression = "java(java.time.Instant.now())")
    DeliSale toDeliSale(DeliSaleInputDto deliSaleInputDto, @Context EmployeeService employeeService);

    @AfterMapping
    default void setSaleOnDeliProduct(@MappingTarget DeliSale deliSale) {
        if (deliSale.getDeliProduct() != null) {
            deliSale.getDeliProduct().setSale(deliSale);
        }
    }

    @Mapping(target = "employeeId", source = "employee.id")
    @Mapping(target = "deliProductInputDto", source = "deliProduct")
    DeliSaleInputDto toDeliSaleInputDto(DeliSale deliSale);

    DeliSaleFetchDto toDeliSaleFetchDto(DeliSale deliSale);

    QuantitySaleSplitDto toQuantitySaleSplitDto(float salesData, int quantityRepresented);


}
