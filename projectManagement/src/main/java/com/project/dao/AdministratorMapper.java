package com.project.dao;

import com.project.pojo.Administrator;
import com.project.pojo.Students;
import org.apache.ibatis.annotations.Param;

public interface AdministratorMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Administrator record);

    int insertSelective(Administrator record);

    Administrator selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Administrator record);

    int updateByPrimaryKey(Administrator record);

    int checkNumber(String name);

    Administrator selectLogin(@Param("name") String name, @Param("password") String password);
}