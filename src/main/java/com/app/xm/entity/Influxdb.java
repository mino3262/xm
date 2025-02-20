package com.app.xm.entity;

import java.sql.Timestamp;

public class Influxdb {
    // 数据库连接ID
    private Integer id;
    // 用户ID
    private Integer userId;
    // 连接名称
    private String connectionName;
    // InfluxDB地址
    private String influxdbUrl;
    // InfluxDB用户名
    private String influxdbUsername;
    // InfluxDB密码
    private String influxdbPassword;
    // InfluxDB数据库名称
    private String influxdbDatabase;
    // 最后连接时间
    private Timestamp lastConnectionTime;
    // 数据获取查询语句
    private String dataFetchQuery;
    // 是否激活
    private Boolean isActive;
    // 创建时间
    private Timestamp createTime;
    // 更新时间
    private Timestamp updateTime;
    // 备注
    private String remark;

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getUserId() {
        return userId;
    }
    public void setUserId(Integer userId) {
        this.userId = userId;
    }
    public String getConnectionName() {
        return connectionName;
    }
    public void setConnectionName(String connectionName) {
        this.connectionName = connectionName;
    }
    public String getInfluxdbUrl() {
        return influxdbUrl;
    }
    public void setInfluxdbUrl(String influxdbUrl) {
        this.influxdbUrl = influxdbUrl;
    }
    public String getInfluxdbUsername() {
        return influxdbUsername;
    }
    public void setInfluxdbUsername(String influxdbUsername) {
        this.influxdbUsername = influxdbUsername;
    }
    public String getInfluxdbPassword() {
        return influxdbPassword;
    }
    public void setInfluxdbPassword(String influxdbPassword) {
        this.influxdbPassword = influxdbPassword;
    }
    public String getInfluxdbDatabase() {
        return influxdbDatabase;
    }
    public void setInfluxdbDatabase(String influxdbDatabase) {
        this.influxdbDatabase = influxdbDatabase;
    }
    public Timestamp getLastConnectionTime() {
        return lastConnectionTime;
    }
    public void setLastConnectionTime(Timestamp lastConnectionTime) {
        this.lastConnectionTime = lastConnectionTime;
    }
    public String getDataFetchQuery() {
        return dataFetchQuery;
    }
    public void setDataFetchQuery(String dataFetchQuery) {
        this.dataFetchQuery = dataFetchQuery;
    }
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public Timestamp getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }
    public Timestamp getUpdateTime() {
        return updateTime;
    }
    public void setUpdateTime(Timestamp updateTime) {
        this.updateTime = updateTime;
    }
    public String getRemark() {
        return remark;
    }
    public void setRemark(String remark) {
        this.remark = remark;
    }
}
