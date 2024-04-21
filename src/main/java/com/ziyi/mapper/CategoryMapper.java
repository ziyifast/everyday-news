package com.ziyi.mapper;

import com.ziyi.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.ArrayList;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 16:44
 */
@Mapper
public interface CategoryMapper {

    @Insert("insert into category (category_name, category_alias, create_user, create_time, update_time) values (#{categoryName}, #{categoryAlias}, #{userId}, now(), now())")
    void add(String categoryName, String categoryAlias, Integer userId);

    @Select("select * from category where create_user = #{uid}")
    ArrayList<Category> list(Integer uid);

    @Select("select * from category where category_name = #{categoryName}")
    Category findByCategoryName(String categoryName);

    @Select("select * from category where id = #{id}")
    Category findById(Integer id);

    @Update("update category set category_name = #{categoryName}, category_alias = #{categoryAlias},  update_time = now() where id = #{id} ")
    void update(Category category);
}

