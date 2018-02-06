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
	<script type="text/javascript" src="<%=basePath%>js/system.cms.js"></script>
   	
<script type="text/javascript">

	

	// 定义js check变量  

	var userIdIsOK = true;
	var contentIsOK = true;
	var mailIsOK = true;
	var replyIsOK = true;

	
	$(function(){		
		

		// 定义js check必录项和长度校验  

		// 用户
		$("#userId").bind({
			focus:function(){
				$("#userIdMsg").html("");
			},blur:function(){
				var userId = $.trim($("#userId").val());
				if(userId.length > 32){
					$("#userIdMsg").html("<font color='red'>用户 超长(32位)!</font>");
					userIdIsOK = false;
					return false;
				}
				userIdIsOK = true;
				return true;
			}
		});

		// 内容
		$("#content").bind({
			focus:function(){
				$("#contentMsg").html("");
			},blur:function(){
				var content = $.trim($("#content").val());
				if(content.length > 200){
					$("#contentMsg").html("<font color='red'>内容 超长(200位)!</font>");
					contentIsOK = false;
					return false;
				}
				contentIsOK = true;
				return true;
			}
		});

		// 邮件
		$("#mail").bind({
			focus:function(){
				$("#mailMsg").html("");
			},blur:function(){
				var mail = $.trim($("#mail").val());
				if(mail.length > 50){
					$("#mailMsg").html("<font color='red'>邮件 超长(50位)!</font>");
					mailIsOK = false;
					return false;
				}
				mailIsOK = true;
				return true;
			}
		});

		// 回复
		$("#reply").bind({
			focus:function(){
				$("#replyMsg").html("");
			},blur:function(){
				var reply = $.trim($("#reply").val());
				if(reply.length > 200){
					$("#replyMsg").html("<font color='red'>回复 超长(200位)!</font>");
					replyIsOK = false;
					return false;
				}
				replyIsOK = true;
				return true;
			}
		});

	
  	});
  
   	// 更新
	function updateSubmit(){
		
		// 提交前校验
		if ( 1==1 && userIdIsOK && contentIsOK && mailIsOK && replyIsOK){
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
		<form action="${pageContext.request.contextPath}/feedbackAction!updateData.action" method="post" id="updateForm">
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
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户：</td>
							<td>
							<input type="text" id="userId" name="entity.userId"  value="${entity.userId }"  tabindex="1" style="width:400px;margin-left:30px;"/>
							<span id="userIdMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">内容：</td>
							<td>
							<input type="text" id="content" name="entity.content"  value="${entity.content }"  tabindex="2" style="width:400px;margin-left:30px;"/>
							<span id="contentMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">邮件：</td>
							<td>
							<input type="text" id="mail" name="entity.mail"  value="${entity.mail }"  tabindex="3" style="width:400px;margin-left:30px;"/>
							<span id="mailMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">回复：</td>
							<td>
							<input type="text" id="reply" name="entity.reply"  value="${entity.reply }"  tabindex="4" style="width:400px;margin-left:30px;"/>
							<span id="replyMsg" style="margin-left:15px;"></span>
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
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/feedbackAction!listData.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="groupId" value="${groupId}" />
		</form>
	</div>
</div>
</body>
</html>
