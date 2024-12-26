package com.app.xm.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.xm.common.Result;
import com.app.xm.entity.User;
import com.app.xm.service.UserService;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 查询用户
     */
    @GetMapping("/selectAll")
    public Result selectAll(){
        List<User> list = userService.selectAll();
        return Result.success(list);
    }
    
    /*
     * 分页查询
     */
    @GetMapping("/selectPage")
    public Result selectPage(@RequestParam(defaultValue = "1") Integer pageNum,
                            @RequestParam(defaultValue = "10") Integer pageSize){
        PageInfo<User> pageInfo = userService.selectPage(pageNum, pageSize); 
        return Result.success(pageInfo);
    }

    /*
     * 按账号查询
     */
    @GetMapping("/selectByAccount/{account}")
    public Result selectByAccount(@PathVariable String account){
        User user = userService.selectByAccount(account);
        return Result.success(user);
    }

    /*
     * 新增用户
     */
    @PostMapping("/addUser")
    public Result addUser(@RequestBody User user){
        userService.addUser(user);
        return Result.success();
    }

    /*
     * 修改用户
     */
    @PutMapping("/updateUser")
    public Result updateUser(@RequestBody User user){
        userService.updateUser(user);
        return Result.success();
    }

    /*
     * 删除用户
     */
    @DeleteMapping("/updateUser")
    public Result deleteById(@PathVariable Integer userId){
        userService.deleteById(userId);
        return Result.success();
    }
}
