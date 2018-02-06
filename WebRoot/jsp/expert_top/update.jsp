<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/jsp/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>新增用户</title>
	<link rel="stylesheet" href="<%=path %>/css/pubmain.css" />
	<link href="<%=request.getContextPath() %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
   	<script type="text/javascript" src="<%=path%>/js/cms/view_image/view_image.js"></script>
   	
  <script type="text/javascript">
   //添加提交
   function addSubmit(){
	   if(isViewUpdateOK)
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
			<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/expertTop_updateData.action" method="post" id="addForm">
			    <div class="content-box">
<!-- 用户录入区  -->
			     	<div class="content-box-header">
				    	<span class="now_location">用户信息:</span> 添加用户
				        <div class="clear"></div>
				    </div>
				    <table class="table table-bordered" id="apiUserTable" >
						<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">登陆账号：</td>
							<td width="30%">
								<input type="text" placeholder="手机号"  id="loginPhone" name="apiUser.loginPhone" value="${apiUser.loginPhone }" readonly="readonly" tabindex="1" style="width:200px;margin-left:30px;"/>
								<span id="loginPhoneMsg" style="margin-left:15px;"></span>
							</td>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">真实姓名：</td>
							<td width="35%">
								<input type="text" placeholder="真实姓名" id="realName" name="apiUser.realName" value="${apiUser.realName}" readonly="readonly"tabindex="2" style="width:200px;margin-left:30px;"/>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">登陆密码：</td>
							<td>
								<input type="password" id="password" name="apiUser.password" value="${apiUser.password }" readonly="readonly"tabindex="3" style="width:200px;margin-left:30px;"/>
								<span id="passwordMsg" style="margin-left:15px;"></span>
							</td>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户昵称：</td>
							<td width="35%">
								<input type="text" placeholder="昵称" id="nickname" name="apiUser.nickname" value="${apiUser.nickname }" readonly="readonly"tabindex="5" style="width:200px;margin-left:30px;"/>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">确认密码：</td>
							<td>
								<input type="password" id="againPassword" value="${apiUser.password }"readonly="readonly" tabindex="4" style="width:200px;margin-left:30px;"/>
								<span id="againPasswordMsg" style="margin-left:15px;"></span>
							</td>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">性别：</td>
							<td>
			                	<c:choose>
										<c:when test="${apiUser.sex==0}">
											<label style="margin-left: 35px; display: inline;">
						                		<input id="men" value="0" type="radio" name="apiUser.sex" checked="checked" tabindex="6" style="margin-top:0px;"/>
						                		<span style="margin-left: 10px;">男</span>
						                	</label>
						                	<label style="margin-left: 48px;display: inline;">
						                		<input id="women" value="1" type="radio" name="apiUser.sex" tabindex="7" style="margin-top:0px;"/>
						                		<span style="margin-left: 10px;">女</span>
						                	</label>
										</c:when>
										<c:otherwise>
											<label style="margin-left: 35px; display: inline;">
						                		<input id="men" value="0" type="radio" name="apiUser.sex" tabindex="6" style="margin-top:0px;"/>
						                		<span style="margin-left: 10px;">男</span>
						                	</label>
						                	<label style="margin-left: 48px;display: inline;">
						                		<input id="women" value="1" type="radio" name="apiUser.sex" checked="checked" tabindex="7" style="margin-top:0px;"/>
						                		<span style="margin-left: 10px;">女</span>
						                	</label>
										</c:otherwise>
									</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户头像：</td>
							<td>
								<input onchange="viewUploadImg(this,'userViewPortrait')" type="file" name="userPortrait" readonly="readonly" tabindex="8"  style="width:260px;margin-left:30px;"/>
								<img 
									<c:if test="${not empty apiUser.avatarUrl}"> 
									 src="${ftpServerIp}${apiUser.avatarUrl}" style="width:50px;height:50px;" 
								 	</c:if>
								 id="userViewPortrait"/>
							</td>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">状态：</td>
							<td>
			                	<c:choose>
										<c:when test="${apiUser.deleteFlag==0}">
											<label style="margin-left: 35px; display: inline;">
						                		<input id="used" type="radio" name="apiUser.deleteFlag" checked="checked" tabindex="9"  value="0" style="margin-top:0px;"/>
						                		<span style="margin-left: 10px;">启&nbsp;用</span>
						                	</label>
						                	<label style="margin-left: 25px;display: inline;">
						                		<input id="unUsed" type="radio" name="apiUser.deleteFlag" value="1" tabindex="10" style="margin-top:0px;"/>
						                		<span style="margin-left: 10px;">禁&nbsp;用</span>
						                	</label>
										</c:when>
										<c:otherwise>
											<label style="margin-left: 35px; display: inline;">
						                		<input id="used" type="radio" name="apiUser.deleteFlag" tabindex="9"  value="0" style="margin-top:0px;"/>
						                		<span style="margin-left: 10px;">启&nbsp;用</span>
						                	</label>
						                	<label style="margin-left: 25px;display: inline;">
						                		<input id="unUsed" type="radio" name="apiUser.deleteFlag" value="1"checked="checked" tabindex="10" style="margin-top:0px;"/>
						                		<span style="margin-left: 10px;">禁&nbsp;用</span>
						                	</label>
										</c:otherwise>
									</c:choose>
							</td>
						</tr>
	            	</table>
<!-- 达人录入区  -->
				    <div class="content-box-header">
				    	<span class="now_location">达人信息:</span> 添加达人
				        <div class="clear"></div>
				    </div>
			   		<div style="margin:0 auto; margin:10px;">
			           	<table class="table table-bordered" >
						<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人微信号：</td>
							<td width="30%">
								<input type="hidden"value="${expertTop.id }" name="expertTop.id"/>
								<input type="text"value="${expertTop.expertWechatNumber }" id="expertWechatNumber" name="expertTop.expertWechatNumber" tabindex="13" style="width:200px;margin-left:30px;"/>
								<span id="topTitleMsg" style="margin-left:15px;"></span>
							</td>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人经验(年)：</td>
							<td width="35%">
								<input type="text" value="${expertTop.expertExperience }"id="expertExperience" name="expertTop.expertExperience" tabindex="14" style="width:200px;margin-left:30px;"
								onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" placeholder="请输入整数数字"/>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人类别：</td>
							<td>
								<select id="topTitle" name="expertTop.topTitle" tabindex="15" style="width:200px;margin-left:30px;">
									<c:forEach var="type" items="${typeList}">
										<option value="${type.id }"<c:if test="${type.id== expertTop.topTitle}">selected="selected"</c:if>>${type.typeValue}</option>
									</c:forEach>
								</select>
							</td>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">状态：</td>
							<td>
			                	<c:choose>
										<c:when test="${expertTop.deleteFlag==0}">
											<label style="margin-left: 35px; display: inline;">
						                		<input type="radio" value="0" name="expertTop.deleteFlag" checked="checked" tabindex="16" style="margin-top:0px;"/>
						                		<span style="margin-left: 10px;">启&nbsp;用</span>
						                	</label>
						                	<label style="margin-left: 25px;display: inline;">
						                		<input type="radio"value="1" name="expertTop.deleteFlag" tabindex="17" style="margin-top:0px;"/>
						                		<span style="margin-left: 10px;">禁&nbsp;用</span>
						                	</label>
										</c:when>
										<c:otherwise>
											<label style="margin-left: 35px; display: inline;">
						                		<input type="radio" value="0" name="expertTop.deleteFlag"  tabindex="16" style="margin-top:0px;"/>
						                		<span style="margin-left: 10px;">启&nbsp;用</span>
						                	</label>
						                	<label style="margin-left: 25px;display: inline;">
						                		<input type="radio"value="1" name="expertTop.deleteFlag" checked="checked" tabindex="17" style="margin-top:0px;"/>
						                		<span style="margin-left: 10px;">禁&nbsp;用</span>
						                	</label>
										</c:otherwise>
									</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人头像：</td>
							<td colspan="3">
								<input onchange="viewUploadImg(this,'expertViewPortrait')" type="file" name="expertPortrait" tabindex="18"  style="width:260px;margin-left:30px;"/>
								<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('expertPortrait','expertPortraitFlag')" />
								<input type="hidden" name="expertPortraitFlag" id="expertPortraitFlag" 
														value="${fn:substringAfter(expertTop.expertPortraitUrl,ftpServerIp) }" />
								<c:if test="${not empty expertTop.expertPortraitUrl}">
									<img src="${expertTop.expertPortraitUrl}"style="width:50px;height:50px;" id="expertViewPortrait"/>
								</c:if>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人简介：</td>
							<td colspan="3">
								<textarea placeholder="简介在300字以内有效" name="expertTop.expertDiscriptionShort" tabindex="19" 
									style="width:75%;margin:5px 0px 5px 30px;">${expertTop.expertDiscriptionShort }
								</textarea>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人座右铭：</td>
							<td colspan="3">
								<textarea  placeholder="座右铭在30字以内有效" name="expertTop.motto" tabindex="20" 
									style="width:75%;margin:5px 0px 5px 30px;">${expertTop.motto }
								</textarea>
								<span id="mottoMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人详细描述：</td>
							<td colspan="3">
								<textarea placeholder="详细描述在500字以内有效" name="expertTop.expertDiscriptionDetail" tabindex="21" 
									style="width:75%;height:60px;margin:5px 0px 5px 30px;">${expertTop.expertDiscriptionDetail }
								</textarea>
								<span id="detailMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">个人照片展示：</td>
							<td colspan="3">
								<div style="margin:5px 0px 10px 0px;">
									<div style="height:50px;width:370px;float:left;">
												<input onchange="updatePageViewImg(this,'viewDisplay1','viewDisplay1Flag')" type="file" name="displayPicture" tabindex="12"  style="width:180px;margin-left:30px;"/>
												<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('viewDisplay1','viewDisplay1Flag')" />
												<input type="hidden" name="viewDisplay1Flag" id="viewDisplay1Flag" 
													value="${fn:substringAfter(expertTop.displayList[0],ftpServerIp) }" />
												<c:if test="${not empty expertTop.displayList[0]}">
													<img src="${expertTop.displayList[0] }"style="width:50px;height:50px;" id="viewDisplay1"/>
												</c:if> 
											</div>
											<div style="height:50px;width:370px;float:left;">
												<input onchange="updatePageViewImg(this,'viewDisplay2','viewDisplay2Flag')" type="file" name="displayPicture" tabindex="13"  style="width:180px;margin-left:10px;"/>
												<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('viewDisplay2','viewDisplay2Flag')" />
												<input type="hidden" name="viewDisplay2Flag" id="viewDisplay2Flag" 
														value="${fn:substringAfter(expertTop.displayList[1],ftpServerIp) }" />
												<c:if test="${not empty expertTop.displayList[1]}">
													<img src="${expertTop.displayList[1] }" style="width:50px;height:50px;" id="viewDisplay2"/>
												</c:if>
											</div>
											<div style="height:50px;width:370px;float:none;">
												<input onchange="updatePageViewImg(this,'viewDisplay3','viewDisplay3Flag')" type="file" name="displayPicture" tabindex="14"  style="width:180px;margin-left:10px;"/>
												<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('viewDisplay3','viewDisplay3Flag')" />
												<input type="hidden" name="viewDisplay3Flag" id="viewDisplay3Flag" 
														value="${fn:substringAfter(expertTop.displayList[2],ftpServerIp) }" />
												<c:if test="${not empty expertTop.displayList[2]}">
													<img src="${expertTop.displayList[2] }" style="width:50px;height:50px;" id="viewDisplay3"/>
												</c:if>
											</div>
										</div>
										<div>
											<div style="height:50px;width:370px;float:left;">
												<input onchange="updatePageViewImg(this,'viewDisplay4','viewDisplay4Flag')" type="file" name="displayPicture" tabindex="15"  style="width:180px;margin-left:30px;"/>
												<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('viewDisplay4','viewDisplay4Flag')" />
												<input type="hidden" name="viewDisplay4Flag" id="viewDisplay4Flag" 
														value="${fn:substringAfter(expertTop.displayList[3],ftpServerIp) }" />
												<c:if test="${not empty expertTop.displayList[3]}">
													<img src="${expertTop.displayList[3] }" style="width:50px;height:50px;" id="viewDisplay4"/>
												</c:if>
											</div>
											<div style="height:50px;width:370px;float:left;">
												<input onchange="updatePageViewImg(this,'viewDisplay5','viewDisplay5Flag')" type="file" name="displayPicture" tabindex="16"  style="width:180px;margin-left:10px;"/>
												<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('viewDisplay5','viewDisplay5Flag')" />
												<input type="hidden" name="viewDisplay5Flag" id="viewDisplay5Flag" 
														value="${fn:substringAfter(expertTop.displayList[4],ftpServerIp) }" />
												<c:if test="${not empty expertTop.displayList[4]}">
													<img src="${expertTop.displayList[4] }"style="width:50px;height:50px;" id="viewDisplay5"/>
												</c:if>
											</div>
											<div style="height:50px;width:370px;float:none;">
												<input onchange="updatePageViewImg(this,'viewDisplay6','viewDisplay6Flag')" type="file" name="displayPicture" tabindex="17"  style="width:180px;margin-left:10px;"/>
												<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deletePicture('viewDisplay6','viewDisplay6Flag')" />
												<input type="hidden" name="viewDisplay6Flag" id="viewDisplay6Flag" 
														value="${fn:substringAfter(expertTop.displayList[5],ftpServerIp) }" />
												<c:if test="${not empty expertTop.displayList[5]}">
													<img src="${expertTop.displayList[5] }" style="width:50px;height:50px;" id="viewDisplay6"/>
												</c:if>
										</div>
								</div>
							</td>
						</tr>
	            	</table>
			     </div> 
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
			</form>
			<!-- 返回，记录列表页数据 -->
			<form id="backForm" method="post" action="${pageContext.request.contextPath}/expertTop_listData.action">
				<input type="hidden" name="pno" value="${currentIndex}" />
				<input type="hidden" name="deleteFlag" value="${deleteFlag}" />
				<input type="hidden" name="topType" value="${topType}" />
				<input type="hidden" name="expertName" value="${expertName}" />
			</form>
	  </div>
    </div>
  </body>
</html>
