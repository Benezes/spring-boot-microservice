package com.mnz.order.dto.request;

import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record OrderLineItemsRequest(String skuCode, BigDecimal price, Integer quantity) {
}
