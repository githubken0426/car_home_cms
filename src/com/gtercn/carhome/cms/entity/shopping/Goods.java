package com.gtercn.carhome.cms.entity.shopping;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class Goods {
	private String id;
    private String brandId;
    private Integer score;
    private String skuCode;
    private BigDecimal primePrice;
    private BigDecimal promotionPrice;
    private BigDecimal costPrice;
    private String productArea;
    private String goodsTitle;
    private String goodsSynopsis;
    private String goodsDescription;
    private String goodsDetail;
    private String searchTag;
    private Integer stock;
    private String bigPicture;
    private String smallPicture;
    private Integer soldNumber;
    private String isHot;
    private String isNew;
    private Date upTime;
    private Date downTime;
    private Integer status;
    private String cityId;
    private Date updateTime;
    private Date createTime;
    private double weight;
    
    private String categoryId;
    private String brandName;
    private List<String> bigPictureList;
    private List<String> smallPictureList;
    private List<String> detailPictureList;
    
    public String getBrandName() {
		return brandName;
	}

	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}

	public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    public String getBrandId() {
		return brandId;
	}

	public void setBrandId(String brandId) {
		this.brandId = brandId;
	}

	public String getSkuCode() {
        return skuCode;
    }

    public void setSkuCode(String skuCode) {
        this.skuCode = skuCode == null ? null : skuCode.trim();
    }

    public String getProductArea() {
        return productArea;
    }

    public void setProductArea(String productArea) {
        this.productArea = productArea == null ? null : productArea.trim();
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle == null ? null : goodsTitle.trim();
    }

    public String getGoodsSynopsis() {
        return goodsSynopsis;
    }

    public void setGoodsSynopsis(String goodsSynopsis) {
        this.goodsSynopsis = goodsSynopsis == null ? null : goodsSynopsis.trim();
    }

    public String getGoodsDescription() {
        return goodsDescription;
    }

    public void setGoodsDescription(String goodsDescription) {
        this.goodsDescription = goodsDescription == null ? null : goodsDescription.trim();
    }

    public String getSearchTag() {
        return searchTag;
    }

    public void setSearchTag(String searchTag) {
        this.searchTag = searchTag == null ? null : searchTag.trim();
    }

    public String getBigPicture() {
		return bigPicture;
	}

	public void setBigPicture(String bigPicture) {
		this.bigPicture = bigPicture == null ? "" : bigPicture.trim();
		this.bigPictureList= StringUtils.isNotBlank(bigPicture) ? Arrays.asList(bigPicture.split(",")):null;
	}

	public String getSmallPicture() {
		return smallPicture;
	}

	public void setSmallPicture(String smallPicture) {
		this.smallPicture = smallPicture == null ? "" : smallPicture.trim();
		this.smallPictureList= StringUtils.isNotBlank(smallPicture) ? Arrays.asList(smallPicture.split(",")):null;
	}

    public String getIsHot() {
        return isHot;
    }

    public void setIsHot(String isHot) {
        this.isHot = isHot == null ? null : isHot.trim();
    }

    public String getIsNew() {
        return isNew;
    }

    public void setIsNew(String isNew) {
        this.isNew = isNew == null ? null : isNew.trim();
    }

    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }

    public Date getDownTime() {
        return downTime;
    }

    public void setDownTime(Date downTime) {
        this.downTime = downTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getGoodsDetail() {
		return goodsDetail;
	}

	public void setGoodsDetail(String goodsDetail) {
		this.goodsDetail = goodsDetail == null ? "" : goodsDetail.trim();
		this.detailPictureList= StringUtils.isNotBlank(goodsDetail) ? Arrays.asList(goodsDetail.split(",")):null;
	}

	public List<String> getBigPictureList() {
		return bigPictureList;
	}

	public void setBigPictureList(List<String> bigPictureList) {
		this.bigPictureList = bigPictureList;
	}

	public List<String> getSmallPictureList() {
		return smallPictureList;
	}

	public void setSmallPictureList(List<String> smallPictureList) {
		this.smallPictureList = smallPictureList;
	}
	
	public List<String> getDetailPictureList() {
		return detailPictureList;
	}

	public void setDetailPictureList(List<String> detailPictureList) {
		this.detailPictureList = detailPictureList;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(String categoryId) {
		this.categoryId = categoryId;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public BigDecimal getPrimePrice() {
		return primePrice;
	}

	public void setPrimePrice(BigDecimal primePrice) {
		this.primePrice = primePrice;
	}

	public BigDecimal getPromotionPrice() {
		return promotionPrice;
	}

	public void setPromotionPrice(BigDecimal promotionPrice) {
		this.promotionPrice = promotionPrice;
	}

	public BigDecimal getCostPrice() {
		return costPrice;
	}

	public void setCostPrice(BigDecimal costPrice) {
		this.costPrice = costPrice;
	}

	public Integer getStock() {
		return stock;
	}

	public void setStock(Integer stock) {
		this.stock = stock;
	}

	public Integer getSoldNumber() {
		return soldNumber;
	}

	public void setSoldNumber(Integer soldNumber) {
		this.soldNumber = soldNumber;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}
}