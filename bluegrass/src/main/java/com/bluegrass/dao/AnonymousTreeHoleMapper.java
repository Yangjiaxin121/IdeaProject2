package com.bluegrass.dao;

import com.bluegrass.pojo.AnonymousTreeHole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AnonymousTreeHoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AnonymousTreeHole record);

    int insertSelective(AnonymousTreeHole record);

    AnonymousTreeHole selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(AnonymousTreeHole record);

    int updateByPrimaryKeyWithBLOBs(AnonymousTreeHole record);

    int updateByPrimaryKey(AnonymousTreeHole record);

    List<AnonymousTreeHole> selectAll();

    List<AnonymousTreeHole> selectByUserId(Integer userId);

    int deleteByUserIdAndTreeHoleId(@Param("userId") Integer userId, @Param("treeHoleId") Integer treeHoleId);

    int increasePraise(Integer treeHoleId);

    Integer selectTreeHolePraiseNum(Integer treeHoleId);

    int decreasePrise(Integer treeHoleId);
}