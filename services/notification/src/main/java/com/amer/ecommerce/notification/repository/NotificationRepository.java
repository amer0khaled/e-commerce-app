package com.amer.ecommerce.notification.repository.payment;

import com.amer.ecommerce.notification.messaging.payment.PaymentConfirmation;

public interface NotificationRepository extends JpaRepository<PaymentConfirmation, Integer>{
}
