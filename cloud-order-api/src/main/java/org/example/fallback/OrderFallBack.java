package org.example.fallback;

import org.example.feign.OrderApi;
import org.example.model.OrderDto;

/**
 * OrderApi 发生限流异常时处理逻辑
 */
public class OrderFallBack implements OrderApi {
    @Override
    public OrderDto getOrderById(Long id) {
        // 根据实际情况返回兜底数据
        return null;
    }

    @Override
    public Boolean createOrder(OrderDto order) {
        throw new RuntimeException("涉及事务处理，兜底方案直接抛出业务异常");
    }
}
