package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.Article;
import com.bluegrass.pojo.ArticleWithBLOBs;
import com.bluegrass.pojo.User;
import com.github.pagehelper.PageInfo;

public interface IArticleService {

    ServerResponse<String> saveOrUpdateArticle(User user, ArticleWithBLOBs  articleWithBLOBs);

    ServerResponse<PageInfo> searchByUserId(Integer userId, int pageNum, int pageSize);

    ServerResponse<PageInfo> searchByArticleTitle(String articleTitle, int pageNum, int pageSize);

    ServerResponse<String> deleteByArticleId(Integer articleId);

    ServerResponse getArticleById(Integer articleId);

    ServerResponse increaseViewCount(Integer articleId);

    }
