package com.bluegrass.common;

import com.bluegrass.pojo.User;
import com.bluegrass.service.IFileService;
import com.bluegrass.util.PropertiesUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Map;



public class FileUpload {

    @Autowired
    IFileService iFileService;

    public ServerResponse upload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request){


        System.out.println("hehehhhe");
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            return ServerResponse.createByErrorCodeMessage(ResponseCode.NEED_LOGIN.getCode(),"用户未登录，请登录管理员");
        }

        //填充我们增加产品的业务逻辑

        String path = request.getSession().getServletContext().getRealPath("upload");
        //System.out.println(path);
        String targetFileName = iFileService.upLoad(file,path);
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix")+targetFileName;

        Map fileMap = Maps.newHashMap();
        fileMap.put("uri",targetFileName);
        fileMap.put("url",url);

        return ServerResponse.createBySuccess(fileMap);

    }


    public Map richtextImguUpload(HttpSession session, @RequestParam(value = "upload_file",required = false) MultipartFile file, HttpServletRequest request, HttpServletResponse response){

        Map resultMap = Maps.newHashMap();
        User user = (User) session.getAttribute(Const.CURRENT_USER);
        if (user == null){
            resultMap.put("success",false);
            resultMap.put("msg","用户未登录，请登录管理员");
            return resultMap;
        }

        //富文本对于返回值有自己的要求，我们要按照simditor，所以按照simditor进行调整
        /*
            {
                "success":true/false
                "msg":"error massage"
                "file_path":"[real file path]"
            }
         */

        //填充我们增加产品的业务逻辑

        String path = request.getSession().getServletContext().getRealPath("upload");

        String targetFileName = iFileService.upLoad(file,path);
        if (StringUtils.isBlank(targetFileName)){
            resultMap.put("success",false);
            resultMap.put("msg","上传失败");
            return resultMap;
        }
        String url = PropertiesUtil.getProperty("ftp.server.http.prefix"+targetFileName);

        resultMap.put("success",true);
        resultMap.put("msg","上传成功");
        resultMap.put("file_path",url);

        response.setHeader("Access-Controller-Allow-Headers","X-File-Name");
        return resultMap;


    }



}
