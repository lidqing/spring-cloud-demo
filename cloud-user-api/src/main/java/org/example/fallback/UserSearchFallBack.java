package org.example.fallback;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.feign.UserSearchApi;
import org.example.model.Role;
import org.example.model.User;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;

/**
 * UserSearchApi 接口发生限流时处理逻辑
 */
@Slf4j
public class UserSearchFallBack implements UserSearchApi {

    @Override
    public User queryUser(String username, String password) {
        //根据实际情况返回，返回兜底数据
        return null;
    }

    @Override
    public Role queryUserRole(JSONObject body) {
        return null;
    }
}
