package com.bluegrass.controller.portal;


import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.ArticleComments;
import com.bluegrass.pojo.TreeHoleComments;
import com.bluegrass.pojo.User;
import com.bluegrass.service.ITreeHoleCommentsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/treehole/comments")
public class TreeHoleCommentsController {


    @Autowired
    ITreeHoleCommentsService iTreeHoleCommentsService;

    @RequestMapping(value = "add_comments.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addComments(HttpSession session, TreeHoleComments treeHoleComments){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null ){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTreeHoleCommentsService.addComment(user,treeHoleComments);

    }

    @RequestMapping(value = "list_comments.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> listComments(Integer treeHoleId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

        return iTreeHoleCommentsService.listComments(treeHoleId, pageNum, pageSize);
    }

    @RequestMapping(value = "delete_comments.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteComments(HttpSession session, Integer treeHoleCommentsId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iTreeHoleCommentsService.deleteComments(user,treeHoleCommentsId);

    }

    @RequestMapping(value = "get_comments_num.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getCommentsNum(Integer treeHoleId){
        return iTreeHoleCommentsService.getTreeHoleCommentsNum(treeHoleId);
    }



}
