package com.bluegrass.pojo;

import java.util.Date;

public class Article {
    private Integer id;

    private Integer userId;

    private String articleTitle;

    private String articleSubtitle;

    private String articleMainImage;

    private Integer articlePraise;

    private Integer articleViewcount;

    private Date articleCreateTime;

    private Date articleUpdateTime;

    public Article(Integer id, Integer userId, String articleTitle, String articleSubtitle, String articleMainImage, Integer articlePraise, Integer articleViewcount, Date articleCreateTime, Date articleUpdateTime) {
        this.id = id;
        this.userId = userId;
        this.articleTitle = articleTitle;
        this.articleSubtitle = articleSubtitle;
        this.articleMainImage = articleMainImage;
        this.articlePraise = articlePraise;
        this.articleViewcount = articleViewcount;
        this.articleCreateTime = articleCreateTime;
        this.articleUpdateTime = articleUpdateTime;
    }

    public Article() {
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

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle == null ? null : articleTitle.trim();
    }

    public String getArticleSubtitle() {
        return articleSubtitle;
    }

    public void setArticleSubtitle(String articleSubtitle) {
        this.articleSubtitle = articleSubtitle == null ? null : articleSubtitle.trim();
    }

    public String getArticleMainImage() {
        return articleMainImage;
    }

    public void setArticleMainImage(String articleMainImage) {
        this.articleMainImage = articleMainImage == null ? null : articleMainImage.trim();
    }

    public Integer getArticlePraise() {
        return articlePraise;
    }

    public void setArticlePraise(Integer articlePraise) {
        this.articlePraise = articlePraise;
    }

    public Integer getArticleViewcount() {
        return articleViewcount;
    }

    public void setArticleViewcount(Integer articleViewcount) {
        this.articleViewcount = articleViewcount;
    }

    public Date getArticleCreateTime() {
        return articleCreateTime;
    }

    public void setArticleCreateTime(Date articleCreateTime) {
        this.articleCreateTime = articleCreateTime;
    }

    public Date getArticleUpdateTime() {
        return articleUpdateTime;
    }

    public void setArticleUpdateTime(Date articleUpdateTime) {
        this.articleUpdateTime = articleUpdateTime;
    }
}