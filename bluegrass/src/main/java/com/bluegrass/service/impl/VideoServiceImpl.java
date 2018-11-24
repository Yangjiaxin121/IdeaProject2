package com.bluegrass.service.impl;


import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.VideoCommentMapper;
import com.bluegrass.dao.VideoMapper;
import com.bluegrass.dao.VideoPraiseMapper;
import com.bluegrass.pojo.ArticleWithBLOBs;
import com.bluegrass.pojo.User;
import com.bluegrass.pojo.Video;
import com.bluegrass.service.IUserService;
import com.bluegrass.service.IVideoService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VideoServiceImpl implements IVideoService {


    @Autowired
    VideoMapper videoMapper;

    @Autowired
    IUserService iUserService;

    @Autowired
    VideoCommentMapper videoCommentMapper;

    @Autowired
    VideoPraiseMapper videoPraiseMapper;


    /**
     * 上传视频
     * @param user
     * @param video
     * @return
     */
    public ServerResponse add(User user, Video video){
        if (video.getDetail() == null){
            return ServerResponse.createByErrorMessage("video的detail不能为空");
        }
        video.setPraise(0);
        video.setUserId(user.getUserId());
        video.setVideoId(0);
        int count = videoMapper.insert(video);
        if (count > 0){
            return ServerResponse.createBySuccess("创建视频成功");
        }
        return ServerResponse.createByErrorMessage("创建视频失败");
    }


    /**
     * 查找我的所有视频
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse searchMyAllVideo(User user, int pageNum, int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<Video> videoList = videoMapper.selectByUserId(user.getUserId());
        PageInfo pageInfo = new PageInfo(videoList);

        return ServerResponse.createBySuccess(pageInfo);
    }


    /**
     * 通过userId来查询视频
     * @param userId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse searchByUserId(Integer userId, int pageNum, int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        ServerResponse serverResponse = iUserService.checkUserId(userId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        List<Video> videoList = videoMapper.selectByUserId(userId);
        PageInfo pageInfo = new PageInfo(videoList);
        return ServerResponse.createBySuccess(pageInfo);
    }


    /**
     * 根据videoId来删除video
     * @param user
     * @param videoId
     * @return
     */
    public ServerResponse deleteByVideoId(User user, Integer videoId){
        if (videoId == null){
            return ServerResponse.createByErrorMessage("videoId不能为空");
        }
        int count = videoMapper.deleteByUserIdAndVideoId(user.getUserId(),videoId);
        if (count > 0){
            int count1 = videoCommentMapper.deleteByVideoId(videoId);
            int count2 = videoPraiseMapper.deleteByVideoId(videoId);
            if (count1>0 && count2>0){
                return ServerResponse.createBySuccessMessage("删除成功");
            }

        }
        return ServerResponse.createByErrorMessage("删除失败");
    }


    /**
     * 检查videoId是否正确
     * @param videoId
     * @return
     */
    public ServerResponse checkVideoId(Integer videoId){
        if (videoId == null){
            return ServerResponse.createByErrorMessage("videoId不能为空");
        }
        int count = videoMapper.checkVideoId(videoId);

        if (count > 0){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("videoId错误");
    }



}



























