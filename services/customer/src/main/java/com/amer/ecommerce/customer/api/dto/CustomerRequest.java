package com.amer.ecommerce.customer.api.dto;

import com.amer.ecommerce.customer.domain.Address;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

public record CustomerRequest(
        @NotNull(message = "Customer First name is required")
        String firstName,

        @NotNull(message = "Customer Last name is required")
        String lastName,

        @NotNull(message = "Customer Email is required")
        @Email(message = "Customer Email is not valid")
        String email,

        Address address
) {
}
