package com.bluegrass.pojo;

import java.util.Date;

public class VideoComment {
    private Integer id;

    private Integer videoId;

    private Integer userId;

    private String comment;

    private Date createTime;

    public VideoComment(Integer id, Integer videoId, Integer userId, String comment, Date createTime) {
        this.id = id;
        this.videoId = videoId;
        this.userId = userId;
        this.comment = comment;
        this.createTime = createTime;
    }

    public VideoComment() {
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment == null ? null : comment.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}