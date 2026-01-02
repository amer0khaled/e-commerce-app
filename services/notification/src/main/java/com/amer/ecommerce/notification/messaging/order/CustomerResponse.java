package com.amer.ecommerce.notification.messaging.order;

public record CustomerResponse(
        Integer id,
        String firstName,
        String lastName,
        String email
) {
}
