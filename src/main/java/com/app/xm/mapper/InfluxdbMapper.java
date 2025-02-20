package com.app.xm.mapper;

import java.util.List;

import com.app.xm.entity.Influxdb;

   
public interface InfluxdbMapper {
    
// 根据ID查询连接信息
    Influxdb selectById(Integer id);

    // 查询所有连接信息
    List<Influxdb> selectAll();

    // 根据用户ID查询该用户的所有连接信息
    List<Influxdb> selectByUserId(Integer userId);

    // 插入一条新的连接信息
    int insert(Influxdb influxdbUserConnections);

    // 根据ID更新连接信息
    int updateById(Influxdb influxdbUserConnections);

    // 根据ID删除连接信息
    int deleteById(Integer id);

}
