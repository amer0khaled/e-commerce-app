package com.amer.microservice.order;

import com.amer.microservice.api.dto.order.OrderRequest;
import com.amer.microservice.api.dto.order.OrderResponse;
import com.amer.microservice.domain.Order;
import com.amer.microservice.domain.OrderLine;
import com.amer.microservice.client.product.dto.PurchaseResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class OrderMapper {
    public Order toOrder(OrderRequest request, List<PurchaseResponse> purchasedProducts) {
        Order order = Order.builder()
                .customerId(request.customerId())
                .paymentMethod(request.paymentMethod())
                .referenceNumber(generateReference())
                .build();

        purchasedProducts.forEach(product -> {
            OrderLine orderLine = OrderLine.builder()
                    .productId(product.productId())
                    .quantity(product.quantity())
                    .unitPrice(product.price())
                    .build();
            order.getOrderLines().add(orderLine);
        });

        return order;
    }

    private String generateReference() {
        return UUID.randomUUID().toString();
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
}
