package com.bluegrass.pojo;

public class DepressionTest {
    private Integer id;

    private String answerA;

    private String answerB;

    private String answerC;

    private String answerD;

    private String valueA;

    private String valueB;

    private String valueC;

    private String valueD;

    private String question;


    public DepressionTest(Integer id, String answerA, String answerB, String answerC, String answerD, String valueA, String valueB, String valueC, String valueD, String question) {
        this.id = id;
        this.answerA = answerA;
        this.answerB = answerB;
        this.answerC = answerC;
        this.answerD = answerD;
        this.valueA = valueA;
        this.valueB = valueB;
        this.valueC = valueC;
        this.valueD = valueD;
        this.question = question;
    }

    public DepressionTest() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAnswerA() {
        return answerA;
    }

    public void setAnswerA(String answerA) {
        this.answerA = answerA == null ? null : answerA.trim();
    }

    public String getAnswerB() {
        return answerB;
    }

    public void setAnswerB(String answerB) {
        this.answerB = answerB == null ? null : answerB.trim();
    }

    public String getAnswerC() {
        return answerC;
    }

    public void setAnswerC(String answerC) {
        this.answerC = answerC == null ? null : answerC.trim();
    }

    public String getAnswerD() {
        return answerD;
    }

    public void setAnswerD(String answerD) {
        this.answerD = answerD == null ? null : answerD.trim();
    }

    public String getValueA() {
        return valueA;
    }

    public void setValueA(String valueA) {
        this.valueA = valueA == null ? null : valueA.trim();
    }

    public String getValueB() {
        return valueB;
    }

    public void setValueB(String valueB) {
        this.valueB = valueB == null ? null : valueB.trim();
    }

    public String getValueC() {
        return valueC;
    }

    public void setValueC(String valueC) {
        this.valueC = valueC == null ? null : valueC.trim();
    }

    public String getValueD() {
        return valueD;
    }

    public void setValueD(String valueD) {
        this.valueD = valueD == null ? null : valueD.trim();
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question == null ? null : question.trim();
    }
}