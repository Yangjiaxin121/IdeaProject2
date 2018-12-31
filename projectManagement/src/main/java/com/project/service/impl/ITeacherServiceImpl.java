package com.project.service.impl;

import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;
import com.project.common.Const;
import com.project.common.ServerResponse;
import com.project.common.TokenCache;
import com.project.dao.ProjectsMapper;
import com.project.dao.StudentsMapper;
import com.project.dao.TeachersMapper;
import com.project.pojo.Projects;
import com.project.pojo.Students;
import com.project.pojo.Teachers;
import com.project.service.ITeacherService;
import com.project.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.UUID;

@Service
public class ITeacherServiceImpl implements ITeacherService {

    @Autowired
    TeachersMapper teachersMapper;

    @Autowired
    ProjectsMapper projectsMapper;

    @Autowired
    StudentsMapper studentsMapper;

    /**
     * 教师登录
     *
     * @param number
     * @param password
     * @return
     */
    public ServerResponse<Teachers> login(String number, String password) {
        int count = teachersMapper.checkNumber(number);
        if (count == 0) {
            return ServerResponse.createByErrorMessage("职工号不存在");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        Teachers teachers = teachersMapper.selectLogin(number, md5Password);
        if (teachers == null) {
            return ServerResponse.createByErrorMessage("职工号或密码错误");
        }
        teachers.settPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功", teachers);
    }


    /**
     * 查询用户问题
     *
     * @param number
     * @return
     */
    public ServerResponse<String> selectQuestion(String number) {
        int count = teachersMapper.checkNumber(number);
        if (count <= 0) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String question = teachersMapper.selectQuestionByUsername(number);
        if (StringUtils.isBlank(question)) {
            return ServerResponse.createByErrorMessage("找回密码的问题是空的");
        }
        return ServerResponse.createBySuccess(question);
    }

    /**
     * 检查问题答案是否正确,并将forgetToken放在本地，防止横向越权
     *
     * @param number
     * @param question
     * @param answer
     * @return
     */
    @Override
    public ServerResponse<String> checkAnswer(String number, String question, String answer) {
        int count = teachersMapper.checkAnswer(number, question, answer);
        System.out.println("count=" + count);
        if (count > 0) {
            //回答正确

            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX + number, forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("回答错误");
    }

    /**
     * 忘记密码重置密码
     *
     * @param number
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @Override
    public ServerResponse<String> forgetRestPassword(String number, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)) {
            return ServerResponse.createByErrorMessage("参数错误，token需要传递");
        }
        int count = teachersMapper.checkNumber(number);
        if (count <= 0) {
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX + number);
        if (StringUtils.isBlank(token)) {
            return ServerResponse.createByErrorMessage("token无效或已过期");
        }
        if (StringUtils.equals(forgetToken, token)) {
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            int count1 = teachersMapper.updatePasswordByTeachersNumber(number, md5Password);
            if (count1 > 0) {
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }

        } else {
            return ServerResponse.createByErrorMessage("token错误，请重新获取充值密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");

    }


    /**
     * 重置密码（登录后）
     *
     * @param passwordOld
     * @param passwordNew
     * @param teachers
     * @return
     */
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Teachers teachers) {
        int count = teachersMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld), teachers.gettNumber());
        if (count == 0) {
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        teachers.settPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int count1 = teachersMapper.updateByPrimaryKeySelective(teachers);
        if (count1 > 0) {
            return ServerResponse.createBySuccessMessage("修改密码成功");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }


    /**
     * 更新用户信息
     *
     * @param teachers
     * @return
     */
    @Override
    public ServerResponse<Teachers> updateInformation(Teachers teachers) {

        int count = teachersMapper.updateByPrimaryKeySelective(teachers);
        System.out.println("这是后面的");
        if (count > 0) {
            return ServerResponse.createBySuccess("更新个人信息成功", teachers);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }

    /**
     * 教师添加project
     *
     * @param teachers
     * @param projects
     * @return
     */
    public ServerResponse addProject(Teachers teachers, Projects projects) {
        if (projects == null) {
            return ServerResponse.createByErrorMessage("project不能为空");
        }

        projects.setpNumber("0");     //初始人数为0人
        projects.setpRemarks(Const.XinFaBu);
        projects.setpFirsttutor(teachers.gettNumber());
        int count = projectsMapper.insert(projects);
        if (count > 0) {

            return ServerResponse.createBySuccess("创建project成功", projects);
        }
        return ServerResponse.createByErrorMessage("创建project失败");
    }


    /**
     * 第一指导老师可以删除项目
     *
     * @param projectId
     * @param teachers
     * @return
     */
    public ServerResponse deleteProject(Integer projectId, Teachers teachers) {
        if (projectId == null) {
            return ServerResponse.createByErrorMessage("projectId不能为空");
        }
        Projects projects = projectsMapper.selectByPrimaryKey(projectId);
        if (projects == null){
            return ServerResponse.createByErrorMessage("project不存在");
        }
        int count = projectsMapper.deleteByProjectIdAndTeacherNumber(projectId, teachers.gettNumber());
        if (count > 0) {
            boolean flag = true;
            String group = projects.getpGroup();
            String[] members = group.split(",");
            for (String studentNumber: members) {
                Students students = studentsMapper.selectByNumber(studentNumber);
                String studentApplied = students.getsRemarks();
                String[] applieds = studentApplied.split(",");
                String newApplied = null;
                for (String s1: applieds) {
                    System.out.println(s1);
                    if (!s1.equals(projectId.toString())){
                        if (newApplied == null) {
                            newApplied = s1;
                        } else {
                            newApplied = new StringBuffer(newApplied).append(",").append(s1).toString();
                        }
                    }
                }
                System.out.println(newApplied);
                students.setsRemarks(newApplied);
                int count1 = studentsMapper.updateByPrimaryKey(students);
                if (count1 < 0){
                    flag = false;
                }
            }
            if (flag == true){
                return ServerResponse.createBySuccess("删除项目成功");
            }

        }
        return ServerResponse.createByErrorMessage("删除项目失败");
    }


    /**
     * 第一指导老师可以更新项目
     *
     * @param projects
     * @param teachers
     * @return
     */
    public ServerResponse updateProject(Projects projects, Teachers teachers) {
        if (projects == null) {
            return ServerResponse.createByErrorMessage("projects不能为空");
        }
        Projects projects1 = projectsMapper.selectByPrimaryKey(projects.getId());
        if (projects1 == null) {
            return ServerResponse.createByErrorMessage("projectId错误");
        } else if (!projects1.getpFirsttutor().equals(teachers.gettNumber())) {
            return ServerResponse.createByErrorMessage("无权限");
        }
        int count = projectsMapper.updateByProjectIdAndTeacherNumber(projects);
        if (count > 0) {
            return ServerResponse.createBySuccess("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }


    /**
     * 教师设置组长
     *
     * @param teachers
     * @param projectId
     * @param studentNumber
     * @return
     */
    public ServerResponse setGroupLeader(Teachers teachers, Integer projectId, String studentNumber) {
        if (projectId == null || studentNumber == null) {
            return ServerResponse.createByErrorMessage("参数不能为空");
        }
        Projects projects = projectsMapper.selectByPrimaryKey(projectId);
        if (projects == null) {
            return ServerResponse.createByErrorMessage("projectId错误");
        } else if (!projects.getpFirsttutor().equals(teachers.gettNumber())) {
            return ServerResponse.createByErrorMessage("无权限");
        }
        String group = projects.getpGroup();
        boolean flag = group.contains(studentNumber);
        if (flag == false) {
            return ServerResponse.createByErrorMessage("学号错误");
        }
        projects.setpGroupleader(studentNumber);
        int count = projectsMapper.updateByProjectIdAndTeacherNumber(projects);
        if (count > 0) {
            return ServerResponse.createBySuccess("设置成功");
        }
        return ServerResponse.createByErrorMessage("设置失败");
    }


    /**
     * 设置项目状态
     *
     * @param teachers
     * @param projectId
     * @param status
     * @return
     */
    public ServerResponse setStatus(Teachers teachers, Integer projectId, String status) {
        if (projectId == null || status == null) {
            return ServerResponse.createByErrorMessage("参数不能为空");
        }
        Projects projects = projectsMapper.selectByPrimaryKey(projectId);
        if (projects == null) {
            return ServerResponse.createByErrorMessage("projectId错误");
        } else if (!projects.getpFirsttutor().equals(teachers.gettNumber())) {
            return ServerResponse.createByErrorMessage("无权限");
        }
        if (status == null || StringUtils.isEmpty(status)) {
            ServerResponse.createByErrorMessage("status不能为空");
        }
        projects.setpRemarks(status);
        int count = projectsMapper.updateByPrimaryKeySelective(projects);
        if (count > 0) {
            return ServerResponse.createBySuccess("设置状态成功");
        }
        return ServerResponse.createByErrorMessage("设置状态失败");

    }


    /**
     * 查询第一指导老师的项目
     *
     * @param teachers
     * @return
     */
    public ServerResponse getTeacherApplied(Teachers teachers) {

        List<Projects> projectsList = projectsMapper.selectByFirstTutor(teachers.gettNumber());

        return ServerResponse.createBySuccess(projectsList);
    }


    /**
     * 教师可以根据学生的学号将学生从project中删除
     * @param teachers
     * @param studentNumber
     * @param projectId
     * @return
     */
    public ServerResponse deleteStudentByStudentNumber(Teachers teachers, String studentNumber, Integer projectId) {
        if (projectId == null || studentNumber == null) {
            return ServerResponse.createByErrorMessage("参数不能为空");
        }
        Projects projects = projectsMapper.selectByPrimaryKey(projectId);
        if (projects == null) {
            return ServerResponse.createByErrorMessage("projectId错误");
        } else if (!projects.getpFirsttutor().equals(teachers.gettNumber())) {
            return ServerResponse.createByErrorMessage("无权限");
        }
        String group = projects.getpGroup();
        boolean flag = group.contains(studentNumber);
        if (flag == false) {
            return ServerResponse.createByErrorMessage("学号错误");
        }
      //  projects.setpGroupleader(studentNumber);
        String groupLeader = projects.getpGroupleader();
        projects.setpGroupleader(null);
        String[] members = group.split(",");
        String newGroup = null;
        for (String string: members) {
            if (!string.equals(studentNumber)){
                if (newGroup == null) {
                    newGroup = string;
                } else {
                    newGroup = new StringBuffer(newGroup).append(",").append(string).toString();
                }
            }

        }
        projects.setpGroup(newGroup);
        int count = projectsMapper.updateByPrimaryKey(projects);
        if (count > 0) {
            Students students = studentsMapper.selectByNumber(studentNumber);
            String studentApplied = students.getsRemarks();
            String[] applieds = studentApplied.split(",");
            String newApplied = null;
            for (String s1: applieds) {
                System.out.println(s1);
                if (!s1.equals(projectId.toString())){
                    if (newApplied == null) {
                        newApplied = s1;
                    } else {
                        newApplied = new StringBuffer(newApplied).append(",").append(s1).toString();
                    }
                }
            }
            System.out.println(newApplied);
            students.setsRemarks(newApplied);
            int count1 = studentsMapper.updateByPrimaryKey(students);
            if (count1 > 0){
                return ServerResponse.createBySuccess("设置成功");
            }

        }
        return ServerResponse.createByErrorMessage("设置失败");
    }




}











