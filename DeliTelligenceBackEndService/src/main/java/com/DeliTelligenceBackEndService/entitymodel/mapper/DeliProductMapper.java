package com.DeliTelligenceBackEndService.entitymodel.mapper;

import com.DeliTelligenceBackEndService.entitymodel.DeliProduct;

import com.DeliTelligenceBackEndService.entitymodel.Ingredient;
import com.DeliTelligenceBackEndService.entitymodel.Product;
import com.DeliTelligenceBackEndService.entitymodeldto.DeliProductInputDto;

import com.DeliTelligenceBackEndService.entitymodeldto.ProductInputDto;
import org.mapstruct.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {ProductMapper.class})
public interface DeliProductMapper {

    @Mapping(target = "deliProduct", source = "productInputDto")
    @Mapping(target = "ingredients", source = "productInputDtos")
    @Mapping(target = "combinedWeight", source = "combinedWeight")
    @Mapping(target = "portionType", source = "portionType")
    @Mapping(target = "sale", ignore = true)
    DeliProduct toDeliProduct(DeliProductInputDto deliProductInputDto);

    default List<Ingredient> map(List<ProductInputDto> productInputDtos) {
        List<Ingredient> ingredients = new ArrayList<>();
        for (ProductInputDto productInputDto : productInputDtos) {
            Product product = new Product();
            product.setId(productInputDto.getId());

            Ingredient ingredient = new Ingredient();
            ingredient.setProduct(product);

            ingredients.add(ingredient);
        }
        return ingredients;
    }

    @AfterMapping
    default void setDeliProductOnIngredients(@MappingTarget DeliProduct deliProduct) {
        if (deliProduct.getIngredients() != null) {
            for (Ingredient ingredient : deliProduct.getIngredients()) {
                ingredient.setDeliProduct(deliProduct);
            }
        }
    }
}


