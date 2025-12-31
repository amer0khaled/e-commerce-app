package com.amer.microservice.client.product;

import com.amer.microservice.client.product.dto.PurchaseRequest;
import com.amer.microservice.client.product.dto.PurchaseResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "product-service"
        // url = "${application.services.product.base-url}"
)
public interface ProductClient {

    @PostMapping("/purchase")
    List<PurchaseResponse> purchaseProducts(
            @RequestBody List<PurchaseRequest> requestBody
    );
}
