package com.bluegrass.controller.backend;


import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.DepressionTest;
import com.bluegrass.pojo.User;
import com.bluegrass.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/test/")
public class TestManageController {

    @Autowired
    ITestService iTestService;


    @RequestMapping("add.do")
    @ResponseBody
    public ServerResponse add(HttpSession session, DepressionTest depressionTest){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        if (Integer.valueOf(user.getUserRole()) == Const.Role.ROLE_ADMIN){
            //说明是管理员用户
            return iTestService.add(depressionTest);
        } else{
            return ServerResponse.createByErrorMessage("不是管理员无法登录");
        }
    }


    @RequestMapping("delete.do")
    @ResponseBody
    public ServerResponse delete(HttpSession session, Integer testId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (Integer.valueOf(user.getUserRole()) == Const.Role.ROLE_ADMIN){
            //说明是管理员用户
            return iTestService.delete(testId);
        } else{
            return ServerResponse.createByErrorMessage("不是管理员无法登录");
        }
    }

    @RequestMapping("update.do")
    @ResponseBody
    public ServerResponse update(HttpSession session, DepressionTest depressionTest){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (Integer.valueOf(user.getUserRole()) == Const.Role.ROLE_ADMIN){
            //说明是管理员用户
            return iTestService.update(depressionTest);
        } else{
            return ServerResponse.createByErrorMessage("不是管理员无法登录");
        }
    }
}









