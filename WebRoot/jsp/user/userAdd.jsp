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
	<script type="text/javascript" src="<%=basePath%>js/wonderful.cms.js"></script>
   	
  <script type="text/javascript">
   var nameA = false;
   var isPwd=false;
   var isEquals=false;
  $(function(){
  	//判断用户是否存在
	  $("#userName").bind({
	  	focus:function(){
	  		$("#userNameMsg").html("");
	  	},blur:function(){
	  		var userName = $.trim($("#userName").val());
			if(userName.length >0){
				$.ajax({
			        type:"POST",  
			        dataType:"json",  
			        data:{name:userName},
			        async:true,
			        url: "${pageContext.request.contextPath}/user_getUserByName.action",  
			        success: function (data) {
			        	if(data==1){
			        		$("#userNameMsg").html("<font color='red'>* 已存在!</font>");
			        		nameA = false;
			        	}else{
			        		$("#userNameMsg").html("<font color='green'>* 可用!</font>");
			        		nameA = true;
			        	}
			        } 
			    });
			}else{
				$("#userNameMsg").html("<font color='red'>* 不能为空!</font>");
				nameA = false;
			}
			return nameA;
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
		   				$("#passwordMsg").html("<font color='green'>* 可用！</font>");
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
				$("#againPasswordMsg").html("<font color='green'>* 可用！</font>");
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
   	  if(nameA && isPwd && isEquals){
   	  	var index=$("#userGroups").get(0).selectedIndex;
   	  	if(index==0){
   	  		alert("请选择所属用户组！");
   	  	}else{
   	  		$("#addForm").submit();
   	  	}
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
			<form action="${pageContext.request.contextPath}/user_addUser.action" method="post" id="addForm">
			    <div class="content-box">
				    <div class="content-box-header">
				    	<span class="now_location">当前位置:</span> 添加用户
				        <div class="clear"></div>
				    </div>
			   		<div style="width:600px; margin:0 auto; margin-top:60px;">
			            <table class="table table-bordered" >
			              <tr>
			                <td width="20%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">账号：</td>
			                <td >
			                	<input type="text" name="user.userName" id="userName" tabindex="1"  style="width:200px;margin-left:30px;"/>
			                	<span id="userNameMsg" style="margin-left:15px;"></span>
			                </td>
			              </tr>
			              <tr>
			                <td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">密码：</td>
			                <td>
			                	<input type="text" id="password" name="user.password" tabindex="2" style="width:200px;margin-left:30px;"/>
			                	<span id="passwordMsg" style="margin-left:15px;"></span>
			                </td>
			              </tr>
			              <tr>
			                <td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">确认密码：</td>
			                <td>
			                	<input type="text" name="againPassword" id="againPassword" tabindex="3" style="width:200px;margin-left:30px;"/>
			                	<span id="againPasswordMsg" style="margin-left:15px;"></span>
			                </td>
			              </tr>
			    		  <tr>
			                <td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">真实姓名：</td>
			                <td>
			                	<input type="text" name="user.realName" id="realName" tabindex="4" style="width:200px;margin-left:30px;"/>
			                </td>
			              </tr>
			              <tr>
			                <td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户组：</td>
			                <td>
			                	<select name="user.groupId" id ="userGroups" style="width:210px;margin-left:30px;height:26px;" tabindex="5"  >
			                		<option value="-1">请选择...</option>
			                		<c:forEach var="group" items="${groupList}">
			                			<option value="${group.id }">${group.groupName }</option>
			                		</c:forEach>
			                	</select>
			                </td>
			              </tr>
			              <tr>
			                <td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">状态：</td>
			                <td>
			                	<label style="margin-left: 35px; display: inline;">
			                		<input type="radio" name="user.status" checked="checked" value="0"/><span style="margin-left: 10px;">启&nbsp;用</span>
			                		
			                	</label>
			                	<label style="margin-left: 25px;display: inline;">
			                		<input type="radio" name="user.status" value="1"/><span style="margin-left: 10px;">禁&nbsp;用</span>
			                	</label>
			                </td>
			              </tr>
			              </table>
			              <table  class="margin-bottom-20 table  no-border" style="margin-top:50px;">
			                <tr>
			     	         <td class="text-center">
			     	         	<input type="button" value="确定" class="btn btn-info " style="width:80px;" onclick="addSubmit()" />
			     	         </td>
			     	         <td align="left">
			     	         	<input type="button" value="返回" class="btn btn-info " style="width:80px;"  onclick="turnBack()"/>
			     	         </td>
			     	         <td></td>
			               </tr>
			            </table>
			         </div> 
				</div>
			</form>
			<!-- 返回，记录列表页数据 -->
			<form id="backForm" method="post" action="${pageContext.request.contextPath}/user_queryAllUser.action">
				<input type="hidden" name="pno" value="${currentIndex}" />
				<input type="hidden" name="groupId" value="${groupId}" />
			</form>
	  </div>
    </div>
  </body>
  
  
</html>
