package com.bluegrass.pojo;

import java.util.Date;

public class RadioPraise {
    private Integer id;

    private Integer radioId;

    private String userId;

    private Date createTime;

    public RadioPraise(Integer id, Integer radioId, String userId, Date createTime) {
        this.id = id;
        this.radioId = radioId;
        this.userId = userId;
        this.createTime = createTime;
    }

    public RadioPraise() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRadioId() {
        return radioId;
    }

    public void setRadioId(Integer radioId) {
        this.radioId = radioId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}