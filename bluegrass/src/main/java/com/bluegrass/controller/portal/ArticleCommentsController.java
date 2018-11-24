package com.bluegrass.controller.portal;


import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.ArticleComments;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IArticleCommentsService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/article/comments/")
public class ArticleCommentsController {

    @Autowired
    IArticleCommentsService iArticleCommentsService;


    @RequestMapping(value = "add_comments.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse addComments(HttpSession session, ArticleComments articleComments){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null ){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iArticleCommentsService.addComment(user,articleComments);

    }

    @RequestMapping(value = "list_comments.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> listComments( Integer articleId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

        return iArticleCommentsService.listComments(articleId, pageNum, pageSize);
    }

    @RequestMapping(value = "delete_comments.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteComments(HttpSession session, Integer articleCommentsId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iArticleCommentsService.deleteComments(user,articleCommentsId);

    }

    @RequestMapping(value = "get_comments_num.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getCommentsNum(Integer articleId){
        return iArticleCommentsService.getArticleCommentsNum(articleId);
    }
}
