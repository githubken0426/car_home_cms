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
	<title>新增</title>
	<link rel="stylesheet" href="<%=path %>/css/pubmain.css" />
	<link href="<%=request.getContextPath() %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/system.cms.js"></script>
   	
  <script type="text/javascript">
  
	

	// 定义js check变量  

	var userIdIsOK = true;
	var titleIsOK = true;
	var contentIsOK = true;
	var picUrlsIsOK = true;
	var availableFlagIsOK = true;

	
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

		// 标题
		$("#title").bind({
			focus:function(){
				$("#titleMsg").html("");
			},blur:function(){
				var title = $.trim($("#title").val());
				if(title.length > 100){
					$("#titleMsg").html("<font color='red'>标题 超长(100位)!</font>");
					titleIsOK = false;
					return false;
				}
				titleIsOK = true;
				return true;
			}
		});

		// 内容
		$("#content").bind({
			focus:function(){
				$("#contentMsg").html("");
			},blur:function(){
				var content = $.trim($("#content").val());
				if(content.length > 1000){
					$("#contentMsg").html("<font color='red'>内容 超长(1000位)!</font>");
					contentIsOK = false;
					return false;
				}
				contentIsOK = true;
				return true;
			}
		});

		// 图片集
		$("#picUrls").bind({
			focus:function(){
				$("#picUrlsMsg").html("");
			},blur:function(){
				var picUrls = $.trim($("#picUrls").val());
				if(picUrls.length > 200){
					$("#picUrlsMsg").html("<font color='red'>图片集 超长(200位)!</font>");
					picUrlsIsOK = false;
					return false;
				}
				picUrlsIsOK = true;
				return true;
			}
		});

		// 有效标记
		$("#availableFlag").bind({
			focus:function(){
				$("#availableFlagMsg").html("");
			},blur:function(){
				var availableFlag = $.trim($("#availableFlag").val());
				availableFlagIsOK = true;
				return true;
			}
		});

	
  	});
  
   	//添加
	function addSubmit(){
		
		// 提交前校验
		if ( 1==1 && userIdIsOK && titleIsOK && contentIsOK && picUrlsIsOK && availableFlagIsOK ){
			$("#addForm").submit();
		} else {
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
		<form action="${pageContext.request.contextPath}/self_driving_trowellingAction!addData.action" method="post" id="addForm">
			<div class="content-box">
				<div class="content-box-header">
			    	<span class="now_location">当前位置:</span>添加
			        <div class="clear"></div>
			    </div>
		   		
		   		<div style="margin:0 auto; margin:10px;">
					<table  class="margin-bottom-20 table  no-border" style="width:300px;margin-top:20px;">
					 	<tr>
							<td class="left">
								<input type="button" value="保存" class="btn btn-info " style="width:80px;" onclick="addSubmit()" />
							</td>
							<td align="left">
								<input type="button" value="返回" class="btn btn-info " style="width:80px;"  onclick="turnBack()"/>
						   	</td>
					   		<td></td>
					   </tr>
					</table>
		   		
	            	<table class="table table-bordered" >
						

						<!-- 数据录入区  -->

						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">用户：</td>
							<td>
							<input type="text" id="userId" name="entity.userId" tabindex="1" style="width:400px;margin-left:30px;"/>
							<span id="userIdMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">标题：</td>
							<td>
							<input type="text" id="title" name="entity.title" tabindex="2" style="width:400px;margin-left:30px;"/>
							<span id="titleMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">内容：</td>
							<td>
							<input type="text" id="content" name="entity.content" tabindex="3" style="width:400px;margin-left:30px;"/>
							<span id="contentMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">图片集：</td>
							<td>
							<input type="text" id="picUrls" name="entity.picUrls" tabindex="4" style="width:400px;margin-left:30px;"/>
							<span id="picUrlsMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">有效标记：</td>
							<td>
							<input type="text" id="availableFlag" name="entity.availableFlag" tabindex="5" style="width:400px;margin-left:30px;"/>
							<span id="availableFlagMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
	            	</table>
	         	</div> 
			</div>
		</form>
			
		<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/self_driving_trowellingAction!listData.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="groupId" value="${groupId}" />
		</form>
	</div>
</div>
</body>
</html>
