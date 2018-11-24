package com.bluegrass.service.impl;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.VideoMapper;
import com.bluegrass.dao.VideoPraiseMapper;
import com.bluegrass.pojo.User;
import com.bluegrass.pojo.VideoPraise;
import com.bluegrass.service.IVideoPraiseService;
import com.bluegrass.service.IVideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class VideoPraiseServiceImpl implements IVideoPraiseService {

    @Autowired
    IVideoService iVideoService;

    @Autowired
    VideoPraiseMapper videoPraiseMapper;

    @Autowired
    VideoMapper videoMapper;


    /**
     * video点赞
     * @param user
     * @param videoId
     * @return
     */
    public ServerResponse addVideoPraise(User user, Integer videoId){
        ServerResponse serverResponse = iVideoService.checkVideoId(videoId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int count = videoPraiseMapper.selectByUserIdAndVideoId(user.getUserId(),videoId);
        if (count > 0){
            return ServerResponse.createByErrorMessage("已经赞过了，不能重复赞");
        }
        VideoPraise videoPraise = new VideoPraise();
        videoPraise.setUserId(user.getUserId());
        videoPraise.setVideoId(videoId);
        int count1 = videoPraiseMapper.insert(videoPraise);
        if (count1 > 0){
            int count2 = videoMapper.increasePraise(videoId);
            if (count2>0){
                return ServerResponse.createBySuccess("点赞成功");
            }
        }
        return ServerResponse.createByErrorMessage("点赞失败");
    }


    /**
     * 获取当前视频的赞数
     * @param videoId
     * @return
     */
    public ServerResponse getVideoPraiseNum(Integer videoId){
        ServerResponse serverResponse = iVideoService.checkVideoId(videoId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int num = videoMapper.selectVideoPraiseNum(videoId);

        return ServerResponse.createBySuccess(num);

    }


    /**
     * 取消赞
     * @param user
     * @param videoId
     * @return
     */
    public ServerResponse deleteVideoPraise(User user, Integer videoId){
        ServerResponse serverResponse = iVideoService.checkVideoId(videoId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int count = videoPraiseMapper.deleteByUserIdAndVideoId(user.getUserId(),videoId);

        if (count>0){

            int count1 = videoMapper.decreasePraise(videoId);
            if (count1>0){
                return ServerResponse.createBySuccess("取消赞成功");
            }
        }

        return ServerResponse.createByErrorMessage("取消赞失败");

    }


}

















