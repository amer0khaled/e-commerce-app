package com.amer.ecommerce.notification.messaging.order;

import com.amer.ecommerce.notification.messaging.payment.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderConfirmation(
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        CustomerResponse customerResponse,
        List<Product> products
) {
}
