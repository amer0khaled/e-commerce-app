package com.amer.ecommerce.notification.messaging.order;

public record Customer(
        Integer id,
        String firstName,
        String lastName,
        String email
) {
}
