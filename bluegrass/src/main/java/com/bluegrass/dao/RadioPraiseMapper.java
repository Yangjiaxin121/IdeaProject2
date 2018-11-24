package com.bluegrass.dao;

import com.bluegrass.pojo.RadioPraise;
import org.apache.ibatis.annotations.Param;

public interface RadioPraiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RadioPraise record);

    int insertSelective(RadioPraise record);

    RadioPraise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RadioPraise record);

    int updateByPrimaryKey(RadioPraise record);

    int deleteByRadioId(Integer radioId);

    int selectByUserIdAndRadioId(@Param("userId") String userId, @Param("radioId") Integer radioId);

    int deleteByUserIdAndRadioId(@Param("userId") String userId, @Param("radioId") Integer radioId);
}