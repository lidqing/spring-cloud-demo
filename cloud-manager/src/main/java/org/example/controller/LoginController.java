package org.example.controller;

import org.example.common.model.R;
import org.example.common.secure.TokenUtil;
import org.example.feign.UserSearchApi;
import org.example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    UserSearchApi userSearchApi;

    @PostMapping
    @RequestMapping("/doLogin")
    public R login(String username, String password) {
        User user = userSearchApi.queryUser(username, password);
        if (user != null) {
            String token = TokenUtil.generate(user.getUserId());
            //写token到redis
            //.....
            return R.ok("success");
        }
        return R.error("failed");
    }

}
