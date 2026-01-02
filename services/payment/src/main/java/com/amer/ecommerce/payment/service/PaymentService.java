package com.amer.ecommerce.payment.service;

import com.amer.ecommerce.payment.api.dto.PaymentRequest;
import com.amer.ecommerce.payment.api.dto.PaymentResponse;

public interface PaymentService {
    PaymentResponse createPayment(PaymentRequest request);
}
