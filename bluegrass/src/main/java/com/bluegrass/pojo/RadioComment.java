package com.bluegrass.pojo;

import java.util.Date;

public class RadioComment {
    private Integer id;

    private String radioId;

    private String userId;

    private String comment;

    private Date createTime;

    public RadioComment(Integer id, String radioId, String userId, String comment, Date createTime) {
        this.id = id;
        this.radioId = radioId;
        this.userId = userId;
        this.comment = comment;
        this.createTime = createTime;
    }

    public RadioComment() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRadioId() {
        return radioId;
    }

    public void setRadioId(String radioId) {
        this.radioId = radioId == null ? null : radioId.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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