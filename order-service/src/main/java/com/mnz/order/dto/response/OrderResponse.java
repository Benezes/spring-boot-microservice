package com.mnz.order.dto.response;

import com.mnz.order.model.OrderModel;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Builder
public record OrderResponse(UUID orderId, String orderNumber, List<OrderLineItemsResponse> orderLineItemsList) {

    public OrderResponse(OrderModel model) {
        this(model.getOrderId(), model.getOrderNumber(), new ArrayList<>());
        model.getOrderLineItemsList().forEach(item -> orderLineItemsList.add(new OrderLineItemsResponse(item)));
    }
}
