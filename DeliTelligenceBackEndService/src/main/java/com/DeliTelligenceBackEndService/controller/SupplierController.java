package com.DeliTelligenceBackEndService.controller;

import com.DeliTelligenceBackEndService.entitymodeldto.SupplierFetchDto;
import com.DeliTelligenceBackEndService.service.SupplierService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class SupplierController {

    private final SupplierService supplierService;

    public SupplierController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @QueryMapping
    public List<SupplierFetchDto> getAllSuppliers() {
        return supplierService.getAllSuppliers();
    }

    @QueryMapping
    public SupplierFetchDto getSupplierByName(@Argument("name") String name) {
        return supplierService.getSupplierByName(name);
    }
}
