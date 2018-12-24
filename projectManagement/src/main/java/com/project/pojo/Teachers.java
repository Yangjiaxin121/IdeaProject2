package com.project.pojo;

public class Teachers {
    private Integer id;

    private String tNumber;

    private String tName;

    private String tSex;

    private String tInstitute;

    private String tMajor;

    private String tProfessionaltitle;

    private String tTelephone;

    private String tPassword;

    private String tQuestion;

    private String tAnswer;

    private String tRule;

    private String tRemarks;

    public Teachers(Integer id, String tNumber, String tName, String tSex, String tInstitute, String tMajor, String tProfessionaltitle, String tTelephone, String tPassword, String tQuestion, String tAnswer, String tRule, String tRemarks) {
        this.id = id;
        this.tNumber = tNumber;
        this.tName = tName;
        this.tSex = tSex;
        this.tInstitute = tInstitute;
        this.tMajor = tMajor;
        this.tProfessionaltitle = tProfessionaltitle;
        this.tTelephone = tTelephone;
        this.tPassword = tPassword;
        this.tQuestion = tQuestion;
        this.tAnswer = tAnswer;
        this.tRule = tRule;
        this.tRemarks = tRemarks;
    }

    public Teachers() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String gettNumber() {
        return tNumber;
    }

    public void settNumber(String tNumber) {
        this.tNumber = tNumber == null ? null : tNumber.trim();
    }

    public String gettName() {
        return tName;
    }

    public void settName(String tName) {
        this.tName = tName == null ? null : tName.trim();
    }

    public String gettSex() {
        return tSex;
    }

    public void settSex(String tSex) {
        this.tSex = tSex == null ? null : tSex.trim();
    }

    public String gettInstitute() {
        return tInstitute;
    }

    public void settInstitute(String tInstitute) {
        this.tInstitute = tInstitute == null ? null : tInstitute.trim();
    }

    public String gettMajor() {
        return tMajor;
    }

    public void settMajor(String tMajor) {
        this.tMajor = tMajor == null ? null : tMajor.trim();
    }

    public String gettProfessionaltitle() {
        return tProfessionaltitle;
    }

    public void settProfessionaltitle(String tProfessionaltitle) {
        this.tProfessionaltitle = tProfessionaltitle == null ? null : tProfessionaltitle.trim();
    }

    public String gettTelephone() {
        return tTelephone;
    }

    public void settTelephone(String tTelephone) {
        this.tTelephone = tTelephone == null ? null : tTelephone.trim();
    }

    public String gettPassword() {
        return tPassword;
    }

    public void settPassword(String tPassword) {
        this.tPassword = tPassword == null ? null : tPassword.trim();
    }

    public String gettQuestion() {
        return tQuestion;
    }

    public void settQuestion(String tQuestion) {
        this.tQuestion = tQuestion == null ? null : tQuestion.trim();
    }

    public String gettAnswer() {
        return tAnswer;
    }

    public void settAnswer(String tAnswer) {
        this.tAnswer = tAnswer == null ? null : tAnswer.trim();
    }

    public String gettRule() {
        return tRule;
    }

    public void settRule(String tRule) {
        this.tRule = tRule == null ? null : tRule.trim();
    }

    public String gettRemarks() {
        return tRemarks;
    }

    public void settRemarks(String tRemarks) {
        this.tRemarks = tRemarks == null ? null : tRemarks.trim();
    }
}