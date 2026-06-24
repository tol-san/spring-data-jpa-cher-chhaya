package com.ecommerce.feature.category;

import com.ecommerce.feature.category.dto.CategoryResponse;
import com.ecommerce.feature.category.dto.CreateCategoryRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    public CategoryResponse createNew(
            @Valid @RequestBody CreateCategoryRequest createCategoryRequest
            ) {
        return categoryService.createNew(createCategoryRequest);
    }

    @GetMapping
    public List<CategoryResponse> getAllCategory(
            @RequestParam(defaultValue = "0") Integer pageNumber,
            @RequestParam(defaultValue = "025") Integer pageSize
    ){
        return categoryService.getAllCategory();
    }

    @GetMapping("/{id}")
    public CategoryResponse getCategory(
            @PathVariable Integer id
    ){
        return categoryService.getCategoryById(id);
    }

    @GetMapping("/{id}/subcategories")
    public List<CategoryResponse> getSubCategory(
            @PathVariable Integer id
    ){
        return categoryService.getSubCategoryById(id);
    }


    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void hardDeleteCategory(
            @PathVariable Integer id
    ){
        categoryService.hardDeleteCategoryById(id);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void softDeleteCategory(
            @PathVariable Integer id
    ){
        categoryService.softDeleteCategory(id);
    }


    @PatchMapping("/update/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCategory(
            @PathVariable Integer id,
            @Valid @RequestBody CreateCategoryRequest request
    ){
        categoryService.updateCategory(id, request);
    }




}
