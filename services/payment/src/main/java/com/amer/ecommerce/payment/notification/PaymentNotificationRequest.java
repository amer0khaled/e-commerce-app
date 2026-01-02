package com.amer.ecommerce.payment.notification;

import com.amer.ecommerce.payment.api.dto.PaymentRequest;
import com.amer.ecommerce.payment.domain.PaymentMethod;

import java.math.BigDecimal;

public record PaymentNotificationRequest(
        String transactionId,
        String orderReference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerFirstName,
        String customerLastName,
        String customerEmail

) {

    public static PaymentNotificationRequest toNotificationRequest(PaymentRequest request, String transactionId) {
        return new PaymentNotificationRequest(
                transactionId,
                request.orderReference(),
                request.amount(),
                request.paymentMethod(),
                request.customer().firstName(),
                request.customer().lastName(),
                request.customer().email()
        );
    }
}
