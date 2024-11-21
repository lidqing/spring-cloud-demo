package org.example.feign;


import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.example.model.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 用户查询接口
 * 说明：
 * 接口和接口用到的实体类单独提前到module里，服务提供者实现接口，服务调用者直接注入使用，避免两边重复定义。
 */
@FeignClient(name = "cloud-user", path = "/api/user")
public interface UserSearchApi {

    /**
     * 查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回用户实体
     */
    @PostMapping("queryUser")
    @SentinelResource("queryUser")
    User queryUser(@RequestParam("username") String username,
                   @RequestParam("password") String password);


    @GetMapping("queryUserById")
    User queryUserById(@RequestParam("userId") String userId);

}
