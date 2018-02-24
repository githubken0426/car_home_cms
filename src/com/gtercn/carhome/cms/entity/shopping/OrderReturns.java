package com.gtercn.carhome.cms.entity.shopping;

import java.math.BigDecimal;
import java.util.Date;

public class OrderReturns {
	private String id;
	private String returnsNo;
	private String orderId;
	private String expressNo;
	private String realname;
	private String telphone;
	private String address;
	private String postalCode;
	private String logisticsNo;
	private Double logisticsFee;
	private BigDecimal returnsAmount;
	private Integer logisticsStatus;
	private Integer returnsType;
	/**
	 * 退货处理方式；
	 * 0.PUPAWAY:退货入库;
	 * 1.REDELIVERY:重新发货;
	 * 3.RECLAIM-REDELIVERY:不要求归还并重新发货;
	 * 4. REFUND:退款;
	 * 5. COMPENSATION:不退货并赔偿
	 */
	private Integer handlingWay;
	private Date applyTime;
	private Date handlingTime;
	private String reason;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getReturnsNo() {
		return returnsNo;
	}
	public void setReturnsNo(String returnsNo) {
		this.returnsNo = returnsNo;
	}
	public String getOrderId() {
		return orderId;
	}
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	public String getExpressNo() {
		return expressNo;
	}
	public void setExpressNo(String expressNo) {
		this.expressNo = expressNo;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getLogisticsNo() {
		return logisticsNo;
	}
	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}
	public Double getLogisticsFee() {
		return logisticsFee;
	}
	public void setLogisticsFee(Double logisticsFee) {
		this.logisticsFee = logisticsFee;
	}
	public BigDecimal getReturnsAmount() {
		return returnsAmount;
	}
	public void setReturnsAmount(BigDecimal returnsAmount) {
		this.returnsAmount = returnsAmount;
	}
	public Integer getLogisticsStatus() {
		return logisticsStatus;
	}
	public void setLogisticsStatus(Integer logisticsStatus) {
		this.logisticsStatus = logisticsStatus;
	}
	public Integer getReturnsType() {
		return returnsType;
	}
	public void setReturnsType(Integer returnsType) {
		this.returnsType = returnsType;
	}
	public Integer getHandlingWay() {
		return handlingWay;
	}
	public void setHandlingWay(Integer handlingWay) {
		this.handlingWay = handlingWay;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getHandlingTime() {
		return handlingTime;
	}
	public void setHandlingTime(Date handlingTime) {
		this.handlingTime = handlingTime;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
}
