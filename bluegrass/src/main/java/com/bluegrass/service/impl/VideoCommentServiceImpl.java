package com.bluegrass.service.impl;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.VideoCommentMapper;
import com.bluegrass.dao.VideoMapper;
import com.bluegrass.pojo.User;
import com.bluegrass.pojo.VideoComment;
import com.bluegrass.service.IVideoCommentService;
import com.bluegrass.service.IVideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class VideoCommentServiceImpl implements IVideoCommentService {

    @Autowired
    IVideoService iVideoService;

    @Autowired
    VideoCommentMapper videoCommentMapper;


    /**
     * 添加videoComment
     * @param user
     * @param videoComment
     * @return
     */
    public ServerResponse addComments(User user, VideoComment videoComment) {
        if (videoComment.getComment() == null){
            return ServerResponse.createByErrorMessage("videoComment不能为空");
        }
        ServerResponse serverResponse = iVideoService.checkVideoId(videoComment.getVideoId());
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        videoComment.setUserId(user.getUserId());
        int count = videoCommentMapper.insert(videoComment);
        if (count > 0){
            return ServerResponse.createBySuccess("添加videoComment成功");
        }
        return ServerResponse.createByErrorMessage("添加videoComment成功");

    }


    /**
     * 列举所有评论
     * @param videoId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse<PageInfo> listComments(Integer videoId, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        ServerResponse serverResponse = iVideoService.checkVideoId(videoId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        List<VideoComment> videoCommentList = videoCommentMapper.selectByVideoId(videoId);
        PageInfo pageInfo = new PageInfo(videoCommentList);
        return ServerResponse.createBySuccess(pageInfo);

    }


    /**
     * 输出评论
     * @param user
     * @param videoCommentId
     * @return
     */
    public ServerResponse deleteComments(User user, Integer videoCommentId){
        if (videoCommentId == null){
            return ServerResponse.createByErrorMessage("videoCommentId不能为空");
        }
        int count = videoCommentMapper.deleteByUserIdAndVideoCommentId(user.getUserId(),videoCommentId);
        if (count > 0){
            return ServerResponse.createByErrorMessage("删除videoComment成功");
        }
        return ServerResponse.createByErrorMessage("删除videoComment失败");
    }


    /**
     * 获取video评论的个数
     * @param videoId
     * @return
     */
    public ServerResponse getCommentsNum(Integer videoId){
        ServerResponse serverResponse = iVideoService.checkVideoId(videoId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int num = videoCommentMapper.selectVideoCommentNum(videoId);
        return ServerResponse.createBySuccess(num);
    }



}


























