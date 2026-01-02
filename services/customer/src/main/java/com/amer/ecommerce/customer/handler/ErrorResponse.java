package com.amer.ecommerce.customer.handler;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
