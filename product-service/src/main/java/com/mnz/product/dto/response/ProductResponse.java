package com.mnz.product.dto.response;

import com.mnz.product.model.ProductModel;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record ProductResponse(UUID productId, String name, String description, BigDecimal price) {
    public ProductResponse(ProductModel model) {
        this(model.getProductId(), model.getName(), model.getDescription(), model.getPrice());
    }
}
