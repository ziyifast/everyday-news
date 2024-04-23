package com.ziyi;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 20:40
 */
@SpringBootTest //勾选之后，在执行测试类之前会先注入加上类到IOC容器
public class RedisTest {

    @Autowired
    private RedisTemplate redisTemplate;

//    @Test
    void setKey(){
        redisTemplate.opsForValue().set("name","zhangsan");
    }

//    @Test
    void getKey(){
        System.out.println(redisTemplate.opsForValue().get("name"));
    }
}
