package com.ecommerce.feature.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record DeleteOrderRequest(
        @NotNull(message = "isDeleted is required.")
        Boolean isDeleted
) {
}
