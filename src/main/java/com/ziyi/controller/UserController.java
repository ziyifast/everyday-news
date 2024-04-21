package com.ziyi.controller;

import com.ziyi.pojo.Result;
import com.ziyi.pojo.User;
import com.ziyi.service.UserService;
import com.ziyi.utils.JwtUtil;
import com.ziyi.utils.Md5Util;
import com.ziyi.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 10:23
 */
@RestController
@RequestMapping("/user")
@Validated //启用校验
public class UserController {

    @Autowired
    private UserService userService;

    //用户名密码必须为5-16位非空字符
    @PostMapping("/register")
    public Result register(@Pattern(regexp="^\\S{5,16}$") String username, @Pattern(regexp="^\\S{5,16}$")String password) {
        //查询用户
        if (userService.findByUsername(username) != null) {
            return Result.error("用户名已存在");
        }
        //注册用户
        userService.register(username, password);
        return Result.success();
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp="^\\S{5,16}$") String username, @Pattern(regexp="^\\S{5,16}$")String password) {
        if (userService.findByUsername(username) == null) {
            return Result.error("用户名不存在");
        }
        //校验密码
        User user = userService.findByUsername(username);
        if (!Md5Util.checkPassword(password, user.getPassword())) {
            return Result.error("密码错误");
        }
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", username);
        claims.put("id", user.getId());
        String token = JwtUtil.genToken(claims);
        return Result.success(token);
    }

    @GetMapping("/userInfo")
    public Result<User> getUserInfo(/*@RequestHeader(name="Authorization") String token*/) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        String username = (String) claims.get("username");
        User user = userService.findByUsername(username);
        return Result.success(user);
    }

    //更新用户信息
    @PutMapping("/update")
    public Result updateUser(@RequestBody @Validated User user) {
        userService.update(user);
        return Result.success();
    }
}
