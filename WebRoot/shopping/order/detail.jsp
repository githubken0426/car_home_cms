<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/jsp/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%
response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>商品详情</title>
	<link rel="stylesheet" href="<%=path %>/css/pubmain.css" />
	<link href="<%=path %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=path %>/css/global.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
	<script type="text/javascript" src="<%=path %>/js/layer/layer.js"></script>
<script type="text/javascript">
    //返回
  	function turnBack(){
  		$("#backForm").submit();
  	}
  </script>
</head>
<body>

<div id="middle">
	<div class="right"  id="mainFrame">
		<form  method="post" id="addForm">
			<div class="content-box">
				<div class="content-box-header">
			    	<span class="now_location">当前位置:</span>订单详情
			    	<input type="button" value="返回" class="btn btn-info" style="width:80px;position: absolute;right: 40px;top: 25px;" onclick="turnBack()"/>
			        <div class="clear"></div>
			    </div>
		   		<div style="margin:0 auto; margin:10px;">
	            	<table class="table table-bordered" >
						<!-- 数据录入区  -->
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">订单号：</td>
							<td width="40%">
								<span style="margin-left:30px;">${entity.orderNo}</span>
							</td>
							<td width="10%"  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">
								助销达人
							</td>
							<td width="40%" >
								<span style="margin-left:30px;">
									<c:choose>
										<c:when test="${not empty entity.expertName}">${entity.expertName}</c:when>
										<c:otherwise>无</c:otherwise>
									</c:choose>
								</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户：</td>
							<td>
								<span style="margin-left:30px;">${entity.userName} </span>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">订单状态：</td>
							<td>
								<span style="margin-left:30px;">
									<c:choose>
										<c:when test="${entity.orderStatus==1 }">待付款</c:when>
										<c:when test="${entity.orderStatus==2 }">已付款</c:when>
										<c:when test="${entity.orderStatus==3 }">订单关闭</c:when>
										<c:when test="${entity.orderStatus==4 }">已发货</c:when>
										<c:when test="${entity.orderStatus==5 }">已签收</c:when>
										<c:when test="${entity.orderStatus==6 }">订单完成</c:when>
										<c:when test="${entity.orderStatus==7 }">退货申请</c:when>
										<c:when test="${entity.orderStatus==8 }">退货中</c:when>
										<c:when test="${entity.orderStatus==9 }">退货完成</c:when>
									</c:choose>
								</span>
							</td>
						</tr>
						
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">下单时间：</td>
							<td>
								<span style="margin-left:30px;">
									<fmt:formatDate value='${entity.orderTime}' type='both' pattern='yyyy-MM-dd HH:mm' dateStyle='long'/>
								</span>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">付款时间：</td>
							<td>
								<span style="margin-left:30px;">
									<fmt:formatDate value='${entity.payTime}' type='both' pattern='yyyy-MM-dd HH:mm' dateStyle='long'/>
								</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">总金额：</td>
							<td>
								<span style="margin-left:30px;">${entity.totalAmount}(元)</span>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">实付金额：</td>
							<td>
								<span style="margin-left:30px;">${entity.payment}</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">收货地址：</td>
							<td>
								<span style="margin-left:30px;">${entity.address}</span>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">物流单号：</td>
							<td>
								<span style="margin-left:30px;">
									<a href="javascript:void(0);" onclick="logisticsDetail()">${entity.logisticsNo}</a>
								</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">支付方式：</td>
							<td>
								<span style="margin-left:30px;">
								<c:choose>
									<c:when test="${entity.payChannel=='A' }">支付宝</c:when>
									<c:when test="${entity.payChannel=='W' }">微信</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
								</span>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">支付流水号：</td>
							<td>
								<span style="margin-left:30px;">${entity.escrowTradeNo}</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">客户备注：</td>
							<td>
								<span style="margin-left:30px;">${entity.customerMark}</span>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">取消交易原因：</td>
							<td>
								<span style="margin-left:30px;">${entity.cancelReason}</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">发票号码：</td>
							<td><span style="margin-left: 30px;">${entity.invoiceNo}</span></td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">发票类型：</td>
							<td>
								<c:choose>
									<c:when test="${entity.invoiceType eq 'E' }">
										<span style="margin-left: 30px;">电子发票</span>
									</c:when>
									<c:when test="${entity.invoiceType eq 'P' }">
										<span style="margin-left: 30px;">纸质发票</span>
									</c:when>
									<c:otherwise></c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">发票抬头：</td>
							<td colspan="3">
								<span style="margin-left: 30px;" title="${entity.invoiceTitle }">${entity.invoiceTitle }</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">发票内容：</td>
							<td colspan="3">
								<span style="margin-left: 30px;" title="${entity.invoiceContent}">${entity.invoiceContent}</span>
							</td>
						</tr>
	            	</table>
	            	<!-- 购买商品明细 -->
	            	<table class="table table-bordered">
	            		<tr>
	            			<td width="10%" align="center" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品明细:</td>
							<td width="40%" align="center" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品标题</td>
							<td width="40%" align="center"  nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品规格</td>
							<td width="10%" align="center"  nowrap="nowrap" bgcolor="#f1f1f1" height="40px">购买数量</td>
						</tr>
	            		<c:forEach var="goodsDetail" items="${entity.orderDetails }">
	            		<tr>
	            			<td nowrap="nowrap" bgcolor="#f1f1f1" height="40px"></td>
							<td align="left" title="${goodsDetail.goodsTitle}">
								<span style="margin-left:30px;">${goodsDetail.goodsTitle}</span>
							</td>
							<td align="left" >
								<span style="margin-left:30px;">${goodsDetail.specItems}</span>
							</td>
							<td align="center" >
								<span style="margin-left:30px;">${goodsDetail.number}</span>
							</td>
						</tr>
	            		</c:forEach>
	            	</table>
	         	</div> 
			</div>
		</form>
		<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/order_list.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="categoryId" value="${categoryId}" />
			<input type="hidden" name="brandId" value="${brandId}" />
			<input type="hidden" name="title" value="${title}" />
			<input type="hidden" name="beginTime" value="${beginTime}" />
			<input type="hidden" name="endTime" value="${endTime}" />
		</form>
	</div>
</div>
<!-- 物流详情 -->
<div id="logisticsDetail" style="display: none">
	<table class="table table-condensed" style="margin-bottom:0px;">
		<tr>
			<td width="70%" align="center" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">物流信息</td>
			<td width="30%" align="center" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">时间</td>
		</tr>
	 <c:forEach var="logiticsDetail" items="${logistics.details }">
	    <tr>
			<td width="70%" >
				<span style="margin-left:30px;" title="${logiticsDetail.description}">${logiticsDetail.description}</span>
			</td>
			<td width="30%">
				<span style="margin-left:30px;">
					<fmt:formatDate value='${logiticsDetail.createTime}' type='both' pattern='yyyy-MM-dd HH:mm' dateStyle='long'/>
				</span>
			</td>
		</tr>
	 </c:forEach>
	</table>
</div>
<script type="text/javascript">
//物流详情
function logisticsDetail(){
	layer.open({
		title : '<i class="icon-location-pin"></i>物流详情:<strong>${logistics.logisticsName } / ${logistics.logisticsNo }</strong>',
		type : 1,
		area: ['600px', '350px'],
		btn: ["<i class='fa fa-ban'></i> 确定"],
		closeBtn: 1,
		content : $("#logisticsDetail")
	});
}
</script>
</body>
</html>
