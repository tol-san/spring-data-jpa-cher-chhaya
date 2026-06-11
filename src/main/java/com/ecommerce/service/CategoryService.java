package com.ecommerce.service;

import com.ecommerce.dto.CreateCategoryRequest;
import com.ecommerce.dto.CategoryResponse;

public interface CategoryService {
    CategoryResponse createNew(CreateCategoryRequest request);
}
