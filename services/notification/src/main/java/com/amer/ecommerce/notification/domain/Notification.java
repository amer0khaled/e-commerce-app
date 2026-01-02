package com.amer.ecommerce.notification.domain;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
@Document
public class Notification {

    @Id
    private String id;

    @Indexed(unique = true)
    private String externalReference;

    private NotificationType notificationType;

    private NotificationStatus status;

    private LocalDateTime notificationDate;

    private Object payload;

}
