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
  	});
  
   	// 更新
	function updateSubmit(){
		if (titleIsOK && isViewUpdateOK){
			$("#updateForm").submit();
		} else {
			alert("信息输入有误，无法保存！");
		}		
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
		<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/article_updateData.action" method="post" id="updateForm">
			<div class="content-box">
				<div class="content-box-header">
					<span class="now_location">当前位置:</span>修改
				    <div class="clear"></div>
			    </div>
	            	<table class="table table-bordered" >
						<!-- 数据修改  -->
						<tr>
							<td width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">文章作者：</td>
							<td>
								<input type="hidden" name="entity.id" value="${entity.id }"/>
								<select id="topTitle" name="entity.userId" tabindex="2" style="width:200px;margin-left:30px;">
									<c:forEach var="type" items="${expertList}">
										<option value="${type.userId }"<c:if test="${type.userId==entity.userId }">selected="selected"</c:if>>
											${type.expertName}
										</option>
									</c:forEach>
								</select>
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
							<td width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">文章标题：</td>
							<td colspan="3">
								<input type="text" id="title" name="entity.title" value="${entity.title }" tabindex="3" style="width:600px;margin-left:30px;"/>
								<span id="titleMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">资讯简介：</td>
							<td colspan="3">
								<textarea placeholder="简介在300字以内" id="introduction" name="entity.introduction" tabindex="4" maxlength="300"
									style="width:600px;height:100px;margin-left:30px;">${entity.introduction }</textarea>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">展示图片：</td>
							<td colspan="3">
								<input onchange="viewUploadImg(this,'viewResUrlList')" type="file" id="resUrlList" name="resUrlList" tabindex="4" maxlength="300"
									style="width:400px;margin-left:30px;"></input>
								<input type="hidden" name="resUrlListFlag" id="resUrlListFlag" 
									value="${fn:substringAfter(entity.resUrlList,ftpServerIp) }" />
								<img 
									<c:if test="${not empty entity.resUrlList}"> 
										src="${entity.resUrlList}"  
									</c:if>style="width:50px;height:50px;" id="viewResUrlList"/>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">文章内容：</td>
							<td colspan="3" style="white-space:pre-wrap">
								<textarea id="editor" name="entity.content" style="width:1024px;height:600px;margin-left:30px;">
									${entity.content }
								</textarea>
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
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/article_listData.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="expertName" value="${expertName}" />
			<input type="hidden" name="title" value="${title}" />
			<input type="hidden" name="beginTime" value="${beginTime}" />
			<input type="hidden" name="endTime" value="${endTime}" />
		</form>
	</div>
</body>
</html>
