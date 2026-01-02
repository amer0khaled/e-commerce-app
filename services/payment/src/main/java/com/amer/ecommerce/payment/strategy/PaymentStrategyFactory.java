package com.amer.ecommerce.payment.strategy.impl;

import com.amer.ecommerce.payment.domain.PaymentMethod;
import com.amer.ecommerce.payment.strategy.PaymentStrategy;
import org.springframework.stereotype.Component;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Component
public class PaymentStrategyFactory {

    private final Map<PaymentMethod, PaymentStrategy> paymentStrategies;

    public PaymentStrategyFactory(List<PaymentStrategy> strategyList) {
        this.paymentStrategies = new EnumMap<>(PaymentMethod.class);
        strategyList.forEach(
                strategy -> paymentStrategies.put(strategy.getSupportedMethod(), strategy)
        );
    }

    public PaymentStrategy getStrategy(PaymentMethod method) {
        PaymentStrategy strategy = paymentStrategies.get(method);
        if (strategy == null) {
            throw new IllegalArgumentException(
                    "No payment strategy found for method: " + method
            );
        }
        return strategy;
    }


}
