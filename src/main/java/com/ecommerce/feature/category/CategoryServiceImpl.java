package com.ecommerce.feature.category;

import com.ecommerce.feature.category.dto.CreateCategoryRequest;
import com.ecommerce.feature.category.dto.CategoryResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

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

    @Override
    public List<CategoryResponse> getAllCategory() {
        var categories = categoryRepository.findAll();
        return categories.stream().map(categoryMapper::mapCategoryToCategoryResponse).toList();
    }

    @Override
    public CategoryResponse getCategoryById(Integer id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found"
                ));

        return categoryMapper.mapCategoryToCategoryResponse(category);
    }

    @Override
    public List<CategoryResponse> getSubCategoryById(Integer parentId) {

        if (!categoryRepository.existsById(parentId)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Parent category not found with id: " + parentId
            );
        }

        var categories = categoryRepository.findByParentCategoryIdAndIsDeletedFalse(parentId);
        return categories.stream()
                .map(categoryMapper::mapCategoryToCategoryResponse).toList();

    }

    @Override
    public void hardDeleteCategoryById(Integer id) {
        if (!categoryRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Parent category not found with id: " + id
            );
        }

        categoryRepository.deleteById(id);
    }

    @Override
    public void softDeleteCategory(Integer id) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found with id: " + id
                ));

        category.setIsDeleted(true);

        categoryRepository.save(category);

    }

    @Override
    public void updateCategory(Integer id, CreateCategoryRequest request) {
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Category not found with id: " + id
                ));

        category.setName(request.name());
        category.setDescription(request.description());
        category.setIcon(request.icon());

        categoryRepository.save(category);

    }
}
