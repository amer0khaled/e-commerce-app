package com.amer.microservice.messaging.kafka;

import com.amer.microservice.client.customer.dto.CustomerResponse;
import com.amer.microservice.client.product.dto.PurchaseResponse;
import com.amer.microservice.domain.Order;
import com.amer.microservice.domain.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;


public record OrderConfirmation(
        Integer orderId,
        String orderReference,
        BigDecimal totalAmount,
        PaymentMethod paymentMethod,
        CustomerResponse customer,
        List<PurchaseResponse> products
) {


    public static OrderConfirmation fromOrder(
            Order order,
            CustomerResponse customer,
            List<PurchaseResponse> products
    ) {
        return new OrderConfirmation(
                order.getId(),
                order.getReferenceNumber(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                customer,
                products
        );

    }


}
