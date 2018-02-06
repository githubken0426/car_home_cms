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
	<title>修改密码</title>
	<link rel="stylesheet" href="<%=path %>/css/pubmain.css" />
	<link href="<%=request.getContextPath() %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
	<script src="<%=request.getContextPath() %>/js/md5.js"></script> 
	<script type="text/javascript" src="<%=basePath%>js/wonderful.cms.js"></script>
  <script type="text/javascript">
   var nameA = false;
   var isPwd=false;
   var isEquals=false;
  $(function(){
  	//判断用户是否存在
	  $("#password").bind({
	  	focus:function(){
	  		$("#passwordMsg").html("");
	  	},blur:function(){
	  		var password = $.trim($("#password").val());
	  		var rpwd='${dealer_user.password}';
	  		password = hex_md5(password);
			if(password.length >0){
				if(password==rpwd){
					$("#passwordMsg").html("<font color='green'>* 正确！</font>");
					nameA = true;
				}else{
					$("#passwordMsg").html("<font color='red'>* 原密码输入错误!</font>");
					nameA = false;
				}
			}else{
				$("#passwordMsg").html("<font color='red'>* 原密码不能为空!</font>");
				nameA = false;
			}
			return nameA;
	  	}
  });
  
  	//新密码
  	$("#newPassword").bind({
  		focus:function(){
  			$("#newPasswordMsg").html("");
  		},blur:function(){
  			var pwd=$.trim($("#newPassword").val());
		   	var pattern = /^([a-zA-Z0-9!_]){6,20}$/;
		   	if(pwd.length>0){
		   		if(pwd.length<6){
		   			 $("#newPasswordMsg").html("<font color='red'>* 密码长度6-20位！</font>");
		   			  isPwd= false;
		   		}else{
		   			if(pattern.test(pwd)){
		   				$("#newPasswordMsg").html("<font color='green'>* 可用！</font>");
			   	  		isPwd= true;
				   	}else{
				   	  $("#newPasswordMsg").html("<font color='red'>* 不能有特殊字符！</font>");
				   	  isPwd= false;
				   	}
		   		}
		   	 }else{
		   	  	$("#newPasswordMsg").html("<font color='red'>* 不能为空!</font>");
		   	  	isPwd= false;
		   	 }
  		}
  	});
  	
	$("#againNewPassword").bind({
	   focus:function(){
			$("#againNewPasswordMsg").html("");
	    },blur:function(){
	    	var pwd=$.trim($("#newPassword").val());
	  		var againPwd=$.trim($("#againNewPassword").val());
			if(pwd==againPwd){
				$("#againNewPasswordMsg").html("<font color='green'>* 可用！</font>");
				isEquals=true;
			}else{
				$("#againNewPasswordMsg").html("<font color='red'>* 两次密码输入不一致！</font>");
				isEquals=false;
			}
		}
	});
  });
  
   //添加提交
   function addSubmit(){
   	  if(nameA && isPwd && isEquals){
   		var pwd=$.trim($("#newPassword").val());
   		pwd = hex_md5(pwd);
		$("#newPassword").val(pwd);
   	  	$("#addForm").submit();
   	  }else{
   	  	alert("信息输入有误，无法修改！");
   	  }
    }
  	
  </script>
  </head>
  
  <body>
   	<div id="middle">
  		<div class="right"  id="mainFrame">
			<form action="${pageContext.request.contextPath}/dealerUser_changePassword.action" method="post" id="addForm">
			    <div class="content-box">
				    <div class="content-box-header">
				    	<span class="now_location">当前位置:</span> 修改密码
				        <div class="clear"></div>
				    </div>
			   		<div style="width:600px; margin:0 auto; margin-top:60px;">
			            <table class="table table-bordered" >
			            <tr>
			                <td width="20%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">账号：</td>
			                <td >
			                	<input type="text" value="${dealer_user.userName }" tabindex="0"  style="width:200px;margin-left:30px;" readonly="readonly"/>
			                </td>
			              </tr>
			              <tr>
			                <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">原密码：</td>
			                <td >
			                	<input type="password" name="password" id="password" tabindex="1"  style="width:200px;margin-left:30px;"/>
			                	<span id="passwordMsg" style="margin-left:15px;"></span>
			                </td>
			              </tr>
			              <tr>
			                <td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">新密码：</td>
			                <td>
			                	<input type="password" id="newPassword" name="newPassword" tabindex="2" style="width:200px;margin-left:30px;"/>
			                	<span id="newPasswordMsg" style="margin-left:15px;"></span>
			                </td>
			              </tr>
			              <tr>
			                <td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">确认新密码：</td>
			                <td>
			                	<input type="password" name="againNewPassword" id="againNewPassword" tabindex="3" style="width:200px;margin-left:30px;"/>
			                	<span id="againNewPasswordMsg" style="margin-left:15px;"></span>
			                </td>
			              </tr>
			              
			              </table>
			              <table  class="margin-bottom-20 table  no-border" style="margin-top:50px;">
			                <tr>
			     	         <td class="text-center">
			     	         	<input type="button" value="确定" class="btn btn-info " style="width:80px;" onclick="addSubmit()" />
			     	         </td>
			     	         <td align="left">
			     	         	<input type="button" value="返回" class="btn btn-info " style="width:80px;"  onclick="window.top.location.href='signin_turnBackDeal.action'"/>
			     	         </td>
			     	         <td></td>
			               </tr>
			            </table>
			         </div> 
				</div>
			</form>
	  </div>
    </div>
  </body>
</html>
