package com.bluegrass.dao;

import com.bluegrass.pojo.ResultRecommend;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ResultRecommendMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ResultRecommend record);

    int insertSelective(ResultRecommend record);

    ResultRecommend selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ResultRecommend record);

    int updateByPrimaryKey(ResultRecommend record);

    List<ResultRecommend> selectByValue(Integer value);
}