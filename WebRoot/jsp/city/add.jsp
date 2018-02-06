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

	var cityCodeIsOK = false;
	var cityNameIsOK = false;

	
	$(function(){
		

		// 定义js check必录项和长度校验  

		// 城市编号
		$("#cityCode").bind({
			focus:function(){
				$("#cityCodeMsg").html("");
			},blur:function(){
				var cityCode = $.trim($("#cityCode").val());
				$.ajax({
			        type:"POST",  
			        dataType:"json",  
			        data:{cityCode:cityCode},
			        async:true,
			        url: "${pageContext.request.contextPath}/city_getInfoByCode.action",  
			        success: function (data) {
			        	if(data==1){
			        		$("#cityCodeMsg").html("<font color='red'>城市编号已存在!</font>");
							cityCodeIsOK = false;
							return false;
			        	}
			        } 
			    });
				if(cityCode.length > 6){
					$("#cityCodeMsg").html("<font color='red'>城市编号 超长(6位)!</font>");
					cityCodeIsOK = false;
					return false;
				} else if (cityCode.length == 0) {
					$("#cityCodeMsg").html("<font color='red'>请填写6位城市编号!</font>");
					cityCodeIsOK = false;
					return false;
				}
				cityCodeIsOK = true;
				return true;
			}
		});

		// 城市名称
		$("#cityName").bind({
			focus:function(){
				$("#cityNameMsg").html("");
			},blur:function(){
				var cityName = $.trim($("#cityName").val());
				$.ajax({
			        type:"POST",  
			        dataType:"json",  
			        data:{cityName:cityName},
			        async:true,
			        url: "${pageContext.request.contextPath}/city_getInfoByName.action",  
			        success: function (data) {
			        	if(data==1){
			        		$("#cityNameMsg").html("<font color='red'>城市名称已存在!</font>");
							cityNameIsOK = false;
							return false;
			        	}
			        } 
			    });
				if(cityName.length > 20){
					$("#cityNameMsg").html("<font color='red'>城市名称 超长(20位)!</font>");
					cityNameIsOK = false;
					return false;
				} else if (cityName.length == 0) {
					$("#cityNameMsg").html("<font color='red'>请填写城市名称!</font>");
					cityNameIsOK = false;
					return false;
				}
				cityNameIsOK = true;
				return true;
			}
		});

	
  	});
  
   	//添加
	function addSubmit(){
		
		// 提交前校验
		if ( 1==1 && cityCodeIsOK && cityNameIsOK ){
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
		<form action="${pageContext.request.contextPath}/city!addData.action" method="post" id="addForm">
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
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">城市编号：</td>
							<td>
							<input type="text" id="cityCode" name="entity.cityCode" tabindex="1" style="width:400px;margin-left:30px;"/>
							<span id="cityCodeMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">城市名称：</td>
							<td>
							<input type="text" id="cityName" name="entity.cityName" tabindex="2" style="width:400px;margin-left:30px;"/>
							<span id="cityNameMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
	            	</table>
	         	</div> 
			</div>
		</form>
			
		<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/city!listData.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="groupId" value="${groupId}" />
		</form>
	</div>
</div>
</body>
</html>
