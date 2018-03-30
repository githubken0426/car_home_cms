package com.gtercn.carhome.cms.entity;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ExpertTop {
	private String id;
	private String userId;
	private String topTitle;
	private String expertName;
	private String expertDiscriptionShort;
	private String expertPortraitUrl;
	private String expertWechatNumber;
	private String expertExperience;
	private String expertTelNumber;
	private String motto;
	private String expertDisplayPicList;
	private String expertDiscriptionDetail;
	private String deleteFlag;
	private Integer isInn;
	private String cityCode;
	private Date insertTime;
	private Date updateTime;
	private String categoryTitle;
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

	public String getTopTitle() {
		return topTitle;
	}

	public void setTopTitle(String topTitle) {
		this.topTitle = topTitle == null ? null : topTitle.trim();
	}

	public String getExpertName() {
		return expertName;
	}

	public void setExpertName(String expertName) {
		this.expertName = expertName == null ? null : expertName.trim();
	}

	public String getExpertDiscriptionShort() {
		return expertDiscriptionShort;
	}

	public void setExpertDiscriptionShort(String expertDiscriptionShort) {
		this.expertDiscriptionShort = expertDiscriptionShort == null ? null
				: expertDiscriptionShort.trim();
	}

	public String getExpertPortraitUrl() {
		return expertPortraitUrl;
	}

	public void setExpertPortraitUrl(String expertPortraitUrl) {
		this.expertPortraitUrl = expertPortraitUrl;
	}

	public String getExpertWechatNumber() {
		return expertWechatNumber;
	}

	public void setExpertWechatNumber(String expertWechatNumber) {
		this.expertWechatNumber = expertWechatNumber == null ? null
				: expertWechatNumber.trim();
	}

	public String getExpertExperience() {
		return expertExperience;
	}

	public void setExpertExperience(String expertExperience) {
		this.expertExperience = expertExperience == null ? null
				: expertExperience.trim();
	}

	public String getExpertTelNumber() {
		return expertTelNumber;
	}

	public void setExpertTelNumber(String expertTelNumber) {
		this.expertTelNumber = expertTelNumber == null ? null : expertTelNumber
				.trim();
	}

	public String getMotto() {
		return motto;
	}

	public void setMotto(String motto) {
		this.motto = motto == null ? null : motto.trim();
	}

	public String getExpertDisplayPicList() {
		return expertDisplayPicList;
	}

	public void setExpertDisplayPicList(String expertDisplayPicList) {
		this.expertDisplayPicList = expertDisplayPicList;
		this.displayList = (expertDisplayPicList == null) ? null : Arrays.asList(expertDisplayPicList.split(","));
	}

	public String getExpertDiscriptionDetail() {
		return expertDiscriptionDetail;
	}

	public void setExpertDiscriptionDetail(String expertDiscriptionDetail) {
		this.expertDiscriptionDetail = expertDiscriptionDetail == null ? null
				: expertDiscriptionDetail.trim();
	}

	public String getDeleteFlag() {
		return deleteFlag;
	}

	public void setDeleteFlag(String deleteFlag) {
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

	public List<String> getDisplayList() {
		return displayList;
	}

	public void setDisplayList(List<String> displayList) {
		this.displayList = displayList;
	}

	public Integer getIsInn() {
		return isInn;
	}

	public void setIsInn(Integer isInn) {
		this.isInn = isInn;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getCategoryTitle() {
		return categoryTitle;
	}

	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
}