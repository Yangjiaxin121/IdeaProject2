package com.bluegrass.dao;

import com.bluegrass.pojo.User;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    int checkUsername(String username);

    User selectLogin(@Param("username") String username,@Param("password") String password);

    int checkEmail(String email);

    String selectQuestionByUsername(String username);

    int checkAnswer(@Param("username") String username, @Param("question") String question, @Param("answer") String answer);

    int updatePasswordByUsername(@Param("username") String username, @Param("password") String password);

    int checkPassword(@Param("password") String password,@Param("userId") Integer userId);

    int checkEmailByUserId(@Param("userEmail") String userEmail,@Param("userId") Integer userId);

    int checkUserId(Integer userId);

    int updateResultByUserId(@Param("userId") Integer userId, @Param("result") String result);
}