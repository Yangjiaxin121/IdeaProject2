package com.bluegrass.pojo;

import java.util.Date;

public class ArticlePrise {
    private Integer id;

    private Integer articleId;

    private Integer userId;

    private Date createTime;

    public ArticlePrise(Integer id, Integer articleId, Integer userId, Date createTime) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
        this.createTime = createTime;
    }

    public ArticlePrise() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getArticleId() {
        return articleId;
    }

    public void setArticleId(Integer articleId) {
        this.articleId = articleId;
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