package com.bluegrass.pojo;

import java.util.Date;

public class VideoPraise {
    private Integer id;

    private Integer videoId;

    private Integer userId;

    private Date createTime;

    public VideoPraise(Integer id, Integer videoId, Integer userId, Date createTime) {
        this.id = id;
        this.videoId = videoId;
        this.userId = userId;
        this.createTime = createTime;
    }

    public VideoPraise() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVideoId() {
        return videoId;
    }

    public void setVideoId(Integer videoId) {
        this.videoId = videoId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}