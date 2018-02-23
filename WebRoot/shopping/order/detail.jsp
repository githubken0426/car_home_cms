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
		<form action="${pageContext.request.contextPath}/goods_add.action" method="post" id="addForm">
			<div class="content-box">
				<div class="content-box-header">
			    	<span class="now_location">当前位置:</span>商品详情
			    	<c:if test="${entity.status==1 }"><font color='red'>(此商品已下架)</font></c:if>
			        <input type="button" value="返回" class="btn btn-info" style="width:80px;position: absolute;right: 40px;top: 25px;" onclick="turnBack()"/>
			        <div class="clear"></div>
			    </div>
		   		<div style="margin:0 auto; margin:10px;">
	            	<table class="table table-bordered" >
						<!-- 数据录入区  -->
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品品牌：</td>
							<td width="40%">
								<span style="margin-left:30px;">${entity.brandName}</span>
							</td>
							<td width="10%"  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">
								<c:if test="${entity.status==1 }">下架时间:</c:if>
							</td>
							<td width="40%" >
								<c:if test="${entity.status==1 }">
									<span style="margin-left:30px;">
										<fmt:formatDate value="${entity.downTime}" type="both"/>
									</span>
								</c:if>
							</td>
						</tr>
						
						<c:forEach var="spec" items="${specList}">
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">${spec.name}：</td>
							<td colspan="3">
								<span style="margin-left:30px;">${spec.item} </span>
							</td>
						</tr>
						</c:forEach>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品标题：</td>
							<td colspan="3">
								<span style="margin-left:30px;">${entity.goodsTitle} </span>
							</td>
						</tr>
						
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">原价：</td>
							<td>
								<span style="margin-left:30px;">${entity.primePrice}(元)</span>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">促销价：</td>
							<td>
								<span style="margin-left:30px;">${entity.promotionPrice}(元)</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">进货价：</td>
							<td>
								<span style="margin-left:30px;">${entity.costPrice}(元)</span>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">积分抵值：</td>
							<td>
								<span style="margin-left:30px;">${entity.score}</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品重量：</td>
							<td>
								<span style="margin-left:30px;">${entity.weight}(kg)</span>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品产地：</td>
							<td>
								<span style="margin-left:30px;">${entity.productArea}</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">库存：</td>
							<td>
								<span style="margin-left:30px;">${entity.stock}</span>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">已售数量：</td>
							<td>
								<span style="margin-left:30px;">${entity.soldNumber}</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">设置搜索标签：</td>
							<td>
								<span style="margin-left:30px;">${entity.searchTag}</span>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">上架时间：</td>
							<td>
								<span style="margin-left:30px;">
									<fmt:formatDate value="${entity.upTime}" type="both"/>
								</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">热门产品：</td>
							<td>
								<c:choose>
									<c:when test="${entity.isHot eq 'Y' }">
										<span style="margin-left: 30px;">是</span>
									</c:when>
									<c:otherwise>
										<span style="margin-left: 30px;">否</span>
									</c:otherwise>
								</c:choose>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">新品：</td>
							<td>
								<c:choose>
									<c:when test="${entity.isNew eq 'Y' }">
										<span style="margin-left: 30px;">是</span>
									</c:when>
									<c:otherwise>
										<span style="margin-left: 30px;">否</span>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品简介：</td>
							<td colspan="3">
								<span style="margin-left: 30px;" title="${entity.goodsSynopsis }">${entity.goodsSynopsis }</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品描述：</td>
							<td colspan="3">
								<span style="margin-left: 30px;" title="${entity.goodsDescription}">${entity.goodsDescription}</span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品小图：</td>
							<td colspan="3">
								<span style="margin-left:25px;"></span>
								<c:forEach var="small" items="${entity.smallPictureList}">
									<img src="${small}" style="width:50px;height:50px;margin:5px;"></img>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品大图：</td>
							<td colspan="3">
								<span style="margin-left:25px;"></span>
								<c:forEach var="big" items="${entity.bigPictureList}">
									<img src="${big}" style="width:100px;height:100px;margin:5px;"></img>
								</c:forEach>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品详情图：</td>
							<td colspan="3">
								<span style="margin-left:25px;"></span>
								<c:forEach var="detail" items="${entity.detailPictureList}">
									<img src="${detail}" style="width:100px;height:100px;margin:5px;"></img>
								</c:forEach>
							</td>
						</tr>
	            	</table>
	         	</div> 
			</div>
		</form>
			
		<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/goods_list.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="categoryId" value="${categoryId}" />
			<input type="hidden" name="brandId" value="${brandId}" />
			<input type="hidden" name="title" value="${title}" />
			<input type="hidden" name="beginTime" value="${beginTime}" />
			<input type="hidden" name="endTime" value="${endTime}" />
		</form>
	</div>
</div>
</body>
</html>
