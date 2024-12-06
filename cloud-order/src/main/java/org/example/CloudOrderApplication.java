package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
//@EnableAutoConfiguration注解只是测试数据库用，改造时不用添加
@EnableAutoConfiguration(exclude = HibernateJpaAutoConfiguration.class)
public class CloudOrderApplication {
    public static void main(String[] args) {
        SpringApplication.run(CloudOrderApplication.class, args);
    }
}
