package com.bluegrass.service.impl;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.ArticleCommentsMapper;
import com.bluegrass.dao.ArticleMapper;
import com.bluegrass.pojo.ArticleComments;
import com.bluegrass.pojo.ArticleWithBLOBs;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IArticleCommentsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleCommentsServiceImpl implements IArticleCommentsService {

    @Autowired
    ArticleCommentsMapper articleCommentsMapper;

    @Autowired
    ArticleMapper articleMapper;


    /**
     * 添加评论
     * @param user
     * @param articleComments
     * @return
     */
    public ServerResponse addComment(User user, ArticleComments articleComments){
        if (articleComments.getComment() != null && articleComments.getArticleId() != null){
            articleComments.setUserId(user.getUserId());
            ServerResponse serverResponse = checkArticleId(articleComments.getArticleId());
            if (!serverResponse.isSuccess()){
                return serverResponse;
            }
            int count = articleCommentsMapper.insert(articleComments);
            if (count >0){
                return ServerResponse.createBySuccess("添加评论成功");
            }
        }
        return ServerResponse.createByErrorMessage("添加评论失败");
    }

    /**
     * 检查传入的articeId是否存在
     * @param articleId
     * @return
     */
    private ServerResponse checkArticleId(Integer articleId) {
        if (articleId == null){
            return ServerResponse.createByErrorMessage("aerticleId为空");
        }
        ServerResponse serverResponse = checkArticleId(articleId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        ArticleWithBLOBs articleWithBLOBs = articleMapper.selectByPrimaryKey(articleId);
        if (articleWithBLOBs == null){
            return ServerResponse.createByErrorMessage("文章不存在");
        }
        return ServerResponse.createBySuccess();
    }


    /**
     * 列出当前文章下的所有评论
     * @param articleId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> listComments(Integer articleId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum,pageSize);
        if (articleId == null){
            return ServerResponse.createByErrorMessage("articleId不能为空");
        }
        ServerResponse serverResponse = checkArticleId(articleId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        List<ArticleComments> articleCommentsList = articleCommentsMapper.selectByArticleId(articleId);
        PageInfo pageInfo = new PageInfo(articleCommentsList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 删除指定文章评论
     * @param user
     * @param articleCommentsId
     * @return
     */
    public ServerResponse deleteComments(User user, Integer articleCommentsId){
        if (articleCommentsId != null){
            System.out.println("detest1");
            int count = articleCommentsMapper.deleteByUserIdAndArticleCommentsId(user.getUserId(),articleCommentsId);
            if (count > 0){
                return ServerResponse.createBySuccess("删除成功");
            }
            System.out.println("te2");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 获取指定文章下的评论数
     * @param articleId
     * @return
     */
    public ServerResponse<Integer> getArticleCommentsNum(Integer articleId){
        if (articleId == null){
            return ServerResponse.createByErrorMessage("articleId为空");
        }
        ServerResponse serverResponse = checkArticleId(articleId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int count = articleCommentsMapper.selectArticleCommentsNum(articleId);
        return ServerResponse.createBySuccess(count);
    }
}









