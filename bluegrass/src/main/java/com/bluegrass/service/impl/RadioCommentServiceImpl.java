package com.bluegrass.service.impl;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.RadioCommentMapper;
import com.bluegrass.pojo.RadioComment;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IRadioCommentService;
import com.bluegrass.service.IRadioService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class RadioCommentServiceImpl implements IRadioCommentService {

    @Autowired
    IRadioService iRadioService;

    @Autowired
    RadioCommentMapper radioCommentMapper;


    /**
     * 添加评论
     * @param user
     * @param radioComment
     * @return
     */
    public ServerResponse addComments(User user, RadioComment radioComment){
        if (radioComment.getComment() == null){
            return ServerResponse.createByErrorMessage("comments不能为空");
        }
        ServerResponse serverResponse = iRadioService.checkRadioId(Integer.parseInt(radioComment.getRadioId()));
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        radioComment.setUserId(String.valueOf(user.getUserId()));
        int count = radioCommentMapper.insert(radioComment);
        if (count>0){
            return ServerResponse.createBySuccess("comments插入成功");
        }
        return ServerResponse.createByErrorMessage("插入comments失败");

    }


    /**
     * 列举指定radioId下的所有comments
     * @param radioId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse listComments(Integer radioId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        ServerResponse serverResponse = iRadioService.checkRadioId(radioId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        List<RadioComment> radioCommentList = radioCommentMapper.selectByRadioId(String.valueOf(radioId));
        PageInfo pageInfo = new PageInfo(radioCommentList);
        return ServerResponse.createBySuccess(pageInfo);

    }


    /**
     * 删除评论
     * @param user
     * @param radioCommentId
     * @return
     */
    public ServerResponse deleteComments(User user, Integer radioCommentId){
        if (radioCommentId == null){
            return ServerResponse.createByErrorMessage("radioCommentId不能为空");
        }

        int count = radioCommentMapper.deleteByUserIdAndId(String.valueOf(user.getUserId()), String.valueOf(radioCommentId));
        if (count>0){
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }


    /**
     * 获取评论数
     * @param radioId
     * @return
     */
    public ServerResponse getCommentsNum(Integer radioId){
        ServerResponse serverResponse = iRadioService.checkRadioId(radioId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int num = radioCommentMapper.selectCommentNum(String.valueOf(radioId));
        return ServerResponse.createBySuccess(num);
    }


}




















