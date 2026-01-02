package com.amer.ecommerce.payment.domain;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

@Validated
public record Customer(
        @NotNull(message = "Customer Id is required")
        String id,
        @NotNull(message = "Customer First name is required")
        @NotBlank(message = "Customer First name cannot be blank")
        String firstName,
        @NotNull(message = "Customer Last name is required")
        @NotBlank(message = "Customer Last name cannot be blank")
        String lastName,
        @NotNull(message = "Customer Email is required")
        @Email(message = "Customer Email is not valid")
        String email

) {
}
