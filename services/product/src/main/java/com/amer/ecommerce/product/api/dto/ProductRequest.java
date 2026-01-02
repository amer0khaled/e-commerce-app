package com.amer.ecommerce.product.api.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

public record ProductRequest(
        @NotNull(message = "Product name is required")
        String name,
        @NotNull(message = "Product description is required")
        String description,
        @NotNull(message = "Product available quantity is required")
        @Positive(message = "Product available quantity must be greater than zero")
        Integer availableQuantity,
        @NotNull(message = "Product price is required")
        @PositiveOrZero(message = "Product price must be greater than or equal to zero")
        BigDecimal price,
        @NotNull(message = "Product category is required")
        @Positive(message = "Product category must be greater than zero")
        Integer categoryId
) {
}
