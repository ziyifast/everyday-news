package com.ziyi.controller;

import com.ziyi.pojo.Category;
import com.ziyi.pojo.Result;
import com.ziyi.service.CategoryService;
import com.ziyi.utils.ThreadLocalUtil;
import jakarta.validation.constraints.NotNull;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
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
        Category byCategoryName = categoryService.findByCategoryName(categoryName);
        if (byCategoryName != null) {
            return Result.error("该分类已存在，请误重复添加");
        }
        categoryService.add(categoryName, categoryAlias, uid);
        return Result.success();
    }

    @GetMapping("/list")
    public Result<ArrayList<Category>> list() {
        ArrayList<Category> categories = categoryService.list();
        return Result.success(categories);
    }

    @GetMapping("/detail")
    public Result<Category> getCategoryDetail(Integer id) {
        if (id == null) {
            return Result.error("参数错误");
        }
        Category categoryDetail = categoryService.getCategoryDetail(id);
        if (categoryDetail == null) {
            return Result.error("该分类不存在");
        }
        return Result.success(categoryDetail);
    }


    @PutMapping("/update")
    public Result update(@RequestBody @Validated(value = Category.Update.class) Category category) {
        categoryService.update(category);
        return Result.success();
    }

    @DeleteMapping("/delete")
    public Result update(Integer id) {
        categoryService.delete(id);
        return Result.success();
    }

}
