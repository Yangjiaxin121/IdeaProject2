package com.project.service;

import com.project.common.ServerResponse;
import com.project.pojo.Projects;
import com.project.pojo.Teachers;

public interface ITeacherService {

    ServerResponse<Teachers> login(String number, String password);

    ServerResponse<String> selectQuestion(String number);

    ServerResponse<String> checkAnswer(String number, String question, String answer);

    ServerResponse<String> forgetRestPassword(String number, String passwordNew, String forgetToken);

    ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Teachers teachers);

    ServerResponse<Teachers> updateInformation(Teachers teachers);

    ServerResponse addProject(Teachers teachers, Projects projects);

    ServerResponse deleteProject(Integer projectId, Teachers teachers);

    ServerResponse updateProject(Projects projects, Teachers teachers);

    ServerResponse setGroupLeader(Teachers teachers, Integer projectId, String studentNumber);

    ServerResponse setStatus(Teachers teachers, Integer projectId, String status);

    ServerResponse getTeacherApplied(Teachers teachers);

    }
