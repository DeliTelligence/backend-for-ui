package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.entitymodel.DeliProduct;
import com.DeliTelligenceBackEndService.entitymodel.Employee;
import com.DeliTelligenceBackEndService.entitymodel.Supplier;
import com.DeliTelligenceBackEndService.entitymodeldto.DeliProductInputDto;
import com.DeliTelligenceBackEndService.entitymodeldto.SupplierFetchDto;
import com.DeliTelligenceBackEndService.service.EmployeeService;
import com.DeliTelligenceBackEndService.service.SupplierService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    Supplier toSupplier(SupplierFetchDto supplierFetchDto);

    SupplierFetchDto toSupplierFetchDto(Supplier supplier);


    default Supplier map(String supplierName, @Context SupplierService supplierService) {
        if (supplierName == null) {
            return null;
        }
        // Fetching managed entity
        return supplierService.getSupplierByNameReal(supplierName);
    }

}
