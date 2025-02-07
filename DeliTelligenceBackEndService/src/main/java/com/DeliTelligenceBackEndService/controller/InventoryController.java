package com.DeliTelligenceBackEndService.controller;

import com.DeliTelligenceBackEndService.entitymodeldto.inventorydto.InventoryAdjustmentInputDto;
import com.DeliTelligenceBackEndService.entitymodeldto.inventorydto.InventoryFetchDto;
import com.DeliTelligenceBackEndService.service.InventoryService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class InventoryController {
    private InventoryService inventoryService;

    public InventoryController(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    @QueryMapping
    public List<InventoryFetchDto> getInventory() {
        return inventoryService.getInventory();
    }

    @MutationMapping
    public String createInventoryAdjustment(@Argument InventoryAdjustmentInputDto input) {
        return inventoryService.createInventoryAdjustment(input);

    }
}
