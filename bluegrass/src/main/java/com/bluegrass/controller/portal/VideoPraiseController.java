package com.bluegrass.controller.portal;

import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IVideoPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/video/praise/")
public class VideoPraiseController {


    @Autowired
    IVideoPraiseService iVideoPraiseService;



    @RequestMapping(value = "add_praise.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addVideoPraise(HttpSession session, Integer videoId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iVideoPraiseService.addVideoPraise(user,videoId);
    }


    @RequestMapping(value = "get_praise_num.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getVideoPraiseNum(Integer videoId){
        return iVideoPraiseService.getVideoPraiseNum(videoId);
    }


    @RequestMapping(value = "delete_praise.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteVideoPrise(HttpSession session, Integer videoId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iVideoPraiseService.deleteVideoPraise(user,videoId);
    }
}
