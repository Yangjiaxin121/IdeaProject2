package com.bluegrass.controller.portal;


import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.ResultRecommend;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IRecommendService;
import com.bluegrass.service.impl.RecommendServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/recommend/")
public class RecommendController {

    @Autowired
    IRecommendService iRecommendService;

    @RequestMapping(value = "add_recommend.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addRecommend(HttpSession session, ResultRecommend resultRecommend){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iRecommendService.add(user,resultRecommend);
    }

    @RequestMapping(value = "update_recommend.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse updateRecommend(HttpSession session, ResultRecommend resultRecommend){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iRecommendService.update(user,resultRecommend);
    }

    @RequestMapping(value = "delete_recommend.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteRecommend(HttpSession session, Integer recommendId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iRecommendService.delete(user,recommendId);
    }

    @RequestMapping(value = "get_recommend.do" ,method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getRecommend(Integer value, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iRecommendService.get(value, pageNum, pageSize);
    }

}
