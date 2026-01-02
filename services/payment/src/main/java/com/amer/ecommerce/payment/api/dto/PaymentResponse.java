package com.amer.ecommerce.payment.api.dto;

import com.amer.ecommerce.payment.domain.PaymentMethod;

import java.math.BigDecimal;

public record PaymentResponse(
        Integer id,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String transactionId
) {
}
