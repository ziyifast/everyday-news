package com.ziyi.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.ziyi.pojo.Article;
import com.ziyi.pojo.PageBean;
import com.ziyi.pojo.Result;
import com.ziyi.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/list")
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pb = new PageBean<>();
        PageHelper.startPage(pageNum, pageSize);
        List<Article> list = articleService.list(categoryId, state);
        Page<Article> page = (Page<Article>) list;
        pb.setTotal(page.getTotal());
        pb.setItems(page.getResult());
        return Result.success(pb);
    }

}
