package com.app.xm.controller;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.xm.common.Jwt;
import com.app.xm.common.Result;
import com.app.xm.entity.User;
import com.app.xm.service.UserService;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

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
     * 按id查询
     */
    @GetMapping("/selectByaccount")
    public Result selectByaccount(HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Map<String, Object> claims = Jwt.parseToken(token);
        User user = userService.selectByAccount(claims.get("account").toString());
        user.setPassword("");
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
     * 修改个人信息
     */
    @PutMapping("/update")
    public Result update(@RequestBody User user, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Map<String, Object> claims = Jwt.parseToken(token);
        user.setAccount(claims.get("account").toString());
        userService.updateByaccount(user);
        return Result.success(userService.selectByAccount(user.getAccount()));
    }
    /*
     * 修改密码
     */
    @PutMapping("/updatePassword")
    public Result updatePassword(@RequestBody Map<String, String> passwordParams, HttpServletRequest request){
        String token = request.getHeader("Authorization");
        Map<String, Object> claims = Jwt.parseToken(token);
        userService.updatePassword(passwordParams.get("originpass"),passwordParams.get("password"),claims.get("account").toString());
        return Result.success();
    }

    /*
     * 删除用户
     */
    @DeleteMapping("/deteleUser")
    public Result deleteById(@PathVariable Integer userId){
        userService.deleteById(userId);
        return Result.success();
    }
}
