package com.bluegrass.pojo;

import java.util.Date;

public class Radio {
    private Integer id;

    private Integer radioId;

    private String detail;

    private Integer praise;

    private Date createTime;

    public Radio(Integer id, Integer radioId, String detail, Integer praise, Date createTime) {
        this.id = id;
        this.radioId = radioId;
        this.detail = detail;
        this.praise = praise;
        this.createTime = createTime;
    }

    public Radio() {
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

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}