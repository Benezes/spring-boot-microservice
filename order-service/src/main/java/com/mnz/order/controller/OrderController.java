package com.mnz.order.controller;

import com.mnz.order.dto.request.OrderRequest;
import com.mnz.order.dto.response.OrderResponse;
import com.mnz.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping(value = "api/order")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.placeOrder(orderRequest));
    }

    @GetMapping
    public ResponseEntity<Set<OrderResponse>> getAllOrders() {
        return ResponseEntity.ok(orderService.listAllOrders());
    }
}
