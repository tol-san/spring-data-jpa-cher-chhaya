package com.ecommerce.dto;

import lombok.Builder;

@Builder
public record CategoryResponse(
        Integer id,
        String name,
        Boolean isDeleted,
        String description,
        String icon,
        Integer parentCategoryId

) {
}
