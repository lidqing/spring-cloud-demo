package org.example.controller;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.example.feign.OrderApi;
import org.example.model.OrderDto;
import org.example.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class OrderController implements OrderApi {

    @Autowired
    OrderService orderService;

    @Override
    public OrderDto getOrderById(Long id) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(id);
        return orderDto;
    }

    @Override
    public Boolean createOrder(OrderDto order) {
        log.info("order xid: {}", RootContext.getXID());
        orderService.createOrder(order);
        return true;
    }
}
