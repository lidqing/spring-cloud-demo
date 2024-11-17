package org.example.feign;

import org.example.model.User;


/**
 * 用户管理API
 */
public interface UserManagerApi {

    User save(User user);

    int deleteUser(String userId);

    int updateUser(String userId);

}
