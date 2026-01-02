package com.amer.ecommerce.notification.repository;

import com.amer.ecommerce.notification.domain.Notification;
import com.amer.ecommerce.notification.domain.NotificationStatus;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface NotificationRepository extends MongoRepository<Notification, String> {

    boolean existsByExternalReference(String s);

    List<Notification> findByStatus(NotificationStatus status);}

