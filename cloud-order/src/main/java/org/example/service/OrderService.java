package org.example.service;

import org.example.dao.BarHibernateDAO;
import org.example.model.OrderDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    BarHibernateDAO barHibernateDAO;

    //@Transactional("hibernateTransaction")
    @Transactional
    public String createOrder(OrderDto order) {

        barHibernateDAO.executeSQL(String.format("INSERT INTO `order`.order_item (user_id, commodity_code, money) VALUES('%s', '%s', %s)"
                , order.getUserId(), order.getCommodityCode(), order.getMoney()));

        if (order.getUserId().equalsIgnoreCase("admin3")) {
            throw new RuntimeException("测试业务异常，事务回滚");
        }
        return null;
    }
}
