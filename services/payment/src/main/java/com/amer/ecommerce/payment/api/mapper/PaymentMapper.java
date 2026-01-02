package com.amer.ecommerce.payment.api.mapper;

import com.amer.ecommerce.payment.api.dto.PaymentRequest;
import com.amer.ecommerce.payment.api.dto.PaymentResponse;
import com.amer.ecommerce.payment.domain.Payment;
import com.amer.ecommerce.payment.domain.PaymentStatus;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        return Payment.builder()
                .amount(request.amount())
                .paymentMethod(request.paymentMethod())
                .orderId(request.orderId())
                .status(PaymentStatus.PENDING)
                .build();
    }

    public PaymentResponse fromPayment(Payment persistedPayment) {
        return new PaymentResponse(
                persistedPayment.getId(),
                persistedPayment.getAmount(),
                persistedPayment.getPaymentMethod(),
                persistedPayment.getOrderId(),
                persistedPayment.getTransactionId()
        );
    }
}
