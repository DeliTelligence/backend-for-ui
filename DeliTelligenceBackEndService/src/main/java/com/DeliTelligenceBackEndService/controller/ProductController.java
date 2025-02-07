package com.DeliTelligenceBackEndService.controller;

import com.DeliTelligenceBackEndService.entitymodeldto.ProductUpdateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.productcreatedto.ProductCreateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.ProductFetchDto;
import com.DeliTelligenceBackEndService.enumformodel.ProductType;
import com.DeliTelligenceBackEndService.service.ProductService;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.UUID;

@Controller

public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @QueryMapping
    private List<ProductFetchDto> getAllProducts() {
        return productService.getAllProducts();
    }

    @QueryMapping
    private List<ProductFetchDto> getProductsByType(@Argument ProductType category) {
        return productService.getProductsByType(category);
    }

    @QueryMapping
    private ProductFetchDto getProductById(@Argument UUID id) {
        return productService.getProductById(id);
    }
    @QueryMapping
    private ProductFetchDto getProductByName(@Argument String productName) {
        return productService.getProductByName(productName);
    }

    @MutationMapping
    public String createProduct(@Argument ProductCreateDto input) {
        return productService.CreateProduct(input);
    }

    @MutationMapping
    public String editProduct(@Argument ProductUpdateDto input) {
        return productService.updateProduct(input);
    }
    @MutationMapping
    public String deleteProduct(@Argument UUID id) {
        return productService.deleteProduct(id);
    }
}
