package com.amer.ecommerce.product.api.dto;

import java.math.BigDecimal;

public record ProductPurchaseResponse(
        Integer productId,
        String productName,
        String productDescription,
        BigDecimal unitPrice,
        Integer purchasedQuantity
) {
}
