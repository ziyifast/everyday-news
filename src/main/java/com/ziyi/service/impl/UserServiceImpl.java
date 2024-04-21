package com.ziyi.service.impl;

import com.ziyi.mapper.UserMapper;
import com.ziyi.pojo.User;
import com.ziyi.service.UserService;
import com.ziyi.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 10:27
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User findByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    @Override
    public void register(String username, String password) {
        //对密码进行编码加密
        String md5String = Md5Util.getMD5String(password);
        userMapper.add(username, md5String);
    }
}
