package com.app.xm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.xm.entity.Role;
import com.app.xm.mapper.RoleMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;

@Service
public class RoleService {
    @Resource
    private RoleMapper roleMapper;

    public List<Role> selectAll() {
        return roleMapper.selectAll();
    }

    public Role selectByRoleId(Integer roleId){
        return roleMapper.selectByRoleId(roleId);
    }

    public Role selectRolesByUserId(Integer userId){
        return roleMapper.selectRolesByUserId(userId);
    }

    public PageInfo<Role> selectByPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Role> list = roleMapper.selectAll();
        return new PageInfo<>(list);
    }

    public void add(Role role) {
        roleMapper.insert(role);
    }

}
