package com.gtercn.carhome.cms.entity.shopping;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public class Order {
	private String id;
	private String userId;
	private String expertId;
	private String orderNo;//订单号
	/**
	 * 订单状态
	 * 1待付款,2已付款(待发货),3关闭订单(超时未付款),
	 * 4已发货(待收货),5已签收(待评价),6已评价(订单完成),
	 * 7退货申请,8退货中,9已退货
	 */
	private Integer orderStatus;
	private Date orderTime;
	private Date payTime;
	private Integer itemCount;
	private BigDecimal totalAmount;
	private BigDecimal payment;
	private String addressId;
	private String logisticsId;
	private String payChannel;
	private String escrowTradeNo;
	private String buyerAccount;
	private String customerMark;
	private String cancelReason;
	private String invoice;
	private String invoiceNo;
	private String invoiceType;//发票类型,E电子发票,P纸质发票
	private String invoiceTitle;
	private String invoiceContent;
	
	private List<OrderDetail> orderDetails;
	private List<LogisticsDetail> logisticsDetail;//物流详情
	private String logisticsNo;
	private String address;
	private String expertName;
	private String userName;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getExpertId() {
		return expertId;
	}
	public void setExpertId(String expertId) {
		this.expertId = expertId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Date getOrderTime() {
		return orderTime;
	}
	public void setOrderTime(Date orderTime) {
		this.orderTime = orderTime;
	}
	public Date getPayTime() {
		return payTime;
	}
	public void setPayTime(Date payTime) {
		this.payTime = payTime;
	}
	public Integer getItemCount() {
		return itemCount;
	}
	public void setItemCount(Integer itemCount) {
		this.itemCount = itemCount;
	}
	public BigDecimal getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	public String getAddressId() {
		return addressId;
	}
	public void setAddressId(String addressId) {
		this.addressId = addressId;
	}
	public String getLogisticsId() {
		return logisticsId;
	}
	public void setLogisticsId(String logisticsId) {
		this.logisticsId = logisticsId;
	}
	public String getPayChannel() {
		return payChannel;
	}
	public void setPayChannel(String payChannel) {
		this.payChannel = payChannel;
	}
	public String getEscrowTradeNo() {
		return escrowTradeNo;
	}
	public void setEscrowTradeNo(String escrowTradeNo) {
		this.escrowTradeNo = escrowTradeNo;
	}
	public String getCustomerMark() {
		return customerMark;
	}
	public void setCustomerMark(String customerMark) {
		this.customerMark = customerMark;
	}
	public BigDecimal getPayment() {
		return payment;
	}
	public void setPayment(BigDecimal payment) {
		this.payment = payment;
	}
	public String getCancelReason() {
		return cancelReason;
	}
	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}
	public String getInvoice() {
		return invoice;
	}
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	public String getInvoiceNo() {
		return invoiceNo;
	}
	public void setInvoiceNo(String invoiceNo) {
		this.invoiceNo = invoiceNo;
	}
	public String getLogisticsNo() {
		return logisticsNo;
	}
	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}
	public List<LogisticsDetail> getLogisticsDetail() {
		return logisticsDetail;
	}
	public void setLogisticsDetail(List<LogisticsDetail> logisticsDetail) {
		this.logisticsDetail = logisticsDetail;
	}
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
	public String getInvoiceType() {
		return invoiceType;
	}
	public void setInvoiceType(String invoiceType) {
		this.invoiceType = invoiceType;
	}
	public String getInvoiceTitle() {
		return invoiceTitle;
	}
	public void setInvoiceTitle(String invoiceTitle) {
		this.invoiceTitle = invoiceTitle;
	}
	public String getInvoiceContent() {
		return invoiceContent;
	}
	public void setInvoiceContent(String invoiceContent) {
		this.invoiceContent = invoiceContent;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getBuyerAccount() {
		return buyerAccount;
	}
	public void setBuyerAccount(String buyerAccount) {
		this.buyerAccount = buyerAccount;
	}
	public String getExpertName() {
		return expertName;
	}
	public void setExpertName(String expertName) {
		this.expertName = expertName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
}
