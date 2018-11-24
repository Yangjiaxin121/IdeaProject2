package com.bluegrass.controller.backend;


import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/manage/user")
public class UserManageController {

    @Autowired
    IUserService iUserService;


    @RequestMapping("login.do")
    @ResponseBody
    public ServerResponse<User> login(HttpSession session, String username, String password){
        ServerResponse<User> response = iUserService.login(username,password);
        if (response.isSuccess()) {
            User user = response.getData();
            if (Integer.valueOf(user.getUserRole()) == Const.Role.ROLE_ADMIN){
                //说明是管理员用户
                session.setAttribute(Const.CURRENT_USER,user);
                return response;
            } else{
                return ServerResponse.createByErrorMessage("不是管理员无法登录");
            }
        }
        return response;
    }


}
