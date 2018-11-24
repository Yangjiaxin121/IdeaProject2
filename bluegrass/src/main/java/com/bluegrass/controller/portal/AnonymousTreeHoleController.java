package com.bluegrass.controller.portal;


import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.AnonymousTreeHole;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IAnonymousTreeHoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/treehole/")
public class AnonymousTreeHoleController {

    @Autowired
    IAnonymousTreeHoleService iAnonymousTreeHoleService;


    @RequestMapping(value = "add_treehole.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addTreeHole(HttpSession session, AnonymousTreeHole anonymousTreeHole){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }

        return iAnonymousTreeHoleService.addTreeHole(user,anonymousTreeHole);
    }

    @RequestMapping(value = "list_treehole.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse listTreeHole(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iAnonymousTreeHoleService.listTreehole(pageNum,pageSize);
    }


    @RequestMapping(value = "search_my_treehole.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse searchMytreeHole(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }

        return iAnonymousTreeHoleService.searchMyTreeHole(user,pageNum,pageSize);
    }


    @RequestMapping(value = "delete_my_treehole.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteMyTreeHole(HttpSession session, Integer treeHoleId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }

        return iAnonymousTreeHoleService.deleteMyTreeHole(user,treeHoleId);
    }


}
