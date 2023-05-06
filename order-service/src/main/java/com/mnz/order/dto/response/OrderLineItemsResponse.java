package com.mnz.order.dto.response;

import com.mnz.order.model.OrderLineItemsModel;
import lombok.Builder;

import java.math.BigDecimal;
import java.util.UUID;

@Builder
public record OrderLineItemsResponse(UUID orderLineId, String skuCode, BigDecimal price, Integer quantity) {
    public OrderLineItemsResponse(OrderLineItemsModel model) {
        this(model.getOrderLineId(), model.getSkuCode(), model.getPrice(), model.getQuantity());
    }
}
