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
			        url: "${pageContext.request.contextPath}/apiUser_getUserByLoginPhone.action",  
			        success: function (data) {
			        	if(data==1){
			        		$("#loginPhoneMsg").html("<font color='red'>* 已存在!</font>");
			        		telephoneIsOk = false;
			        	}else{
			        		$("#loginPhoneMsg").html("<font color='green'>* OK!</font>");
			        		telephoneIsOk = true;
			        	}
			        } 
			    });
			}else{
				$("#loginPhoneMsg").html("<font color='red'>* 不能为空!</font>");
				telephoneIsOk = false;
			}
			return telephoneIsOk;
	  	}
  });
  
  	//验证密码
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
		   			if(pattern.test(pwd)){
		   				$("#passwordMsg").html("<font color='green'>* OK！</font>");
			   	  		isPwd= true;
				   	}else{
				   	  $("#passwordMsg").html("<font color='red'>* 不能有特殊字符！</font>");
				   	  isPwd= false;
				   	}
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
  
   //添加提交
   function addSubmit(){
   	  if(telephoneIsOk && isPwd && isEquals){
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
			<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/apiUser_addData.action" method="post" id="addForm">
			    <div class="content-box">
<!-- 用户录入区  -->
			     	<div class="content-box-header">
				    	<span class="now_location">用户信息:</span> 添加用户
				        <div class="clear"></div>
				    </div>
				    <table class="table table-bordered" >
						<tr>
							<td width="25%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">登陆账号：</td>
							<td width="75%">
								<input type="text" placeholder="手机号"  id="loginPhone" name="entity.loginPhone" tabindex="1" style="width:220px;margin-left:30px;"/>
								<span id="loginPhoneMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">登陆密码：</td>
							<td>
								<input type="password" id="password" name="entity.password" tabindex="3" style="width:220px;margin-left:30px;"/>
								<span id="passwordMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">确认密码：</td>
							<td>
								<input type="password" id="againPassword" tabindex="4" style="width:220px;margin-left:30px;"/>
								<span id="againPasswordMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">真实姓名：</td>
							<td width="35%">
								<input type="text" placeholder="真实姓名" id="realName" name="entity.realName" tabindex="5" style="width:220px;margin-left:30px;"/>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户昵称：</td>
							<td width="35%">
								<input type="text" placeholder="昵称" id="nickname" name="entity.nickname" tabindex="5" style="width:220px;margin-left:30px;"/>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户头像：</td>
							<td>
								<input onchange="viewUploadImg(this,'userViewPortrait')" type="file" name="userPortrait" tabindex="8"  style="width:300px;margin-left:30px;"/>
								<img style="width:50px;height:50px;display:none;" id="userViewPortrait"/>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">性别：</td>
							<td>
								<label style="margin-left: 35px; display: inline;">
			                		<input type="radio" name="entity.sex" checked="checked" tabindex="6"  value="0" style="margin-top:0px;"/>
			                		<span style="margin-left: 10px;">男</span>
			                	</label>
			                	<label style="margin-left: 48px;display: inline;">
			                		<input type="radio" name="entity.sex" value="1" tabindex="7" style="margin-top:0px;"/>
			                		<span style="margin-left: 10px;">女</span>
			                	</label>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">状态：</td>
							<td>
								<label style="margin-left: 35px; display: inline;">
			                		<input type="radio" name="entity.deleteFlag" checked="checked" tabindex="9"  value="0" style="margin-top:0px;"/>
			                		<span style="margin-left: 10px;">启&nbsp;用</span>
			                	</label>
			                	<label style="margin-left: 25px;display: inline;">
			                		<input type="radio" name="entity.deleteFlag" value="1" tabindex="10" style="margin-top:0px;"/>
			                		<span style="margin-left: 10px;">禁&nbsp;用</span>
			                	</label>
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
			</form>
			<!-- 返回，记录列表页数据 -->
			<form id="backForm" method="post" action="${pageContext.request.contextPath}/apiUser_listData.action">
				<input type="hidden" name="pno" value="${currentIndex}" />
				<input type="hidden" name="deleteFlag" value="${deleteFlag}" />
				<input type="hidden" name="loginPhone" value="${loginPhone}" />
				<input type="hidden" name="beginTime" value="${beginTime}" />
				<input type="hidden" name="endTime" value="${endTime}" />
			</form>
	  </div>
    </div>
  </body>
  
  
</html>
