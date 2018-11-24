package com.bluegrass.dao;

import com.bluegrass.pojo.Article;
import com.bluegrass.pojo.ArticleWithBLOBs;

import java.util.List;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleWithBLOBs record);

    int insertSelective(ArticleWithBLOBs record);

    ArticleWithBLOBs selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleWithBLOBs record);

    int updateByPrimaryKeyWithBLOBs(ArticleWithBLOBs record);

    int updateByPrimaryKey(Article record);

    List<Article> searchByUserId(Integer userId);

    List<Article> searchByArticleTitle(String articleTitle);

    int increasePrise(Integer articleId);

    int selectArticlePriseNum(Integer articleId);

    int decreasePrise(Integer articleId);

    int increaseViewCount(Integer articleId);
}