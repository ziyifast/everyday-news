package com.ziyi.controller;

import com.ziyi.pojo.Category;
import com.ziyi.pojo.Result;
import com.ziyi.service.CategoryService;
import com.ziyi.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 16:43
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/add")
    public Result add(@RequestBody @Validated Category category) {
        String categoryName = category.getCategoryName();
        String categoryAlias = category.getCategoryAlias();
        Map<String, Object> claims = ThreadLocalUtil.get();
        Integer uid = (Integer) claims.get("id");
        categoryService.add(categoryName, categoryAlias, uid);
        return Result.success();
    }

}
