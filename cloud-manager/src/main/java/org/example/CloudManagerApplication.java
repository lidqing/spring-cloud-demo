package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableDiscoveryClient   //enable服务注册
@EnableFeignClients      //enable openFeign
//@EnableCircuitBreaker    //允许熔断
//@EnableAspectJAutoProxy(exposeProxy = true, proxyTargetClass = true)
public class CloudManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudManagerApplication.class, args);
    }
}
