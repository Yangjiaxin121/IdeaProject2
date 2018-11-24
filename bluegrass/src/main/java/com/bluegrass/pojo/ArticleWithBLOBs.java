package com.bluegrass.pojo;

import java.util.Date;

public class ArticleWithBLOBs extends Article {
    private String articleSubImage;

    private String articleDetail;

    public ArticleWithBLOBs(Integer id, Integer userId, String articleTitle, String articleSubtitle, String articleMainImage, Integer articlePraise, Integer articleViewcount, Date articleCreateTime, Date articleUpdateTime, String articleSubImage, String articleDetail) {
        super(id, userId, articleTitle, articleSubtitle, articleMainImage, articlePraise, articleViewcount, articleCreateTime, articleUpdateTime);
        this.articleSubImage = articleSubImage;
        this.articleDetail = articleDetail;
    }

    public ArticleWithBLOBs() {
        super();
    }

    public String getArticleSubImage() {
        return articleSubImage;
    }

    public void setArticleSubImage(String articleSubImage) {
        this.articleSubImage = articleSubImage == null ? null : articleSubImage.trim();
    }

    public String getArticleDetail() {
        return articleDetail;
    }

    public void setArticleDetail(String articleDetail) {
        this.articleDetail = articleDetail == null ? null : articleDetail.trim();
    }
}