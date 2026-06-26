package com.ecommerce.feature.order.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record SetPaymentStatusRequest(
        @NotNull(message = "Status is required.")
        Boolean status
) {
}
