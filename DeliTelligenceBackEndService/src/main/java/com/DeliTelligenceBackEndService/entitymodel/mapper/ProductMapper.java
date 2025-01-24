package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.entitymodel.Ingredient;
import com.DeliTelligenceBackEndService.entitymodel.Product;
import com.DeliTelligenceBackEndService.entitymodeldto.ProductFetchDto;
import com.DeliTelligenceBackEndService.entitymodeldto.ProductInputDto;
import com.DeliTelligenceBackEndService.service.ProductService;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.mapstruct.Context;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.*;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring", uses = {StandardWeightProductMapper.class})
public interface ProductMapper {

    @Mapping(target = "productImageDto", source = "productImage")
    ProductFetchDto toProductFetchDto(Product product);

    @Mapping(target = "productImage", source = "productImageDto")
    Product toProduct(ProductFetchDto productFetchDto);

    @Mapping(target = "id", source = "id")
    Product toProduct(ProductInputDto productInputDto);

    ProductInputDto toProductInputDto(Product product);


    default Product map(String productName, @Context ProductService productService) {
        if (productName == null) {
            return null;
        }
        return (productService.getProductByNameReal(productName));

    }

    // Image mapping
    default String map(byte[] productImage) {
        return productImage == null ? null : Base64.getEncoder().encodeToString(productImage);
    }

    default byte[] map(String productImageDto) {
        return productImageDto == null ? null : Base64.getDecoder().decode(productImageDto);
    }
}

