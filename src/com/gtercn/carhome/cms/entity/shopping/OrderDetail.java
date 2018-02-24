package com.gtercn.carhome.cms.entity.shopping;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

public class OrderDetail {
	private String id;
	private String orderId;
	private String orderNo;
	private String goodsId;
	private BigDecimal goodsPrice;
	private BigDecimal subtotal;
	private String specItemIds;
	private Integer number;
	private Date createTime;
	private String smallPicture;
	private List<String> smallPictureList;
	private String goodsTitle;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public BigDecimal getGoodsPrice() {
		return goodsPrice;
	}
	public void setGoodsPrice(BigDecimal goodsPrice) {
		this.goodsPrice = goodsPrice;
	}
	public String getSpecItemIds() {
		return specItemIds;
	}
	public void setSpecItemIds(String specItemIds) {
		this.specItemIds = specItemIds;
	}
	public Integer getNumber() {
		return number;
	}
	public void setNumber(Integer number) {
		this.number = number;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public BigDecimal getSubtotal() {
		return subtotal;
	}
	public void setSubtotal(BigDecimal subtotal) {
		this.subtotal = subtotal;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public List<String> getSmallPictureList() {
		return smallPictureList;
	}
	public void setSmallPictureList(List<String> smallPictureList) {
		this.smallPictureList = smallPictureList;
	}
	public String getSmallPicture() {
		return smallPicture;
	}
	public void setSmallPicture(String smallPicture) {
		this.smallPicture = smallPicture == null ? "" : smallPicture.trim();
		this.smallPictureList= StringUtils.isNotBlank(smallPicture) ? Arrays.asList(smallPicture.split(",")):null;
	}
	public String getGoodsTitle() {
		return goodsTitle;
	}
	public void setGoodsTitle(String goodsTitle) {
		this.goodsTitle = goodsTitle;
	}
}
