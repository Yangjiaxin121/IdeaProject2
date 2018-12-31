package com.bluegrass.pojo;

public class ResultRecommend {
    private Integer id;

    private Integer resultValue;

    private String recommend;

    private String userId;

    public ResultRecommend(Integer id, Integer resultValue, String recommend, String userId) {
        this.id = id;
        this.resultValue = resultValue;
        this.recommend = recommend;
        this.userId = userId;
    }

    public ResultRecommend() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getResultValue() {
        return resultValue;
    }

    public void setResultValue(Integer resultValue) {
        this.resultValue = resultValue;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend == null ? null : recommend.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }
}