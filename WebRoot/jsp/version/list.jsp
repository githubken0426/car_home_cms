<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/jsp/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
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
	<title>软件版本</title>
	<link rel="stylesheet" href="<%=path%>/css/pubmain.css" />
	<link href="<%=request.getContextPath() %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="<%=path%>/js/cms/kkpager/kkpager_blue.css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/cms/kkpager/kkpager.js"></script>
   	
  <script type="text/javascript">
  
	function getParameter(name) { 
		var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)"); 
		var r = window.location.search.substr(1).match(reg); 
		if (r!=null)
			return unescape(r[2]); 
		return null;
	}

  	$(function(){
		//分页开始
  		var totalPage = ${totalPages};
  		var totalRecords = ${totalCount};
  		var pageNo = getParameter('pno');
  		$("#backPageNo").val(pageNo);
  		if(!pageNo){
  			if('${currentIndex}'!=null && '${currentIndex}'!=''){
  				pageNo='${currentIndex}';
  			}else{
  				pageNo = 1;
  			}
  		}
  		//初始化分页控件
  		kkpager.init({
  			pno : pageNo,
  			total : totalPage,			 //总页码
  			totalRecords : totalRecords, //总数据条数
  			hrefFormer : '${pageContext.request.contextPath}/version!listData',//链接前部
  			hrefLatter : '.action',		 //链接尾部
  			getLink : function(n){
  				return this.hrefFormer + this.hrefLatter + "?pno="+n; 
  			},
  			lang : {
  				prePageText : '上一页',
  				nextPageText : '下一页',
  				totalPageBeforeText : '共',
  				totalPageAfterText : '页',
  				totalRecordsAfterText : '条数据',
  				gopageBeforeText : '转到',
  				gopageButtonOkText : '确定',
  				gopageAfterText : '页',
  				buttonTipBeforeText : '第',
  				buttonTipAfterText : '页'
  			}
  		});
  		//生成
  		kkpager.generPageHtml();
  		//全选全不选
  		$("#isSelectAll").bind({
  			click:function(){
  				var checkboxs=$("input:checkbox[name=id]");
  				if($("#isSelectAll").is(":checked")){
  					for(var i=0;i<checkboxs.length;i++){
  					  if(!(checkboxs[i].checked)){
						  	checkboxs[i].checked=true;
						}
  					}
  				}else{
  					for(var i=0;i<checkboxs.length;i++){
  					  if((checkboxs[i].checked)){
						  	checkboxs[i].checked=false;
						}
  					}
  				}
  			}
  		});
  	});

	
	//按条件查询
  	function query(){
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/version!listData.action");
	  	$("#totalForm").submit();
  	}
	
  	//重置
  	function clean(){
		$("#versionCode").attr("value","");
		$("#content").attr("value","");
  	}
  	
	
  	//修改
  	function updateDataPage(){
  		var checkboxs=$("input:checkbox[name=id]");
  		var ret = 0;  // 选中的记录数
  		var idx = 0;  // 被选中的数据索引号
  		for(var i=0; i<checkboxs.length; i++){ 
  			if(checkboxs[i].checked) {
  				ret = ret + 1;
  				idx = i;
  			}
 		} 
  		
  		if (ret == 0) {
  			alert("请选择修改数据！");
  			return;
  		} else if (ret > 1) {
  			alert("请只选择一条数据！");
  			return;
  		} else if (ret == 1) {
			$("#id").val(checkboxs[idx].value);			 
			$("#totalForm").attr("action","${pageContext.request.contextPath}/version!updateDataPage.action");
			$("#totalForm").submit();	
  		}
  	}
  	
  	//批量修改状态
  	function changeStatus(status){
  		var checkboxs=$("input:checkbox[name=id]");
  		if(checkboxs.is(":checked")){
  			$("#userStatus").val(status);
	  		$("#totalForm").attr("action","${pageContext.request.contextPath}/version!updateDataBatch.action");
	  		$("#totalForm").submit();
  		}else{
  			alert("请选择修改项！");
  		}
  	}
  	
  </script>
  <style type="">
  	.footer{margin-top:0px;}
  </style>
  </head>
  
<body>
<div id="middle">
	<div class="right"  id="mainFrame">    	
		<form action="" method="post" id="totalForm" >

			<!-- 查询条件区域 -->
			<div class="content-box">
			    <div class="content-box-header">
			    	<span class="now_location">当前位置:</span>[软件版本]
			        <div class="clear"></div>
			    </div>
			    <%--<div class=" margin-bottom-5 mt10">
					<span class="margin-left-10" style="font-size: 15px;">
						最新版本号
						<input type="text" id="versionCode" name="versionCode" value="${versionCode}"class="laydate-icon" style="width:150px;padding:5px;" />
					</span>

					<span class="margin-left-10" style="font-size: 15px;">
						内容
						<input type="text" id="content" name="content" value="${content}"class="laydate-icon" style="width:150px;padding:5px;" />
					</span>
			   		<span style="float:right;">
			   			<input onclick="clean()" type="button" value="重置" class="btn btn-info" style="width:100px;margin-right:8px;" />
			   			<input onclick="query()" type="button" value="查询" class="btn btn-info" style="width:100px;margin-right:8px;" />
			   	 	</span>
			   	 	<div style="clear:both"></div>			   		
			   </div>
			--%></div>

			<!-- 检索结果区域 -->	
			<div class="content-box" style="overflow:scroll;">
			  	<div class="content-box-header"></div>
			    <table class="table table-bordered table-striped table-hover">
		      	<tbody>
			        <tr align="center">
			       	 	<td nowrap="nowrap" width="40px"><input type="checkbox" id="isSelectAll"/></td>
						<!--  检索结果表格题头 -->
						<td nowrap="nowrap" width="80px"><strong>最新版本号</strong></td>
						<td nowrap="nowrap" width="100px"><strong>最新版本名</strong></td>
						<td nowrap="nowrap" width="220px"><strong>内容</strong></td>
						<td nowrap="nowrap" width="120px"><strong>最低可用版本号</strong></td>
						<td nowrap="nowrap" width="120px"><strong>最低可用版本</strong></td>
						<td nowrap="nowrap" width="120px"><strong>审核状态</strong></td>
						<td nowrap="nowrap" width="120px"><strong>系统类型</strong></td>
	       			</tr>
		       		<c:forEach var="o" items="${VersionList}" varStatus="s">					
					<tr align="center">
						<td><input type="checkbox" name="id" value="${o.id}"/></td>
						<!--  检索结果表格内容 -->
						<td>${o.versionCode }</td>
						<td>${o.versionName }</td>
						<td>${o.content }</td>
						<td>${o.minCode }</td>
						<td>${o.minVersion }</td>
						<td><c:choose>
								<c:when test="${o.state==false && o.systemBj==0}">审核中</c:when>
								<c:when test="${o.state==true && o.systemBj==0}">审核通过</c:when>
								<c:otherwise></c:otherwise>
							</c:choose>
						</td>
						<td><c:choose>
								<c:when test="${o.systemBj==0 }">ios</c:when>
								<c:otherwise>android</c:otherwise>
							</c:choose>
						</td>

					</tr>					
					</c:forEach>
		     	  </tbody>
			   </table>
			   
			   <!-- 功能按钮区域 -->
			   <div class=" margin-left-20">
			   		<span style="font-size:14px;">操作:</span>
			   		<span class=" margin-left-10">			   	
				   		<!-- <input onclick="addDataPage();"     type="button" value="增加" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input onclick="deleteData();"      type="button" value="删除" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" /> -->
				   		<input onclick="updateDataPage()"   type="button" value="修改" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input type="hidden" id= "backPageNo" name="backPageNo" />
				   	</span>
			   	</div>
			   	
			</div>
			<!-- 给修改用的 -->
			<input type="hidden" id= "id" name="id" />
		</form>
		
		<div class="pkp">
            <div id="kkpager"></div>
    	</div>
	</div>
</div>
</body>
</html>
