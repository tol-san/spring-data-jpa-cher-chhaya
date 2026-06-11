package com.ecommerce.dto;

import com.ecommerce.domain.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record CreateCategoryRequest(

        @NotBlank(message = "Name is required")
        @Size(max = 255)
        String name,

        String description,

        @Size(max = 255)
        String icon,

        @Positive
        Integer parentCategory
) {
}
