package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.*;

import com.DeliTelligenceBackEndService.entitymodel.mapper.DeliProductMapper;
import com.DeliTelligenceBackEndService.entitymodel.mapper.DeliSaleMapper;
import com.DeliTelligenceBackEndService.entitymodel.mapper.ProductMapper;
import com.DeliTelligenceBackEndService.entitymodel.repository.DeliSaleRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.delisaledto.DeliSaleInputDto;
import org.springframework.stereotype.Service;

@Service
public class DeliServiceImpl {

    private final DeliSaleRepository deliSaleRepository;
    private final DeliSaleMapper deliSaleMapper;
    private final EmployeeService employeeService;

    public DeliServiceImpl(DeliSaleRepository deliSaleRepository, DeliSaleMapper deliSaleMapper, EmployeeService employeeService) {
        this.deliSaleRepository = deliSaleRepository;
        this.deliSaleMapper = deliSaleMapper;
        this.employeeService = employeeService;
    }

    public String CreateSale(DeliSaleInputDto inputDto) {
        DeliSale deliSale = deliSaleMapper.toDeliSale(inputDto, employeeService);
        deliSaleRepository.save(deliSale);

        return "Done";
    }


}
