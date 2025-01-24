package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.Supplier;
import com.DeliTelligenceBackEndService.entitymodel.mapper.SupplierMapper;
import com.DeliTelligenceBackEndService.entitymodel.repository.SupplierRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.SupplierFetchDto;
import com.DeliTelligenceBackEndService.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;
    private final SupplierMapper supplierMapper;

    public SupplierService(SupplierRepository supplierRepository, SupplierMapper supplierMapper) {
        this.supplierRepository = supplierRepository;
        this.supplierMapper = supplierMapper;
    }

    public List<SupplierFetchDto> getAllSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        List<SupplierFetchDto> supplierFetchDtos = new ArrayList<>();
        for (Supplier supplier : suppliers) {
            SupplierFetchDto supplierFetchDto = supplierMapper.toSupplierFetchDto(supplier);
            supplierFetchDtos.add(supplierFetchDto);
        }
        return supplierFetchDtos;
    }

    public SupplierFetchDto getSupplierByName(String supplierName) {
        Supplier supplier =  supplierRepository.findBySupplierName(supplierName).orElseThrow(() ->
                new ResourceNotFoundException("Supplier", "Name", supplierName));

        return supplierMapper.toSupplierFetchDto(supplier);
    }

    public Supplier getSupplierByNameReal(String supplierName) {
         return  supplierRepository.findBySupplierName(supplierName).orElseThrow(() ->
                new ResourceNotFoundException("Supplier", "Name", supplierName));




    }
}
