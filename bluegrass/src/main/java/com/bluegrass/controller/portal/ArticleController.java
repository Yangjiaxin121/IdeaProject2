package com.bluegrass.controller.portal;

import com.bluegrass.common.Const;
import com.bluegrass.common.FileUpload;
import com.bluegrass.common.ResponseCode;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.Article;
import com.bluegrass.pojo.ArticleWithBLOBs;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IArticleService;
import com.bluegrass.service.IFileService;
import com.bluegrass.service.IUserService;
import com.bluegrass.util.PropertiesUtil;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
@RequestMapping("/article/")
public class ArticleController {

    @Autowired
    IArticleService iArticleService;

    @Autowired
    IUserService iUserService;

    @Autowired
    IFileService iFileService;






    @RequestMapping(value = "increase_viewcount.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse increaseViewcount(Integer articleId){
        return iArticleService.increaseViewCount(articleId);
    }

    @RequestMapping(value = "get_article_by_id.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse getArticleById(Integer articleId){

        return iArticleService.getArticleById(articleId);

    }

    @RequestMapping(value = "save.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> saveArticle(HttpSession session, ArticleWithBLOBs articleWithBLOBs){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iArticleService.saveOrUpdateArticle(user,articleWithBLOBs);
    }

    @RequestMapping(value = "update.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> updateArticle(HttpSession session, ArticleWithBLOBs articleWithBLOBs){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iArticleService.saveOrUpdateArticle(user,articleWithBLOBs);
    }

    @RequestMapping(value = "search_by_userId.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> searchByUserId(Integer userId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iArticleService.searchByUserId(userId,pageNum,pageSize);
    }

    @RequestMapping(value = "search_my_article.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> searchMyArticle(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iArticleService.searchByUserId(user.getUserId(),pageNum,pageSize);
    }

    @RequestMapping(value = "search_by_article_title.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> searchByArticleTitle( String articleTitle, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){

        return iArticleService.searchByArticleTitle(articleTitle,pageNum,pageSize);
    }


    @RequestMapping(value = "delete_by_article_id.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<String> deleteByArticleId(HttpSession session, Integer articleId){

        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iArticleService.deleteByArticleId(articleId);

    }

    @RequestMapping("upload.do")
    @ResponseBody
    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){

        FileUpload fileUpload = new FileUpload();
        return fileUpload.upload(session,file,request);

    }

    @RequestMapping("richtext_img_upload.do")
    @ResponseBody
    public Map richtextImguUpload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){

        FileUpload fileUpload = new FileUpload();
        return fileUpload.richtextImguUpload(session,file,request,response);

    }





}
