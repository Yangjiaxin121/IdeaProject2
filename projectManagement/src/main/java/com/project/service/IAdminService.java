package com.project.service;

import com.github.pagehelper.PageInfo;
import com.project.common.ServerResponse;
import com.project.pojo.Administrator;
import com.project.pojo.Projects;
import com.project.pojo.Teachers;

public interface IAdminService {

    ServerResponse login(String name, String password);

    ServerResponse<String> register(Administrator administrator);

    ServerResponse deleteProject(Integer projectId);

    ServerResponse<PageInfo> selectAllProject(int pageNum, int pageSize);

    ServerResponse updateProject(Projects projects);

    ServerResponse deleteStudent(String number);

    ServerResponse getStudent(String number);

    ServerResponse deleteTeacher(String number);

    ServerResponse getTeacher(String number);

    ServerResponse addTeacher(Teachers teachers);

    ServerResponse checkTeacherNumber(String number);

    ServerResponse<PageInfo> getProjectTeacher(Integer projectId, int pageNum, int pageSize);

    ServerResponse<PageInfo> getProjectStudent(Integer projectId, int pageNum, int pageSize);

    ServerResponse getProjectByStatus(String status);

    }
