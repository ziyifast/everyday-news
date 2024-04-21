package com.ziyi.mapper;

import com.ziyi.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * @author xsky
 * @description TODO
 * @date 2024/4/21 10:27
 */
@Mapper
public interface UserMapper {

    @Select("select * from user where username = #{username}")
    User findByUsername(String username);

    @Insert("insert into  user (username, password, create_time, update_time) values (#{username}, #{md5Str}, now(), now())")
    void add(String username, String md5Str);

    @Update("update user set nickname = #{nickname}, email = #{email} where id = #{id}")
    void update(User user);

    @Update("update user set user_pic = #{avatorUrl}, update_time = now() where id = #{uid}")
    void updateAvator(String avatorUrl, Integer uid);

    @Update("update user set password = #{newPwd}, update_time = now() where id = #{uid}")
    void updatePwd(String newPwd, Integer uid);
}
