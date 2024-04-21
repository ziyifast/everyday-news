package com.ziyi.service.impl;

import com.ziyi.mapper.ArticleMapper;
import com.ziyi.mapper.CategoryMapper;
import com.ziyi.pojo.Article;
import com.ziyi.pojo.Category;
import com.ziyi.service.ArticleService;
import com.ziyi.service.CategoryService;
import com.ziyi.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Map;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 16:43
 */
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;

    public void add(Article article) {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer uid = (Integer) claims.get("id");
        article.setCreateUser(uid);
        articleMapper.add(article);
    }

}
