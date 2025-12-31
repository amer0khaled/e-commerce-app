package com.amer.ecommerce.product;

import java.math.BigDecimal;

public record ProductResponse(
        Integer id,
        String name,
        String description,
        Integer availableQuantity,
        BigDecimal price,
        Integer categoryId,
        String categoryName,
        String productDescription
) {
}
