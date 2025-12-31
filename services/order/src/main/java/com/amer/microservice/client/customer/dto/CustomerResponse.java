package com.amer.microservice.client.customer.dto;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
