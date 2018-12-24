package com.project.controller.portal;


import com.project.common.Const;
import com.project.common.ServerResponse;
import com.project.pojo.Students;
import com.project.service.IStudentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/students/")
public class StudentsController {

    @Autowired
    IStudentsService iStudentsService;


    @RequestMapping(value = "login.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Students> login(String studentsNumber, String studentPassword, HttpSession session){
        //System.out.println("userPassword:"+userPassword);
        ServerResponse<Students> response =  iStudentsService.login(studentsNumber,studentPassword);
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
    public ServerResponse<String> register(Students students){

        return iStudentsService.register(students);

    }

    @RequestMapping(value = "check_valid.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> checkValid(String str, String type) {
        return iStudentsService.checkValid(str, type);
    }


    @RequestMapping(value = "get_students_info.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Students> getUserInfo(HttpSession session){
        Students user = (Students) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,无法获取用户信息");
        }
        return ServerResponse.createBySuccess(user);
    }

    @RequestMapping("forget_get_question")
    @ResponseBody
    public ServerResponse<String> forgetGetQuestion(String studentsNumber){

        return iStudentsService.selectQuestion(studentsNumber);

    }

    @RequestMapping(value = "forget_check_answer.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetCheckAnswer(String studentsNumber, String question, String answer){
        return iStudentsService.checkAnswer(studentsNumber, question, answer);
    }


    @RequestMapping(value = "forget_reset_password.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> forgetRestPassword(String studentsNumber, String passwordNew, String forgetToken){

        return iStudentsService.forgetRestPassword(studentsNumber,passwordNew,forgetToken);
    }


    @RequestMapping(value = "reset_password",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> resetPassword(HttpSession session, String passwordOld, String passwordNew){
        Students students = (Students) session.getAttribute(Const.CURRENT_USER);
        if (students == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iStudentsService.resetPassword(passwordOld,passwordNew,students);
    }

    @RequestMapping(value = "update_information.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<Students> updateInformation(HttpSession session, Students students){
        Students currentStudents = (Students) session.getAttribute(Const.CURRENT_USER);
        if (currentStudents == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        students.setId(currentStudents.getId());
        students.setsNumber(currentStudents.getsNumber());
        ServerResponse<Students> response = iStudentsService.updateInformation(students);
        if (response.isSuccess()){
            session.setAttribute(Const.CURRENT_USER,response.getData());
        }
        return response;
    }

    @RequestMapping(value = "student_apply.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse studentApply(HttpSession session, Integer projectId){
        Students currentStudents = (Students) session.getAttribute(Const.CURRENT_USER);
        if (currentStudents == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iStudentsService.studentApply(currentStudents,projectId);
    }

    @RequestMapping(value = "get_applied.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getApplied(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        Students currentStudents = (Students) session.getAttribute(Const.CURRENT_USER);
        if (currentStudents == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iStudentsService.getApplied(currentStudents,pageNum,pageSize);
    }






}
