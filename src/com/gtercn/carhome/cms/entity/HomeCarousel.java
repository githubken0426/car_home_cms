package com.gtercn.carhome.cms.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import com.gtercn.carhome.cms.ApplicationConfig;

public class HomeCarousel {
    private String id;
    private String title;
    private String cityCode;
    private String content;
    private String resUrlList;
    private String htmlUrl;
    private String pictureUrl;
    private Integer deleteFlag;
    private Date insertTime;
    private Date updateTime;
    
    private List<String> displayList;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    public String getResUrlList() {
        return resUrlList;
    }



    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl == null ? null : htmlUrl.trim();
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    public Date getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(Date insertTime) {
        this.insertTime = insertTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public void setResUrlList(String resUrlList) {
        String url = (resUrlList == null) ? null : ApplicationConfig
				.appendFtpIpToURL(resUrlList.trim());
		this.resUrlList = url;
		this.displayList = (url == null) ? null : Arrays.asList(url.split(","));
    }
	
	public List<String> getDisplayList() {
		return displayList;
	}

	public void setDisplayList(List<String> displayList) {
		this.displayList = displayList;
	}

	public String getPictureUrl() {
		return pictureUrl;
	}

	public void setPictureUrl(String pictureUrl) {
		this.pictureUrl = pictureUrl;
	}
}