package com.amer.microservice.service.order;

import com.amer.microservice.api.dto.OrderRequest;
import com.amer.microservice.api.dto.OrderResponse;
import com.amer.microservice.client.customer.CustomerClient;
import com.amer.microservice.exception.BussinessException;
import com.amer.microservice.messaging.kafka.OrderConfirmation;
import com.amer.microservice.messaging.kafka.OrderProducer;
import com.amer.microservice.api.dto.OrderLineRequest;
import com.amer.microservice.api.mapper.OrderMapper;
import com.amer.microservice.repository.order.OrderRepository;
import com.amer.microservice.service.orderline.OrderLineService;
import com.amer.microservice.client.product.ProductClient;
import com.amer.microservice.client.product.dto.PurchaseRequest;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl {

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderLineService orderLineService;
    private final OrderProducer orderProducer;

    @Transactional
    public Integer createOrder(OrderRequest request) {
        // Validate customer existence(openFeign)
        var customer = customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BussinessException("Cannot Create Order:: Customer Not Found With ID:: " + request.customerId()));

        // Validate products -> product service
        var purchasedProducts = this.productClient.purchaseProducts(request.products());
        var order = orderRepository.save(orderMapper.toOrder(request, purchasedProducts));

        for (PurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // toDo start payment process

        // send the order confirmation notification to a kafka topic
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.customerId(),
                        order.getReferenceNumber(),
                        null,
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();
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
