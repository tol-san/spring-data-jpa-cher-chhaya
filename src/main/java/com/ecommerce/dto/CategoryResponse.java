package com.ecommerce.dto;

import com.ecommerce.domain.Category;
import lombok.Builder;

@Builder
public record CategoryResponse(
        Integer id,
        String name,
        Boolean isDeleted,
        String description,
        String icon,
        CategoryResponse parentCategory

) {
}
