package com.amer.microservice.order;

public enum OrderStatus {
    CREATED,        // Order created, not paid yet
    PAYMENT_PENDING,// Payment initiated but not confirmed
    PAID,           // Payment successful
    CONFIRMED,      // Order confirmed (stock reserved / business accepted)
    CANCELLED,      // Cancelled by user or system
    FAILED          // Payment failed or business failure

}
