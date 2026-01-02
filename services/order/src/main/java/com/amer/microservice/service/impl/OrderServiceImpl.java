package com.amer.microservice.service.order.impl;

import com.amer.microservice.api.dto.OrderRequest;
import com.amer.microservice.api.dto.OrderResponse;
import com.amer.microservice.api.mapper.OrderMapper;
import com.amer.microservice.client.customer.CustomerClient;
import com.amer.microservice.client.product.ProductClient;
import com.amer.microservice.exception.BussinessException;
import com.amer.microservice.messaging.kafka.OrderConfirmation;
import com.amer.microservice.messaging.kafka.OrderProducer;
import com.amer.microservice.repository.order.OrderRepository;
import com.amer.microservice.service.order.OrderService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderProducer orderProducer;

    @Transactional
    public Integer createOrder(OrderRequest request) {
        // Validate customer (openFeign)
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BussinessException(
                        "Cannot Create Order:: Customer Not Found With ID:: " + request.customerId()
                        )
                );

        // Validate products -> product service
        var purchasedProducts = this.productClient.purchaseProducts(request.products());

        // create order (aggregate root)
        var order = orderMapper.toOrder(request);
        // add order lines to the order
        purchasedProducts.forEach(
                product -> order.addOrderLine(
                        product.productId(),
                        product.quantity(),
                        product.price()
                )
        );
        // persist the aggregate (Order + OrderLines inside the Order)
        var persistedOrderId = orderRepository.save(order).getId();

        // toDo start payment process

        // send the order confirmation notification to a kafka topic
        orderProducer.sendOrderConfirmation(
                OrderConfirmation.fromOrder(
                        order,
                        customer,
                        purchasedProducts
                )
        );

        return persistedOrderId;
    }

    public List<OrderResponse> findAll() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .toList();
    }

    public OrderResponse findById(Integer orderId) {
        return orderRepository.findById(orderId)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException("Order Not Found With ID:: " + orderId));
    }

}
