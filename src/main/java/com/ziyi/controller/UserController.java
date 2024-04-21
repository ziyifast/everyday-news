package com.ziyi.controller;

import com.ziyi.pojo.Result;
import com.ziyi.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 10:23
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username, String password) {
        //查询用户
        if (userService.findByUsername(username) != null) {
            return Result.error("用户名已存在");
        }
        //注册用户
        userService.register(username, password);
        return Result.success();
    }
}
