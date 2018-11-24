package com.bluegrass.service;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.ArticleComments;
import com.bluegrass.pojo.User;
import com.github.pagehelper.PageInfo;

public interface IArticleCommentsService {

    ServerResponse addComment(User user, ArticleComments articleComments);

    ServerResponse<PageInfo> listComments(Integer articleId, int pageNum, int pageSize);

    ServerResponse deleteComments(User user, Integer articleCommentsId);

    ServerResponse<Integer> getArticleCommentsNum(Integer articleId);

    }
