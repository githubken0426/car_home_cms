package com.gtercn.carhome.cms.entity;

import java.util.Date;

public class Shop {
    private String id;

    private String rescueService;

    private String repairService;

    private String cleanService;

    private String maintainService;

    private String tyreService;

    private String shopPicUrl;

    private String shopName;

    private String shopScore;

    private String shopDescription;

    private String longitude;

    private String latitude;

    private String province;

    private String city;

    private String district;

    private String detailAddress;

    private String telNumberList;

    private String displayPicUrlList;

    private Integer isTop;

    private Integer displayPriority;

    private Integer deleteFlag;

    private Date insertTime;

    private Date updateTime;
    
    private String displayPicUrl1;
    
    private String displayPicUrl2;
    
    private String displayPicUrl3;
    
    private String displayPicUrl4;
    
    private String displayPicUrl5;
    
    private String typeValue;

    private String experience;
    
    private String productDescription;
    
    private String cityCode;
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getRescueService() {
        return rescueService;
    }

    public void setRescueService(String rescueService) {
        this.rescueService = rescueService;
    }

    public String getRepairService() {
        return repairService;
    }

    public void setRepairService(String repairService) {
        this.repairService = repairService;
    }

    public String getCleanService() {
        return cleanService;
    }

    public void setCleanService(String cleanService) {
        this.cleanService = cleanService;
    }

    public String getMaintainService() {
        return maintainService;
    }

    public void setMaintainService(String maintainService) {
        this.maintainService = maintainService;
    }

    public String getTyreService() {
        return tyreService;
    }

    public void setTyreService(String tyreService) {
        this.tyreService = tyreService;
    }

    public String getShopPicUrl() {
        return shopPicUrl;
    }

    public void setShopPicUrl(String shopPicUrl) {
        this.shopPicUrl = shopPicUrl == null ? null : shopPicUrl.trim();
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName == null ? null : shopName.trim();
    }

    public String getShopScore() {
        return shopScore;
    }

    public void setShopScore(String shopScore) {
        this.shopScore = shopScore == null ? null : shopScore.trim();
    }

    public String getShopDescription() {
        return shopDescription;
    }

    public void setShopDescription(String shopDescription) {
        this.shopDescription = shopDescription == null ? null : shopDescription.trim();
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude == null ? null : longitude.trim();
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude == null ? null : latitude.trim();
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province == null ? null : province.trim();
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city == null ? null : city.trim();
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district == null ? null : district.trim();
    }

    public String getDetailAddress() {
        return detailAddress;
    }

    public void setDetailAddress(String detailAddress) {
        this.detailAddress = detailAddress == null ? null : detailAddress.trim();
    }

    public String getTelNumberList() {
        return telNumberList;
    }

    public void setTelNumberList(String telNumberList) {
        this.telNumberList = telNumberList == null ? null : telNumberList.trim();
    }

    public String getDisplayPicUrlList() {
        return displayPicUrlList;
    }

    public void setDisplayPicUrlList(String displayPicUrlList) {
        this.displayPicUrlList = displayPicUrlList == null ? null : displayPicUrlList.trim();
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public Integer getDisplayPriority() {
        return displayPriority;
    }

    public void setDisplayPriority(Integer displayPriority) {
        this.displayPriority = displayPriority;
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

	public String getDisplayPicUrl1() {
		return displayPicUrl1;
	}

	public void setDisplayPicUrl1(String displayPicUrl1) {
		this.displayPicUrl1 = displayPicUrl1;
	}

	public String getDisplayPicUrl2() {
		return displayPicUrl2;
	}

	public void setDisplayPicUrl2(String displayPicUrl2) {
		this.displayPicUrl2 = displayPicUrl2;
	}

	public String getDisplayPicUrl3() {
		return displayPicUrl3;
	}

	public void setDisplayPicUrl3(String displayPicUrl3) {
		this.displayPicUrl3 = displayPicUrl3;
	}

	public String getDisplayPicUrl4() {
		return displayPicUrl4;
	}

	public void setDisplayPicUrl4(String displayPicUrl4) {
		this.displayPicUrl4 = displayPicUrl4;
	}

	public String getDisplayPicUrl5() {
		return displayPicUrl5;
	}

	public void setDisplayPicUrl5(String displayPicUrl5) {
		this.displayPicUrl5 = displayPicUrl5;
	}

	public String getTypeValue() {
		return typeValue;
	}

	public void setTypeValue(String typeValue) {
		this.typeValue = typeValue;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}
}