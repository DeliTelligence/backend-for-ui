package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.DeliSale;
import com.DeliTelligenceBackEndService.entitymodel.Employee;
import com.DeliTelligenceBackEndService.entitymodel.Product;
import com.DeliTelligenceBackEndService.entitymodel.repository.DeliProductRepository;
import com.DeliTelligenceBackEndService.entitymodel.repository.DeliSaleRepository;
import com.DeliTelligenceBackEndService.entitymodel.repository.EmployeeRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.DeliProductInputDto;
import com.DeliTelligenceBackEndService.entitymodeldto.DeliSaleInputDto;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class DeliServiceImpl {

    private final DeliSaleRepository deliSaleRepository;
    private final EmployeeService employeeService;
    private final ProductService productService;


    public DeliServiceImpl(DeliSaleRepository deliSaleRepository, EmployeeService employeeService, ProductService productService) {
        this.deliSaleRepository = deliSaleRepository;
        this.employeeService = employeeService;
        this.productService = productService;
    }

//    public String createSale(DeliProductInputDto deliProductInputDto) {
//        List<Product> productList = new ArrayList<>();
//
//        for (String productName : deliProductInputDto.getProducts()){
//            Product product = productService.getProductByNameHelper(productName);
//            productList.add(product);
//        }
//
//        Employee employeeToFind = employeeService.getEmployeeById(deliProductInputDto.getEmployeeId());
//
//        DeliSale deliSale = new DeliSale();
//        deliSale.setEmployee(employeeToFind);
//        deliSale.setSalePrice(deliProductInputDto.getDeliProductPrice());
//        deliSale.setSaleDate(LocalDate.now());
//        deliSale.setSaleWeight(deliProductInputDto.getCombinedWeight());
//        deliSale.setSaleTime(Instant.now());
//        deliSale.setWastePerSale(deliProductInputDto.get);
//        deliSale.setWastePerSaleValue(deliSaleInputDto.getWastPerSaleValue());
//        deliSale.setDifferenceWeight(deliSaleInputDto.getDifferenceWeight());
//        deliSale.setSaleType(deliSaleInputDto.getSaleType());
//        deliSale.setQuantity(deliSaleInputDto.getSaleQuantity());
//
//        deliSaleRepository.save(deliSale);
//        return "Printing Barcode";
//    }

}
