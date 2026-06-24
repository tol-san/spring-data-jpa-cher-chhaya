package com.ecommerce.feature.product.dto;

import com.ecommerce.feature.category.dto.CategorySnippetResponse;
import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String code,
        String slug,
        String name,
        String description,
        String thumbnail,
        BigDecimal unitPrice,
        Integer quantity,
        Boolean isAvailable,
        Boolean isDeleted,
        CategorySnippetResponse category
) {
}
