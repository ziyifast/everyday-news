package com.ziyi.controller;

import com.ziyi.pojo.Article;
import com.ziyi.pojo.Result;
import com.ziyi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 13:46
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @PostMapping("/add")
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success("");
    }
}
