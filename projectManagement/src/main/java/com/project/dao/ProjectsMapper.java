package com.project.dao;

import com.project.pojo.Projects;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProjectsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Projects record);

    int insertSelective(Projects record);

    Projects selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Projects record);

    int updateByPrimaryKey(Projects record);

    List<Projects> selectAllProject();

    int deleteByProjectIdAndTeacherNumber(@Param("projectId") Integer projectId, @Param("number") String number);

    int updateByProjectIdAndTeacherNumber(Projects record);

    List<Projects> selectByStatus(String status);

    List<Projects> selectByFirstTutor(String gettNumber);
}