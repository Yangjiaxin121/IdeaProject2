package com.bluegrass.pojo;

import java.util.Date;

public class TreeHolePraise {
    private Integer id;

    private Integer treeHoleId;

    private Integer userId;

    private Date createTime;

    public TreeHolePraise(Integer id, Integer treeHoleId, Integer userId, Date createTime) {
        this.id = id;
        this.treeHoleId = treeHoleId;
        this.userId = userId;
        this.createTime = createTime;
    }

    public TreeHolePraise() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTreeHoleId() {
        return treeHoleId;
    }

    public void setTreeHoleId(Integer treeHoleId) {
        this.treeHoleId = treeHoleId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}