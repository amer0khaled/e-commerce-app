package com.amer.ecommerce.payment.api.dto;

import com.amer.ecommerce.payment.domain.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerDto customer
) {
}
