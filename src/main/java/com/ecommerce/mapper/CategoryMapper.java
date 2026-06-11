package com.ecommerce.mapper;

import com.ecommerce.domain.Category;
import com.ecommerce.dto.CategoryResponse;
import com.ecommerce.dto.CreateCategoryRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "parentCategory", ignore = true)
    @Mapping(target = "isDeleted", ignore = true)
    Category mapCreateCategoryRequestToCategory(CreateCategoryRequest request);

    CategoryResponse mapCategoryToCategoryResponse(Category category);
}
