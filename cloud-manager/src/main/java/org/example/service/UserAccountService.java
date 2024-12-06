package org.example.service;

import org.example.feign.UserAccountApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserAccountService {

    @Autowired
    UserAccountApi userAccountApi;

    public boolean updateUserAccount(String userId){
        return userAccountApi.updateAccount(userId);
    }

}
