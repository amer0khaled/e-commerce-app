package com.amer.ecommerce.notification.strategy.impl;

import com.amer.ecommerce.notification.domain.Notification;
import com.amer.ecommerce.notification.domain.NotificationType;
import com.amer.ecommerce.notification.messaging.email.EmailService;
import com.amer.ecommerce.notification.messaging.payment.PaymentConfirmation;
import com.amer.ecommerce.notification.strategy.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class PaymentNotificationStrategy implements NotificationStrategy {

    private final EmailService emailService;

    @Override
    public boolean supports(NotificationType notificationType) {
        return notificationType == NotificationType.PAYMENT_CONFIRMATION;
    }

    @Override
    public void send(Notification notification) {
        PaymentConfirmation payload =
                (PaymentConfirmation) notification.getPayload();

        Map<String, Object> variables = Map.of(
                "customerName", payload.customerFirstName() + " " + payload.customerLastName(),
                "amount", payload.amount(),
                "orderReference", payload.orderReference()
        );

        emailService.send(
                payload.customerEmail(),
                "Payment Successful",
                "payment-confirmation.html",
                variables
        );
    }
}
