package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.Inventory;
import com.DeliTelligenceBackEndService.entitymodel.InventoryAdjustment;
import com.DeliTelligenceBackEndService.entitymodel.Product;
import com.DeliTelligenceBackEndService.entitymodel.StandardWeightProduct;
import com.DeliTelligenceBackEndService.entitymodel.mapper.InventoryAdjustmentMapper;
import com.DeliTelligenceBackEndService.entitymodel.mapper.InventoryMapper;
import com.DeliTelligenceBackEndService.entitymodel.repository.InventoryAdjustmentRepository;
import com.DeliTelligenceBackEndService.entitymodel.repository.InventoryRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.InventoryAdjustmentInputDto;
import com.DeliTelligenceBackEndService.entitymodeldto.InventoryFetchDto;
import com.DeliTelligenceBackEndService.enumformodel.AdjustmentType;
import com.DeliTelligenceBackEndService.enumformodel.StandardType;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {
    private final InventoryRepository inventoryRepository;
    private final InventoryMapper inventoryMapper;
    private final InventoryAdjustmentRepository inventoryAdjustmentRepository;
    private final InventoryAdjustmentMapper inventoryAdjustmentMapper;
    private final ProductService productService;
    private final SupplierService supplierService;

    public InventoryService(InventoryRepository inventoryRepository, InventoryMapper inventoryMapper, InventoryAdjustmentRepository inventoryAdjustmentRepository, InventoryAdjustmentMapper inventoryAdjustmentMapper, ProductService productService, SupplierService supplierService) {
        this.inventoryRepository = inventoryRepository;
        this.inventoryMapper = inventoryMapper;
        this.inventoryAdjustmentRepository = inventoryAdjustmentRepository;
        this.inventoryAdjustmentMapper = inventoryAdjustmentMapper;
        this.productService = productService;
        this.supplierService = supplierService;
    }

    public List<InventoryFetchDto> getInventory() {
        List<Inventory> inventoryList = inventoryRepository.findAll();
        List<InventoryFetchDto> inventoryFetchDtoList = new ArrayList<>();

        for (Inventory inventory : inventoryList) {
            InventoryFetchDto inventoryFetchDto = inventoryMapper.toInventoryFetchDto(inventory);

            float fillingPortion = 0;
            float saladPortion = 0;

            for (Product product : inventory.getProducts()) {
                boolean fillingFound = false;
                boolean saladFound = false;

                for (StandardWeightProduct swp : product.getStandardWeightProducts()) {
                    if (swp.getStandardWeight().getStandardType() == StandardType.FILLING && swp.getStandardWeightValue() > 0) {
                        fillingPortion += (float) (product.getInventory().getTotalWeight() / swp.getStandardWeightValue());
                        fillingFound = true;
                    } else if (swp.getStandardWeight().getStandardType() == StandardType.SALAD && swp.getStandardWeightValue() > 0) {
                        saladPortion += (float) (product.getInventory().getTotalWeight() / swp.getStandardWeightValue());
                        saladFound = true;
                    }

                    if (fillingFound && saladFound) {
                        break;
                    }
                }

                // If no SALAD type found, use FILLING type for SALAD as well
                if (!saladFound && fillingFound) {
                    saladPortion += (float) (product.getInventory().getTotalWeight() / product.getStandardWeightProducts().stream()
                            .filter(swp -> swp.getStandardWeight().getStandardType() == StandardType.FILLING)
                            .findFirst()
                            .map(StandardWeightProduct::getStandardWeightValue)
                            .orElse(0.0)); // Default to 1 if no FILLING type found (to avoid division by zero)
                }
            }

            inventoryFetchDto.setFillingPortion(fillingPortion);
            inventoryFetchDto.setSaladPortion(saladPortion);

            inventoryFetchDtoList.add(inventoryFetchDto);
        }

        return inventoryFetchDtoList;
    }




    public String createInventoryAdjustment(InventoryAdjustmentInputDto inventoryAdjustmentInputDto){
        InventoryAdjustment inventoryAdjustment = inventoryAdjustmentMapper.toInventoryAdjustment(inventoryAdjustmentInputDto, supplierService, productService);
        inventoryAdjustmentRepository.save(inventoryAdjustment);

        Product productInventoryToUpdate = productService.getProductByNameHelper(inventoryAdjustmentInputDto.getProductName());
        Inventory inventoryInventoryToUpdate = productInventoryToUpdate.getInventory();
        if (inventoryAdjustmentInputDto.getAdjustmentType() == AdjustmentType.DELIVERY) {
            float newInventoryWeight = inventoryInventoryToUpdate.getTotalWeight() + inventoryAdjustmentInputDto.getOrderWeight();
            float newInventoryValue = inventoryInventoryToUpdate.getInventoryValue() + inventoryAdjustmentInputDto.getCostPerBox();
            inventoryInventoryToUpdate.setTotalWeight(newInventoryWeight);
            inventoryInventoryToUpdate.setInventoryValue(newInventoryValue);
        }
        else if (inventoryAdjustmentInputDto.getAdjustmentType() == AdjustmentType.WASTE){
            float newInventoryWeight = inventoryInventoryToUpdate.getTotalWeight() - inventoryAdjustmentInputDto.getOrderWeight();
            float newInventoryValue = inventoryInventoryToUpdate.getInventoryValue() - inventoryAdjustmentInputDto.getCostPerBox();
            inventoryInventoryToUpdate.setTotalWeight(newInventoryWeight);
            inventoryInventoryToUpdate.setInventoryValue(newInventoryValue);

        }

        inventoryRepository.save(inventoryInventoryToUpdate);

        return "Done";
    }



}
