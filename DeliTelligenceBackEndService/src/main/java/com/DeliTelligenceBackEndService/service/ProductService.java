package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.Product;
import com.DeliTelligenceBackEndService.entitymodel.mapper.ProductMapper;
import com.DeliTelligenceBackEndService.entitymodel.mapper.StandardWeightMapper;
import com.DeliTelligenceBackEndService.entitymodel.mapper.StandardWeightProductMapper;
import com.DeliTelligenceBackEndService.entitymodel.repository.ProductRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.ProductFetchDto;
import com.DeliTelligenceBackEndService.enumformodel.ProductType;
import com.DeliTelligenceBackEndService.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service

public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final StandardWeightProductMapper standardWeightProductMapper;
    private final StandardWeightMapper standardWeightMapper;


    public ProductService(ProductRepository productRepository,
                          ProductMapper productMapper,
                          StandardWeightProductMapper standardWeightProductMapper,
                          StandardWeightMapper standardWeightMapper) {
        this.productRepository = productRepository;
        this.productMapper = productMapper;
        this.standardWeightProductMapper = standardWeightProductMapper;
        this.standardWeightMapper = standardWeightMapper;
    }


    public List<ProductFetchDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductFetchDto> productFetchDtos = products.stream()
                .map(productMapper::toProductFetchDto)
                .collect(Collectors.toList());
        return productFetchDtos;
    }

    public ProductFetchDto getProductById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id));

        ProductFetchDto productFetchDto = new ProductFetchDto();

        productFetchDto = productMapper.toProductFetchDto(product);


        return productFetchDto;

    }

    public Product getProductByIdReal(UUID id){
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id));

        return product;
    }

    public ProductFetchDto getProductByName(String productName) {
        Product product = productRepository.getProductByProductName(productName).orElseThrow(() ->
                new ResourceNotFoundException("Product", "ProductName", productName));

        ProductFetchDto productFetchDto = new ProductFetchDto();
        productFetchDto = productMapper.toProductFetchDto(product);

        return productFetchDto;

    }

    public Product getProductByNameHelper(String productName) {
        Product product = productRepository.getProductByProductName(productName).orElseThrow(() ->
                new ResourceNotFoundException("Product", "ProductName", productName));

        return product;

    }

    public List<ProductFetchDto> getProductsByType(ProductType productType) {
        List<Product> products = productRepository.findAll();
        List<ProductFetchDto> productFetchDtos = new ArrayList<>();
        for (Product product : products) {
            productFetchDtos.add(productMapper.toProductFetchDto(product));

        }
        return productFetchDtos;    }
}
