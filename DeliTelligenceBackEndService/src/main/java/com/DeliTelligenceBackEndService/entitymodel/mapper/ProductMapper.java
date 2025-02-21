package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.helper.EditCustomMapping;
import com.DeliTelligenceBackEndService.entitymodel.*;
import com.DeliTelligenceBackEndService.entitymodel.repository.ProductRepository;
import com.DeliTelligenceBackEndService.entitymodeldto.ProductUpdateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.productcreatedto.ProductCreateDto;
import com.DeliTelligenceBackEndService.entitymodeldto.ProductFetchDto;
import com.DeliTelligenceBackEndService.entitymodeldto.ProductInputDto;
import com.DeliTelligenceBackEndService.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.mapstruct.*;

import java.util.Base64;

@Mapper(componentModel = "spring", uses = {StandardWeightProductMapper.class, StandardWeightMapper.class, EditCustomMapping.class})
public interface ProductMapper {

    @Mapping(target = "productImageDto", source = "productImage")
    ProductFetchDto toProductFetchDto(Product product);

    @Mapping(target = "productImage", source = "productImageDto")
    Product toProduct(ProductFetchDto productFetchDto);

    @Mapping(target = "id", source = "id")
    Product toProduct(ProductInputDto productInputDto);

    @Mapping(target = "productDeleted", constant = "false")
    @Mapping(target = "productImage", source = "productImageDto")
    Product toProduct(ProductCreateDto productCreateDto);



    @AfterMapping
    default void setProductReferenceAndStandardWeight(@MappingTarget Product product) {
        if (product.getStandardWeightProducts() != null) {
            for (StandardWeightProduct swp : product.getStandardWeightProducts()) {
                swp.setProduct(product); // Set the product reference

            }
        }
    }
    @BeforeMapping
    default void loadProduct(ProductUpdateDto productUpdateDto, @MappingTarget Product product, @Context ProductRepository productRepository) {
        Product loadedProduct = productRepository.findById(productUpdateDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found for this id :: " + productUpdateDto.getId()));

        // Copy values from the loaded product
        product.setProductName(loadedProduct.getProductName());
        product.setProductDescription(loadedProduct.getProductDescription());
        product.setProductType(loadedProduct.getProductType());
        product.setProductImage(loadedProduct.getProductImage());
        product.setProductPrice(loadedProduct.getProductPrice());
        product.setStandardWeightProducts(loadedProduct.getStandardWeightProducts());
        product.setAdjustments(loadedProduct.getAdjustments());
        product.setDeliProducts(loadedProduct.getDeliProducts());
        product.setIngredients(loadedProduct.getIngredients());
    }

    @Mapping(target = "productDeleted", constant = "false")
    @Mapping(target = "productName", conditionExpression = "java(isDifferentOrNull(product.getProductName(), productUpdateDto.getProductName()))", source = "productName")
    @Mapping(target = "productDescription", conditionExpression = "java(isDifferentOrNull(product.getProductDescription(), productUpdateDto.getProductDescription()))", source = "productDescription")
    @Mapping(target = "productPrice", conditionExpression = "java(isDifferentOrNull(product.getProductPrice(), productUpdateDto.getProductPrice()))", source = "productPrice")
    @Mapping(target = "productType", conditionExpression = "java(isDifferentOrNull(product.getProductType(), productUpdateDto.getProductType()))", source = "productType")
    @Mapping(target = "productImage", conditionExpression = "java(isDifferentOrNull(product.getProductImage(), productUpdateDto.getProductImageDto() != null ? productUpdateDto.getProductImageDto().getBytes() : null))", source = "productImageDto")
    Product toProduct(ProductUpdateDto productUpdateDto, @Context ProductRepository productRepository);


    default boolean isDifferentOrNull(Object entityValue, Object dtoValue) {
        return EditCustomMapping.isDifferentOrNull(entityValue, dtoValue);
    }


    default Product map(String productName, @Context ProductService productService) {
        if (productName == null) {
            return null;
        }
        return productService.getProductByNameReal(productName);
    }

    // Image mapping
    default String map(byte[] productImage) {
        return productImage == null ? null : Base64.getEncoder().encodeToString(productImage);
    }

    default byte[] map(String productImageDto) {
        if (productImageDto == null) {
            return null;
        }
        // Remove "data:image/*;base64," prefix if it exists
        if (productImageDto.startsWith("data:image")) {
            int commaIndex = productImageDto.indexOf(',');
            if (commaIndex > 0) {
                productImageDto = productImageDto.substring(commaIndex + 1);
            }
        }
        try {
            return Base64.getDecoder().decode(productImageDto);
        } catch (IllegalArgumentException e) {
            // Handle invalid base64 input appropriately
            throw new IllegalArgumentException("Invalid base64 input", e);
        }
    }


}
