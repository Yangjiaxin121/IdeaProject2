package com.bluegrass.dao;

import com.bluegrass.pojo.ArticleComments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ArticleCommentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticleComments record);

    int insertSelective(ArticleComments record);

    ArticleComments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticleComments record);

    int updateByPrimaryKey(ArticleComments record);

    List<ArticleComments> selectByArticleId(Integer articleId);

    int deleteByUserIdAndArticleCommentsId(@Param("userId") Integer userId, @Param("articleCommentsId") Integer articleCommentsId);

    int selectArticleCommentsNum(Integer articleId);

    int deleteByArticleId(Integer articleId);
}