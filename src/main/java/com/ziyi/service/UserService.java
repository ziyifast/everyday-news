package com.ziyi.service;

import com.ziyi.pojo.User;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 10:26
 */
public interface UserService {

    User findByUsername(String username);

    void register(String username, String password);

    void update(User user);

    void updateAvator(String avatorUrl);

    void updatePwd(String newPwd);
}
