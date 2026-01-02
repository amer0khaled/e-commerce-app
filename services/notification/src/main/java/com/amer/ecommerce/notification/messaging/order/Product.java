package com.amer.ecommerce.notification.messaging.order;

import java.math.BigDecimal;

public record Product(
        Integer productId,
        String productName,
        String productDescription,
        BigDecimal unitPrice,
        Integer purchasedQuantity
) {
}
