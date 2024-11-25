package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.example.common.model.R;
import org.example.common.secure.TokenUtil;
import org.example.feign.UserSearchApi;
import org.example.model.Role;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserSearchApi userSearchApi;

    @PostMapping("/doLogin")
    public R login(String username, String password) {
        User user = userSearchApi.queryUser(username, password);
        if (user != null) {
            JSONObject body = new JSONObject();
            body.put("roleId", user.getRoleId());
            Role role = userSearchApi.queryUserRole(body);
            log.info(role.getRoleName());
            String token = TokenUtil.generate(user.getUserId());
            log.info("gen token: {}", token);
            //写token到redis
            //.....
            return R.ok("success");
        }
        return R.error("failed");
    }

    //@SentinelResource(value = "queryUser6")
    User queryUser(String username, String password){
        return userSearchApi.queryUser(username, password);
    }


}
