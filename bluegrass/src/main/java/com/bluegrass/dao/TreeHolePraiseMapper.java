package com.bluegrass.dao;

import com.bluegrass.pojo.TreeHolePraise;
import org.apache.ibatis.annotations.Param;

public interface TreeHolePraiseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TreeHolePraise record);

    int insertSelective(TreeHolePraise record);

    TreeHolePraise selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TreeHolePraise record);

    int updateByPrimaryKey(TreeHolePraise record);

    int selectByUserId(Integer userId);

    int deleteByUserIdAndTreeHoleId(@Param("userId") Integer userId, @Param("treeHoleId") Integer treeHoleId);
}