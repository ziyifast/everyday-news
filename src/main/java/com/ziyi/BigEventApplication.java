package com.ziyi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 09:35
 */
@SpringBootApplication
@MapperScan("com.ziyi.mapper")
public class BigEventApplication {
    public static void main(String[] args) {
        SpringApplication.run(BigEventApplication.class, args);
    }
}
