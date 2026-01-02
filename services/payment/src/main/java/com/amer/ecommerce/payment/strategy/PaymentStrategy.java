package com.amer.ecommerce.payment.strategy;

import com.amer.ecommerce.payment.domain.Payment;
import com.amer.ecommerce.payment.domain.PaymentMethod;

public interface PaymentStrategy {
    PaymentMethod getSupportedMethod();

    PaymentResult pay(Payment payment);
}
