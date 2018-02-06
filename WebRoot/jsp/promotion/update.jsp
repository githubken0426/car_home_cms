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
	<link href="<%=path %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=path %>/css/global.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
   	<script type="text/javascript" charset="utf-8" src="<%=path %>/js/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/js/ueditor/ueditor.all.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path %>/js/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" src="<%=path%>/js/cms/view_image/view_image.js"></script>
    <script type="text/javascript" src="<%=path %>/js/cms/laydate/laydate.js"></script>
    
  <script type="text/javascript">
  	var initUe={enterTag:'br'};
	//实例化编辑器
	 $(document).ready(function(){
		 var ue = UE.getEditor('editor',initUe);
	  });
	  
	var titleIsOK = true;
	var shopIsOK=true;
	$(function(){
		$("#title").bind({
			focus:function(){
				$("#titleMsg").html("");
			},blur:function(){
				var title = $.trim($("#title").val());
				if(title.length > 100){
					$("#titleMsg").html("<font color='red'>标题100字以内!</font>");
					titleIsOK = false;
					return false;
				}
				titleIsOK = true;
				return true;
			}
		});
		//验证店铺
		$("#shopName").bind({
		  	focus:function(){
		  		$("#shopMsg").html("");
		  	},blur:function(){
		  		var shopName = $.trim($("#shopName").val());
				if(shopName){
					$.ajax({
				        type:"POST",  
				        dataType:"json",  
				        data:{shopName:shopName},
				        async:true,
				        url: "${pageContext.request.contextPath}/promotion_getShopByName.action",  
				        success: function (data) {
				        	if(data){
					        	console.log(data.id);
					        	$("#shopId").val(data.id);
				        		$("#shopMsg").html("<font color='green'>* OK!</font>");
				        		shopIsOK = true;
				        	}else{
				        		$("#shopMsg").html("<font color='red'>* 没有找到此店铺!</font>");
				        		shopIsOK = false;
				        	}
				        } 
				    });
				}else{
					$("#shopMsg").html("<font color='red'>* 不能为空!</font>");
					shopIsOK = false;
				}
				return shopIsOK;
		  	}
	  });
		  
  	});
  
   	//添加
	function addSubmit(){
		if (titleIsOK && shopIsOK && isViewUpdateOK){
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
		<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/promotion_updateData.action" method="post" id="addForm">
			<div class="content-box">
				<div class="content-box-header">
			    	<span class="now_location">当前位置:</span>添加
			        <div class="clear"></div>
			    </div>
		   		<div style="margin:0 auto; margin:10px;">
	            	<table class="table table-bordered" >
						<!-- 数据录入区  -->
						<tr>
							<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">促销店铺：</td>
							<td width="38%">
								<input type="text" id="shopName"  value="${promotion.shopName}"placeholder="店铺名称"tabindex="3" style="width:300px;margin-left:30px;"/>
								<input type="hidden" id="shopId" name="promotion.shopId" value="${promotion.shopId }"/>
								<input type="hidden" name="promotion.id" value="${promotion.id }"/>
								<span id="shopMsg" style="margin-left:5px;"></span>
							</td>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">促销日期：</td>
							<td>
								<input type="text" id="startDate" name="promotion.startDate" 
									value="<fmt:formatDate value="${promotion.startDate}" type="both" pattern="yyyy-MM-dd" dateStyle="long"/>" 
									placeholder="开始日期"  class="laydate-icon" style="width:150px;padding:3px;margin-left:30px;" />
					 			至
								<input type="text" id="endDate" name="promotion.endDate"
									value="<fmt:formatDate value="${promotion.endDate}" type="both" pattern="yyyy-MM-dd" dateStyle="long"/>"  
									placeholder="结束日期" class="laydate-icon" style="width:150px;margin:0px 10px 0px 2px;padding:3px;" />
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">促销标题：</td>
							<td>
								<input type="text" id="title"placeholder="标题在100字以内" 
									name="promotion.title" value="${promotion.title }"  tabindex="3" style="width:300px;margin-left:30px;"/>
								<span id="titleMsg" style="margin-left:5px;"></span>
							</td>
							<td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">状态：</td>
							<td width="38%">
								<c:choose>
									<c:when test="${promotion.deleteFlag==0}">
										<label style="margin-left: 35px; display: inline;">
						                	<input type="radio" name="promotion.deleteFlag" checked="checked" tabindex="9"  value="0" style="margin-top:0px;"/>
						                	<span style="margin-left: 10px;">启&nbsp;用</span>
						                </label>
						                <label style="margin-left: 25px;display: inline;">
						                	<input type="radio" name="promotion.deleteFlag" value="1" tabindex="10" style="margin-top:0px;"/>
						                	<span style="margin-left: 10px;">禁&nbsp;用</span>
						                </label>
									</c:when>	
									<c:otherwise>
										<label style="margin-left: 35px; display: inline;">
						                	<input type="radio" name="promotion.deleteFlag" tabindex="9"  value="0" style="margin-top:0px;"/>
						                	<span style="margin-left: 10px;">启&nbsp;用</span>
						               </label>
						               <label style="margin-left: 25px;display: inline;">
						                	<input type="radio" name="promotion.deleteFlag" value="1"checked="checked" tabindex="10" style="margin-top:0px;"/>
						                	<span style="margin-left: 10px;">禁&nbsp;用</span>
						               </label>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">促销简介：</td>
							<td colspan="3">
								<textarea placeholder="简介在300字以内" id="introduction" name="promotion.introduction" tabindex="4" maxlength="300"
									style="width:600px;height:100px;margin-left:30px;">${promotion.introduction }
								</textarea>
							</td>
						</tr>
						<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">展示图片：</td>
							<td>
								<input onchange="viewUploadImg(this,'viewPictureList')" type="file" id="pictureList" name="pictureList" tabindex="4" maxlength="300"
									style="width:300px;margin-left:30px;"></input>
								<input type="hidden" value="${promotion.pictureList }" name="pictureListFlag" id="pictureListFlag"/>
								<img 
									<c:if test="${not empty promotion.pictureList}"> 
										src="${ftpServerIp }${promotion.pictureList}"  
									</c:if>style="width:50px;height:50px;" id="viewPictureList"/>
							</td>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">背景图片：</td>
							<td>
								<input onchange="viewUploadImg(this,'viewBackgroundImage')" type="file" id="backgroundImage" name="backgroundImage" tabindex="4" maxlength="300"
									style="width:300px;margin-left:30px;"></input>
								<input type="hidden" value="${promotion.backgroundImage }" name="backgroundImageFlag" id="backgroundImageFlag"/>
								<img 
									<c:if test="${not empty promotion.backgroundImage}"> 
										src="${ftpServerIp }${promotion.backgroundImage}"  
									</c:if>style="width:50px;height:50px;" id="viewBackgroundImage"/>
							</td>
						</tr>
						<tr>
							<td  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">促销内容：</td>
							<td colspan="3" style="white-space:pre-wrap">
								<textarea id="editor" name="promotion.content" style="width:1024px;height:600px;margin-left:30px;">
									${promotion.content }
								</textarea>
								<%--<script name="promotion.content" id="editor" type="text/plain" 
									style="width:1024px;height:600px;margin-left:30px;"></script>--%>
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
			</div>
		</form>
			
		<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/promotion_listData.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="deleteFlag" value="${deleteFlag}" />
			<input type="hidden" name="shopName" value="${shopName}" />
			<input type="hidden" name="beginTime" value="${beginTime}" />
			<input type="hidden" name="endTime" value="${endTime}" />
		</form>
	</div>
</div>
<script type="text/javascript">
  !function(){
	laydate.skin('molv');
		laydate({
			elem : '#startDate',
			istoday : true,
			format : 'YYYY-MM-DD',
			max : laydate.now()
		});
		laydate({
			elem : '#endDate',
			istime: true,
			istoday : false,
			format : 'YYYY-MM-DD'
		});
	}();
</script>
</body>
</html>