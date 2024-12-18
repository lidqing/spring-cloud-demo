package org.example.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.example.common.model.R;
import org.example.feign.UserSearchApi;
import org.example.model.Role;
import org.example.model.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Enumeration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

@Slf4j
@RestController
public class UserSearchController implements UserSearchApi {

    //@SentinelResource(value = "userSearch")
    @Override
    public User queryUser(String username, String password) {
        User user = new User();
        user.setId(1L);
        user.setUserId(username);
        user.setUsername("管理员");
        user.setPassword(password);
        user.setRoleId("1");
        sleep(ThreadLocalRandom.current().nextInt(40, 60));


        String path = "D:\\qny" + "/static";
        log.info("path:>>>>>{}", path);
        File file = new File(path);
        log.info("file>>>{}", file.getName());
        path = this.getClass().getResource("/static/app.properties").getPath();
        log.info("path from path: {}", path);
        Properties prop = new Properties();
        try {
            prop.load(this.getClass().getResourceAsStream("/static/app.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        log.info("port: {}", prop.getProperty("port"));
        log.info("prop: {}", prop.toString());
        File file2 = new File(path);
        log.info("file name: {}, length: {}", file2.getName(), file2.length());
        try {
            List<String> lines = FileUtils.readLines(file2);
            log.info("line size: {}", lines.size());
            lines.forEach(log::info);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


        log.info("getParentFile.listFiles.....{}", file.listFiles().length);

        return user;
    }

    private static void sleep(int timeMs) {
        try {
            log.info("暂停ms：" + timeMs);
            TimeUnit.MILLISECONDS.sleep(timeMs);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    //@SentinelResource(value = "userSearch")
    @Override
    public Role queryUserRole(JSONObject body) {
        Role role = new Role();
        role.setRoleId(body.getString("roleId"));
        role.setRoleName("admin");
        return role;
    }
}
