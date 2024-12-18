package org.example.controller;

import io.seata.core.context.RootContext;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.example.common.model.R;
import org.example.feign.UserAccountApi;
import org.example.model.OrderDto;
import org.example.service.OrderService;
import org.example.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

/**
 * 演示seata分布式事务
 */
@Slf4j
@RequestMapping("/busi")
@RestController
public class BusiController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserAccountService userAccountService;

    @GlobalTransactional
    @PostMapping("/purchase")
    public R purchase(@RequestBody OrderDto order, @RequestParam(name = "error", defaultValue = "0") Integer error) {
        log.info("global xid: {}", RootContext.getXID());
        //创建订单
        orderService.createOrder(order);
        //更新账号余额
        userAccountService.updateUserAccount(order.getUserId());
        if (error == 1) {
            throw new RuntimeException("模拟事务执行中发生异常");
        }
        try {
            //模拟执行事务中暂停5s
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return R.ok("操作成功");
    }

}
