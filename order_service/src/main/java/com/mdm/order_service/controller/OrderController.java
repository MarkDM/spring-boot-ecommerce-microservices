package com.mdm.order_service.controller;

import com.mdm.order_service.dto.OrderRequest;
import com.mdm.order_service.dto.OrderResponse;
import com.mdm.order_service.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderResponse placeOrder(@RequestBody OrderRequest request) {
        var orderCreated = orderService.placeOrder(request);

        return new OrderResponse(
                orderCreated.getId(),
                orderCreated.getSkuCode(),
                orderCreated.getPrice(),
                orderCreated.getQuantity()
        );

    }
}
