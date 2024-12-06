package org.example.controller;

import io.seata.core.context.RootContext;
import lombok.extern.slf4j.Slf4j;
import org.example.feign.UserAccountApi;
import org.example.service.UserAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class UserAccountController implements UserAccountApi {

    @Autowired
    UserAccountService userAccountService;

    @Override
    public Boolean updateAccount(String userId) {
        log.info("account xid: {}", RootContext.getXID());
        userAccountService.updateAccount(userId);
        return true;
    }
}
