package com.amer.ecommerce.exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}
