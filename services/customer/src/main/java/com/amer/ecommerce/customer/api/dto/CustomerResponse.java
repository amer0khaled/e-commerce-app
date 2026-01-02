package com.amer.ecommerce.customer.api.dto;

import com.amer.ecommerce.customer.domain.Address;

public record CustomerResponse(
        String id,
        String firstName,
        String lastName,
        String email,
        Address address
) {
}
