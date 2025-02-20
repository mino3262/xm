package com.app.xm.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.xm.entity.Influxdb;
import com.app.xm.mapper.InfluxdbMapper;

import jakarta.annotation.Resource;


@Service
public class InfluxdbService {
    @Resource
    private InfluxdbMapper influxdbMapper;

    // 根据ID查询连接信息
    public Influxdb findById(Integer id) {
        return influxdbMapper.selectById(id);
    }

    // 查询所有连接信息
    public List<Influxdb> findAll() {
        System.out.println(influxdbMapper.selectAll());
        return influxdbMapper.selectAll();
    }

    // 根据用户ID查询该用户的所有连接信息
    public List<Influxdb> findByUserId(Integer userId) {
        return influxdbMapper.selectByUserId(userId);
    }

    // 插入一条新的连接信息
    public Influxdb save(Influxdb influxdbUserConnections) {
        influxdbMapper.insert(influxdbUserConnections);
        return influxdbUserConnections;
    }

    // 根据ID更新连接信息
    public Influxdb updateById(Integer id, Influxdb updatedConnection) {
        updatedConnection.setId(id);
        influxdbMapper.updateById(updatedConnection);
        return updatedConnection;
    }

    // 根据ID删除连接信息
    public void deleteById(Integer id) {
        influxdbMapper.deleteById(id);
    }
}
