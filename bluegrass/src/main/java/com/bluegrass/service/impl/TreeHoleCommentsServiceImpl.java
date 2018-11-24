package com.bluegrass.service.impl;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.AnonymousTreeHoleMapper;
import com.bluegrass.dao.TreeHoleCommentsMapper;
import com.bluegrass.pojo.AnonymousTreeHole;
import com.bluegrass.pojo.ArticleWithBLOBs;
import com.bluegrass.pojo.TreeHoleComments;
import com.bluegrass.pojo.User;
import com.bluegrass.service.ITreeHoleCommentsService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TreeHoleCommentsServiceImpl implements ITreeHoleCommentsService {

    @Autowired
    TreeHoleCommentsMapper treeHoleCommentsMapper;

    @Autowired
    AnonymousTreeHoleMapper anonymousTreeHoleMapper;

    /**
     * 添加树洞
     * @param user
     * @param treeHoleComments
     * @return
     */
    public ServerResponse addComment(User user, TreeHoleComments treeHoleComments){
        if (treeHoleComments.getComment()!=null && treeHoleComments.getTreeHoleId()!=null){
            treeHoleComments.setUserId(user.getUserId());
            ServerResponse serverResponse = checkTreeHoleId(treeHoleComments.getTreeHoleId());
            if (!serverResponse.isSuccess()){
                return serverResponse;
            }
            int count = treeHoleCommentsMapper.insert(treeHoleComments);
            if (count>0){
                return ServerResponse.createBySuccess("评论成功");
            }
        }
        return ServerResponse.createByErrorMessage("添加评论失败");
    }

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
     * 列举当前树洞所有评论
     * @param treeHoleId
     * @param pageSize
     * @param pageNum
     * @return
     */
    public ServerResponse listComments(Integer treeHoleId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        if (treeHoleId == null){
            return ServerResponse.createByErrorMessage("treeHoleId不能为空");
        }
        ServerResponse serverResponse = checkTreeHoleId(treeHoleId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        List<TreeHoleComments> treeHoleCommentsList = treeHoleCommentsMapper.selectByTreeHoleId(treeHoleId);
        for (TreeHoleComments treeHoleComments:treeHoleCommentsList) {
            treeHoleComments.setUserId(null);
        }
        PageInfo pageInfo = new PageInfo(treeHoleCommentsList);
        return ServerResponse.createBySuccess(pageInfo);
    }

    /**
     * 删除指定的评论
     * @param user
     * @param treeHoleCommentsId
     * @return
     */
    public ServerResponse deleteComments(User user, Integer treeHoleCommentsId){

        if (treeHoleCommentsId == null){
            return ServerResponse.createByErrorMessage("treeHoleCommentsId为空");
        }

        int count = treeHoleCommentsMapper.deleteByUserIdAndTreeHoleCommentsId(user.getUserId(),treeHoleCommentsId);
        if (count>0){
            return ServerResponse.createBySuccess("删除评论成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 获取当前树洞的评论个数
     * @param treeHoleId
     * @return
     */
    public ServerResponse getTreeHoleCommentsNum(Integer treeHoleId){
        if (treeHoleId == null){
            return ServerResponse.createByErrorMessage("treeHoleId不能为空");
        }
        ServerResponse serverResponse = checkTreeHoleId(treeHoleId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int num = treeHoleCommentsMapper.selectTreeHoleCommentsNum(treeHoleId);
        return ServerResponse.createBySuccess(num);
    }
}










