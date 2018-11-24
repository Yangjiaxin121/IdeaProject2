package com.bluegrass.service.impl;


import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.RadioCommentMapper;
import com.bluegrass.dao.RadioMapper;
import com.bluegrass.dao.RadioPraiseMapper;
import com.bluegrass.pojo.Radio;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IRadioService;
import com.bluegrass.service.IUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RadioServiceImpl implements IRadioService {

    @Autowired
    RadioMapper radioMapper;

    @Autowired
    IUserService iUserService;

    @Autowired
    RadioCommentMapper radioCommentMapper;

    @Autowired
    RadioPraiseMapper radioPraiseMapper;


    /**
     * 上传radio成功
     * @param user
     * @param radio
     * @return
     */
    public ServerResponse add(User user, Radio radio){
        if (radio.getDetail() == null){
            return ServerResponse.createByErrorMessage("detail不能为空");
        }
        radio.setRadioId(user.getUserId());
        radio.setPraise(0);
        int count = radioMapper.insert(radio);
        if (count>0){
            return ServerResponse.createBySuccess("上传radio成功");
        }
        return ServerResponse.createByErrorMessage("上传radio失败");
    }


    /**
     * 查询自己的radio
     * @param user
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse searchMyAllRadio(User user, int pageNum, int pageSize){

        PageHelper.startPage(pageNum, pageSize);
        List<Radio> radioList = radioMapper.selectByUserId(user.getUserId());
        PageInfo pageInfo = new PageInfo(radioList);

        return ServerResponse.createBySuccess(pageInfo);
    }


    /**
     * 根据userId查询Radio
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
        List<Radio> radioList = radioMapper.selectByUserId(userId);

        PageInfo pageInfo = new PageInfo(radioList);
        return ServerResponse.createBySuccess(pageInfo);
    }


    /**
     * 通过radioId删除自己的某个radio
     * @param user
     * @param radioId
     * @return
     */
    public ServerResponse deleteByRadioId(User user, Integer radioId){
        ServerResponse serverResponse = checkRadioId(radioId);
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        int count = radioMapper.deleteByUserIdAndRadioId(user.getUserId(), radioId);
        if (count > 0){
            int count1 = radioCommentMapper.deleteByRadioId(radioId);
            int count2 = radioPraiseMapper.deleteByRadioId(radioId);
            if (count1>0 && count2>0){
                return ServerResponse.createBySuccess("删除成功");
            }
        }
        return ServerResponse.createByErrorMessage("删除失败");

    }


    /**
     * 检查radioId是否存在
     * @param radioId
     * @return
     */
    public ServerResponse checkRadioId(Integer radioId){
        if (radioId == null){
            return ServerResponse.createByErrorMessage("radioId不能为空");
        }
        int count = radioMapper.checkRadioId(radioId);
        if (count > 0){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByErrorMessage("radioId错误");
    }
}




























