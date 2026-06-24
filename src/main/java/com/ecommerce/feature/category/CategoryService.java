package com.ecommerce.feature.category;

import com.ecommerce.feature.category.dto.CreateCategoryRequest;
import com.ecommerce.feature.category.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {
    CategoryResponse createNew(CreateCategoryRequest request);
    List<CategoryResponse> getAllCategory();
    CategoryResponse getCategoryById(Integer id);
    List<CategoryResponse> getSubCategoryById(Integer parentId);
    void hardDeleteCategoryById(Integer id);
    void softDeleteCategory(Integer id);
    void updateCategory(Integer id, CreateCategoryRequest request);

}
