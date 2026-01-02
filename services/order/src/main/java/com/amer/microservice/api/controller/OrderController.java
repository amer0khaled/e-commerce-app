package com.amer.microservice.api.controller;

import com.amer.microservice.api.dto.OrderRequest;
import com.amer.microservice.api.dto.OrderResponse;
import com.amer.microservice.service.impl.OrderServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServiceImpl orderServiceImpl;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(
            @RequestBody @Valid OrderRequest request
    ) {
        return ResponseEntity.ok(orderServiceImpl.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> findAll() {
        return ResponseEntity.ok(orderServiceImpl.findAll());
    }

    @GetMapping("/{order_id}")
    public ResponseEntity<OrderResponse> findById(
            @PathVariable Integer orderId
    ) {
        return ResponseEntity.ok(orderServiceImpl.findById(orderId));
    }

}
