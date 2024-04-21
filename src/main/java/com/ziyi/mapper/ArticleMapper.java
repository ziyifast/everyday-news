package com.ziyi.mapper;

import com.ziyi.pojo.Article;
import com.ziyi.pojo.PageBean;
import com.ziyi.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * @author xsky
 * @description TODO
 * @date 2024/4/21 10:27
 */
@Mapper
public interface ArticleMapper {

    @Insert("insert into  article (title, content, cover_img, state,category_id,create_user, create_time, update_time) values (#{title}, #{content}, #{coverImg},#{state},#{categoryId},#{createUser},now(), now())")
    void add(Article article);

    List<Article> list(Integer userId, Integer categoryId, String state);
}
