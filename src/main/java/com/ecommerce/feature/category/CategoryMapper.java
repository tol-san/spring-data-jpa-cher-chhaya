package com.ecommerce.feature.category;

import com.ecommerce.feature.category.dto.CategoryResponse;
import com.ecommerce.feature.category.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest request);

    @Mapping(target = "parentCategoryId", source = "parentCategory.id")
    CategoryResponse mapCategoryToCategoryResponse(Category category);
}
