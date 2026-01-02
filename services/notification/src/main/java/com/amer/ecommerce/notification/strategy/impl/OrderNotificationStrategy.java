package com.amer.ecommerce.notification.strategy.impl;

import com.amer.ecommerce.notification.domain.Notification;
import com.amer.ecommerce.notification.domain.NotificationType;
import com.amer.ecommerce.notification.messaging.email.EmailService;
import com.amer.ecommerce.notification.messaging.order.OrderConfirmation;
import com.amer.ecommerce.notification.strategy.NotificationStrategy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class OrderNotificationStrategy implements NotificationStrategy {

    private final EmailService emailService;

    @Override
    public boolean supports(NotificationType notificationType) {
        return notificationType == NotificationType.ORDER_CONFIRMATION;
    }

    @Override
    public void send(Notification notification) {
        OrderConfirmation payload =
                (OrderConfirmation) notification.getPayload();

        Map<String, Object> variables = Map.of(
                "customerName", payload.customerResponse().firstName() + " " + payload.customerResponse().lastName(),
                "totalAmount", payload.amount(),
                "orderReference", payload.orderReference(),
                "products", payload.products()
        );

        emailService.send(
                payload.customerResponse().email(),
                "Order Confirmation",
                "order-confirmation.html",
                variables
        );
    }
}
