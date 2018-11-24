package com.bluegrass.dao;

import com.bluegrass.pojo.RadioComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RadioCommentMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RadioComment record);

    int insertSelective(RadioComment record);

    RadioComment selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RadioComment record);

    int updateByPrimaryKey(RadioComment record);

    int deleteByRadioId(Integer radioId);

    List<RadioComment> selectByRadioId(String radioId);

    int deleteByUserIdAndId(@Param("userId") String userId, @Param("radioCommentId") String radioCommentId);

    int selectCommentNum(String radioId);
}