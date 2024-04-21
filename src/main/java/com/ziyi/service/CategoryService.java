package com.ziyi.service;

import com.ziyi.pojo.Category;

import java.util.ArrayList;

/**
 * @author xsky
 * @description TODO
 * @date 2024/4/21 16:43
 */
public interface CategoryService {

    public void add(String categoryName, String categoryAlias, Integer createUserId);

    Category findByCategoryName(String categoryName);

    ArrayList<Category> list();

    Category getCategoryDetail(Integer id);

    void update(Category category);
}
