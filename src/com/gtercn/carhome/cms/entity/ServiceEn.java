package com.gtercn.carhome.cms.entity;

import java.util.Date;

public class ServiceEn {
    private String id;

    private String shopId;

    private Integer serviceType;

    private String serviceBrandsUrl;

    private Integer deleteFlag;

    private Date updateTime;

    private Date insertTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId == null ? null : shopId.trim();
    }

    public Integer getServiceType() {
        return serviceType;
    }

    public void setServiceType(Integer serviceType) {
        this.serviceType = serviceType;
    }

    public String getServiceBrandsUrl() {
        return serviceBrandsUrl;
    }

    public void setServiceBrandsUrl(String serviceBrandsUrl) {
        this.serviceBrandsUrl = serviceBrandsUrl == null ? null : serviceBrandsUrl.trim();
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