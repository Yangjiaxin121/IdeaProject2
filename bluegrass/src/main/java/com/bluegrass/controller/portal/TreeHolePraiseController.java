package com.bluegrass.controller.portal;


import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;
import com.bluegrass.service.ITreeHolePraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/treehole/praise")
public class TreeHolePraiseController {

    @Autowired
    ITreeHolePraiseService iTreeHolePraiseService;

    @RequestMapping(value = "add_prise.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addTreeHolePrise(HttpSession session, Integer treeHoleId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTreeHolePraiseService.addTreeHolePraise(user,treeHoleId);
    }

    @RequestMapping(value = "get_prise_num.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getTreeHolePriseNum(Integer treeHoleId){

        return iTreeHolePraiseService.getTreeHolePriseNum(treeHoleId);
    }

    @RequestMapping(value = "delete_prise.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteTreeHolePrise(HttpSession session, Integer treeHoleId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTreeHolePraiseService.deleteTreeHolePrise(user,treeHoleId);
    }
}
