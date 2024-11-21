package org.example;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient   //enable服务注册
@EnableFeignClients      //enable openFeign
public class CloudManagerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CloudManagerApplication.class, args);
    }
}
