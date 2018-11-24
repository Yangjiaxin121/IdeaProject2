package com.bluegrass.pojo;

import java.util.Date;

public class ArticleComments {
    private Integer id;

    private Integer articleId;

    private Integer userId;

    private String comment;

    private Date createTime;

    public ArticleComments(Integer id, Integer articleId, Integer userId, String comment, Date createTime) {
        this.id = id;
        this.articleId = articleId;
        this.userId = userId;
        this.comment = comment;
        this.createTime = createTime;
    }

    public ArticleComments() {
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