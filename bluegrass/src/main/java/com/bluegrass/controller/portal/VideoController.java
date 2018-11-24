package com.bluegrass.controller.portal;


import com.bluegrass.common.Const;
import com.bluegrass.common.FileUpload;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.pojo.User;
import com.bluegrass.pojo.Video;
import com.bluegrass.service.IVideoService;
import com.github.pagehelper.PageInfo;
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
@RequestMapping("/video/")
public class VideoController {


    @Autowired
    IVideoService iVideoService;


    @RequestMapping(value = "add.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse add(HttpSession session, Video video){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iVideoService.add(user,video);
    }


    @RequestMapping(value = "search_my_all_video.do", method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse searchMyAllVideo(HttpSession session, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iVideoService.searchMyAllVideo(user,pageNum,pageSize);

    }


    @RequestMapping(value = "search_by_userId.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse<PageInfo> searchByUserId(Integer userId, @RequestParam(value = "pageNum", defaultValue = "1") int pageNum, @RequestParam(value = "pageSize", defaultValue = "10") int pageSize){
        return iVideoService.searchByUserId(userId,pageNum,pageSize);
    }

    @RequestMapping(value = "delete_by_videoId.do",method = RequestMethod.POST)
    @ResponseBody
    public ServerResponse deleteByVideoId(HttpSession session, Integer videoId){
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户未登录,请登录");
        }
        return iVideoService.deleteByVideoId(user,videoId);
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



















