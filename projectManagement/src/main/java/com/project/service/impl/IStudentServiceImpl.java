package com.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.project.common.Const;
import com.project.common.ServerResponse;
import com.project.common.TokenCache;
import com.project.dao.ProjectsMapper;
import com.project.dao.StudentsMapper;
import com.project.pojo.Projects;
import com.project.pojo.Students;
import com.project.service.IStudentsService;
import com.project.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Service
public class IStudentServiceImpl implements IStudentsService {

    @Autowired
    StudentsMapper studentsMapper;

    @Autowired
    ProjectsMapper projectsMapper;


    /**
     * 学生登录
     * @param number
     * @param password
     * @return
     */
    public ServerResponse<Students> login(String number, String password){
        int count = studentsMapper.checkNumber(number);
        if (count == 0){
            return ServerResponse.createByErrorMessage("学号不存在");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        Students students = studentsMapper.selectLogin(number,md5Password);
        if (students == null){
            return ServerResponse.createByErrorMessage("用户名或密码错误");
        }
        students.setsPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",students);
    }

    /**
     * 用户注册
     * @param students
     * @return
     */
    public ServerResponse<String> register(Students students){
        ServerResponse validResponse = checkValid(students.getsNumber(), Const.NUMBER);
        if (!validResponse.isSuccess()){
            return validResponse;
        }

        validResponse = checkValid(students.getsTelephone(), Const.TEL);
        if (!validResponse.isSuccess()){
            return validResponse;
        }
        //md5加密
        String md5Password = MD5Util.MD5EncodeUtf8(students.getsPassword());
        students.setsPassword(md5Password);
        int count = studentsMapper.insert(students);
        if (count == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    /**
     * 查看是否存在该用户
     * @param str
     * @param type
     * @return
     */
    public ServerResponse<String> checkValid(String str, String type){
        if (StringUtils.isNotBlank(type)){
            //开始校验
            if (Const.NUMBER.equals(type)){
                int resultcount = studentsMapper.checkNumber(str);
                if (resultcount > 0){
                    return ServerResponse.createByErrorMessage("学号已存在");
                }
            }
            if (Const.TEL.equals(type)){
                int resultcount = studentsMapper.checkTel(str);
                if (resultcount > 0){
                    return ServerResponse.createByErrorMessage("电话号码已存在");
                }
            }

        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }


    /**
     * 查询用户问题
     * @param number
     * @return
     */
    public ServerResponse<String> selectQuestion(String number){
        ServerResponse response = checkValid(number, Const.NUMBER);
        if (response.isSuccess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String question = studentsMapper.selectQuestionByUsername(number);
        if (StringUtils.isBlank(question)){
            return ServerResponse.createByErrorMessage("找回密码的问题是空的");
        }
        return ServerResponse.createBySuccess(question);
    }

    /**
     * 检查问题答案是否正确,并将forgetToken放在本地，防止横向越权
     * @param number
     * @param question
     * @param answer
     * @return
     */
    @Override
    public ServerResponse<String> checkAnswer(String number, String question, String answer) {
        int count = studentsMapper.checkAnswer(number,question,answer);
        System.out.println("count="+count);
        if (count > 0){
            //回答正确

            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+number,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("回答错误");
    }

    /**
     * 忘记密码重置密码
     * @param number
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @Override
    public ServerResponse<String> forgetRestPassword(String number, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)){
            return ServerResponse.createByErrorMessage("参数错误，token需要传递");
        }
        ServerResponse response = checkValid(number, Const.NUMBER);
        if (response.isSuccess()){
            return ServerResponse.createByErrorMessage("请输入有效的学号");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+number);
        if (StringUtils.isBlank(token)){
            return ServerResponse.createByErrorMessage("token无效或已过期");
        }
        if (StringUtils.equals(forgetToken,token)){
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            Students students = new Students();
            students.setsNumber(number);
            students.setsPassword(md5Password);
            int count = studentsMapper.updatePasswordByStudentsNumber(number,md5Password);
            if (count > 0){
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }

        }else{
            return ServerResponse.createByErrorMessage("token错误，请重新获取充值密码的token");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");

    }


    /**
     * 重置密码（登录后）
     * @param passwordOld
     * @param passwordNew
     * @param students
     * @return
     */
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, Students students){
        int count = studentsMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld),students.getsNumber());
        if (count == 0){
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        students.setsPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int count1 = studentsMapper.updateByPrimaryKeySelective(students);
        if (count1 > 0){
            return ServerResponse.createBySuccessMessage("修改密码成功");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }



    /**
     * 更新用户信息
     * @param students
     * @return
     */
    @Override
    public ServerResponse<Students> updateInformation(Students students) {
        //uusername是不能被更新的
        //email也要进行校验，校验新的email是不是已经存在，并且不能是当前用户的
        int resultCount = studentsMapper.checkTelByStudentsId(students.getsTelephone(),students.getId());
        if (resultCount > 0){
            return ServerResponse.createByErrorMessage("电话号码已存在，请更换电话号码尝试再更新");
        }

        int count = studentsMapper.updateByPrimaryKeySelective(students);
        System.out.println("这是后面的");
        if (count > 0){
            return ServerResponse.createBySuccess("更新个人信息成功",students);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }


    /**
     * 学生加入项目
     * @param students
     * @param projectId
     * @return
     */
    public ServerResponse studentApply(Students students, Integer projectId){
        if (projectId == null){
            return ServerResponse.createByErrorMessage("projects不能为空");
        }
        Projects projects = projectsMapper.selectByPrimaryKey(projectId);
        if (projects == null){
            return ServerResponse.createByErrorMessage("project不存在");
        }
        String group = projects.getpGroup();
        if (group != null){
            if (group.contains(students.getsNumber())){
                return ServerResponse.createByErrorMessage("已将加入过了，不能重复加入");
            }
//            String[] groupMember = group.split(",");
//            if (groupMember.length >= Integer.parseInt(projects.getpNumber())){
//                return ServerResponse.createByErrorMessage("项目成员已满");
//            }
        }

        if (group == null){
            group = students.getsNumber();
        } else{
            group = new StringBuffer(group).append(",").append(students.getsNumber()).toString();
        }
        projects.setpGroup(group);
        projects.setpNumber(String.valueOf(Integer.parseInt(projects.getpNumber())+1));
        int count = projectsMapper.updateByPrimaryKeySelective(projects);
        if (count > 0){
            String attendProjects = students.getsRemarks();
            if (attendProjects == null){
                attendProjects = String.valueOf(projects.getId());
            }else{
                attendProjects = new StringBuffer(attendProjects).append(",").append(projects.getId()).toString();
            }
            students.setsRemarks(attendProjects);
            int count1 = studentsMapper.updateByPrimaryKeySelective(students);
            if (count1 > 0){
                return ServerResponse.createBySuccess("加入项目成功");
            }
        }
        return ServerResponse.createByErrorMessage("加入项目失败");
    }


    /**
     * 获取已经参加的项目
     * @param students
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse getApplied(Students students, int pageNum, int pageSize){
        Students students1 = studentsMapper.selectByNumber(students.getsNumber());
        if (students1 == null){
            return ServerResponse.createByErrorMessage("student为空");
        }
        String attendProjects = students.getsRemarks();
        String[] attendProjectsItem = attendProjects.split(",");
        List<Projects> projectsList = Lists.newArrayList();
        for (String attend: attendProjectsItem) {
            Projects projects = projectsMapper.selectByPrimaryKey(Integer.parseInt(attend));
            projectsList.add(projects);
        }

        return ServerResponse.createBySuccess(projectsList);
//        return ServerResponse.createBySuccess("已参加的project");
    }

}

























