package com.amer.microservice.api.mapper;

import com.amer.microservice.api.dto.OrderRequest;
import com.amer.microservice.api.dto.OrderResponse;
import com.amer.microservice.domain.Order;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class OrderMapper {

    public Order toOrder(OrderRequest request) {
        return Order.create(
                request.customerId(),
                request.paymentMethod(),
                generateReference()
        );
    }

    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReferenceNumber(),
                order.getCustomerId(),
                order.getTotalAmount(),
                order.getPaymentMethod()
        );
    }


    private String generateReference() {
        return UUID.randomUUID().toString();
    }

}
