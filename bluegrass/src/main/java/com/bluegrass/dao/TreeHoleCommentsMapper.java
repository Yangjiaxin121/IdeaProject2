package com.bluegrass.dao;

import com.bluegrass.pojo.TreeHoleComments;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface TreeHoleCommentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TreeHoleComments record);

    int insertSelective(TreeHoleComments record);

    TreeHoleComments selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TreeHoleComments record);

    int updateByPrimaryKey(TreeHoleComments record);

    List<TreeHoleComments> selectByTreeHoleId(Integer treeHoleId);

    int deleteByUserIdAndTreeHoleCommentsId(@Param("userId") Integer userId, @Param("treeHoleCommentsId") Integer treeHoleCommentsId);

    int selectTreeHoleCommentsNum(Integer treeHoleId);
}