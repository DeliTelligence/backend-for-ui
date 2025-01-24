package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.entitymodel.Inventory;
import com.DeliTelligenceBackEndService.entitymodel.InventoryAdjustment;
import com.DeliTelligenceBackEndService.entitymodel.Product;
import com.DeliTelligenceBackEndService.entitymodel.Supplier;
import com.DeliTelligenceBackEndService.entitymodeldto.InventoryAdjustmentInputDto;
import com.DeliTelligenceBackEndService.service.InventoryService;
import com.DeliTelligenceBackEndService.service.ProductService;
import com.DeliTelligenceBackEndService.service.SupplierService;
import org.mapstruct.*;

@Mapper(componentModel = "spring", uses = {SupplierMapper.class, ProductMapper.class})
public interface InventoryAdjustmentMapper {

    @Mapping(target = "supplier", source = "supplierName")
    @Mapping(target = "product", source = "productName")
    @Mapping(target = "weightPerBox", source = "orderWeight")
    @Mapping(target = "dateOfAdjustment", expression = "java(java.time.LocalDate.now())")
    @Mapping(target = "inventory", ignore = true) // We'll set this manually
    InventoryAdjustment toInventoryAdjustment(InventoryAdjustmentInputDto inventoryAdjustmentInputDto, @Context SupplierService supplierService, @Context ProductService productService);

    @AfterMapping
    default void setInventory(@MappingTarget InventoryAdjustment inventoryAdjustment) {
        // Fetch the just-mapped Product
        Product product = inventoryAdjustment.getProduct();

        // Here we assume that product.getInventories() returns a list of inventories
        // If there is exactly one inventory, you can get it like this
        Inventory inventory = product.getInventory();

        inventoryAdjustment.setInventory(inventory);
    }
}

