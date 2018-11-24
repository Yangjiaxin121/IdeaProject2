package com.bluegrass.controller.portal;


import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.RadioComment;
import com.bluegrass.pojo.User;
import com.bluegrass.pojo.VideoComment;
import com.bluegrass.service.IRadioCommentService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/radio/comments/")
public class RadioCommentController {

    @Autowired
    IRadioCommentService iRadioCommentService;

    @RequestMapping(value = "add_comments.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addComments(HttpSession session, RadioComment radioComment){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null ){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iRadioCommentService.addComments(user,radioComment);

    }

    @RequestMapping(value = "list_comments.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> listComments(Integer radioId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

        return iRadioCommentService.listComments(radioId, pageNum, pageSize);
    }

    @RequestMapping(value = "delete_comments.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteComments(HttpSession session, Integer radioCommentsId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iRadioCommentService.deleteComments(user,radioCommentsId);

    }

    @RequestMapping(value = "get_comments_num.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getCommentsNum(Integer radioId){
        return iRadioCommentService.getCommentsNum(radioId);
    }
}
