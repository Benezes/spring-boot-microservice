package com.mnz.order.service;

import com.mnz.order.dto.request.OrderLineItemsRequest;
import com.mnz.order.dto.request.OrderRequest;
import com.mnz.order.dto.response.OrderResponse;
import com.mnz.order.model.OrderLineItemsModel;
import com.mnz.order.model.OrderModel;
import com.mnz.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.beans.Transient;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    @Transient
    public OrderResponse placeOrder(OrderRequest orderRequest) {
        List<OrderLineItemsModel> orderLineItemsModels = new ArrayList<>();
        for (OrderLineItemsRequest orderLineItemsRequest : orderRequest.orderLineItemsList()) {
            OrderLineItemsModel build = OrderLineItemsModel.builder()
                    .price(orderLineItemsRequest.price())
                    .quantity(orderLineItemsRequest.quantity())
                    .skuCode(orderLineItemsRequest.skuCode())
                    .build();
            orderLineItemsModels.add(build);
        }


        OrderModel orderModel = OrderModel
                .builder()
                .orderNumber(UUID.randomUUID().toString())
                .orderLineItemsList(orderLineItemsModels)
                .build();

        return new OrderResponse(orderRepository.save(orderModel));
    }

    public Set<OrderResponse> listAllOrders() {
        return orderRepository.findAll().stream().map(OrderResponse::new).collect(Collectors.toSet());
    }
}
