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
		var title=$.trim($("#title").val());
   		if(!title){
   			layer.tips('请输入广告标题！', '#title');
   			return false;
   		}
   		var url=$.trim($("#url").val());
		if(!url){
			layer.tips('请输入广告链接！', '#url');
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
		<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/adver_update.action" method="post" id="updateForm">
			<div class="content-box">
				<div class="content-box-header">
					<span class="now_location">当前位置:</span>修改
				    <div class="clear"></div>
			    </div>
	            	<table class="table table-bordered" >
						<!-- 数据修改  -->
						<tr>
							<td width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">文章标题：</td>
							<td colspan="3">
								<input type="hidden" name="entity.id" value="${entity.id }"/>
								<input type="text" id="title" name="entity.title" value="${entity.title }" tabindex="3" style="width:600px;margin-left:30px;"/>
							</td>
						</tr>
						<tr>
							<td width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">广告链接：</td>
							<td colspan="3">
								<input type="text" id="url" name="entity.url" value="${entity.url }"tabindex="3" style="width:600px;margin-left:30px;"/>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">广告图片：</td>
							<td colspan="3">
								<input onchange="viewUploadImg(this,'viewResUrlList')" type="file" id="resUrlList" name="resUrlList" 
									tabindex="4" maxlength="300" style="width:400px;margin-left:30px;"/>
								<input type="hidden" name="entity.picturePath" id="picturePath" value="${entity.picturePath}" />
								<img src="${entity.picturePath}" style="width:50px;height:50px;" id="viewResUrlList"/>
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
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/adver_listData.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="title" value="${title}" />
			<input type="hidden" name="beginTime" value="${beginTime}" />
			<input type="hidden" name="endTime" value="${endTime}" />
		</form>
	</div>
</body>
</html>