package org.example.fallback;

import org.example.feign.UserAccountApi;
import org.example.model.Account;

public class UserAccountFallBack implements UserAccountApi {
    @Override
    public Boolean updateAccount(String userId) {
        return false;
    }
}
