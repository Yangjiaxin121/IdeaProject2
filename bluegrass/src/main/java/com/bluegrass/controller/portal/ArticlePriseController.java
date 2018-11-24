package com.bluegrass.controller.portal;


import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IArticlePriseService;
import com.bluegrass.service.impl.ArticleServiceImpl;
import com.bluegrass.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/article/prise/")
public class ArticlePriseController {

    @Autowired
    IArticlePriseService iArticlePriseService;


    @RequestMapping(value = "add_prise.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addArticlePrise(HttpSession session, Integer articleId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iArticlePriseService.addArticlePrise(user,articleId);
    }

    @RequestMapping(value = "get_prise_num.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getArticlePriseNum(Integer articleId){

        return iArticlePriseService.getArticlePriseNum(articleId);
    }

    @RequestMapping(value = "delete_prise.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteArticlePrise(HttpSession session, Integer articleId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iArticlePriseService.deleteArticlePrise(user,articleId);
    }


}
