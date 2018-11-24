package com.bluegrass.service.impl;


import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.ArticleMapper;
import com.bluegrass.dao.ArticlePriseMapper;
import com.bluegrass.pojo.Article;
import com.bluegrass.pojo.ArticlePrise;
import com.bluegrass.pojo.ArticleWithBLOBs;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IArticlePriseService;
import com.bluegrass.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticlePriseServiceImpl implements IArticlePriseService {

    @Autowired
    ArticlePriseMapper articlePriseMapper;

    @Autowired
    ArticleMapper articleMapper;



    /**
     * 检查传入的articeId是否存在
     * @param articleId
     * @return
     */
    private ServerResponse checkArticleId(Integer articleId) {
        if (articleId == null){
            return ServerResponse.createByErrorMessage("aerticleId为空");
        }
        ArticleWithBLOBs articleWithBLOBs = articleMapper.selectByPrimaryKey(articleId);
        if (articleWithBLOBs == null){
            return ServerResponse.createByErrorMessage("文章不存在");
        }
        return ServerResponse.createBySuccess();
    }


    /**
     * 文章点赞
     * @param user
     * @param articleId
     * @return
     */
    public ServerResponse addArticlePrise(User user, Integer articleId){
        if (articleId == null){
            return ServerResponse.createByErrorMessage("articleId为空");
        }
        ServerResponse serverResponse = checkArticleId(articleId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int num = articlePriseMapper.selectByUserIdAndArticleId(user.getUserId(),articleId);
        if (num > 0){
            return ServerResponse.createByErrorMessage("已经赞过了，不能重复赞");
        }
        ArticlePrise articlePrise = new ArticlePrise();
        articlePrise.setArticleId(articleId);
        articlePrise.setUserId(user.getUserId());
        int count = articlePriseMapper.insert(articlePrise);
        if (count > 0){

            int count1 = articleMapper.increasePrise(articleId);
            if (count1 > 0) {
                return ServerResponse.createBySuccess("点赞成功");
            }
        }
        return ServerResponse.createByErrorMessage("点赞失败");
    }

    /**
     * 获取文章赞的个数
     * @param articleId
     * @return
     */
    public ServerResponse getArticlePriseNum(Integer articleId){
        if (articleId == null){
            return ServerResponse.createByErrorMessage("articleId为空");
        }
        ServerResponse serverResponse = checkArticleId(articleId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int num = articleMapper.selectArticlePriseNum(articleId);
        return ServerResponse.createBySuccess(num);
    }


    /**
     * 取消赞
     * @param user
     * @param articleId
     * @return
     */
    public ServerResponse deleteArticlePrise(User user, Integer articleId){
        if (articleId == null){
            return ServerResponse.createByErrorMessage("articleId为空");
        }
        ServerResponse serverResponse = checkArticleId(articleId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int count = articlePriseMapper.deleteByUserIdAndArticleId(user.getUserId(),articleId);
        if (count > 0){

            int count1 = articleMapper.decreasePrise(articleId);
            if (count1 > 0) {
                return ServerResponse.createBySuccess("取消赞成功");
            }
        }
        return ServerResponse.createByErrorMessage("取消赞失败");
    }

}
