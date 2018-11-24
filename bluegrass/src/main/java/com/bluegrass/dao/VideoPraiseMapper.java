package com.bluegrass.dao;

import com.bluegrass.pojo.VideoPraise;
import org.apache.ibatis.annotations.Param;

public interface VideoPraiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoPraise record);

    int insertSelective(VideoPraise record);

    VideoPraise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoPraise record);

    int updateByPrimaryKey(VideoPraise record);

    int selectByUserIdAndVideoId(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    int deleteByUserIdAndVideoId(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    int deleteByVideoId(Integer videoId);
}