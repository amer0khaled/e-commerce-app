package com.amer.microservice.service;

import com.amer.microservice.api.dto.OrderRequest;
import com.amer.microservice.api.dto.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(OrderRequest request);

    List<OrderResponse> findAll();

    OrderResponse findById(Integer orderId);

}
