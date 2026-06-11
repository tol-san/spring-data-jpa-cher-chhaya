package com.ecommerce.service;

import com.ecommerce.dto.CreateCategoryRequest;
import com.ecommerce.dto.CategoryResponse;
import com.ecommerce.repository.CategoryRepository;

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
