package com.project.dao;

import com.project.pojo.Students;
import com.project.pojo.Teachers;
import org.apache.ibatis.annotations.Param;

public interface TeachersMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Teachers record);

    int insertSelective(Teachers record);

    Teachers selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Teachers record);

    int updateByPrimaryKey(Teachers record);

    int checkNumber(String number);

    Teachers selectLogin(@Param("number") String number, @Param("password") String password);

    String selectQuestionByUsername(String number);

    int checkAnswer(@Param("number") String number, @Param("question") String question, @Param("answer") String answer);

    int updatePasswordByTeachersNumber(@Param("number") String number, @Param("password") String password);

    int checkPassword(@Param("password") String password, @Param("number") String number);

    int deleteByNumber(String number);

    Teachers selectByNumber(String number);
}