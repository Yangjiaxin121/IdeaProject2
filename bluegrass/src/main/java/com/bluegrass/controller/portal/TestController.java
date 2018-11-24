package com.bluegrass.controller.portal;


import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;
import com.bluegrass.service.ITestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/test/")
public class TestController {


    @Autowired
    ITestService iTestService;


    @RequestMapping("get.do")
    @ResponseBody
    public ServerResponse getTest(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTestService.getTest(pageNum, pageSize);
    }

    @RequestMapping("save_result.do")
    @ResponseBody
    public ServerResponse saveResult(HttpSession session, Integer result){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTestService.saveResult(user,result);
    }
}
