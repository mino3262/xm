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
import org.springframework.web.bind.annotation.RestController;

import com.app.xm.common.Result;
import com.app.xm.common.Auth;
import com.app.xm.common.CustomInfluxdb;
import com.app.xm.common.Jwt;
import com.app.xm.entity.Influxdb;
import com.app.xm.entity.InfluxdbResult;
import com.app.xm.service.InfluxdbService;
import jakarta.servlet.http.HttpServletRequest;

import jakarta.annotation.Resource;

@RestController
@RequestMapping("/devices")
public class InfluxdbController {

    @Resource 
    private InfluxdbService influxdbService;
    @Resource Auth auth;
    

     // 根据ID查询连接信息
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        Influxdb connection = influxdbService.findById(id);
        if (connection!= null) {
            return Result.success(connection);
        } else {
            return Result.error("404", "未找到对应连接信息");
        }
    }

     // 根据ID查询数据库信息
     @GetMapping("Info/{id}")
     public Result getInfoById(@PathVariable String id) {
         Influxdb connection = influxdbService.findById(Integer.valueOf(id));
         CustomInfluxdb customInfluxdb = new CustomInfluxdb();
         List<InfluxdbResult> info = customInfluxdb.queryListData(connection);
         if (info!= null) {
             return Result.success(info);
         } else {
             return Result.error("404", "未找到对应数据库信息");
         }
     }

    // 查询所有连接信息
    @GetMapping
    public Result getAll(HttpServletRequest request) {
        List<Influxdb> connections;
        if(auth.hasPermission(request)){
            connections = influxdbService.findAll();
        }
        else{
            Integer userId = auth.getUserInfo(request).getUserId();
            connections = influxdbService.findByUserId(userId);
        }
        return Result.success(connections);
    }

    // 根据用户ID查询该用户的所有连接信息
    @GetMapping("/user/{user_id}")
    public Result getByUserId(@PathVariable Integer userId, HttpServletRequest request) {
        Auth auth = new Auth();
        if(!auth.hasPermission(request)){
            userId = auth.getUserInfo(request).getUserId();
        }
        List<Influxdb> connections = influxdbService.findByUserId(userId);
        return Result.success(connections);
    }

    // 插入一条新的连接信息
    @PostMapping
    public Result save(@RequestBody Influxdb influxdbUserConnections, HttpServletRequest request) {
        Auth auth = new Auth();
        influxdbUserConnections.setUserId(auth.getUserInfo(request).getUserId());
        Influxdb savedConnection = influxdbService.save(influxdbUserConnections);
        return Result.success(savedConnection);
    }

    // 根据ID更新连接信息
    @PutMapping("/{id}")
    public Result updateById(@PathVariable Integer id, @RequestBody Influxdb updatedConnection) {
        Influxdb result = influxdbService.updateById(id, updatedConnection);
        return Result.success(result);
    }

    // 根据ID删除连接信息
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id) {
        influxdbService.deleteById(id);
        return Result.success();
    }
}
