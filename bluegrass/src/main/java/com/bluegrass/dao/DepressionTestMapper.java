package com.bluegrass.dao;

import com.bluegrass.pojo.DepressionTest;

import java.util.List;

public interface DepressionTestMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(DepressionTest record);

    int insertSelective(DepressionTest record);

    DepressionTest selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(DepressionTest record);

    int updateByPrimaryKeyWithBLOBs(DepressionTest record);

    int updateByPrimaryKey(DepressionTest record);

    List<DepressionTest> selectAll();
}