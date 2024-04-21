package com.ziyi.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author zhouYi
 * @description TODO
 * @date 2024/4/21 16:44
 */
@Mapper
public interface CategoryMapper {

    @Insert("insert into category (category_name, category_alias, create_user, create_time, update_time) values (#{categoryName}, #{categoryAlias}, #{userId}, now(), now())")
    public void add(String categoryName, String categoryAlias, Integer userId);
}
