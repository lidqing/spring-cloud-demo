package org.example.feign;


import org.example.model.User;
import org.springframework.cloud.openfeign.FeignClient;

/**
 * 用户查询接口
 * 接口单独提前到独立module里，服务提供者实现接口，服务调用者直接注入使用，避免重复定义。
 */
@FeignClient(name = "cloud-user", path = "/api/user")
public interface UserSearchApi {

    User queryUser(String username, String password);


    User queryUser(String username);

}
