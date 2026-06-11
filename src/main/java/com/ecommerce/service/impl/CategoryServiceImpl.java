package com.ecommerce.service.impl;

import com.ecommerce.domain.Category;
import com.ecommerce.dto.CreateCategoryRequest;
import com.ecommerce.dto.CategoryResponse;
import com.ecommerce.mapper.CategoryMapper;
import com.ecommerce.repository.CategoryRepository;
import com.ecommerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;
    @Override
    public CategoryResponse createNew(CreateCategoryRequest request) {
        log.info("createNew {}", request);


        boolean isCategoryNameExist = categoryRepository.existsByName(request.name());

        if (isCategoryNameExist)
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Category has already been used."
            );

        Category parentCategory = null;


        if (request.parentCategory() != null) {
             parentCategory = categoryRepository.findById(request.parentCategory())
                    .orElseThrow(() -> new ResponseStatusException(
                            HttpStatus.NOT_FOUND,
                            "Parent category has not been found."
                    ));
        }


        var category = categoryMapper.mapCreateCategoryRequestToCategory(request);
        category.setIsDeleted(false);
        category.setParentCategory(parentCategory);

        category = categoryRepository.save(category);


        return categoryMapper.mapCategoryToCategoryResponse(category);

    }
}
