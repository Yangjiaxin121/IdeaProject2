package com.project.pojo;

public class Students {
    private Integer id;

    private String sNumber;

    private String sName;

    private String sSex;

    private String sInstitute;

    private String sMajor;

    private String sClass;

    private String sTelephone;

    private String sPassword;

    private String sQuestion;

    private String sAnswer;

    private String sRule;

    private String sRemarks;

    private String sMessageboard;

    public Students(Integer id, String sNumber, String sName, String sSex, String sInstitute, String sMajor, String sClass, String sTelephone, String sPassword, String sQuestion, String sAnswer, String sRule, String sRemarks, String sMessageboard) {
        this.id = id;
        this.sNumber = sNumber;
        this.sName = sName;
        this.sSex = sSex;
        this.sInstitute = sInstitute;
        this.sMajor = sMajor;
        this.sClass = sClass;
        this.sTelephone = sTelephone;
        this.sPassword = sPassword;
        this.sQuestion = sQuestion;
        this.sAnswer = sAnswer;
        this.sRule = sRule;
        this.sRemarks = sRemarks;
        this.sMessageboard = sMessageboard;
    }

    public Students() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getsNumber() {
        return sNumber;
    }

    public void setsNumber(String sNumber) {
        this.sNumber = sNumber == null ? null : sNumber.trim();
    }

    public String getsName() {
        return sName;
    }

    public void setsName(String sName) {
        this.sName = sName == null ? null : sName.trim();
    }

    public String getsSex() {
        return sSex;
    }

    public void setsSex(String sSex) {
        this.sSex = sSex == null ? null : sSex.trim();
    }

    public String getsInstitute() {
        return sInstitute;
    }

    public void setsInstitute(String sInstitute) {
        this.sInstitute = sInstitute == null ? null : sInstitute.trim();
    }

    public String getsMajor() {
        return sMajor;
    }

    public void setsMajor(String sMajor) {
        this.sMajor = sMajor == null ? null : sMajor.trim();
    }

    public String getsClass() {
        return sClass;
    }

    public void setsClass(String sClass) {
        this.sClass = sClass == null ? null : sClass.trim();
    }

    public String getsTelephone() {
        return sTelephone;
    }

    public void setsTelephone(String sTelephone) {
        this.sTelephone = sTelephone == null ? null : sTelephone.trim();
    }

    public String getsPassword() {
        return sPassword;
    }

    public void setsPassword(String sPassword) {
        this.sPassword = sPassword == null ? null : sPassword.trim();
    }

    public String getsQuestion() {
        return sQuestion;
    }

    public void setsQuestion(String sQuestion) {
        this.sQuestion = sQuestion == null ? null : sQuestion.trim();
    }

    public String getsAnswer() {
        return sAnswer;
    }

    public void setsAnswer(String sAnswer) {
        this.sAnswer = sAnswer == null ? null : sAnswer.trim();
    }

    public String getsRule() {
        return sRule;
    }

    public void setsRule(String sRule) {
        this.sRule = sRule == null ? null : sRule.trim();
    }

    public String getsRemarks() {
        return sRemarks;
    }

    public void setsRemarks(String sRemarks) {
        this.sRemarks = sRemarks == null ? null : sRemarks.trim();
    }

    public String getsMessageboard() {
        return sMessageboard;
    }

    public void setsMessageboard(String sMessageboard) {
        this.sMessageboard = sMessageboard == null ? null : sMessageboard.trim();
    }
}