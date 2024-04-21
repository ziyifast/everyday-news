package com.ziyi.controller;

import com.ziyi.pojo.Result;
import com.ziyi.pojo.User;
import com.ziyi.service.UserService;
import com.ziyi.utils.JwtUtil;
import com.ziyi.utils.Md5Util;
import com.ziyi.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
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


    @PutMapping("/updateAvator")
    public Result updateAvator(@RequestParam @URL String avatorUrl) {
        userService.updateAvator(avatorUrl);
        return Result.success();
    }

    @PutMapping("/updatePwd")
    public Result updatePassword(@RequestBody Map<String, String> params) {
        //参数校验
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");
        if (!(StringUtils.hasLength(oldPwd) && StringUtils.hasLength(newPwd) && StringUtils.hasLength(rePwd))) {
            return Result.error("参数错误");
        }
        if (!newPwd.equals(rePwd)) {
            return Result.error("两次密码不一致");
        }
        //校验新密码是否合法
        if (!newPwd.matches("^\\S{5,16}$")) {
            return Result.error("密码必须为5-16位非空字符");
        }
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUsername(username);
        if (!Md5Util.checkPassword(oldPwd, user.getPassword())) {
            return Result.error("原密码错误");
        }
        //新密码不能和原密码相同
        if (Md5Util.checkPassword(newPwd, userService.findByUsername(username).getPassword())) {
            return Result.error("新密码不能和原密码相同");
        }
        userService.updatePwd(newPwd);
        return Result.success();
    }
}
