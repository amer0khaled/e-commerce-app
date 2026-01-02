package com.amer.ecommerce.notification.strategy;

import com.amer.ecommerce.notification.domain.Notification;
import com.amer.ecommerce.notification.domain.NotificationType;

public interface NotificationStrategy {

    boolean supports(NotificationType notificationType);
    void send(Notification notification);
}
