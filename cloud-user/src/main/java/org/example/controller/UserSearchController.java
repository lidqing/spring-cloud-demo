package org.example.controller;

import org.example.feign.UserSearchApi;
import org.example.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserSearchController implements UserSearchApi {

    @PostMapping
    @RequestMapping("/queryUser")
    @Override
    public User queryUser(String username, String password) {
        User user = new User();
        user.setId(1L);
        user.setUserId("admin");
        user.setUsername("管理员");
        user.setPassword("123");
        user.setRoleId("1");
        return user;
    }

    @Override
    public User queryUser(String username) {
        return null;
    }
}
