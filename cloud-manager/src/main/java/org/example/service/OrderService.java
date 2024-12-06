package org.example.service;

import org.example.feign.OrderApi;
import org.example.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    @Autowired
    OrderApi orderApi;

    public Boolean createOrder(OrderDto order) {
        // 创建订单
        return orderApi.createOrder(order);
    }
}
