package com.bluegrass.pojo;

import java.util.Date;

public class AnonymousTreeHole {
    private Integer id;

    private Integer userId;

    private Integer praise;

    private Integer treeHoleId;

    private Date createTime;

    private String detail;

    public AnonymousTreeHole(Integer id, Integer userId, Integer praise, Integer treeHoleId, Date createTime, String detail) {
        this.id = id;
        this.userId = userId;
        this.praise = praise;
        this.treeHoleId = treeHoleId;
        this.createTime = createTime;
        this.detail = detail;
    }

    public AnonymousTreeHole() {
        super();
    }

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

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Integer getTreeHoleId() {
        return treeHoleId;
    }

    public void setTreeHoleId(Integer treeHoleId) {
        this.treeHoleId = treeHoleId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    @Override
    public String toString() {
        return "AnonymousTreeHole{" +
                "id=" + id +
                ", userId=" + userId +
                ", praise=" + praise +
                ", treeHoleId=" + treeHoleId +
                ", createTime=" + createTime +
                ", detail='" + detail + '\'' +
                '}';
    }
}