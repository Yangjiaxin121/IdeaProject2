package com.bluegrass.dao;

import com.bluegrass.pojo.ArticlePrise;
import org.apache.ibatis.annotations.Param;

public interface ArticlePriseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ArticlePrise record);

    int insertSelective(ArticlePrise record);

    ArticlePrise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ArticlePrise record);

    int updateByPrimaryKey(ArticlePrise record);

    int deleteByUserIdAndArticleId(@Param("userId") Integer userId,@Param("articleId") Integer articleId);

    int deleteByArticleId(Integer articleId);

    int selectByUserIdAndArticleId(@Param("userId") Integer userId,@Param("articleId") Integer articleId);
}