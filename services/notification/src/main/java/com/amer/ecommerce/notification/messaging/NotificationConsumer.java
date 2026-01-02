package com.amer.ecommerce.notification.messaging;

import com.amer.ecommerce.notification.domain.Notification;
import com.amer.ecommerce.notification.domain.NotificationStatus;
import com.amer.ecommerce.notification.domain.NotificationType;
import com.amer.ecommerce.notification.messaging.order.OrderConfirmation;
import com.amer.ecommerce.notification.messaging.payment.PaymentConfirmation;
import com.amer.ecommerce.notification.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationRepository notificationRepository;


    @Transactional
    @KafkaListener(
            topics = "payment-topic",
            groupId = "notification-payment-group"
    )
    public void consumePaymentConfirmation(PaymentConfirmation message) {
        log.info("Consumed payment confirmation: {}", message);
        if (notificationRepository.existsByExternalReference(message.transactionId())) {
            log.warn("Payment notification already exists for transactionId={}", message.transactionId());
            return;
        }

        Notification notification = Notification.builder()
                .externalReference(message.transactionId())
                .notificationType(NotificationType.PAYMENT_CONFIRMATION)
                .status(NotificationStatus.PENDING)
                .notificationDate(LocalDateTime.now())
                .payload(message)
                .build();

        notificationRepository.save(notification);

    }

    @Transactional
    @KafkaListener(
            topics = "order-topic",
            groupId = "notification-order-group"
    )
    public void consumeOrderConfirmation(OrderConfirmation message) {
        log.info("Consumed order confirmation: {}", message);

        if (notificationRepository.existsByExternalReference(message.orderReference())) {
            log.warn("Order notification already exists for orderReference={}", message.orderReference());
            return;
        }

        Notification notification = Notification.builder()
                .externalReference(message.orderReference())
                .notificationType(NotificationType.ORDER_CONFIRMATION)
                .status(NotificationStatus.PENDING)
                .notificationDate(LocalDateTime.now())
                .payload(message)
                .build();

        notificationRepository.save(notification);
    }

}
