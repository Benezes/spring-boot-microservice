package com.mnz.product.service;

import com.mnz.product.dto.request.ProductRequest;
import com.mnz.product.dto.response.ProductResponse;
import com.mnz.product.model.ProductModel;
import com.mnz.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    public ProductResponse createProduct(ProductRequest productRequest) {
        ProductModel productModel = ProductModel
                .builder()
                .productId(UUID.randomUUID())
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();

        ProductResponse productResponse = new ProductResponse(productRepository.save(productModel));
        log.info("Product: {} saved!", productResponse.name());
        return productResponse;
    }

    public Set<ProductResponse> findAllProducts() {
        return productRepository.findAll().stream().map(ProductResponse::new).collect(Collectors.toSet());
    }
}
