package com.amer.microservice.client.customer;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email
) {
}
