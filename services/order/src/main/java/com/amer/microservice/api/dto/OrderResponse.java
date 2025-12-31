package com.amer.microservice.api.dto;

import com.amer.microservice.domain.PaymentMethod;

import java.math.BigDecimal;

public record OrderResponse(
        Integer orderId,
        String referenceNumber,
        String customerId,
        BigDecimal amount,
        PaymentMethod paymentMethod
) {
}
