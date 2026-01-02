package com.amer.microservice.client.payment.dto;

import com.amer.microservice.api.dto.OrderRequest;
import com.amer.microservice.client.customer.dto.CustomerResponse;
import com.amer.microservice.domain.PaymentMethod;

import java.math.BigDecimal;

public record PaymentRequest(
        BigDecimal amount,
        PaymentMethod paymentMethod,
        Integer orderId,
        String orderReference,
        CustomerResponse customer
) {
    public static PaymentRequest makePaymentRequest(
            OrderRequest request,
            Integer orderId,
            CustomerResponse customer
    ) {

        return new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                orderId,
                request.referenceNumber(),
                customer
        );

    }

}
