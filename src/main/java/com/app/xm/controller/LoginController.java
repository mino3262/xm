package com.app.xm.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.xm.common.Result;
import com.app.xm.entity.User;
import com.app.xm.service.UserService;

import jakarta.annotation.Resource;

@RestController
public class LoginController {

    @Resource 
    private UserService userService;
    

    /*
     * 登陆验证
    */
    @PostMapping("/login")
    public Result login(@RequestBody User user){
        userService.login(user);
        return Result.success();
    }

    /*
     * 用户注册
    */
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        userService.register(user);
        return Result.success();
    }
}
