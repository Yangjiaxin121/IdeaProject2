package com.bluegrass.service.impl;


import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.ArticleCommentsMapper;
import com.bluegrass.dao.ArticleMapper;
import com.bluegrass.dao.ArticlePriseMapper;
import com.bluegrass.pojo.Article;
import com.bluegrass.pojo.ArticleWithBLOBs;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IArticleService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements IArticleService {

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    ArticleCommentsMapper articleCommentsMapper;

    @Autowired
    ArticlePriseMapper articlePriseMapper;


    /**
     * viewcount浏览次数的增加
     * @param articleId
     * @return
     */
    public ServerResponse increaseViewCount(Integer articleId){
        if (articleId == null){
            return ServerResponse.createByErrorMessage("articleId不能为空");
        }
        int count = articleMapper.increaseViewCount(articleId);
        if (count>0){
            return ServerResponse.createBySuccess("浏览次数+1");
        }
        return ServerResponse.createByErrorMessage("未知错误");
    }

    /**
     * 根据articleId获取article
     * @param articleId
     * @return
     */
    public ServerResponse getArticleById(Integer articleId){
        if (articleId == null){
            return ServerResponse.createByErrorMessage("articleId为空");
        }
        ArticleWithBLOBs articleWithBLOBs = articleMapper.selectByPrimaryKey(articleId);
        return ServerResponse.createBySuccess(articleWithBLOBs);
    }


    /**
     * 修改或保存文章
     * @param user
     * @param
     * @return
     */
    public ServerResponse<String> saveOrUpdateArticle(User user, ArticleWithBLOBs articleWithBLOBs){
        if(articleWithBLOBs != null){
            articleWithBLOBs.setUserId(user.getUserId());
            if (articleWithBLOBs.getId() == null){
                articleWithBLOBs.setArticlePraise(0);
                int count = articleMapper.insert(articleWithBLOBs);
                if (count > 0){
                    return ServerResponse.createBySuccess("添加文章成功");
                }else {
                    return ServerResponse.createByErrorMessage("添加文章失败");
                }
            }else{
                int count1 = articleMapper.updateByPrimaryKeySelective(articleWithBLOBs);
                if (count1 > 0){
                    return ServerResponse.createBySuccess("修改文章成功");
                }
                return ServerResponse.createBySuccessMessage("修改文章失败");
            }
        }
        return ServerResponse.createByErrorMessage("参数错误");
    }

    /**
     * 通过userId查询文章
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> searchByUserId(Integer userId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);

        List<Article> articleList = articleMapper.searchByUserId(userId);
        PageInfo pageInfo = new PageInfo(articleList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 根据文章标题查询
     * @param articleTitle
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> searchByArticleTitle(String articleTitle, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if (StringUtils.isNotBlank(articleTitle)) {
            articleTitle = new StringBuilder().append("%").append(articleTitle).append("%").toString();
        }
        List<Article> articleList = articleMapper.searchByArticleTitle(articleTitle);
        PageInfo pageInfo = new PageInfo(articleList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 根据articleId删除Article
     * @param articleId
     * @return
     */
    public ServerResponse<String> deleteByArticleId(Integer articleId){
        int count = articleMapper.deleteByPrimaryKey(articleId);
        if (count > 0){
            int count1 = articleCommentsMapper.deleteByArticleId(articleId);
            int count2 = articlePriseMapper.deleteByArticleId(articleId);
            if (count1>0 && count2>0){
                return ServerResponse.createBySuccessMessage("删除成功");
            }
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

}
