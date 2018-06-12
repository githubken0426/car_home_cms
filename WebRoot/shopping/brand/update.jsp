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
	<title>修改</title>
	<link rel="stylesheet" href="<%=path %>/css/pubmain.css" />
	<link href="<%=request.getContextPath() %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/cms/view_image/view_image.js"></script>
    <script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">
   	// 更新
	function updateSubmit(){
		var enName=$.trim($("#enName").val());
   		if(!enName){
   			layer.tips('请输入品牌中文名称！', '#enName');
   			return false;
   		}
		$("#updateForm").submit();	
	}
   	// 返回
  	function turnBack(){
  		$("#backForm").submit();
  	}
</script>
</head>
  
<body>
<div id="middle">
	<div class="right"  id="mainFrame">
		<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/brand_update.action" method="post" id="updateForm">
			<div class="content-box">
				<div class="content-box-header">
					<span class="now_location">当前位置:</span>修改
				    <div class="clear"></div>
			    </div>
	            	<table class="table table-bordered" >
						<!-- 数据修改  -->
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">所属分类：</td>
							<td width="25%">
								<input type="hidden" name="entity.id" value="${entity.id }"/>
								<select id="categoryId" name="entity.categoryId" style="height:25px;width:220px;margin-left:30px;">
									<c:forEach var="category" items="${categoryList}">
										<option value="${category.id }" 
										<c:if test="${entity.categoryId==category.id }">selected="selected"</c:if>>
											${category.title}
										</option>
									</c:forEach>
								</select>
							</td>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">品牌英文名称：</td>
							<td width="25%">
								<input type="text" id="enName" name="entity.enName" value="${entity.enName }" tabindex="3" style="width:220px;margin-left:30px;"/>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">品牌中文名称：</td>
							<td>
								<input type="text" id="cnName" name="entity.cnName" value="${entity.cnName }" tabindex="3" style="width:210px;margin-left:30px;"/>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">品牌LOGO：</td>
							<td>
								<input onchange="viewUploadImg(this,'viewResUrlList')" type="file" id="resUrlList" name="logo" 
									tabindex="4" maxlength="300" style="margin:0px;width:220px;margin-left:30px;"/>
								<input type="hidden" name="entity.logo" id="logo" value="${entity.logo}" />
									<img src="${entity.logo}" style="width:50px;height:50px;" id="viewResUrlList"/>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">品牌介绍：</td>
							<td colspan="3" height="125px;">
								<textarea id="descrption" name="entity.descrption" style="height:105px;width:575px;margin:0px 30px;padding:10px;">${entity.descrption }</textarea>
							</td>
						</tr>
	            	</table>
	            	<table  class="margin-bottom-20 table  no-border" style="margin-top:20px;">
			             <tr>
			     	         <td align="right">
			     	         	<input type="button" value="保存" class="btn btn-info " style="width:80px;" onclick="updateSubmit()" />
			     	         </td>
			     	         <td align="center">
			     	         	<input type="button" value="返回" class="btn btn-info " style="width:80px;"  onclick="turnBack()"/>
			     	         </td>
			     	         <td></td>
			               </tr>
			          </table>
	         	</div>	         	
		</form>
		</div>
		<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/brand_list.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="cnName" value="${cnName}" />
			<input type="hidden" name="categoryId" value="${categoryId}" />
		</form>
	</div>
</body>
</html>
