package com.amer.ecommerce.notification.service.impl;

import com.amer.ecommerce.notification.domain.NotificationStatus;
import com.amer.ecommerce.notification.repository.NotificationRepository;
import com.amer.ecommerce.notification.service.NotificationProcessor;
import com.amer.ecommerce.notification.strategy.NotificationStrategy;
import com.amer.ecommerce.notification.strategy.NotificationStrategyFactory;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationProcessorImpl implements NotificationProcessor {

    private final NotificationRepository notificationRepository;
    private final NotificationStrategyFactory strategyFactory;

    @Override
    @Scheduled(fixedDelay = 5000)
    public void processPendingNotifications() {

        var notifications =
                notificationRepository.findByStatus(NotificationStatus.PENDING);

        notifications.forEach(notification -> {
            try {
                NotificationStrategy strategy =
                        strategyFactory.getStrategy(notification.getNotificationType());

                strategy.send(notification);

                notification.setStatus(NotificationStatus.SENT);
                log.info("Notification {} sent successfully", notification.getId());

            } catch (Exception e) {
                notification.setStatus(NotificationStatus.FAILED);
                log.error("Failed to send notification {}", notification.getId(), e);
            }

            notificationRepository.save(notification);
        });
    }

}
