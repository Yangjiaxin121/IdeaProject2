package com.bluegrass.pojo;

import java.util.Date;

public class User {
    private Integer userId;

    private String userName;

    private String userPassword;

    private Integer userArticlenum;

    private String userPhonenumber;

    private String userEmail;

    private String examResult;

    private String userRole;

    private String userQuestion;

    private String userAnswer;

    private String userTestValue;

    private Date createTime;

    private Date updateTime;

    public User(Integer userId, String userName, String userPassword, Integer userArticlenum, String userPhonenumber, String userEmail, String examResult, String userRole, String userQuestion, String userAnswer, String userTestValue, Date createTime, Date updateTime) {
        this.userId = userId;
        this.userName = userName;
        this.userPassword = userPassword;
        this.userArticlenum = userArticlenum;
        this.userPhonenumber = userPhonenumber;
        this.userEmail = userEmail;
        this.examResult = examResult;
        this.userRole = userRole;
        this.userQuestion = userQuestion;
        this.userAnswer = userAnswer;
        this.userTestValue = userTestValue;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public User() {
        super();
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword == null ? null : userPassword.trim();
    }

    public Integer getUserArticlenum() {
        return userArticlenum;
    }

    public void setUserArticlenum(Integer userArticlenum) {
        this.userArticlenum = userArticlenum;
    }

    public String getUserPhonenumber() {
        return userPhonenumber;
    }

    public void setUserPhonenumber(String userPhonenumber) {
        this.userPhonenumber = userPhonenumber == null ? null : userPhonenumber.trim();
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail == null ? null : userEmail.trim();
    }

    public String getExamResult() {
        return examResult;
    }

    public void setExamResult(String examResult) {
        this.examResult = examResult == null ? null : examResult.trim();
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole == null ? null : userRole.trim();
    }

    public String getUserQuestion() {
        return userQuestion;
    }

    public void setUserQuestion(String userQuestion) {
        this.userQuestion = userQuestion == null ? null : userQuestion.trim();
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer == null ? null : userAnswer.trim();
    }

    public String getUserTestValue() {
        return userTestValue;
    }

    public void setUserTestValue(String userTestValue) {
        this.userTestValue = userTestValue == null ? null : userTestValue.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}