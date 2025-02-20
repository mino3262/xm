package com.app.xm.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.app.xm.common.Jwt;
import com.app.xm.entity.Role;
import com.app.xm.entity.User;
import com.app.xm.exception.CustomException;
import com.app.xm.mapper.RoleMapper;
import com.app.xm.mapper.UserMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import jakarta.annotation.Resource;

@Service
public class UserService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RoleMapper roleMapper;

    public List<User> selectAll() {
        return userMapper.selectAll();
    }


    public PageInfo<User> selectPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<User> list = userMapper.selectAll();
        return PageInfo.of(list);
    }

    public User selectByAccount(String account) {
        return userMapper.selectByAccount(account);
    }

    public void addUser(User user) {
        userMapper.insert(user);
    }


    public void updateUser(User user) {
        userMapper.updateById(user);
    }

    public void updateByaccount(User user) {
        userMapper.updateByaccount(user);
    }


    public void deleteById(Integer userId) {
        userMapper.deleteById(userId);
    }

    /*
     * 登陆验证
    */
    public Map<String, Object> login(User user) {
        User dbUser = userMapper.selectByAccount(user.getAccount());
        if (dbUser == null) {  //数据库没有找到符合的账户
            throw new CustomException("500 ", "账号不存在");
        }
        if (!dbUser.getAccount().equals(user.getPassword())){    //密码不对应
            throw new CustomException("500 ", "密码错误");
        } else{
            Role dbRole = roleMapper.selectRolesByUserId(dbUser.getUserId());
            Map<String, Object> claims = new HashMap<>();
            claims.put("account", dbUser.getAccount());
            String token = Jwt.generateToken(claims);
            claims.put("roleName", dbRole.getRoleName());
            //claims.put("userId",dbUser.getUserId());
            claims.put("userName", dbUser.getUserName());
            claims.put("roleLabel",dbRole.getRoleLabel());
            Map<String, Object> data = new HashMap<>();
            data.put("info", claims);
            data.put("token", token);
            return data;
        }
    }


    /*
     * 用户注册
    */
    public void register(User user) {
        User dbUser = userMapper.selectByAccount(user.getAccount());
        if (dbUser != null) {  //账号已存在，
            throw new CustomException("500 ", "账号已存在");
        }
        if (user.getPassword() == null || user.getPassword().isEmpty() ){    //密码为空
           user.setPassword("123");
        }
        if (user.getUserName() == null || user.getUserName().isEmpty() ){    //用户名为空
            user.setUserName(user.getAccount());
        }
        LocalDateTime now = LocalDateTime.now();   //创建时间
        user.setCreateTime(Timestamp.valueOf(now));
        userMapper.insert(user);
        User roleUser = userMapper.selectByAccount(user.getAccount());
        userMapper.insertRole(roleUser.getUserId(), 2);
    }


    public void updatePassword(String  originpass,String password, String account) {
        User dbUser = userMapper.selectByAccount(account);
        if (dbUser == null) {  //数据库没有找到符合的账户
            throw new CustomException("500 ", "账号不存在");
        }
        if (!dbUser.getPassword().equals(originpass)){    //密码不对应
            throw new CustomException("500 ", "原始密码错误");
        } else{
            dbUser.setPassword(password);
            userMapper.updateByaccount(dbUser);
        }
    }
    
}
