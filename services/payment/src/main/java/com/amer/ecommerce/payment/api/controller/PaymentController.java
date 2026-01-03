package com.amer.ecommerce.payment.api.controller;

import com.amer.ecommerce.payment.api.dto.PaymentRequest;
import com.amer.ecommerce.payment.api.dto.PaymentResponse;
import com.amer.ecommerce.payment.service.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping
    public ResponseEntity<PaymentResponse> createPayment(
            @RequestBody @Valid PaymentRequest request
    ) {
        return ResponseEntity.ok(paymentService.createPayment(request));
    }

}
