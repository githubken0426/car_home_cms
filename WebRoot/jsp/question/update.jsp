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
<script type="text/javascript">
	var contentIsOK=true; 
	$(function(){		
		$("#content").bind({
			focus:function(){
				$("#contentMsg").html("");
			},blur:function(){
				var content = $.trim($("#content").val());
				if(content)
					contentIsOK = true;
				else{
					contentIsOK=false;
					$("#contentMsg").html("<font color='red'>不能为空!</font>");
				}
			}
		});

  	});
  
   	// 更新
	function updateSubmit(){
		if (contentIsOK ){
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
		<form action="${pageContext.request.contextPath}/question_updateData.action" enctype="multipart/form-data" method="post" id="updateForm">
			<div class="content-box">
				<div class="content-box-header">
					<span class="now_location">当前位置:</span>修改
				    <div class="clear"></div>
			    </div>
			    <div style="margin:0 auto; margin:10px;">
	            	<table class="table table-bordered" >
						<!-- 数据修改  -->
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户账号：</td>
							<td>
								<input type="hidden" id="id" name="writeEntity.id" value="${entity.id }" />
								<span style="margin-left:30px;">${entity.loginPhone }</span>
							</td>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户昵称：</td>
							<td>
								<span style="margin-left:30px;">${entity.nickName }</span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">提问时间：</td>
							<td>
								<span style="margin-left:30px;">
									<fmt:formatDate value="${entity.insertTime}" type="both" pattern="yyyy-MM-dd" dateStyle="long"/>
								</span>
							</td>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">回复数量：</td>
							<td>
								<span style="margin-left:30px;">${entity.replyNum }</span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">问题标题：</td>
							<td colspan="3">
								<textarea id="content" placeholder="请输入你的问题" name="writeEntity.content" tabindex="9"
											style="width: 75%; margin: 5px 0px 5px 30px;">${entity.content }</textarea>
								<span id="contentMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">上传附件：</td>
							<td colspan="3">
								<div style="margin:5px 0px 10px 0px;">
											<div style="height:50px;width:370px;float:left;">
												<input onchange="updatePageViewImg(this,'viewDisplay1','viewDisplay1Flag')" type="file" name="displayPicture" tabindex="12"  style="width:180px;margin-left:30px;"/>
												<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('viewDisplay1','viewDisplay1Flag')" />
												<input type="hidden" name="viewDisplay1Flag" id="viewDisplay1Flag" 
													value="${fn:substringAfter(entity.displayList[0],ftpServerIp) }" />
												<c:if test="${not empty entity.displayList[0]}">
													<img src="${entity.displayList[0] }" style="width:50px;height:50px;" id="viewDisplay1"/>
												</c:if>
											</div>
											<div style="height:50px;width:370px;float:left;">
												<input onchange="updatePageViewImg(this,'viewDisplay2','viewDisplay2Flag')" type="file" name="displayPicture" tabindex="13"  style="width:180px;margin-left:10px;"/>
												<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('viewDisplay2','viewDisplay2Flag')" />
												<input type="hidden" name="viewDisplay2Flag" id="viewDisplay2Flag" 
														value="${fn:substringAfter(entity.displayList[1],ftpServerIp) }" />
												<c:if test="${not empty entity.displayList[1]}">
													<img src="${entity.displayList[1] }" style="width:50px;height:50px;" id="viewDisplay2"/>
												</c:if>
											</div>
											<div style="height:50px;width:370px;float:none;">
												<input onchange="updatePageViewImg(this,'viewDisplay3','viewDisplay3Flag')" type="file" name="displayPicture" tabindex="14"  style="width:180px;margin-left:10px;"/>
												<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('viewDisplay3','viewDisplay3Flag')" />
												<input type="hidden" name="viewDisplay3Flag" id="viewDisplay3Flag" 
														value="${fn:substringAfter(entity.displayList[2],ftpServerIp) }" />
												<c:if test="${not empty entity.displayList[2]}">
													<img src="${entity.displayList[2] }" style="width:50px;height:50px;" id="viewDisplay3"/>
												</c:if>
											</div>
										</div>
										<div>
											<div style="height:50px;width:370px;float:left;">
												<input onchange="updatePageViewImg(this,'viewDisplay4','viewDisplay4Flag')" type="file" name="displayPicture" tabindex="15"  style="width:180px;margin-left:30px;"/>
												<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('viewDisplay4','viewDisplay4Flag')" />
												<input type="hidden" name="viewDisplay4Flag" id="viewDisplay4Flag" 
														value="${fn:substringAfter(entity.displayList[3],ftpServerIp) }" />
												<c:if test="${not empty entity.displayList[3]}">
													<img src="${entity.displayList[3] }" style="width:50px;height:50px;" id="viewDisplay4"/>
												</c:if>
											</div>
											<div style="height:50px;width:370px;float:left;">
												<input onchange="updatePageViewImg(this,'viewDisplay5','viewDisplay5Flag')" type="file" name="displayPicture" tabindex="16"  style="width:180px;margin-left:10px;"/>
												<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('viewDisplay5','viewDisplay5Flag')" />
												<input type="hidden" name="viewDisplay5Flag" id="viewDisplay5Flag" 
														value="${fn:substringAfter(entity.displayList[4],ftpServerIp) }" />
												<c:if test="${not empty entity.displayList[4]}">
													<img src="${entity.displayList[4] }" style="width:50px;height:50px;" id="viewDisplay5"/>
												</c:if>
											</div>
											<div style="height:50px;width:370px;float:none;">
												<input onchange="updatePageViewImg(this,'viewDisplay6','viewDisplay6Flag')" type="file" name="displayPicture" tabindex="17"  style="width:180px;margin-left:10px;"/>
												<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('viewDisplay6','viewDisplay6Flag')" />
												<input type="hidden" name="viewDisplay6Flag" id="viewDisplay6Flag" 
														value="${fn:substringAfter(entity.displayList[5],ftpServerIp) }" />
												<c:if test="${not empty entity.displayList[5]}">
													<img src="${entity.displayList[5] }" style="width:50px;height:50px;" id="viewDisplay6"/>
												</c:if>
											</div>
										</div>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">状态：</td>
							<td colspan="3">
							<c:choose>
										<c:when test="${entity.deleteFlag==0}">
											<label style="margin-left: 35px; display: inline;">
											<input type="radio" name="writeEntity.deleteFlag" checked="checked" tabindex="6" value="0"
												style="margin-top: 0px;" />
											<span style="margin-left: 10px;">启&nbsp;用</span>
											</label>
											<label style="margin-left: 25px; display: inline;">
											<input type="radio" name="writeEntity.deleteFlag" value="1"
												tabindex="7" style="margin-top: 0px;" />
											<span style="margin-left: 10px;">禁&nbsp;用</span>
											</label>
										</c:when>
										<c:otherwise>
											<label style="margin-left: 35px; display: inline;">
											<input type="radio" name="writeEntity.deleteFlag" tabindex="6" value="0"
												style="margin-top: 0px;" />
											<span style="margin-left: 10px;">启&nbsp;用</span>
											</label>
											<label style="margin-left: 25px; display: inline;">
											<input type="radio" name="writeEntity.deleteFlag" checked="checked" value="1"
												tabindex="7" style="margin-top: 0px;" />
											<span style="margin-left: 10px;">禁&nbsp;用</span>
											</label>
										</c:otherwise>
									</c:choose>
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
			</div>
		</form>

		<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/question_and_articleAction!listData.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="content" value="${content}" />
			<input type="hidden" name="beginTime" value="${beginTime}" />
			<input type="hidden" name="endTime" value="${endTime}" />
			<input type="hidden" name="deleteFlag" value="${deleteFlag}" />
		</form>
	</div>
</div>
</body>
</html>
