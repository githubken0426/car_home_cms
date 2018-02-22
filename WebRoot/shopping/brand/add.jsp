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
    <script type="text/javascript" src="<%=path%>/js/cms/view_image/view_image.js"></script>
    <script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
    <link href="<%=path %>/js/webuploader/webuploader.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path %>/js/webuploader/preview.css" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="<%=path%>/js/webuploader/webuploader.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/webuploader/preview.js"></script>
    
<script type="text/javascript">
   	//添加
	function addSubmit(){
   		var title=$.trim($("#title").val());
   		if(!title){
   			layer.tips('请输入广告标题！', '#title');
   			return false;
   		}
   		var url=$.trim($("#url").val());
		if(!url){
			layer.tips('请输入广告链接！', '#url');
			return false;
   		}
		$("#addForm").submit();	
	}
    //返回
  	function turnBack(){
  		$("#backForm").submit();
  	}
  </script>
<style type="text/css">
  	input{padding:8px;}
</style>
</head>
<body>

<div id="middle">
	<div class="right"  id="mainFrame">
		<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/brand_add.action" method="post" id="addForm">
			<div class="content-box">
				<div class="content-box-header">
			    	<span class="now_location">当前位置:</span>添加
			        <div class="clear"></div>
			    </div>
		   		<div style="margin:0 auto; margin:10px;">
	            	<table class="table table-bordered" >
						<!-- 数据录入区  -->
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">所属分类：</td>
							<td width="25%">
								<select id="categoryId" name="categoryId" style="height:25px;width:220px;margin-left:30px;">
									<c:forEach var="category" items="${categoryList}">
										<option value="${category.id }">
											${category.title}
										</option>
									</c:forEach>
								</select>
							</td>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">品牌英文名称：</td>
							<td width="25%">
								<input type="text" id="enName" name="entity.enName" tabindex="3" style="width:220px;margin-left:30px;"/>
							</td>
							<td width="30%"></td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">品牌中文名称：</td>
							<td>
								<input type="text" id="cnName" name="entity.cnName" tabindex="3" style="width:220px;margin-left:30px;"/>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">品牌LOGO：</td>
							<td>
								<input onchange="viewUploadImg(this,'brandLogo')" type="file" id="logo" name="logo" tabindex="4" style="width:220px;margin-left:30px;"/>
								<img style="width:50px;height:50px;display:none;" id="brandLogo"/>
							</td>
							<td></td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">品牌介绍：</td>
							<td colspan="3" height="125px;">
								<textarea id="descrption" name="entity.descrption" style="height:105px;width:90%;margin-left:30px;padding:10px;"></textarea>
							</td>
							<td></td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">品牌产品图片：</td>
							<td colspan="3">
								<div id="container">
						            <div id="uploader">
						                <div class="queueList">
						                    <div id="dndArea" class="placeholder">
						                        <div id="filePicker"></div>
						                    </div>
						                </div>
						                <div class="statusBar" style="display:none;">
						                    <div class="progress">
						                        <span class="text">0%</span>
						                        <span class="percentage"></span>
						                    </div><div class="info"></div>
						                    <div class="btns">
						                        <div id="filePicker2"></div><div class="uploadBtn">开始上传</div>
						                    </div>
						                </div>
						            </div>
						        </div>
								</td>
							<td></td>
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
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/brand_list.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="cnName" value="${cnName}" />
			<input type="hidden" name="categoryId" value="${categoryId}" />
		</form>
	</div>
</div>
</body>
</html>
