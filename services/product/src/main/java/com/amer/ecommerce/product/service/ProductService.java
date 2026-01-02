package com.amer.ecommerce.product.service;

import com.amer.ecommerce.product.api.dto.ProductPurchaseRequest;
import com.amer.ecommerce.product.api.dto.ProductPurchaseResponse;
import com.amer.ecommerce.product.api.dto.ProductRequest;
import com.amer.ecommerce.product.api.dto.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse createProduct(ProductRequest request);

    List<ProductPurchaseResponse> purchaseProduct(
            List<ProductPurchaseRequest> request
    );

    ProductResponse findById(Integer productId);

    List<ProductResponse> findAll();

}
