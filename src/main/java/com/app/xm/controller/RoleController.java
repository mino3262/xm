package com.app.xm.controller;
import java.util.List;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.app.xm.common.Result;
import com.app.xm.entity.Role;
import com.app.xm.service.RoleService;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;
    

    /*
     * 查询所有角色
    */
    @GetMapping("/selectAll")
    public Result selectAll(){
        List<Role> list = roleService.selectAll();
        return Result.success(list);
    }
    /*
     * 分页查询
     */
    @GetMapping("/selectByPage")
    public Result selectByPage(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<Role> pageInfo = roleService.selectByPage(pageNum, pageSize); 
        return Result.success(pageInfo);
    }

    /*
     * 新增角色
     */
    @PostMapping("/add")
    public Result add(@RequestBody Role role){
        roleService.add(role);
        return Result.success();
    }
}
