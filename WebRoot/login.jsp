<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/jsp/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+"/";
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" 
	"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    
    <title>顺驾天下平台管理系统</title>
    <link rel="stylesheet" href="css/pubmain.css" />
	<script src="<%=request.getContextPath() %>/js/jquery1.9.0.min.js"></script> 
	<!--[if lt IE 9]>
<script src="${pageContext.request.contextPath}/js/portal/modernizr.js"></script>
<![endif]-->
<!--[if IE 6]>
 <script  src="${pageContext.request.contextPath}/js/portal/png.js"></script>
 <script type="text/javascript">
    EvPNG.fix('.logo');
    EvPNG.fix('.ie6png');
 </script>
<![endif]-->
	
	<script type="text/javascript">
		$(function(){
			var isUserName=true;
			$("#userName").bind({
				focus:function(){
					$("#userMsgInfo").html("");
				},blur:function(){
					var userName=$.trim($("#userName").val());
					if(userName.length==0){
						isUserName=false;
						$("#userMsgInfo").html("* 用户名不能为空！");
					}else{
						isUserName=true
					}
				}
			});
			var isPwd=true;
			$("#pwd").bind({
				focus:function(){
					$("#msgInfo").html("");
				},blur:function(){
					var pwd=$.trim($("#pwd").val());
					if(pwd.length==0){
						isPwd=false;
						$("#msgInfo").show();
						$("#msgInfo").html("* 密码不能为空！");
					}else{
						isPwd=true;
					}
				}
			});
			//表单提交
			var isSubmit=true;
			$("#subFrom").click(function (){
				if(isUserName && isPwd){
					if(isSubmit){
						isSubmit=false;
						$("#loginForm").submit();
					}
				}
			});
			//回车提交
			$(document).keyup(function(event){
				var userName=$.trim($("#userName").val());
				var pwd=$.trim($("#pwd").val());
				if(event.keyCode ==13){
					if(userName.length>0 && pwd.length>0){
						$("#loginForm").submit();
					}else{
						if(userName.length==0){
							$("#userMsgInfo").html("* 用户名不能为空！");
						}else if(pwd.length==0){
							$("#msgInfo").html("* 密码不能为空！");
						}
					}
  				}
			});
    	});
		
		//经销商登陆
	  	function deal() {
			window.location.href = "${basePath}/car_home_dealer_cms/signin_logindeal.action";
		 }
	</script>
	
<style type="text/css">
	body {
	  margin: 0;
	  font-family: "Helvetica Neue", Helvetica, Arial, sans-serif;
	  font-size: 12px;
	  line-height: 20px;
	  color: #333333;
	  background-color: #ffffff;
	  background:#0066A8  no-repeat center 0px;
	}
	.tit{ margin:auto; margin-top:170px; text-align:center; width:520px; padding-bottom:20px;}
	.login-wrap{ width:250px; padding:30px 50px 0 330px; height:220px; background:#fff url(img/log1.jpg) no-repeat 30px 40px; margin:auto; overflow: hidden;}
	.login_input{ display:block;width:180px;}
	.login_user{ background: url(img/input_icon_1.png) no-repeat 170px center;
				 font-family: "Lucida Sans Unicode", "Lucida Grande", sans-serif}
	.login_password{ background: url(img/input_icon_2.png) no-repeat 170px center; font-family:"Courier New", Courier, monospace}
	.btn-login{background:#40454B; box-shadow:none; text-shadow:none; color:#fff; border:none;height:35px; line-height:26px; font-size:14px; font-family:"microsoft yahei";}
	.btn-login:hover{ background:#333; color:#fff;}
	.copyright{ margin:auto; margin-top:10px; text-align:center; width:370px; color:#CCC}
	@media (max-height: 700px) {.tit{ margin:auto; margin-top:100px; }}
	@media (max-height: 500px) {.tit{ margin:auto; margin-top:50px; }}
	
	.p_text{
		display:-moz-inline-box; display:inline-block;
		min-width:45px;
	}
	.btn-block{ margin-left:50px;width:77%;}
</style>
  </head>
  
  <body>
    <form method="post" action="${pageContext.request.contextPath}/signin_login.action" id="loginForm">
		<div class="tit"><img class="ie6png" src="${pageContext.request.contextPath}/img/tit.png" alt="" /></div>
		<div class="login-wrap">
		  <table width="100%" border="0" cellspacing="0" cellpadding="0">
		  	<tr><td height="25" ></td></tr>
		    <tr>
		      <td height="25" valign="bottom">
		      	<span class="p_text">用户名：</span>
		      	 <input type="text" class="login_input login_user" name="user.userName" id="userName" tabindex="1"/>
		      </td>
		    </tr>
		    <tr>
		    	<td height="35" > 
		    		<span style="color:red;margin-left:50px;" id="userMsgInfo"></span>
		    	</td>
		    </tr>
		    
		    <tr>
		      <td>
		      	<span class="p_text">密&nbsp;&nbsp;&nbsp;&nbsp;码：</span>
		      	<input type="password"  class="login_input login_password" name="user.password" id="pwd" tabindex="2"/>
		      	</td>
		    </tr>
		     <tr>
		    	<td height="30" >
		    	  <span style="color:red;margin-left:50px;" id="msgInfo">
			    	<c:if test="${! empty requestScope.loginError}">
			    		*${requestScope.loginError}
			    	</c:if>
		    	  </span>
		    	</td>
		    </tr>
		    <tr>
		      <td height="40">
		      	<div id="subFrom" class="btn btn-block btn-login">管理员登录</div>
		      </td>
		    </tr>
		   <tr>
		      <td height="40" valign="bottom">
		      	<div id="b1" onclick="deal()" class="btn btn-block btn-login">经销商登录</div>
		      </td>
		    </tr>
		   </table>
		</div>
		<!-- <div class="copyright">版权所有：大连盖特尔信息科技有限公司</div> -->
	</form>
  </body>
</html>
