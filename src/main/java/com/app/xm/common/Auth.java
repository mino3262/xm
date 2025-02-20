package com.app.xm.common;

import java.util.Map;

import com.app.xm.entity.Role;
import com.app.xm.entity.User;
import com.app.xm.service.RoleService;
import com.app.xm.service.UserService;

import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Auth{

    private  static UserService userService;
    @Autowired
    public  void setUserService(UserService userService) {
        Auth.userService = userService;
    }
    private static RoleService roleService;
    @Autowired
    public  void setRoleService(RoleService roleService) {
        Auth.roleService = roleService;
    }

    // 获取当前登录用户信息
    public  User getUserInfo(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token == null) {
            // 可以根据实际情况选择抛出异常或者返回null等处理方式，这里返回null表示未获取到token
            return null;
        }
        Map<String, Object> claims = Jwt.parseToken(token);
        if (claims == null || claims.get("account") == null) {
            // 同样根据情况处理，比如token解析失败或者没有获取到账号信息的情况
            return null;
        }
        //System.out.println(claims.get("account").toString());
        return userService.selectByAccount(claims.get("account").toString());
    }

    // 获取当前登录用户的权限信息
    public  Role getUserRole(HttpServletRequest request) {
        User user = getUserInfo(request);
        if (user == null) {
            // 如果没有获取到用户信息，直接返回null，也可以根据业务抛异常等
            return null;
        }
        return roleService.selectRolesByUserId(user.getUserId());
    }

    // 判断当前用户是否具有指定权限
    public  boolean hasPermission(HttpServletRequest request) {
        Role role = getUserRole(request);
        if ("admin".equals(role.getRoleName())) {
            return true;
        }
        else return false;
    }
}