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
  var reg = /^1[3|4|5|7|8][0-9]{9}$/;
   var telephoneIsOk = false;
   var isPwd=false;
   var isEquals=false;
  $(function(){
  	//判断用户是否存在
	  $("#loginPhone").bind({
	  	focus:function(){
	  		$("#loginPhoneMsg").html("");
	  	},blur:function(){
	  		var loginPhone = $.trim($("#loginPhone").val());
			if(loginPhone){
				if(!reg.test(loginPhone)){
					$("#loginPhoneMsg").html("<font color='red'>请输入合法的账号!</font>");
					telephoneIsOk = false;
					return false;
				}
				$.ajax({
			        type:"POST",  
			        dataType:"json",  
			        data:{loginPhone:loginPhone},
			        async:true,
			        url: "${pageContext.request.contextPath}/expertTop_getUserByLoginPhone.action",  
			        success: function (data,xhr,textStatus) {
				        var isExpert=data.isExpert;
				        if(isExpert==0){//账号不存在
				        	$("#loginPhoneMsg").html("<font color='green'>* OK!</font>");
				        	telephoneIsOk = true;
				        	$("#apiUserTable input").removeAttr("readonly");
				        	changeData(data);
		        			return ;
					    }
				        if(isExpert==1){
				        	$("#loginPhoneMsg").html("<font color='red'>* 账号已被达人绑定!</font>");
		        			telephoneIsOk = false;
		        			return ;
					    }
					    if(isExpert==2){//账号存在
					    	changeData(data);
					    	$("#apiUserTable input").attr("readonly","readonly");
					    	$("#loginPhone").removeAttr("readonly");
					    	$("#loginPhoneMsg").html("<font color='green'>* OK!</font>");
				        	telephoneIsOk = true;
				        	isPwd=true;
				        	isEquals=true;
						}
			        },error:function(data,xhr,textStatus){
			        	console.log(textStatus);
			        	telephoneIsOk = false;
			        	$("#expertTelNumberMsg").html("<font color='red'>* 绑定失败!</font>");
				    } 
			    });
			}else{
				$("#loginPhoneMsg").html("<font color='red'>* 不能为空!</font>");
				telephoneIsOk = false;
			}
			return telephoneIsOk;
	  	}
  });
  
  	/*验证密码*/
  	$("#password").bind({
  		focus:function(){
  			$("#passwordMsg").html("");
  		},blur:function(){
  			var pwd=$.trim($("#password").val());
		   	var pattern = /^([a-zA-Z0-9!_]){6,20}$/;
		   	if(pwd.length>0){
		   		if(pwd.length<6){
		   			 $("#passwordMsg").html("<font color='red'>* 密码长度6-20位！</font>");
		   			  isPwd= false;
		   		}else{
		   			$("#passwordMsg").html("<font color='green'>* OK！</font>");
		   	  		isPwd= true;
		   		}
		   	 }else{
		   	  	$("#passwordMsg").html("<font color='red'>* 密码不能为空!</font>");
		   	  	isPwd= false;
		   	 }
  		}
  	});
  	
	$("#againPassword").bind({
	   focus:function(){
			$("#againPasswordMsg").html("");
	    },blur:function(){
	    	var pwd=$.trim($("#password").val());
	  		var againPwd=$.trim($("#againPassword").val());
			if(pwd==againPwd){
				$("#againPasswordMsg").html("<font color='green'>* OK！</font>");
				isEquals=true;
			}else{
				$("#againPasswordMsg").html("<font color='red'>* 两次密码输入不一致！</font>");
				isEquals=false;
			}
		}
	});
  });

  function changeData(data){
	var sex=data.sex;
  	var deleteFlag=data.deleteFlag;
  	$("#realName").val(data.realName);
  	$("#nickname").val(data.nickname);
  	if(sex==1)
  		$("#women").attr("checked",true);
  	else
  		$("#men").attr("checked",true);
  	if(deleteFlag==1)
  		$("#unUsed").attr("checked",true);
  	else
  		$("#used").attr("checked",true);
  	$("#password").val(data.password);
  	$("#againPassword").val(data.password);
  	$("#userViewPortrait").attr("src",data.portraitUrl);
  	$("#apiUserId").val(data.userId);
   }
   //添加提交
   function addSubmit(){
   	  if(telephoneIsOk && isPwd && isEquals && isViewOK){
   		$("#addForm").submit();
   	  }else{
   	  	alert("信息输入有误，无法保存！");
   	  }
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
			<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/expert_addData.action" method="post" id="addForm">
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
								<input type="hidden" id="apiUserId" name="apiUser.userId"/>
								<input type="text" placeholder="手机号"  id="loginPhone" name="apiUser.loginPhone" tabindex="1" style="width:200px;margin-left:30px;"/>
								<span id="loginPhoneMsg" style="margin-left:15px;"></span>
							</td>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">真实姓名：</td>
							<td width="35%">
								<input type="text" placeholder="真实姓名" id="realName" name="apiUser.realName" tabindex="2" style="width:200px;margin-left:30px;"/>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">登陆密码：</td>
							<td>
								<input type="password" id="password" name="apiUser.password" tabindex="3" style="width:200px;margin-left:30px;"/>
								<span id="passwordMsg" style="margin-left:15px;"></span>
							</td>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户昵称：</td>
							<td width="35%">
								<input type="text" placeholder="昵称" id="nickname" name="apiUser.nickname" tabindex="5" style="width:200px;margin-left:30px;"/>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">确认密码：</td>
							<td>
								<input type="password" id="againPassword" tabindex="4" style="width:200px;margin-left:30px;"/>
								<span id="againPasswordMsg" style="margin-left:15px;"></span>
							</td>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">性别：</td>
							<td>
								<label style="margin-left: 35px; display: inline;">
			                		<input id="men" value="0" type="radio" name="apiUser.sex" checked="checked" tabindex="6" style="margin-top:0px;"/>
			                		<span style="margin-left: 10px;">男</span>
			                	</label>
			                	<label style="margin-left: 48px;display: inline;">
			                		<input id="women" value="1" type="radio" name="apiUser.sex" tabindex="7" style="margin-top:0px;"/>
			                		<span style="margin-left: 10px;">女</span>
			                	</label>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户头像：</td>
							<td>
								<input onchange="viewUploadImg(this,'userViewPortrait')" type="file" name="userPortrait" tabindex="8"  style="width:260px;margin-left:30px;"/>
								<img style="width:50px;height:50px;display:none;" id="userViewPortrait"/>
							</td>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">状态：</td>
							<td>
								<label style="margin-left: 35px; display: inline;">
			                		<input id="used" type="radio" name="apiUser.deleteFlag" checked="checked" tabindex="9"  value="0" style="margin-top:0px;"/>
			                		<span style="margin-left: 10px;">启&nbsp;用</span>
			                	</label>
			                	<label style="margin-left: 25px;display: inline;">
			                		<input id="unUsed" type="radio" name="apiUser.deleteFlag" value="1" tabindex="10" style="margin-top:0px;"/>
			                		<span style="margin-left: 10px;">禁&nbsp;用</span>
			                	</label>
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
								<input type="text" id="expertWechatNumber" name="expertTop.expertWechatNumber" tabindex="13" style="width:200px;margin-left:30px;"/>
								<span id="topTitleMsg" style="margin-left:15px;"></span>
							</td>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人经验(年)：</td>
							<td width="35%">
								<input type="text" id="expertExperience" name="expertTop.expertExperience" tabindex="14" style="width:200px;margin-left:30px;"
								  onkeyup="this.value=this.value.replace(/[^0-9]/g,'')" placeholder="请输入整数数字"/>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人类别：</td>
							<td>
								<select id="topTitle" name="expertTop.topTitle" tabindex="15" style="height:25px;margin-left:30px;width:200px;">
									<c:forEach var="type" items="${categoryList}">
										<option value="${type.id }">${type.title}</option>
									</c:forEach>
								</select>
							</td>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">城市：</td>
							<td>
								<select id="cityCode" name="expertTop.cityCode" style="height:25px;margin-left:30px;width:200px;">
									<c:forEach var="city" items="${cityList}">
										<option value="${city.id }">
											${city.cityName}
										</option>
									</c:forEach>
								</select>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人头像：</td>
							<td>
								<input onchange="viewUploadImg(this,'expertViewPortrait')" type="file" name="expertPortrait" tabindex="18"  style="width:260px;margin-left:30px;"/>
								<img style="width:50px;height:50px;display:none;" id="expertViewPortrait"/>
							</td>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">状态：</td>
							<td>
								<label style="margin-left: 35px; display: inline;">
			                		<input type="radio" value="0" name="expertTop.deleteFlag" checked="checked" tabindex="16" style="margin-top:0px;"/>
			                		<span style="margin-left: 10px;">启&nbsp;用</span>
			                	</label>
			                	<label style="margin-left: 25px;display: inline;">
			                		<input type="radio"value="1" name="expertTop.deleteFlag" tabindex="17" style="margin-top:0px;"/>
			                		<span style="margin-left: 10px;">禁&nbsp;用</span>
			                	</label>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人简介：</td>
							<td colspan="3">
								<textarea placeholder="简介在300字以内有效" name="expertTop.expertDiscriptionShort" tabindex="19" style="width:75%;margin:5px 0px 5px 30px;"></textarea>
								<span id="Msg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人座右铭：</td>
							<td colspan="3">
								<textarea  placeholder="座右铭在30字以内有效" name="expertTop.motto" tabindex="20" style="width:75%;margin:5px 0px 5px 30px;"></textarea>
								<span id="mottoMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">达人详细描述：</td>
							<td colspan="3">
								<textarea placeholder="详细描述在500字以内有效" name="expertTop.expertDiscriptionDetail" tabindex="21" style="width:75%;height:60px;margin:5px 0px 5px 30px;"></textarea>
								<span id="detailMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">个人照片展示：</td>
							<td colspan="3">
								<div style="margin:5px 0px 10px 0px;">
									<div style="height:50px;width:300px;float:left;">
										<input onchange="viewUploadImg(this,'viewDisplay1')" type="file" name="displayPicture" tabindex="22"  style="width:200px;margin-left:30px;"/>
										<img style="width:50px;height:50px;display:none;" id="viewDisplay1"/>
									</div>
									<div style="height:50px;width:300px;float:left;">
										<input onchange="viewUploadImg(this,'viewDisplay2')" type="file" name="displayPicture" tabindex="23"  style="width:200px;margin-left:30px;"/>
										<img style="width:50px;height:50px;display:none;" id="viewDisplay2"/>
									</div>
									<div style="height:50px;width:300px;float:none;">
										<input onchange="viewUploadImg(this,'viewDisplay3')" type="file" name="displayPicture" tabindex="24"  style="width:200px;margin-left:30px;"/>
										<img style="width:50px;height:50px;display:none;" id="viewDisplay3"/>
									</div>
								</div>
								<div>
									<div style="height:50px;width:300px;float:left;">
										<input onchange="viewUploadImg(this,'viewDisplay4')" type="file" name="displayPicture" tabindex="25"  style="width:200px;margin-left:30px;"/>
										<img style="width:50px;height:50px;display:none;" id="viewDisplay4"/>
									</div>
									<div style="height:50px;width:300px;float:left;">
										<input onchange="viewUploadImg(this,'viewDisplay5')" type="file" name="displayPicture" tabindex="26"  style="width:200px;margin-left:30px;"/>
										<img style="width:50px;height:50px;display:none;" id="viewDisplay5"/>
									</div>
									<div style="height:50px;width:300px;float:none;">
										<input onchange="viewUploadImg(this,'viewDisplay6')" type="file" name="displayPicture" tabindex="27"  style="width:200px;margin-left:30px;"/>
										<img style="width:50px;height:50px;display:none;" id="viewDisplay6"/>
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
