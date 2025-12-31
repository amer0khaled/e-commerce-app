package com.amer.ecommerce.product;

import com.amer.ecommerce.exception.InsufficientStockException;
import com.amer.ecommerce.exception.ProductNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;

    public Integer createProduct(ProductRequest request) {
        var product = productRepository.save(productMapper.toProduct(request));
        return product.getId();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<ProductPurchaseResponse> purchaseProduct(List<ProductPurchaseRequest> request) {
        // 1. check if the product is available
        var requestedProductIds = request.stream()
                .map(ProductPurchaseRequest::productId)
                .distinct()
                .sorted()
                .toList();

        var storedProducts = productRepository.findByIdsWithLock(requestedProductIds);
        checkProductsAvailability(storedProducts, requestedProductIds);

        // 2. check if quantity is available
        Map<Integer, Integer> productIdToQuantityMap = request.stream()
                .collect(Collectors.toMap(
                        ProductPurchaseRequest::productId,
                        ProductPurchaseRequest::quantity,
                        Integer::sum
                        )
                );

        var purchasedProducts = new ArrayList<ProductPurchaseResponse>();
        for (var product : storedProducts) {
            int requestedQuantity = productIdToQuantityMap.getOrDefault(product.getId(), 0);
            if (requestedQuantity > product.getAvailableQuantity()) {
                throw new InsufficientStockException(
                        "Insufficient stock for product " + product.getId() +
                                ". Available: " + product.getAvailableQuantity() +
                                ", Requested: " + requestedQuantity
                );
            }
            // 3. update the available quantity
            product.setAvailableQuantity(product.getAvailableQuantity() - requestedQuantity);
            purchasedProducts.add(productMapper.toProductPurchaseResponse(product));
        }

        // 4. update the database
        productRepository.saveAll(storedProducts);
        return purchasedProducts;
    }

    public ProductResponse findById(Integer productId) {
        return productRepository.findById(productId)
                .map(productMapper::fromProduct)
                .orElseThrow(() -> new EntityNotFoundException("Product Not Found With ID:: " + productId));
    }

    public List<ProductResponse> findAll() {
        return productRepository.findAll()
                .stream()
                .map(productMapper::fromProduct)
                .toList();
    }

    private void checkProductsAvailability(List<Product> products, List<Integer> requestedProductIds) {
        if (products.size() != requestedProductIds.size()) {
            var foundProductIds = products.stream()
                    .map(Product::getId)
                    .collect(Collectors.toSet());

            var missingProductIds = requestedProductIds.stream()
                    .filter(id -> !foundProductIds.contains(id))
                    .toList();

            throw new ProductNotFoundException(
                    "One or more products not found :: " + missingProductIds
            );
        }
    }

}
