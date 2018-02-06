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
	<script src="<%=request.getContextPath() %>/js/md5.js"></script> 
	<script type="text/javascript" src="<%=basePath%>js/system.cms.js"></script>
   	
<script type="text/javascript">

	

	// 定义js check变量  

	var userNameIsOK = true;
	var passwordIsOK = true;
	var realNameIsOK = true;
	var cityCodeIsOK = true;

	var nameA = true;
	var isPwd=true;
	
	$(function(){		
		

		// 定义js check必录项和长度校验  

		// 用户名
		/* $("#userName").bind({
			focus:function(){
				$("#userNameMsg").html("");
			},blur:function(){
				var userName = $.trim($("#userName").val());
				if(userName.length > 30){
					$("#userNameMsg").html("<font color='red'>用户名 超长(30位)!</font>");
					userNameIsOK = false;
					return false;
				} else if (userName.length == 0) {
					$("#userNameMsg").html("<font color='red'>请填写用户名!</font>");
					userNameIsOK = false;
					return false;
				}
				userNameIsOK = true;
				return true;
			}
		});

		// 密码
		$("#password").bind({
			focus:function(){
				$("#passwordMsg").html("");
			},blur:function(){
				var password = $.trim($("#password").val());
				if(password.length > 30){
					$("#passwordMsg").html("<font color='red'>密码 超长(30位)!</font>");
					passwordIsOK = false;
					return false;
				} else if (password.length == 0) {
					$("#passwordMsg").html("<font color='red'>请填写密码!</font>");
					passwordIsOK = false;
					return false;
				}
				passwordIsOK = true;
				return true;
			}
		}); */

		//判断用户是否存在
		  $("#userName").bind({
		  	focus:function(){
		  		$("#userNameMsg").html("");
		  	},blur:function(){
		  		var userName = $.trim($("#userName").val());
		  		var id=$.trim($("#userId").val());
				if(userName.length >0){
					$.ajax({
				        type:"POST",  
				        dataType:"json",  
				        data:{name:userName,id:id},
				        async:true,
				        url: "dealerUser_getUserByName.action",  
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
	  	
		// 真实姓名
		$("#realName").bind({
			focus:function(){
				$("#realNameMsg").html("");
			},blur:function(){
				var realName = $.trim($("#realName").val());
				if(realName.length > 30){
					$("#realNameMsg").html("<font color='red'>真实姓名 超长(30位)!</font>");
					realNameIsOK = false;
					return false;
				} else if (realName.length == 0) {
					$("#realNameMsg").html("<font color='red'>请填写真实姓名!</font>");
					realNameIsOK = false;
					return false;
				}
				realNameIsOK = true;
				return true;
			}
		});

		// 城市编号
		$("#cityCode").bind({
			focus:function(){
				$("#cityCodeMsg").html("");
			},blur:function(){
				var cityCode = $.trim($("#cityCode").val());
				if(cityCode.length > 6){
					$("#cityCodeMsg").html("<font color='red'>城市编号 超长(6位)!</font>");
					cityCodeIsOK = false;
					return false;
				} else if (cityCode.length == 0) {
					$("#cityCodeMsg").html("<font color='red'>请选择城市!</font>");
					cityCodeIsOK = false;
					return false;
				}
				cityCodeIsOK = true;
				return true;
			}
		});

	
  	});
  
   	// 更新
	function updateSubmit(){
		
		// 提交前校验
		if ( 1==1 && nameA && isPwd && realNameIsOK && cityCodeIsOK){
			var pwd=$.trim($("#password").val());
	   		pwd = hex_md5(pwd);
			$("#password").val(pwd);
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
		<form action="${pageContext.request.contextPath}/dealerUser!updateData.action" method="post" id="updateForm">
			<div class="content-box">
				<div class="content-box-header">
					<span class="now_location">当前位置:</span>修改
				    <div class="clear"></div>
			    </div>
				    
			    <div style="margin:0 auto; margin:10px;">
			   		<table  class="margin-bottom-20 table  no-border" style="width:300px;margin-top:20px;">
					 	<tr>
							<td class="text-center">
								<input type="button" value="保存" class="btn btn-info " style="width:80px;" onclick="updateSubmit()" />
							</td>
							<td align="left">
								<input type="button" value="返回" class="btn btn-info " style="width:80px;"  onclick="turnBack()"/>
						   	</td>
					   		<td></td>
					   	</tr>
					</table>
			    
	            	<table class="table table-bordered" >
	            		<input type="hidden" id="id" name="entity.id" value="${entity.id }" />
	            	
	            		

						<!-- 数据修改  -->

						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户名：</td>
							<td>
							<input type="text" id="userName" name="entity.userName" disabled="disabled" value="${entity.userName }"  tabindex="1" style="width:400px;margin-left:30px;"/>
							<span id="userNameMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<%-- <tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">密码：</td>
							<td>
							<input type="text" id="password" name="entity.password"  value="${entity.password }"  tabindex="2" style="width:400px;margin-left:30px;"/>
							<span id="passwordMsg" style="margin-left:15px;"></span>
							</td>
						</tr> 
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">密码：</td>
							<td>
							<input type="text" id="password" name="entity.password" tabindex="2" style="width:400px;margin-left:30px;"/>
							<span id="passwordMsg" style="margin-left:15px;"></span>
							</td>
						</tr>--%>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">真实姓名：</td>
							<td>
							<input type="text" id="realName" name="entity.realName"  value="${entity.realName }"  tabindex="3" style="width:400px;margin-left:30px;"/>
							<span id="realNameMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">城市选择：</td>
							<td>
							<%-- <input type="text" id="cityCode" name="entity.cityCode"  value="${entity.cityCode }"  tabindex="4" style="width:400px;margin-left:30px;"/> --%>
							<select id="cityCode" name="entity.cityCode" tabindex="4" style="width:200px;height:25px;margin-left:30px;">
					     		<c:forEach var="city" items="${cityList}">
					     		    <option value="${city.cityCode}" <c:if test="${entity.cityCode==city.cityCode}">selected="selected"</c:if>>${city.cityName}</option>
					     		</c:forEach>
					     	</select> 
							<span id="cityCodeMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
	            	  	
	            	  	<!-- 
	            	  	<tr>
			                <td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">orgid：</td>
			                <td>
			    				<input type="hidden" id="id" name="config.id" value="${config.id }" />
			                	<input type="text" id="orgid" name="config.orgid" value="${config.orgid }" tabindex="1" style="width:400px;margin-left:30px;"/>
			                	<span id="orgidMsg" style="margin-left:15px;"></span>
			                </td>
		              	</tr>
						-->
	            	</table>
	            	
	         	</div>	         	
			</div>
		</form>

		<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/dealerUser!listData.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="groupId" value="${groupId}" />
		</form>
	</div>
</div>
</body>
</html>
