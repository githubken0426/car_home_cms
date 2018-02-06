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
   	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/js/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/js/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="<%=path%>/js/cms/view_image/view_image.js"></script>
    
<script type="text/javascript">
//实例化编辑器
	var initUe={enterTag:'br'};
	 $(document).ready(function(){
		 var ue = UE.getEditor('editor',initUe);
	  });
	var titleIsOK = true;
	var contentIsOK = true;
	var picIsOK = true;
	$(function(){		
		// 标题名
		$("#title").bind({
			focus:function(){
				$("#titleMsg").html("");
			},blur:function(){
				var title = $.trim($("#title").val());
				if(title.length > 100){
					$("#titleMsg").html("<font color='red'>文章标题100字以内!</font>");
					titleIsOK = false;
					return false;
				}
				titleIsOK = true;
				return true;
			}
		});
		
		$("#editor").bind({
			focus:function(){
				$("#contentMsg").html("");
			},blur:function(){
				var editor = $.trim($("#editor").val());
				if(editor.length > 100){
					$("#contentMsg").html("<font color='red'>文章内容1000字以内!</font>");
					contentIsOK = false;
					return false;
				} else if (editor.length == 0) {
					$("#contentMsg").html("<font color='red'>请填写文章内容!</font>");
					contentIsOK = false;
					return false;
				}
				contentIsOK = true;
				return true;
			}
		});
		
		$("#pictureUrl").bind({
			focus:function(){
				$("#myFileMsg").html("");
			},blur:function(){
				var pictureUrl = $.trim($("#pictureUrl").val());
				if(pictureUrl.length == 0){
					$("#myFileMsg").html("<font color='red'>请选择轮播图片!</font>");
					picIsOK = false;
					return false;
				}
				picIsOK = true;
				return true;
			}
		});
  	});
  
   	// 更新
	function updateSubmit(){
		var ed = UE.getEditor('editor').getContent();
		if (ed.length != 0) {
			contentIsOK = true;
			if (titleIsOK && picIsOK && contentIsOK){
				$("#updateForm").submit();
			} else {
				alert("信息输入有误，无法保存！");
			}
		} else {
			alert("请填写广告内容！");
		}
	}
   
   	// 返回
  	function turnBack(){
  		$("#backForm").submit();
  	}
   	
 	// 删除
	function deleteSubmit(){
		
		$("#picFlag").attr("value","");
		$("#pictureUrl").attr("value","");
		
		var img = document.getElementById("viewPortrait");
		img.src = "";
		img.style.display = 'none';
		picIsOK = false;
	}
</script>
</head>
  
<body>
<div id="middle">
	<div class="right"  id="mainFrame">
		<form action="${pageContext.request.contextPath}/homeCarousel_updateData.action" method="post" id="updateForm" enctype="multipart/form-data">
			<div class="content-box">
				<div class="content-box-header">
					<span class="now_location">当前位置:</span>修改
				    <div class="clear"></div>
			    </div>
	            	<table class="table table-bordered" >
						<!-- 数据修改  -->
						<tr>
							<%-- <td width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">公司图片：</td>
							<td colspan="3">
							<div style="margin:10px 0px 10px 0px;">
							<div style="height:60px;width:450px;float:left;">
							<input type="hidden" name="picFlag" id="picFlag" value="${entity.pictureUrl }" />
							 <!-- <input type="file" id="shopPicUrl" name="myFile" style="width:400px;margin-left:30px;"/> -->
							 <input onchange="viewUploadImg(this,'viewPortrait')" type="file" id="pictureUrl" value="${entity.pictureUrl }" name="myFile" tabindex="1" 
								style="width: 350px; margin-left: 30px;" />
							<!-- <input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deleteSubmit()" /> -->
							<img src="${entity.pictureUrl }" 
							<c:if test="${empty entity.pictureUrl}">style="display:none;width:50px;height:50px;"</c:if> 
								style="width:50px;height:50px;" id="viewPortrait"/>
							 <span id="pictureUrllMsg" style="margin-left:15px;"></span>
							</div></div>
							</td> --%>
							
							<td width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">首页广告图：</td>
							<td width = "700px">
								<div style="margin:10px 0px 10px 0px;">
								<div style="height:60px;width:450px;float:left;">
								<input type="hidden" name="picFlag" id="picFlag" value="${entity.pictureUrl }" />
								 <!-- <input type="file" id="shopPicUrl" name="myFile" style="width:400px;margin-left:30px;"/> -->
								 <input onchange="viewUploadImg(this,'viewPortrait')" type="file" id="pictureUrl" value="${entity.pictureUrl }" name="myFile" tabindex="1"
									style="width: 350px; margin-left: 30px;" />
								<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deleteSubmit()" /> 
								<img src="${ftpServerIp}${entity.pictureUrl }" 
								<c:if test="${empty entity.pictureUrl}">style="display:none;width:50px;height:50px;"</c:if> 
									style="width:50px;height:50px;" id="viewPortrait"/>
								 <span id="myFileMsg" style="margin-left:15px;"></span>
								</div>
								</div>
							</td>
							
							
							<td  width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">状态：</td>
							<td>
								<c:choose>
										<c:when test="${entity.deleteFlag==0}">
											<label style="margin-left: 35px; display: inline;">
											<input type="radio" name="entity.deleteFlag" checked="checked" tabindex="6" value="0"
												style="margin-top: 0px;" />
											<span style="margin-left: 10px;">启&nbsp;用</span>
											</label>
											<label style="margin-left: 25px; display: inline;">
											<input type="radio" name="entity.deleteFlag" value="1"
												tabindex="7" style="margin-top: 0px;" />
											<span style="margin-left: 10px;">禁&nbsp;用</span>
											</label>
										</c:when>
										<c:otherwise>
											<label style="margin-left: 35px; display: inline;">
											<input type="radio" name="entity.deleteFlag" tabindex="6" value="0"
												style="margin-top: 0px;" />
											<span style="margin-left: 10px;">启&nbsp;用</span>
											</label>
											<label style="margin-left: 25px; display: inline;">
											<input type="radio" name="entity.deleteFlag" checked="checked" value="1"
												tabindex="7" style="margin-top: 0px;" />
											<span style="margin-left: 10px;">禁&nbsp;用</span>
											</label>
										</c:otherwise>
									</c:choose>
							</td>
						</tr>
						<tr>
							<td width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">广告标题：</td>
							<td colspan="3">
								<input type="hidden" name="entity.id" value="${entity.id }"/>
								<input type="text" id="title" name="entity.title" value="${entity.title }" tabindex="3" style="width:600px;margin-left:30px;"/>
								<span id="titleMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">广告内容：</td>
							<td colspan="3" style="white-space:pre-wrap">
								<textarea id="editor" name="entity.content" style="width:1024px;height:600px;margin-left:30px;">
									${entity.content }
								</textarea>
								<br></br>
								<span id="contentMsg" style="margin-left:15px;"></span>
								<%--<script name="entity.content" value="${entity.content }" id="editor" type="text/plain" style="width:1024px;height:600px;margin-left:30px;"></script>
							--%></td>
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
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/homeCarousel_listData.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="expertName" value="${expertName}" />
			<input type="hidden" name="title" value="${title}" />
			<input type="hidden" name="beginTime" value="${beginTime}" />
			<input type="hidden" name="endTime" value="${endTime}" />
		</form>
	</div>
</body>
</html>
