package com.gtercn.carhome.cms.entity;

import java.util.Date;

public class Favor {
    private String id;

    private String userId;

    private String favorId;

    private Integer favorType;

    private Integer deleteFlag;

    private Date updateTime;

    private Date insertTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getFavorId() {
        return favorId;
    }

    public void setFavorId(String favorId) {
        this.favorId = favorId == null ? null : favorId.trim();
    }

    public Integer getFavorType() {
        return favorType;
    }

    public void setFavorType(Integer favorType) {
        this.favorType = favorType;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }
}