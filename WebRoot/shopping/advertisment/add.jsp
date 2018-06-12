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
	<title>新增</title>
	<link rel="stylesheet" href="<%=path %>/css/pubmain.css" />
	<link href="<%=path %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=path %>/css/global.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/cms/view_image/view_image.js"></script>
    <script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">
function getBrandByCtegory(ele) {
	$("#brandId").empty();
	var optA="<option value='-1'>请选择品牌</option>";
	 $("#brandId").append(optA);
	if (ele.value!=-1) {
		$.ajax({
			type : "POST",
			dataType : "json",
			data : {
				categoryId : ele.value
			},
			async : true,
			url : "${pageContext.request.contextPath}/brand_getBrandByCtegory.action",			
			success : function(data) {
				 var json = eval(data); //数组       
	             $.each(json, function (index, item) {
	                 //循环获取数据  
	                 var name = json[index].cnName;
	                 var id = json[index].id;
	                 var opt="<option value="+id+">"+name+"</option>";
	                 $("#brandId").append(opt);
	             });
			}		
		});
	}
}

function getGoodsByCity() {
	$("#goodsId").empty();
	var optA="<option value='-1'>请选择商品</option>";
	$("#goodsId").append(optA);
	var brandId=$("#brandId").val();
	var cityId=$("#cityId").val();
	if (brandId!=-1) {
		$.ajax({
			type : "POST",
			dataType : "json",
			data : {
				brandId:brandId,
				cityId : cityId
			},
			async : true,
			url : "${pageContext.request.contextPath}/goods_selectGoodsByCity.action",			
			success : function(data) {
				 var json = eval(data); //数组       
	             $.each(json, function (index, item) {
	                 //循环获取数据  
	                 var title = json[index].goodsTitle;
	                 var id = json[index].id;
	                 var opt="<option value="+id+">"+title+"</option>";
	                 $("#goodsId").append(opt);
	             });
			}		
		});
	}
}

   	//添加
	function addSubmit(){
   		var title=$.trim($("#title").val());
   		if(!title){
   			layer.tips('请输入广告标题！', '#title');
   			return false;
   		}
		$("#addForm").submit();	
	}
    //返回
  	function turnBack(){
  		$("#backForm").submit();
  	}
  </script>
</head>
<body>

<div id="middle">
	<div class="right"  id="mainFrame">
		<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/adver_add.action" method="post" id="addForm">
			<div class="content-box">
				<div class="content-box-header">
			    	<span class="now_location">当前位置:</span>添加
			        <div class="clear"></div>
			    </div>
		   		<div style="margin:0 auto; margin:10px;">
	            	<table class="table table-bordered" >
						<!-- 数据录入区  -->
						<tr>
							<td width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">广告分类：</td>
							<td colspan="3">
								<select onchange="getBrandByCtegory(this)" id="categoryId" name="categoryId" 
									style="padding:3px;height:35px;font-size:14px;margin-left:30px;width:195px;">
									<option value="-1">请选择分类</option>
									<c:forEach var="category" items="${categoryList}">
										<option value="${category.id }"
											<c:if test='${categoryId ==category.id}'>selected='selected'</c:if>>
											${category.title}</option>
									</c:forEach>
								</select> - 
								<select onclick="getGoodsByCity()" id="brandId" name="brandId" style="padding:3px;height:35px;font-size:14px;width:200px;">
									<option value="-1">请选择品牌</option>
									<c:forEach var="brand" items="${brandList}">
										<option value="${brand.id }"
											<c:if test='${brandId ==brand.id}'>selected='selected'</c:if>>
											${brand.cnName}</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">广告标题：</td>
							<td colspan="3">
								<input type="text" id="title" name="entity.title" tabindex="1" maxlength="100" style="font-size:14px;padding:8px;width:400px;margin-left:30px;"/>
							</td>
						</tr>
						<tr>
							<td width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">城市：</td>
							<td colspan="3">
								<select onclick="getGoodsByCity(this)" id="cityId" name="entity.cityId" style="padding:3px;height:35px;font-size:14px;width:415px;margin-left:30px;">
									<c:forEach var="city" items="${cityList}">
										<option value="${city.id }" <c:if test='${city.cityCode eq cityCode}'>selected='selected'</c:if>>
											${city.cityName}
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">广告链接：</td>
							<td colspan="3">
								<select id="goodsId" name="entity.goodsId" style="padding:3px;height:35px;font-size:14px;width:415px;margin-left:30px;">
								<option value="-1">请选择促销商品</option>
								<c:forEach var="goods" items="${goodsList}">
									<option value="${goods.id }">
										${goods.goodsTitle}</option>
								</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">广告图片：</td>
							<td colspan="3">
								<input onchange="viewUploadImg(this,'viewResUrlList')" type="file" 
									id="resUrlList" name="resUrlList" tabindex="4" maxlength="300" style="padding:4px;width:405px;margin-left:30px;"/>
								<img style="width:50px;height:50px;display:none;" id="viewResUrlList"/>
							</td>
						</tr>
	            	</table>
	            	 <table  class="margin-bottom-20 table  no-border" style="margin-top:20px;">
			              <tr>
			     	         <td align="right">
			     	         	<input type="button" value="保存" class="btn btn-info " style="width:80px;" onclick="addSubmit()" />
			     	         </td>
			     	         <td align="center">
			     	         	<input type="button" value="返回" class="btn btn-info " style="width:80px;"  onclick="turnBack()"/>
			     	         </td>
			     	         <td></td>
			             </tr>
			        </table>
	         	</div> 
			</div>
		</form>
			
		<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/adver_listData.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="title" value="${title}" />
			<input type="hidden" name="beginTime" value="${beginTime}" />
			<input type="hidden" name="endTime" value="${endTime}" />
		</form>
	</div>
</div>
</body>
</html>
