package com.project.controller.portal;


import com.project.common.Const;
import com.project.common.ServerResponse;
import com.project.pojo.Projects;
import com.project.pojo.Students;
import com.project.pojo.Teachers;
import com.project.service.ITeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/teachers/")
public class TeachersController {

    @Autowired
    ITeacherService iTeacherService;

    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Teachers> login(String teacherNumber, String teacherPassword, HttpSession session){
        //System.out.println("userPassword:"+userPassword);
        ServerResponse<Teachers> response =  iTeacherService.login(teacherNumber,teacherPassword);
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

    @RequestMapping(value = "get_teachers_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Teachers> getTeachersInfo(HttpSession session){
        Teachers teachers = (Teachers) session.getAttribute(Const.CURRENT_USER);
        if (teachers == null){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取用户信息");
        }
        return ServerResponse.createBySuccess(teachers);
    }



    @RequestMapping(value = "forget_get_question.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String teachersNumber){

        return iTeacherService.selectQuestion(teachersNumber);

    }

    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String teachersNumber, String question, String answer){
        return iTeacherService.checkAnswer(teachersNumber, question, answer);
    }


    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String teachersNumber, String passwordNew, String forgetToken){

        return iTeacherService.forgetRestPassword(teachersNumber,passwordNew,forgetToken);
    }


    @RequestMapping(value = "reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew){
        Teachers teachers = (Teachers) session.getAttribute(Const.CURRENT_USER);
        if (teachers == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTeacherService.resetPassword(passwordOld,passwordNew,teachers);
    }

    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Teachers> updateInformation(HttpSession session, Teachers teachers){
        Teachers currentTeachers = (Teachers) session.getAttribute(Const.CURRENT_USER);
        if (currentTeachers == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        teachers.setId(currentTeachers.getId());
        teachers.settNumber(currentTeachers.gettNumber());
        ServerResponse<Teachers> response = iTeacherService.updateInformation(teachers);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "add_project.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addProject(HttpSession session, Projects projects){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (object instanceof Students){
            return ServerResponse.createByErrorMessage("无权限");
        }
        //Teachers teachers = (Teachers) session.getAttribute(Const.CURRENT_USER);
        Teachers teachers = (Teachers) object;
        if (teachers == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTeacherService.addProject(teachers,projects);

    }

    @RequestMapping(value = "delete_project.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteProject(HttpSession session, Integer projectId){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Teachers)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        //Teachers teachers = (Teachers) session.getAttribute(Const.CURRENT_USER);
        Teachers teachers = (Teachers) object;
        if (teachers == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTeacherService.deleteProject(projectId,teachers);
    }

    @RequestMapping(value = "update_project.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateProject(HttpSession session, Projects projects){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Teachers)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        //Teachers teachers = (Teachers) session.getAttribute(Const.CURRENT_USER);
        Teachers teachers = (Teachers) object;
        if (teachers == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTeacherService.updateProject(projects,teachers);
    }


    @RequestMapping(value = "set_group_leader.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setGroupLeader(HttpSession session, Integer projectId, String studentNumber){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Teachers)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        //Teachers teachers = (Teachers) session.getAttribute(Const.CURRENT_USER);
        Teachers teachers = (Teachers) object;
        if (teachers == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTeacherService.setGroupLeader(teachers,projectId,studentNumber);

    }

    @RequestMapping(value = "set_status.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse setProjectStatus(HttpSession session, Integer projectId, String status){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Teachers)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        Teachers teachers = (Teachers) object;
        if (teachers == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTeacherService.setStatus(teachers,projectId,status);

    }

    @RequestMapping(value = "get_teacher_applied.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getTeacherApplied(HttpSession session){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Teachers)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        Teachers teachers = (Teachers) object;
        if (teachers == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTeacherService.getTeacherApplied(teachers);
    }


    @RequestMapping(value = "delete_student_by_StudentNumber.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteStudentByStudentNumber(HttpSession session, Integer projectId, String studentNumber){
        Object object = session.getAttribute(Const.CURRENT_USER);
        if (!(object instanceof Teachers)){
            return ServerResponse.createByErrorMessage("无权限");
        }
        //Teachers teachers = (Teachers) session.getAttribute(Const.CURRENT_USER);
        Teachers teachers = (Teachers) object;
        if (teachers == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTeacherService.deleteStudentByStudentNumber(teachers,studentNumber,projectId);
    }

}

















