package com.mdm.order_service.service;

import com.mdm.order_service.dto.OrderRequest;
import com.mdm.order_service.model.Order;
import com.mdm.order_service.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;

    public void placeOrder(OrderRequest request) {
        var order = new Order();
        BeanUtils.copyProperties(request, order);
        orderRepository.save(order);
    }
}
