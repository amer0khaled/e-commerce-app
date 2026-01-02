package com.amer.ecommerce.notification.messaging.payment;

import java.math.BigDecimal;

public record PaymentConfirmation(
        String transactionId,
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail
) {
}
