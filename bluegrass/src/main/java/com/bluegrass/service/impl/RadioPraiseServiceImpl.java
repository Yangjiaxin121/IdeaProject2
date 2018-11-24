package com.bluegrass.service.impl;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.RadioMapper;
import com.bluegrass.dao.RadioPraiseMapper;
import com.bluegrass.pojo.RadioPraise;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IRadioPraiseService;
import com.bluegrass.service.IRadioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RadioPraiseServiceImpl implements IRadioPraiseService {


    @Autowired
    IRadioService iRadioService;

    @Autowired
    RadioPraiseMapper radioPraiseMapper;

    @Autowired
    RadioMapper radioMapper;


    /**
     * 点赞
     * @param user
     * @param radioId
     * @return
     */
    public ServerResponse addRadioPraise(User user, Integer radioId){
        ServerResponse serverResponse = iRadioService.checkRadioId(radioId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int count = radioPraiseMapper.selectByUserIdAndRadioId(String.valueOf(user.getUserId()),radioId);
        if (count > 0){
            return ServerResponse.createByErrorMessage("已经赞过了，不能重复赞");
        }
        RadioPraise radioPraise = new RadioPraise();
        radioPraise.setUserId(String.valueOf(user.getUserId()));
        radioPraise.setRadioId(radioId);
        int count1 = radioPraiseMapper.insert(radioPraise);
        if (count1>0){
            int count2 = radioMapper.increasePraise(radioId);
            if (count2>0){
                return ServerResponse.createBySuccess("点赞成功");
            }
        }
        return ServerResponse.createByErrorMessage("点赞失败");
    }

    /**
     * 查询Radio的赞数
     * @param radioId
     * @return
     */
    public ServerResponse getRadioPraiseNum(Integer radioId){
        ServerResponse serverResponse = iRadioService.checkRadioId(radioId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int num = radioMapper.selectRadioPriseNum(radioId);

        return ServerResponse.createBySuccess(num);
    }


    /**
     * 取消赞
     * @param user
     * @param radioId
     * @return
     */
    public ServerResponse deleteRadioPraise(User user, Integer radioId){

        ServerResponse serverResponse = iRadioService.checkRadioId(radioId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int count = radioPraiseMapper.deleteByUserIdAndRadioId(String.valueOf(user.getUserId()),radioId);
        if (count>0){
            int count1 = radioMapper.decreasePraise(radioId);
            if (count1>0){
                return ServerResponse.createBySuccess("取消赞成功");
            }
        }
        return ServerResponse.createByErrorMessage("取消赞失败");
    }
}






















