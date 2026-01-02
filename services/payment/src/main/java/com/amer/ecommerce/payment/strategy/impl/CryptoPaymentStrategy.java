package com.amer.ecommerce.payment.strategy.impl;

import com.amer.ecommerce.payment.domain.Payment;
import com.amer.ecommerce.payment.domain.PaymentMethod;
import com.amer.ecommerce.payment.strategy.PaymentResult;
import com.amer.ecommerce.payment.strategy.PaymentStrategy;

import java.util.UUID;

public class CryptoPaymentStrategy implements PaymentStrategy {

    @Override
    public PaymentMethod getSupportedMethod() {
        return PaymentMethod.CRYPTO;
    }

    @Override
    public PaymentResult pay(Payment payment) {
        try {
            String txId = UUID.randomUUID().toString();
            return PaymentResult.success(txId);
        } catch (Exception e) {
            return PaymentResult.failure("Crypto Payment cannot be processed at the moment.");
        }
    }
}
