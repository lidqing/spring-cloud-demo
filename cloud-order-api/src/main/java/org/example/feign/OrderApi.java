package org.example.feign;

import org.example.fallback.OrderFallBack;
import org.example.model.OrderDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "cloud-order", path = "/api/order", fallback = OrderFallBack.class,
        configuration = OrderApi.FeignConfiguration.class)
public interface OrderApi {

    //注意：参数要加@RequestParam("id")修饰，不然接口可能有问题
    @GetMapping("/getOrder")
    OrderDto getOrderById(@RequestParam("id") Long id);

    @PostMapping("/createOrder")
    Boolean createOrder(@RequestBody OrderDto order);

    class FeignConfiguration {
        @Bean
        public OrderFallBack OrderFallBack() {
            return new OrderFallBack();
        }
    }
}
