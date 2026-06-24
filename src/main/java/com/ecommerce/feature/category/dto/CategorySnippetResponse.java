package com.ecommerce.feature.category.dto;

import lombok.Builder;

@Builder
public record CategorySnippetResponse(
        String id,
        String name
) {
}
