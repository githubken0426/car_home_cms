package com.gtercn.carhome.cms.entity.shopping;

import java.util.Date;
import java.util.List;
import java.util.Map;

public class GoodsBrand {
    private String id;
    private String categoryId;
    private String cnName;
    private String enName;
    private Date createTime;
    private String logo;
    private String descrption;
    private String picture;
    
    private String categoryName;
    private List<Map<String,String>> pictureList;
    
    public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

    public List<Map<String, String>> getPictureList() {
		return pictureList;
	}

	public void setPictureList(List<Map<String, String>> pictureList) {
		this.pictureList = pictureList;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId == null ? null : categoryId.trim();
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName == null ? null : cnName.trim();
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName == null ? null : enName.trim();
    }
    
    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getDescrption() {
        return descrption;
    }

    public void setDescrption(String descrption) {
        this.descrption = descrption == null ? null : descrption.trim();
    }

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture == null ? "" : picture.trim();
	}
}