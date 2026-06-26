package com.ecommerce.feature.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record OrderLineDto(
        @NotBlank(message = "Code is required.")
        String code,
        @Positive
        @NotBlank(message = "Quantity is require.")
        Integer quantity,

        @Positive
        BigDecimal unitPrice) {
}
