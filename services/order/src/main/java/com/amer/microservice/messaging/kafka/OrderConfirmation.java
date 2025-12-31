package com.amer.microservice.messaging.kafka;

import com.amer.microservice.client.customer.dto.CustomerResponse;
import com.amer.microservice.domain.PaymentMethod;
import com.amer.microservice.client.product.dto.PurchaseResponse;

import java.math.BigDecimal;
import java.util.List;


public record OrderConfirmation(
        String orderId,
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {
}
