package org.example.feign;

import com.alibaba.fastjson.JSONObject;
import org.example.fallback.UserSearchFallBack;
import org.example.model.Role;
import org.example.model.User;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;

/**
 * 用户查询接口
 * 说明：
 * 接口和接口用到的实体类单独提前到module里，服务提供者实现接口，服务调用者直接注入使用，避免两边重复定义。
 */
@FeignClient(contextId = "cloud-user-userSearchApi", name = "cloud-user", path = "/api/user", fallback = UserSearchFallBack.class,
        configuration = UserSearchApi.FeignConfiguration.class)
public interface UserSearchApi {

    /**
     * 查询用户
     *
     * @param username 用户名
     * @param password 密码
     * @return 返回用户实体
     */
    @PostMapping("queryUser")
    //@LoadBalanced
    //@SentinelResource(value = "queryUser")
    User queryUser(@RequestParam("username") String username,
                   @RequestParam("password") String password);


    /**
     * 通过body参数查询用户
     *
     * @param body request body
     * @return
     */
    @PostMapping("queryUserRole")
    //@LoadBalanced
    Role queryUserRole(@RequestBody JSONObject body);

    class FeignConfiguration {
        @Bean
        public UserSearchFallBack userSearchFallBack() {
            return new UserSearchFallBack();
        }
    }


}
