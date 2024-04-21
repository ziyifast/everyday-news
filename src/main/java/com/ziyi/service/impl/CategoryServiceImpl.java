package com.ziyi.service.impl;

import com.ziyi.mapper.CategoryMapper;
import com.ziyi.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 16:43
 */
@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public void add(String categoryName, String categoryAlias, Integer createUserId) {
        categoryMapper.add(categoryName, categoryAlias, createUserId);
    }

}
