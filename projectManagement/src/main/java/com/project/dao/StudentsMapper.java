package com.project.dao;

import com.project.pojo.Students;
import org.apache.ibatis.annotations.Param;

public interface StudentsMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Students record);

    int insertSelective(Students record);

    Students selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Students record);

    int updateByPrimaryKey(Students record);

    int checkNumber(String number);

    Students selectLogin(@Param("number") String number, @Param("password") String password);

    int checkTel(String tel);

    String selectQuestionByUsername(String number);

    int checkAnswer(@Param("number") String number, @Param("question") String question, @Param("answer") String answer);

    int updatePasswordByStudentsNumber(@Param("number") String number, @Param("md5Password") String md5Password);

    int checkPassword(@Param("password") String password, @Param("number") String number);

    int checkTelByStudentsId(@Param("tel") String tel, @Param("id") Integer id);

    int deleteByNumber(String number);

    Students selectByNumber(String number);
}