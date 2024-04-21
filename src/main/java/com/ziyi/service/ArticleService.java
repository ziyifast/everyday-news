package com.ziyi.service;

import com.ziyi.pojo.Article;
import com.ziyi.pojo.User;

import java.util.List;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 10:26
 */
public interface ArticleService {


    void add(Article article);

    List<Article> list(Integer categoryId, String state);
}
