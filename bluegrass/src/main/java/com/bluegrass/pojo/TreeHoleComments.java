package com.bluegrass.pojo;

import java.util.Date;

public class TreeHoleComments {
    private Integer id;

    private Integer treeHoleId;

    private Integer userId;

    private String comment;

    private Date createTime;

    public TreeHoleComments(Integer id, Integer treeHoleId, Integer userId, String comment, Date createTime) {
        this.id = id;
        this.treeHoleId = treeHoleId;
        this.userId = userId;
        this.comment = comment;
        this.createTime = createTime;
    }

    public TreeHoleComments() {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}