package com.app.xm.entity;

import java.util.Map;

public class InfluxdbResult {
    private Long time;
    private Map<String, String> tags;
    private Map<String, Object> fields;
    
    public Long getTime() {
        return time;
    }
    public void setTime(Long time) {
        this.time = time;
    }
    public Map<String, String> getTags() {
        return tags;
    }
    public void setTags(Map<String, String> tags) {
        this.tags = tags;
    }
    public Map<String, Object> getFields() {
        return fields;
    }
    public void setFields(Map<String, Object> fields) {
        this.fields = fields;
    }

}
