package com.bluegrass.service.impl;

import com.bluegrass.common.Const;
import com.bluegrass.common.ServerResponse;
import com.bluegrass.common.TokenCache;
import com.bluegrass.dao.UserMapper;
import com.bluegrass.pojo.User;
import com.bluegrass.service.IUserService;
import com.bluegrass.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    UserMapper userMapper;

    /**
     * 用户登录
     * @param username
     * @param password
     * @return
     */
    public ServerResponse<User> login(String username, String password){
        System.out.println(username);
        int resultCount = userMapper.checkUsername(username);
        if (resultCount == 0){
            return ServerResponse.createByErrorMessage("用户名不存在");
        }
        System.out.println("11");
        String md5Password = MD5Util.MD5EncodeUtf8(password);
        System.out.println("md5:"+md5Password);
        User user = userMapper.selectLogin(username,md5Password);
        if (user == null){
            return ServerResponse.createByErrorMessage("密码错误");
        }
        System.out.println("hwh");
        user.setUserPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess("登录成功",user);

    }

    /**
     * 用户注册
     * @param user
     * @return
     */
    public ServerResponse<String> register(User user){
        ServerResponse validResponse = checkValid(user.getUserName(), Const.USERNAME);
        if (!validResponse.isSuccess()){
            return validResponse;
        }

        validResponse = checkValid(user.getUserEmail(), Const.EMAIL);
        if (!validResponse.isSuccess()){
            return validResponse;
        }

        //md5加密
        String md5Password = MD5Util.MD5EncodeUtf8(user.getUserPassword());
        user.setUserPassword(md5Password);
        int count = userMapper.insert(user);
        if (count == 0){
            return ServerResponse.createByErrorMessage("注册失败");
        }
        return ServerResponse.createBySuccessMessage("注册成功");
    }

    /**
     * 查看是否存在该用户
     * @param str
     * @param type
     * @return
     */
    public ServerResponse<String> checkValid(String str, String type){
        if (StringUtils.isNotBlank(type)){
            //开始校验
            if (Const.USERNAME.equals(type)){
                int resultcount = userMapper.checkUsername(str);
                if (resultcount > 0){
                    return ServerResponse.createByErrorMessage("用户名已存在");
                }
            }
            if (Const.EMAIL.equals(type)){
                int resultcount = userMapper.checkEmail(str);
                if (resultcount > 0){
                    return ServerResponse.createByErrorMessage("Email已存在");
                }
            }


        }else{
            return ServerResponse.createByErrorMessage("参数错误");
        }
        return ServerResponse.createBySuccessMessage("校验成功");
    }


    /**
     * 查询用户问题
     * @param username
     * @return
     */
    public ServerResponse<String> selectQuestion(String username){
        ServerResponse response = checkValid(username, Const.USERNAME);
        if (response.isSuccess()){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        String question = userMapper.selectQuestionByUsername(username);
        if (StringUtils.isBlank(question)){
            return ServerResponse.createByErrorMessage("找回密码的问题是空的");
        }
        return ServerResponse.createBySuccess(question);
    }

    /**
     * 检查问题答案是否正确,并将forgetToken放在本地，防止横向越权
     * @param username
     * @param question
     * @param answer
     * @return
     */
    @Override
    public ServerResponse<String> checkAnswer(String username, String question, String answer) {
        int count = userMapper.checkAnswer(username,question,answer);
        System.out.println("count="+count);
        if (count > 0){
            //回答正确

            String forgetToken = UUID.randomUUID().toString();
            TokenCache.setKey(TokenCache.TOKEN_PREFIX+username,forgetToken);
            return ServerResponse.createBySuccess(forgetToken);
        }
        return ServerResponse.createByErrorMessage("回答错误");
    }

    /**
     * 忘记密码重置密码
     * @param username
     * @param passwordNew
     * @param forgetToken
     * @return
     */
    @Override
    public ServerResponse<String> forgetRestPassword(String username, String passwordNew, String forgetToken) {
        if (StringUtils.isBlank(forgetToken)){
            return ServerResponse.createByErrorMessage("参数错误，token需要传递");
        }
        ServerResponse response = checkValid(username, Const.USERNAME);
        if (response.isSuccess()){
            return ServerResponse.createByErrorMessage("请输入有效的用户名");
        }
        String token = TokenCache.getKey(TokenCache.TOKEN_PREFIX+username);
        if (StringUtils.isBlank(token)){
            return ServerResponse.createByErrorMessage("token无效或已过期");
        }
        if (StringUtils.equals(forgetToken,token)){
            String md5Password = MD5Util.MD5EncodeUtf8(passwordNew);
            User user = new User();
            user.setUserName(username);
            user.setUserPassword(md5Password);
            int count = userMapper.updatePasswordByUsername(username,md5Password);
            if (count > 0){
                return ServerResponse.createBySuccessMessage("修改密码成功");
            }

        }else{
            return ServerResponse.createByErrorMessage("token错误，请重新获取充值密码的token");
        }
       return ServerResponse.createByErrorMessage("修改密码失败");

    }


    /**
     * 重置密码（登录后）
     * @param passwordOld
     * @param passwordNew
     * @param user
     * @return
     */
    public ServerResponse<String> resetPassword(String passwordOld, String passwordNew, User user){
        int count = userMapper.checkPassword(MD5Util.MD5EncodeUtf8(passwordOld),user.getUserId());
        if (count == 0){
            return ServerResponse.createByErrorMessage("旧密码错误");
        }
        user.setUserPassword(MD5Util.MD5EncodeUtf8(passwordNew));
        int count1 = userMapper.updateByPrimaryKeySelective(user);
        if (count1 > 0){
            return ServerResponse.createBySuccessMessage("修改密码成功");
        }
        return ServerResponse.createByErrorMessage("修改密码失败");
    }

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    @Override
    public ServerResponse<User> updateInformation(User user) {
        //uusername是不能被更新的
        //email也要进行校验，校验新的email是不是已经存在，并且不能是当前用户的
        int resultCount = userMapper.checkEmailByUserId(user.getUserEmail(),user.getUserId());
        if (resultCount > 0){
            return ServerResponse.createByErrorMessage("email已存在，请更换email尝试再更新");
        }
        User updateUser = new User();
        updateUser.setUserId(user.getUserId());
        updateUser.setUserEmail(user.getUserEmail());
        updateUser.setUserAnswer(user.getUserAnswer());
        updateUser.setUserQuestion(user.getUserQuestion());
        updateUser.setUserPhonenumber(user.getUserPhonenumber());
        updateUser.setUpdateTime(new Date());
        System.out.println("执行到这里了");
        int count = userMapper.updateByPrimaryKeySelective(updateUser);
        System.out.println("这是后面的");
        if (count > 0){
            return ServerResponse.createBySuccess("更新个人信息成功",updateUser);
        }
        return ServerResponse.createByErrorMessage("更新个人信息失败");
    }


    /**
     * 获取用户信息
     * @param id
     * @return
     */
    @Override
    public ServerResponse<User> getInformation(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (user == null){
            return ServerResponse.createByErrorMessage("用户不存在");
        }
        user.setUserPassword(StringUtils.EMPTY);
        return ServerResponse.createBySuccess(user);
    }


    /**
     * 检查是否为管理员
     * @param user
     * @return
     */
    public ServerResponse checkAdminRole(User user){
        if (user != null && Integer.valueOf(user.getUserRole()) == Const.Role.ROLE_ADMIN){
            return ServerResponse.createBySuccess();
        }
        return ServerResponse.createByError();
    }


    /**
     * 检查userId是否存在
     * @param userId
     * @return
     */
    public ServerResponse checkUserId(Integer userId){
        if (userId == null){
            return ServerResponse.createByErrorMessage("传入的userId不能为空");
        }
        int count = userMapper.checkUserId(userId);
        if (count > 0){
            return ServerResponse.createBySuccess("ojbk");
        }
        return ServerResponse.createByErrorMessage("userId不存在");
    }





}
