package com.ziyi.service.impl;

import com.ziyi.mapper.CategoryMapper;
import com.ziyi.pojo.Category;
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
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryMapper categoryMapper;

    public void add(String categoryName, String categoryAlias, Integer createUserId) {
        categoryMapper.add(categoryName, categoryAlias, createUserId);
    }

    @Override
    public Category findByCategoryName(String categoryName) {
        return categoryMapper.findByCategoryName(categoryName);
    }

    @Override
    public ArrayList<Category> list() {
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer uid = (Integer) claims.get("id");
        return categoryMapper.list(uid);
    }

    @Override
    public Category getCategoryDetail(Integer id) {
        Category category = categoryMapper.findById(id);
        return category;
    }

    @Override
    public void update(Category category) {
        categoryMapper.update(category);
    }

}
