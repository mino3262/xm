package com.app.xm.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Select;

import com.app.xm.entity.User;

public interface UserMapper {

    List<User> selectAll();

    @Select("select * from `sys_user` where account = #{account}")
    User selectByAccount(String account);

    void insert(User user);

    void updateById(User user);

    @Delete("delete from `sys_user` where user_id = #{userId}")
    void deleteById(Integer userId);

}