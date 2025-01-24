package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.entitymodel.Inventory;
import com.DeliTelligenceBackEndService.entitymodeldto.InventoryFetchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @Mapping(target = "productName", expression = "java(inventory.getProducts() != null && !inventory.getProducts().isEmpty() ? inventory.getProducts().get(0).getProductName() : null)")
    InventoryFetchDto toInventoryFetchDto(Inventory inventory);


}
