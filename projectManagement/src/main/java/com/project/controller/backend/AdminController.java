package com.project.controller.backend;


import com.github.pagehelper.PageInfo;
import com.project.common.Const;
import com.project.common.ServerResponse;
import com.project.pojo.Administrator;
import com.project.pojo.Projects;
import com.project.pojo.Students;
import com.project.pojo.Teachers;
import com.project.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin/")
public class AdminController {


    @Autowired
    IAdminService iAdminService;


    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Students> login(String adminName, String adminPassword, HttpSession session){
        //System.out.println("userPassword:"+userPassword);
        ServerResponse<Students> response =  iAdminService.login(adminName,adminPassword);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "logout.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> logout(HttpSession session){
        session.removeAttribute(Const.CURRENT_USER);
        return ServerResponse.createBySuccess();
    }

    @RequestMapping(value = "register.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> register(Administrator administrator){

        return iAdminService.register(administrator);
    }


    @RequestMapping(value = "delete_project.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteProject(HttpSession session, Integer projectsId){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Administrator)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        return iAdminService.deleteProject(projectsId);
    }

    @RequestMapping(value = "select_all_project.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse selectAllProjects(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

        return iAdminService.selectAllProject(pageNum, pageSize);
    }

    @RequestMapping(value = "update_project.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateProject(HttpSession session, Projects projects){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Administrator)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        return iAdminService.updateProject(projects);

    }

    @RequestMapping(value = "delete_student.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteStudent(HttpSession session, String studentNumber){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Administrator)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        return iAdminService.deleteStudent(studentNumber);
    }


    @RequestMapping(value = "get_student.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getStudent(HttpSession session, String studentNumber){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Administrator)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        return iAdminService.getStudent(studentNumber);
    }

    @RequestMapping(value = "delete_teacher.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteTeacher(HttpSession session, String teacherNumber){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Administrator)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        return iAdminService.deleteTeacher(teacherNumber);
    }


    @RequestMapping(value = "get_teacher.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getTeacher(HttpSession session, String teacherNumber){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Administrator)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        return iAdminService.getTeacher(teacherNumber);
    }

    @RequestMapping(value = "add_teacher.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addTeacher(HttpSession session, Teachers teachers){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Administrator)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        return iAdminService.addTeacher(teachers);

    }

    @RequestMapping(value = "get_project_teacher.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getProjectTeacher(Integer projectId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iAdminService.getProjectTeacher(projectId, pageNum, pageSize);
    }

    @RequestMapping(value = "get_project_student.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getProjectStudent(Integer projectId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iAdminService.getProjectStudent(projectId, pageNum, pageSize);
    }

    @RequestMapping(value = "get_project_by_status.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getProjectByStatus(String status){

        return iAdminService.getProjectByStatus(status);
    }

}
