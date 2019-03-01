package com.liaocx.api.model.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.liaocx.common.convert.DateSerializer;

import java.io.Serializable;
import java.util.Date;

public class Banner implements Serializable {
    private Long id;

    private String img;

    private Long activityId;

    private String url;

    private Byte type;

    private Boolean isOnline;

    private Integer sort;

    private Boolean isDeleted;

    @JsonSerialize(using = DateSerializer.class)
    private Date gmtCreate;

    private Long gmtCreateUser;

    private Date gmtModified;

    private Long gmtModifiedUser;

    private static final long serialVersionUID = 1L;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Byte getType() {
        return type;
    }

    public void setType(Byte type) {
        this.type = type;
    }

    public Boolean getIsOnline() {
        return isOnline;
    }

    public void setIsOnline(Boolean isOnline) {
        this.isOnline = isOnline;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Boolean getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public Date getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(Date gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public Long getGmtCreateUser() {
        return gmtCreateUser;
    }

    public void setGmtCreateUser(Long gmtCreateUser) {
        this.gmtCreateUser = gmtCreateUser;
    }

    public Date getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(Date gmtModified) {
        this.gmtModified = gmtModified;
    }

    public Long getGmtModifiedUser() {
        return gmtModifiedUser;
    }

    public void setGmtModifiedUser(Long gmtModifiedUser) {
        this.gmtModifiedUser = gmtModifiedUser;
    }
}