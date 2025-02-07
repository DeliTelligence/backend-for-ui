package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.entitymodel.Supplier;
import com.DeliTelligenceBackEndService.entitymodeldto.inventorydto.SupplierFetchDto;
import com.DeliTelligenceBackEndService.service.SupplierService;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SupplierMapper {

    SupplierFetchDto toSupplierFetchDto(Supplier supplier);

    default Supplier map(String supplierName, @Context SupplierService supplierService) {
        if (supplierName == null) {
            return null;
        }
        // Fetching managed entity
        return supplierService.getSupplierByNameReal(supplierName);
    }

}
