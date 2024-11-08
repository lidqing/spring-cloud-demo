package org.example.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class TestManagerController {

    @RequestMapping("/hello")
    public String hello(String name) {
        return "Hello " + name + ",this is manager";
    }
}
