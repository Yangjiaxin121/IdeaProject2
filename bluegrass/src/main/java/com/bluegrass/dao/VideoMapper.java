package com.bluegrass.dao;

import com.bluegrass.pojo.Video;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Video record);

    int insertSelective(Video record);

    Video selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Video record);

    int updateByPrimaryKey(Video record);

    List<Video> selectByUserId(Integer userId);

    int deleteByUserIdAndVideoId(@Param("userId") Integer userId, @Param("videoId") Integer videoId);

    int checkVideoId(Integer videoId);

    int increasePraise(Integer videoId);

    int selectVideoPraiseNum(Integer videoId);

    int decreasePraise(Integer videoId);
}