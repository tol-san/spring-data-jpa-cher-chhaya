package com.ecommerce.feature.product.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;

public record CreateProductRequest(
        @NotBlank(message = "Name is require")
        @Size(max = 255)
        String name,

        @Size(max = 500)
        String description,

        String thumbnail,

        @NotNull(message = "Unit price is required")
        @Min(0)
        BigDecimal unitPrice,

        @NotNull(message = "Quantity is required")
        @Min(0)
        Integer quantity,

        @NotNull(message = "Category IS is required")
        @Positive
        Integer categoryId
) {
}
