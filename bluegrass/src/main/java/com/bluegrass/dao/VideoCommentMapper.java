package com.bluegrass.dao;

import com.bluegrass.pojo.VideoComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface VideoCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(VideoComment record);

    int insertSelective(VideoComment record);

    VideoComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(VideoComment record);

    int updateByPrimaryKey(VideoComment record);

    int deleteByVideoId(Integer videoId);

    List<VideoComment> selectByVideoId(Integer videoId);

    int deleteByUserIdAndVideoCommentId(@Param("userId") Integer userId, @Param("videoCommentId") Integer videoCommentId);

    int selectVideoCommentNum(Integer videoId);
}