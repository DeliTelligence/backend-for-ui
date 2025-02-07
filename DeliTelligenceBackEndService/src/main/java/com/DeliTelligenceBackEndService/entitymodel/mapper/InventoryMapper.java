package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.entitymodel.Inventory;
import com.DeliTelligenceBackEndService.entitymodeldto.inventorydto.InventoryFetchDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;



@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface InventoryMapper {

    @Mappings({
            @Mapping(target = "products", source = "products"),
            @Mapping(target = "totalWeight", source = "totalWeight"),
            @Mapping(target = "location", source = "location"),
            @Mapping(target = "inventoryValue", source = "inventoryValue")
    })
    InventoryFetchDto toInventoryFetchDto(Inventory inventory);



}