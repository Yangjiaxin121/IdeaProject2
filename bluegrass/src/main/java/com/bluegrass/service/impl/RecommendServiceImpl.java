package com.bluegrass.service.impl;

import com.bluegrass.common.ServerResponse;
import com.bluegrass.dao.ResultRecommendMapper;
import com.bluegrass.pojo.ResultRecommend;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IRecommendService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecommendServiceImpl implements IRecommendService {

    @Autowired
    ResultRecommendMapper resultRecommendMapper;


    /**
     * 用户添加新的建议
     * @param user
     * @param resultRecommend
     * @return
     */
    public ServerResponse add(User user, ResultRecommend resultRecommend){
        if (resultRecommend == null){
            return ServerResponse.createByErrorMessage("recommend不能为空");
        }
        resultRecommend.setUserId(String.valueOf(user.getUserId()));
        int count = resultRecommendMapper.insert(resultRecommend);
        if (count > 0){
            return ServerResponse.createBySuccess("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");
    }


    /**
     * 用户更新recommend
     * @param user
     * @param resultRecommend
     * @return
     */
    public ServerResponse update(User user, ResultRecommend resultRecommend){
        if (resultRecommend == null){
            return ServerResponse.createByErrorMessage("recommend不能为空");
        }
        resultRecommend.setUserId(String.valueOf(user.getUserId()));
        String userId = String.valueOf(user.getUserId());
        if (!userId.equals(resultRecommend.getUserId())){
            return ServerResponse.createByErrorMessage("userId错误");
        }
        int count = resultRecommendMapper.updateByPrimaryKeySelective(resultRecommend);
        if (count > 0){
            return ServerResponse.createBySuccess("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }


    /**
     * 用户删除 recommend
     * @param user
     * @param recommendId
     * @return
     */
    public ServerResponse delete(User user, Integer recommendId){
        if (recommendId == null){
            return ServerResponse.createByErrorMessage("recommend不能为空");
        }
        ResultRecommend resultRecommend = resultRecommendMapper.selectByPrimaryKey(recommendId);
        if (resultRecommend == null || !resultRecommend.getUserId().equals(String.valueOf(user.getUserId()))){
            return ServerResponse.createByErrorMessage("recommendId错误");
        }
        int count = resultRecommendMapper.deleteByPrimaryKey(recommendId);
        if (count > 0){
            return ServerResponse.createByErrorMessage("删除成功");

        }
        return ServerResponse.createByErrorMessage("删除失败");
    }


    /**
     * 获取最接近value的五条数据
     * @param value
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse get(Integer value, int pageNum, int pageSize){
        PageHelper.startPage(pageNum, pageSize);
        if (value == null){
            return ServerResponse.createByErrorMessage("value不能为空");
        }
        List<ResultRecommend> resultRecommendList = resultRecommendMapper.selectByValue(value);
        PageInfo pageInfo = new PageInfo(resultRecommendList);
        return ServerResponse.createBySuccess(pageInfo);
    }
}
