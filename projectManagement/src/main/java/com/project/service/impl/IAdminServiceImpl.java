package com.project.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.project.common.Const;
import com.project.common.ServerResponse;
import com.project.dao.AdministratorMapper;
import com.project.dao.ProjectsMapper;
import com.project.dao.StudentsMapper;
import com.project.dao.TeachersMapper;
import com.project.pojo.Administrator;
import com.project.pojo.Projects;
import com.project.pojo.Students;
import com.project.pojo.Teachers;
import com.project.service.IAdminService;
import com.project.util.MD5Util;
import com.project.vo.StudentVo;
import com.project.vo.TeacherVo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IAdminServiceImpl implements IAdminService {

    @Autowired
    AdministratorMapper administratorMapper;


    @Autowired
    ProjectsMapper projectsMapper;

    @Autowired
    StudentsMapper studentsMapper;

    @Autowired
    TeachersMapper teachersMapper;

    /**
     *
     * @param name
     * @param password
     * @return
     */
    public ServerResponse login(String name, String password){
        int count = administratorMapper.checkNumber(name);
        if (count == 0){
            return ServerResponse.createByErrorMessage("name不存在");
        }
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        Administrator administrator = administratorMapper.selectLogin(name,md5Password);
        if (administrator == null){
            return ServerResponse.createByErrorMessage("用户名或密码错误");
        }
        administrator.setAdminPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",administrator);
    }

    /**
     * 用户注册
     * @param administrator
     * @return
     */
    public ServerResponse<String> register(Administrator administrator){
        //md5加密
        String md5Password = MD5Util.MD5EncodeUtf8(administrator.getAdminPassword());
        administrator.setAdminPassword(md5Password);
        int count = administratorMapper.insert(administrator);
        if (count == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }


    /**
     * 删除项目
     * @param projectId
     * @return
     */
    public ServerResponse deleteProject(Integer projectId){
        if (projectId == null){
            return ServerResponse.createByErrorMessage("projectId不能为空");
        }
        int count = projectsMapper.deleteByPrimaryKey(projectId);
        if (count > 0){
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }


    /**
     * 查询全部项目
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse selectAllProject(int pageNum, int pageSize){
        List<Projects> projectsList = projectsMapper.selectAllProject();

        return ServerResponse.createBySuccess(projectsList);
    }


    /**
     * 更新project信息
     * @param projects
     * @return
     */
    public ServerResponse updateProject(Projects projects){
        if (projects == null){
            return ServerResponse.createByErrorMessage("projects不能为空");
        }
        int count = projectsMapper.updateByPrimaryKeySelective(projects);
        if (count > 0){
            return ServerResponse.createBySuccess("修改成功");
        }
        return ServerResponse.createByErrorMessage("修改失败");
    }


    /**
     * 根据学号删除学生
     * @param number
     * @return
     */
    public ServerResponse deleteStudent(String number){
        if (number == null){
            return ServerResponse.createByErrorMessage("学号不能为空");
        }
        int count = studentsMapper.deleteByNumber(number);
        if (count > 0){
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }


    /**
     * 根据学号获取学生信息
     * @param number
     * @return
     */
    public ServerResponse getStudent(String number){
        List<StudentVo> list=new ArrayList();
        if (number == null){
            return ServerResponse.createByErrorMessage("number不能为空");
        }
        Students students = studentsMapper.selectByNumber(number);
        if (students != null){
            StudentVo studentVo = assembleStudentVo(students);
            list.add(studentVo);
            return ServerResponse.createBySuccess("查询成功",list);
        }
        return ServerResponse.createByErrorMessage("查无此人");
    }

    /**
     * 根据学工号删除教师
     * @param number
     * @return
     */
    public ServerResponse deleteTeacher(String number){
        if (number == null){
            return ServerResponse.createByErrorMessage("学工号不能为空");
        }
        int count = teachersMapper.deleteByNumber(number);
        if (count > 0){
            return ServerResponse.createBySuccess("删除成功");
        }
        return ServerResponse.createByErrorMessage("删除失败");
    }

    /**
     * 根据学工号获取教师信息
     * @param number
     * @return
     */
    public ServerResponse getTeacher(String number){
        List <TeacherVo>list = new ArrayList();
        if (number == null){
            return ServerResponse.createByErrorMessage("number不能为空");
        }
        Teachers teachers = teachersMapper.selectByNumber(number);
        if (teachers != null){
            TeacherVo teacherVo = assembleTeacherVo(teachers);
            list.add(teacherVo);
            return ServerResponse.createBySuccess("查询成功",list);
        }
        return ServerResponse.createByErrorMessage("查无此人");
    }


    /**
     * 添加教师
     * @param teachers
     * @return
     */
    public ServerResponse addTeacher(Teachers teachers){
        if (teachers == null){
            return ServerResponse.createByErrorMessage("teacher信息不能为空");
        }
        ServerResponse serverResponse = checkTeacherNumber(teachers.gettNumber());
        if (!serverResponse.isSuccess()){
            return serverResponse;
        }
        String md5Password = MD5Util.MD5EncodeUtf8(teachers.gettPassword());
        teachers.settPassword(md5Password);
        int count = teachersMapper.insert(teachers);
        if (count > 0){
            return ServerResponse.createBySuccess("添加成功");
        }
        return ServerResponse.createByErrorMessage("添加失败");

    }

    /**
     * 检查教师学工号是否重复
     * @param number
     * @return
     */
    public ServerResponse checkTeacherNumber(String number){
        if (number == null){
            return ServerResponse.createByErrorMessage("TeacherNumber不能为空");
        }
        Teachers teachers = teachersMapper.selectByNumber(number);
        if (teachers != null){
            return ServerResponse.createByErrorMessage("学工号已存在");
        }
        return ServerResponse.createBySuccess();
    }


    /**
     * 获取项目的老师
     * @param projectId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse getProjectTeacher(Integer projectId, int pageNum, int pageSize){

        if (projectId == null){
            return ServerResponse.createByErrorMessage("projectId不能为空");
        }
        Projects projects = projectsMapper.selectByPrimaryKey(projectId);
        String tutor = projects.getpTutor();
        List<TeacherVo> teacherVoList = Lists.newArrayList();
        if (tutor != null || StringUtils.isNotEmpty(tutor)) {

            String[] tutorItem = tutor.split(",");
            for (String item : tutorItem) {
                Teachers teachers = teachersMapper.selectByNumber(item);
                if (teachers == null){
                    continue;
                }
                TeacherVo teacherVo = assembleTeacherVo(teachers);
                teacherVoList.add(teacherVo);
            }
        }

        return ServerResponse.createBySuccess(teacherVoList);

    }

    /**
     * 组装TeacherVo
     * @param teachers
     * @return
     */
    private TeacherVo assembleTeacherVo(Teachers teachers) {
        TeacherVo teacherVo = new TeacherVo();
        teacherVo.settNumber(teachers.gettNumber());
        teacherVo.settInstitute(teachers.gettInstitute());
        teacherVo.settMajor(teachers.gettMajor());
        teacherVo.settProfessionaltitle(teachers.gettProfessionaltitle());
        teacherVo.settSex(teachers.gettSex());
        teacherVo.settTelephone(teachers.gettTelephone());

        return teacherVo;

    }

    /**
     * 获取项目的学生
     * @param projectId
     * @param pageNum
     * @param pageSize
     * @return
     */
    public ServerResponse getProjectStudent(Integer projectId, int pageNum, int pageSize){

        if (projectId == null){
            return ServerResponse.createByErrorMessage("projectId不能为空");
        }
        Projects projects = projectsMapper.selectByPrimaryKey(projectId);
        String group = projects.getpGroup();
        List<StudentVo> studentVoList = Lists.newArrayList();
        if (group != null || StringUtils.isNotEmpty(group)){


            String[] groupItem = group.split(",");

            for (String item: groupItem) {
                Students students = studentsMapper.selectByNumber(item);
                if (students == null){
                    continue;
                }
                StudentVo studentVo = assembleStudentVo(students);
                studentVoList.add(studentVo);
            }
        }

        return ServerResponse.createBySuccess(studentVoList);

    }

    /**
     * 组装StudentVo
     * @param students
     * @return
     */
    private StudentVo assembleStudentVo(Students students) {
        StudentVo studentVo = new StudentVo();
        studentVo.setsNumber(students.getsNumber());
        studentVo.setsClass(students.getsClass());
        studentVo.setsInstitute(students.getsInstitute());
        studentVo.setsMajor(students.getsMajor());
        studentVo.setsName(students.getsName());
        studentVo.setsSex(students.getsSex());
        studentVo.setsTelephone(students.getsTelephone());

        return studentVo;
    }



    /**
     * 设置项目状态
     * @param status
     * @return
     */
    public ServerResponse getProjectByStatus(String status){

        if (status == null || StringUtils.isEmpty(status)){
            ServerResponse.createByErrorMessage("status不能为空");
        }

        List<Projects> projectsList = projectsMapper.selectByStatus(status);

        return ServerResponse.createBySuccess(projectsList);

    }

    /**
     * 获取所有老师
     * @return
     */
    public ServerResponse getAllTeachers(){
        List<Teachers> teachersList = teachersMapper.selectAllTeachers();
        List<TeacherVo> teacherVoList = Lists.newArrayList();
        for (Teachers teacher:teachersList) {
            TeacherVo teacherVo = assembleTeacherVo(teacher);
            teacherVoList.add(teacherVo);
        }
        return ServerResponse.createBySuccess(teacherVoList);
    }

    /**
     * 获取所有学生
     * @return
     */
    public ServerResponse getAllStudents(){
        List<Students> studentsList = studentsMapper.selectAllStudents();
        List<StudentVo> studentVoList = Lists.newArrayList();
        for (Students student:studentsList) {
            StudentVo studentVo = assembleStudentVo(student);
            studentVoList.add(studentVo);
        }
        return ServerResponse.createBySuccess(studentVoList);
    }



}


















