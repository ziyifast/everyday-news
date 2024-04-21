package com.ziyi.controller;

import com.ziyi.pojo.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 13:46
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/listAll")
    public Result listAll() {
        return Result.success("所有文章内容");
    }
}
