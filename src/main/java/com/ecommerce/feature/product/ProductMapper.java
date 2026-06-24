package com.ecommerce.feature.product;

import com.ecommerce.feature.product.dto.CreateProductRequest;
import com.ecommerce.feature.product.dto.ProductResponse;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    Product toEntity(CreateProductRequest request);
    ProductResponse toDto(Product product);
}
