package com.mnz.order.dto.request;

import lombok.Builder;

import java.util.List;

@Builder
public record OrderRequest(List<OrderLineItemsRequest> orderLineItemsList) {
}
