package com.bluegrass.dao;

import com.bluegrass.pojo.Radio;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RadioMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Radio record);

    int insertSelective(Radio record);

    Radio selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Radio record);

    int updateByPrimaryKey(Radio record);

    List<Radio> selectByUserId(Integer userId);

    int checkRadioId(Integer radioId);

    int deleteByUserIdAndRadioId(@Param("userId") Integer userId, @Param("radioId") Integer radioId);

    int increasePraise(Integer radioId);

    int selectRadioPriseNum(Integer radioId);

    int decreasePraise(Integer radioId);
}