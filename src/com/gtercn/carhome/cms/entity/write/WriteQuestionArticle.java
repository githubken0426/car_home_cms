package com.gtercn.carhome.cms.entity.write;

import java.util.Arrays;
import java.util.Date;
import java.util.List;


public class WriteQuestionArticle {
    private String id;
    private String userId;
    private Integer type;//1:问题墙 2:车友会 3:文章
    private String title;
    private Integer supportNumber;
    private Integer favorNumber;
    private Integer glanceNumber;
    private String introduction;//简介
    private String content;
    private String resUrlList;
    private String htmlUrl;
    private Integer deleteFlag;
    private Date insertTime;
    private Date updateTime;
    private String cityCode;
    private String nickName;
    private String loginPhone;
    private Integer replyNum;
    private List<String> displayList;
    
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Integer getSupportNumber() {
        return supportNumber;
    }

    public void setSupportNumber(Integer supportNumber) {
        this.supportNumber = supportNumber;
    }

    public Integer getFavorNumber() {
        return favorNumber;
    }

    public void setFavorNumber(Integer favorNumber) {
        this.favorNumber = favorNumber;
    }

    public Integer getGlanceNumber() {
        return glanceNumber;
    }

    public void setGlanceNumber(Integer glanceNumber) {
        this.glanceNumber = glanceNumber;
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

    public void setResUrlList(String resUrlList) {
		this.resUrlList = resUrlList;
		this.displayList = (resUrlList == null) ? null : Arrays.asList(resUrlList.split(","));
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

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLoginPhone() {
		return loginPhone;
	}

	public void setLoginPhone(String loginPhone) {
		this.loginPhone = loginPhone;
	}

	public Integer getReplyNum() {
		return replyNum;
	}

	public void setReplyNum(Integer replyNum) {
		this.replyNum = replyNum;
	}

	public List<String> getDisplayList() {
		return displayList;
	}

	public void setDisplayList(List<String> displayList) {
		this.displayList = displayList;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}