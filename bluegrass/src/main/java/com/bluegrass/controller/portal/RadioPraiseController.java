package com.bluegrass.controller.portal;


import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IRadioPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/radio/praise/")
public class RadioPraiseController {


    @Autowired
    IRadioPraiseService iRadioPraiseService;


    @RequestMapping(value = "add_praise.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addRadioPraise(HttpSession session, Integer radioId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iRadioPraiseService.addRadioPraise(user,radioId);
    }


    @RequestMapping(value = "get_praise_num.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getRadioPraiseNum(Integer radioId){
        return iRadioPraiseService.getRadioPraiseNum(radioId);
    }


    @RequestMapping(value = "delete_praise.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteRadioPrise(HttpSession session, Integer radioId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iRadioPraiseService.deleteRadioPraise(user,radioId);
    }


}
