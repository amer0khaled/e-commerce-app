package com.amer.ecommerce.product.mapper;

import com.amer.ecommerce.product.category.Category;
import com.amer.ecommerce.product.api.dto.ProductPurchaseResponse;
import com.amer.ecommerce.product.api.dto.ProductRequest;
import com.amer.ecommerce.product.api.dto.ProductResponse;
import com.amer.ecommerce.product.domain.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toProduct(ProductRequest request) {
        return Product.builder()
                .name(request.name())
                .description(request.description())
                .availableQuantity(request.availableQuantity())
                .price(request.price())
                .category(
                        Category.builder()
                                .id(request.categoryId())
                                .build()
                )
                .build();
    }

    public ProductResponse fromProduct(Product product) {
        return new ProductResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getAvailableQuantity(),
                product.getPrice(),
                product.getCategory().getId(),
                product.getCategory().getName(),
                product.getCategory().getDescription()
        );
    }


    public ProductPurchaseResponse toProductPurchaseResponse(Product product) {
        return new ProductPurchaseResponse(
                product.getId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getAvailableQuantity()
        );
    }
}
