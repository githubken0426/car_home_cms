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
   	
<script type="text/javascript">
	var versionCodeIsOK = true;
	var versionNameIsOK = true;
	var contentIsOK = true;
	$(function(){		
		// 最新版本号
		$("#versionCode").bind({
			focus:function(){
				$("#versionCodeMsg").html("");
			},blur:function(){
				var reg=/^[0-9]*$/;
				var versionCode = $.trim($("#versionCode").val());
				if(!versionCode){
					versionCodeIsOK = false;
					$("#versionCodeMsg").html("<font color='red'>请输入版本号！</font>");
					return ;
				}
				if(!reg.test(versionCode)){
					versionCodeIsOK = false;
					$("#versionCodeMsg").html("<font color='red'>请输入数字！</font>");
					return ;
				}
				versionCodeIsOK = true;
			}
		});

		// 最新版本名
		$("#versionName").bind({
			focus:function(){
				$("#versionNameMsg").html("");
			},blur:function(){
				var versionName = $.trim($("#versionName").val());
				if(!versionName){
					$("#versionNameMsg").html("<font color='red'>请输入最新版本名!</font>");
					versionNameIsOK = false;
					return;
				}
				if(versionName.length > 50){
					$("#versionNameMsg").html("<font color='red'>最新版本名超长(50位)!</font>");
					versionNameIsOK = false;
					return;
				}
			}
		});

		// 内容
		$("#content").bind({
			focus:function(){
				$("#contentMsg").html("");
			},blur:function(){
				var content = $.trim($("#content").val());
				if(content.length > 500){
					$("#contentMsg").html("<font color='red'>内容超长(500位)!</font>");
					contentIsOK = false;
					return false;
				}
			}
		});
  	});
  
   	// 更新
	function updateSubmit(){
		if (versionCodeIsOK && versionNameIsOK && contentIsOK){
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
		<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/version!updateData.action" method="post" id="updateForm">
			<div class="content-box">
				<div class="content-box-header">
					<span class="now_location">当前位置:</span>修改
				    <div class="clear"></div>
			    </div>
			    <div style="margin:0 auto; margin:10px;">
	            	<table class="table table-bordered" >
						<!-- 数据修改  -->
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">最新版本号：</td>
							<td width="40%">
								<input type="hidden" id="id" name="entity.id" value="${entity.id }" />
								<input type="text" id="versionCode" name="entity.versionCode"  value="${entity.versionCode }"tabindex="1" style="width:150px;margin-left:30px;"/>
								<span id="versionCodeMsg" style="margin-left:15px;"></span>
							</td>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">最新版本名：</td>
							<td width="40%">
								<input type="text" id="versionName" name="entity.versionName"  value="${entity.versionName }" tabindex="2" style="width:150px;margin-left:30px;"/>
								<span id="versionNameMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">内容：</td>
							<td colspan="3">
								<textarea placeholder="内容在500字以内有效" id="content" name="entity.content" 
								tabindex="3" style="width:70%;height:80px;margin:5px 0px 5px 30px;">${entity.content }</textarea>
							<span id="contentMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">更新地址：</td>
							<td colspan="3">
								<input type="file" name="versionUrl" tabindex="4" style="width:400px;margin-left:30px;"/>
								[${fn:substringAfter(entity.url,"version/") }]
								<span id="urlMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">最低可用版本号：</td>
							<td width="40%">
								<input type="text" id="minCode" name="entity.minCode"  value="${entity.minCode }" tabindex="5" style="width:150px;margin-left:30px;"/>
								<span id="minCodeMsg" style="margin-left:15px;"></span>
							</td>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">最低可用版本：</td>
							<td width="40%">
							<input type="text" id="minVersion" name="entity.minVersion" value="${entity.minVersion }"tabindex="6" style="width:150px;margin-left:30px;"/>
							<span id="minVersionMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">最低版本更新消息：</td>
							<td colspan="3">
								<textarea placeholder="内容在500字以内有效" id="minContent" name="entity.minContent" tabindex="7" 
								style="width:70%;height:80px;margin:5px 0px 5px 30px;">${entity.minContent }</textarea>
								<span id="minContentMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<c:choose>
						<c:when test="${entity.systemBj == 0}">
							<tr>
								<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">是否审核中：</td>
								<td width="40%">
								<select id="state" name="entity.state" tabindex="8" style="height:25px;margin-left:30px;">
						   	 		<c:choose>
									   <c:when test="${entity.state == true}">
									   		<option value=""></option>  
									   		<option value="true" selected="selected">审核通过</option>
									   		<option value="false">审核中</option>
									   </c:when>
									   <c:when test="${entity.state == false}">
									   		<option value=""></option>  
									   		<option value="true">审核通过</option>
									   		<option value="false" selected="selected">审核中</option>
									   </c:when>
									   <c:otherwise>
									   		<option value="" selected="selected"></option>  
									   		<option value="true">审核通过</option>
									   		<option value="false">审核中</option>
									   </c:otherwise>
								   </c:choose>
						   		</select>
								<span id="stateMsg" style="margin-left:15px;"></span>
								</td>
								<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">手机类型：</td>
								<td width="40%">
								<select id="systemBj" disabled="disabled" name="entity.systemBj" tabindex="9" style="height:25px;margin-left:30px;">
						   	 		<c:choose>
									   <c:when test="${entity.systemBj == 0}">
									   		<option value="0" selected="selected">ios</option>
									   		<option value="1">android</option>
									   </c:when>
									   <c:otherwise>  
									   		<option value="0">ios</option>
									   		<option value="1" selected="selected">android</option>
									   </c:otherwise>
								   </c:choose>
						   		</select>
								<span id="systemBjMsg" style="margin-left:15px;"></span>
								</td>
							</tr>
						</c:when>
						<c:otherwise>
						<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">手机类型：</td>
			            <td colspan="3">
			            	<select id="systemBj" disabled="disabled" name="entity.systemBj" tabindex="9" style="height:25px;margin-left:30px;">
					   	 		<c:choose>
								   <c:when test="${systemBj == 0}">
								   		<option value="0" selected="selected">ios</option>
								   		<option value="1">android</option>
								   </c:when>
								   <c:otherwise>  
								   		<option value="0">ios</option>
								   		<option value="1" selected="selected">android</option>
								   </c:otherwise>
							   </c:choose>
					   		</select>
							<span id="systemBjMsg" style="margin-left:15px;"></span>
			            </td>
						</c:otherwise>
						</c:choose>
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
			</div>
		</form>
	</div>
	<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/version!listData.action">
		</form>
</div>
</body>
</html>
