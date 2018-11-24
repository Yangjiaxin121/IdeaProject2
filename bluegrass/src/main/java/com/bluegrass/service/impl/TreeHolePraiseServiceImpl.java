package com.bluegrass.service.impl;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.AnonymousTreeHoleMapper;
import com.bluegrass.dao.TreeHolePraiseMapper;
import com.bluegrass.pojo.AnonymousTreeHole;
import com.bluegrass.pojo.ArticlePrise;
import com.bluegrass.pojo.TreeHolePraise;
import com.bluegrass.pojo.User;
import com.bluegrass.service.ITreeHolePraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TreeHolePraiseServiceImpl implements ITreeHolePraiseService {


    @Autowired
    TreeHolePraiseMapper treeHolePraiseMapper;

    @Autowired
    AnonymousTreeHoleMapper anonymousTreeHoleMapper;



    /**
     * 检查treeHoleId是否存在
     * @param treeHoleId
     * @return
     */
    public ServerResponse checkTreeHoleId(Integer treeHoleId){
        if (treeHoleId == null){
            return ServerResponse.createByErrorMessage("treeHoleCommentsId为空");
        }
        AnonymousTreeHole anonymousTreeHole = anonymousTreeHoleMapper.selectByPrimaryKey(treeHoleId);
        if (anonymousTreeHole == null){
            return ServerResponse.createByErrorMessage("treeHoleCommentsId错误");
        }
        return ServerResponse.createBySuccess();
    }
    /**
     * 文章点赞
     * @param user
     * @param
     * @return
     */
    public ServerResponse addTreeHolePraise(User user, Integer treeHoleId){
        if (treeHoleId == null){
            return ServerResponse.createByErrorMessage("articleId为空");
        }
        ServerResponse serverResponse = checkTreeHoleId(treeHoleId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int num = treeHolePraiseMapper.selectByUserId(user.getUserId());
        if (num > 0){
            return ServerResponse.createByErrorMessage("已经赞过了，不能重复赞");
        }
        TreeHolePraise treeHolePraise = new TreeHolePraise();
        treeHolePraise.setUserId(user.getUserId());
        treeHolePraise.setTreeHoleId(treeHoleId);
        int count = treeHolePraiseMapper.insert(treeHolePraise);
        if (count > 0){
            int count1 = anonymousTreeHoleMapper.increasePraise(treeHoleId);
            if (count1>0){
                return ServerResponse.createBySuccess("点赞成功");
            }
        }
        return ServerResponse.createByErrorMessage("点赞失败");
    }

    /**
     * 获取文章赞的个数
     * @param
     * @return
     */
    public ServerResponse getTreeHolePriseNum(Integer treeHoleId){
        if (treeHoleId == null){
            return ServerResponse.createByErrorMessage("treeHoleId为空");
        }
        ServerResponse serverResponse = checkTreeHoleId(treeHoleId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        Integer num = anonymousTreeHoleMapper.selectTreeHolePraiseNum(treeHoleId);
        return ServerResponse.createBySuccess(num);
    }


    /**
     * 取消赞
     * @param user
     * @param
     * @return
     */
    public ServerResponse deleteTreeHolePrise(User user, Integer treeHoleId){
        if (treeHoleId == null){
            return ServerResponse.createByErrorMessage("treeHoleId为空");
        }
        ServerResponse serverResponse = checkTreeHoleId(treeHoleId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }

        int count = treeHolePraiseMapper.deleteByUserIdAndTreeHoleId(user.getUserId(),treeHoleId);
        if (count > 0){

            int count1 = anonymousTreeHoleMapper.decreasePrise(treeHoleId);
            if (count1 > 0) {
                return ServerResponse.createBySuccess("取消赞成功");
            }
        }
        return ServerResponse.createByErrorMessage("取消赞失败");
    }

}
