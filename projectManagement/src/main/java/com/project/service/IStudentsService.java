package com.project.service;

import com.github.pagehelper.PageInfo;
import com.project.common.ServerResponse;
import com.project.pojo.Students;

public interface IStudentsService {

    ServerResponse<Students> login(String number, String password);

    ServerResponse<String> register(Students students);

    ServerResponse<String> checkValid(String str, String type);

    ServerResponse<String> checkAnswer(String number, String question, String answer);

    ServerResponse<String> selectQuestion(String number);

    ServerResponse<String> forgetRestPassword(String number, String passwordNew, String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Students students);

    ServerResponse<Students> updateInformation(Students students);

    ServerResponse studentApply(Students students, Integer projectId);

    ServerResponse<PageInfo> getApplied(Students students, int pageNum, int pageSize);
    }
