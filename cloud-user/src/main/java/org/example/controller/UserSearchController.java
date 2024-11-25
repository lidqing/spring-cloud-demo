package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import org.example.common.model.R;
import org.example.feign.UserSearchApi;
import org.example.model.Role;
import org.example.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserSearchController implements UserSearchApi {

    @SentinelResource(value = "userSearch")
    @Override
    public User queryUser(String username, String password) {
        User user = new User();
        user.setId(1L);
        user.setUserId(username);
        user.setUsername("管理员");
        user.setPassword(password);
        user.setRoleId("1");
        return user;
    }

    @SentinelResource(value = "userSearch")
    @Override
    public Role queryUserRole(JSONObject body) {
        Role role = new Role();
        role.setRoleId(body.getString("roleId"));
        role.setRoleName("admin");
        return role;
    }
}
