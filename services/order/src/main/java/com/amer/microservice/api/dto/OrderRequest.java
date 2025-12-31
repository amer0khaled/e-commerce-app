package com.amer.microservice.api.dto.order;

import com.amer.microservice.client.product.dto.PurchaseRequest;
import com.amer.microservice.domain.PaymentMethod;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record OrderRequest(
        @NotBlank(message = "Customer Id cannot be blank")
        String customerId,
        @NotNull(message = "Payment Method is required")
        PaymentMethod paymentMethod,
        @NotEmpty(message = "Purchase Requests cannot be empty")
        List<PurchaseRequest> products
) {
}

