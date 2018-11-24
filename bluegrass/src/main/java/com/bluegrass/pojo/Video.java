package com.bluegrass.pojo;

import java.util.Date;

public class Video {
    private Integer id;

    private Integer userId;

    private Integer videoId;

    private String detail;

    private Integer praise;

    private Date createTime;

    public Video(Integer id, Integer userId, Integer videoId, String detail, Integer praise, Date createTime) {
        this.id = id;
        this.userId = userId;
        this.videoId = videoId;
        this.detail = detail;
        this.praise = praise;
        this.createTime = createTime;
    }

    public Video() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }

    public Integer getPraise() {
        return praise;
    }

    public void setPraise(Integer praise) {
        this.praise = praise;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}