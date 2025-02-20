package com.app.xm.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Select;

import com.app.xm.entity.Role;

   
public interface RoleMapper {
    
    @Select("select * from `sys_role`")
    List<Role> selectAll();

    @Select("select * from `sys_role` where role_id = #{roleId}")
    Role selectByRoleId(Integer roleId);

    void insert(Role role);
    
    void update(Role role);

    @Select("select * from `sys_role` where role_id in (select role_id from `sys_user_role` where user_id = #{userId})")
    Role selectRolesByUserId(Integer userId);
}
