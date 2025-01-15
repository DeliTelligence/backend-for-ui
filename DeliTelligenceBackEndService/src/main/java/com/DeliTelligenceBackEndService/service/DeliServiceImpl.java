package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.*;

import com.DeliTelligenceBackEndService.entitymodel.mapper.DeliProductMapper;
import com.DeliTelligenceBackEndService.entitymodel.mapper.DeliSaleMapper;
import com.DeliTelligenceBackEndService.entitymodel.mapper.ProductMapper;
import com.DeliTelligenceBackEndService.entitymodel.repository.DeliProductRepository;
import com.DeliTelligenceBackEndService.entitymodel.repository.DeliSaleRepository;
import com.DeliTelligenceBackEndService.entitymodel.repository.EmployeeRepository;
import com.DeliTelligenceBackEndService.entitymodel.repository.ProductRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.DeliProductInputDto;
import com.DeliTelligenceBackEndService.entitymodeldto.DeliSaleInputDto;
import com.DeliTelligenceBackEndService.entitymodeldto.ProductFetchDto;
import com.DeliTelligenceBackEndService.entitymodeldto.ProductInputDto;
import org.mapstruct.Context;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.util.*;

@Service
public class DeliServiceImpl {

    private final DeliSaleRepository deliSaleRepository;
    private final DeliSaleMapper deliSaleMapper;
    private final EmployeeService employeeService;
    private final DeliProductMapper deliProductMapper;
    private final ProductService productService;
    private final ProductMapper productMapper;

    public DeliServiceImpl(DeliSaleRepository deliSaleRepository, DeliSaleMapper deliSaleMapper, EmployeeService employeeService, DeliProductMapper deliProductMapper, ProductService productService, ProductMapper productMapper) {
        this.deliSaleRepository = deliSaleRepository;
        this.deliSaleMapper = deliSaleMapper;
        this.employeeService = employeeService;
        this.deliProductMapper = deliProductMapper;
        this.productService = productService;
        this.productMapper = productMapper;
    }

    public String CreateSale(DeliSaleInputDto inputDto) {
        DeliSale deliSale = deliSaleMapper.toDeliSale(inputDto, employeeService);


//        Employee employee = employeeService.getEmployeeById(inputDto.getEmployeeId());
//        Product deliProduct = productService.getProductByIdReal(inputDto.getDeliProductInputDto().getProductInputDto().getId());
//
//        List<Ingredient> ingredients = new ArrayList<>();
//        for (ProductInputDto productInputDto: inputDto.getDeliProductInputDto().getProductInputDtos()){
//            Product product = productService.getProductByIdReal(productInputDto.getId());
//            Ingredient ingredient = new Ingredient();
//            ingredient.setProduct(product);
//            ingredients.add(ingredient);
//        }
//
//        DeliProduct deliProductHolder = new DeliProduct();
//        deliProductHolder.setDeliProduct(deliProduct);
//        deliProductHolder.setIngredients(ingredients);
//        deliProductHolder.setCombinedWeight(inputDto.getDeliProductInputDto().getCombinedWeight());
//        deliProductHolder.setPortionType(inputDto.getDeliProductInputDto().getPortionType());
//
//        for (Ingredient ingredient: ingredients){
//            ingredient.setDeliProduct(deliProductHolder);
//        }
//
//        DeliSale deliSale = new DeliSale();
//        deliSale.setDeliProduct(deliProductHolder);
//        deliSale.setSalePrice(inputDto.getSalePrice());
//        deliSale.setSaleDate(LocalDate.now());
//        deliSale.setSaleType(inputDto.getSaleType());
//        deliSale.setSaleTime(Instant.now());
//        deliSale.setQuantity(inputDto.getQuantity());
//        deliSale.setSaleWeight(inputDto.getSaleWeight());
//        deliSale.setDifferenceWeight(inputDto.getDifferenceWeight());
//        deliSale.setHandMade(inputDto.isHandMade());
//        deliSale.setWastePerSaleValue(inputDto.getWastePerSaleValue());
//        deliSale.setEmployee(employee);
//
//        deliProductHolder.setSale(deliSale);


        // Save DeliSale (this will cascade to DeliProduct)
        deliSaleRepository.save(deliSale);
        return "Done";
    }


}
