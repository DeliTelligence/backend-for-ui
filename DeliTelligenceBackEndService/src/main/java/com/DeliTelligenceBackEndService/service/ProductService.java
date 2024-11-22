package com.DeliTelligenceBackEndService.service;

import com.DeliTelligenceBackEndService.entitymodel.Product;
import com.DeliTelligenceBackEndService.entitymodel.repository.ProductRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.ProductFetchDto;
import com.DeliTelligenceBackEndService.enumformodel.ProductType;
import com.DeliTelligenceBackEndService.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service

public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductFetchDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductFetchDto> productFetchDtos = new ArrayList<>();
        for (Product product : products) {
            ProductFetchDto productFetchDto = new ProductFetchDto();
            productFetchDto.setProductName(product.getProductName());
            productFetchDto.setProductType(product.getProductType().toString());
            productFetchDto.setStandardWeight(product.getStandardWeight());
            productFetchDto.setProductPrice(product.getProductPrice());
            String base64Image = ProductFetchDto.encodeImage(product.getProductImage());
            productFetchDto.setProductImage(base64Image);
            productFetchDtos.add(productFetchDto);
        }
        return productFetchDtos;
    }

    public ProductFetchDto getProductById(UUID id) {
        Product product = productRepository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product", "Id", id));

        ProductFetchDto productFetchDto = new ProductFetchDto();
        productFetchDto.setProductName(product.getProductName());
        productFetchDto.setProductType(product.getProductType().toString());
        productFetchDto.setStandardWeight(product.getStandardWeight());
        productFetchDto.setProductPrice(product.getProductPrice());
        String base64Image = ProductFetchDto.encodeImage(product.getProductImage());
        productFetchDto.setProductImage(base64Image);

        return productFetchDto;

    }

    public ProductFetchDto getProductByName(String productName) {
        Product product = productRepository.getProductByProductName(productName).orElseThrow(() ->
                new ResourceNotFoundException("Product", "ProductName", productName));

        ProductFetchDto productFetchDto = new ProductFetchDto();
        productFetchDto.setProductName(product.getProductName());
        productFetchDto.setProductType(product.getProductType().toString());
        productFetchDto.setStandardWeight(product.getStandardWeight());
        productFetchDto.setProductPrice(product.getProductPrice());
        String base64Image = ProductFetchDto.encodeImage(product.getProductImage());
        productFetchDto.setProductImage(base64Image);

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
            ProductFetchDto productFetchDto = new ProductFetchDto();
            productFetchDto.setProductName(product.getProductName());
            productFetchDto.setProductType(product.getProductType().toString());
            productFetchDto.setStandardWeight(product.getStandardWeight());
            productFetchDto.setProductPrice(product.getProductPrice());
            String base64Image = ProductFetchDto.encodeImage(product.getProductImage());
            productFetchDto.setProductImage(base64Image);
            productFetchDtos.add(productFetchDto);
        }
        return productFetchDtos;    }
}
