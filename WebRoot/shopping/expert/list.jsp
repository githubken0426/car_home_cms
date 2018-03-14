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
	<title>达人榜</title>
	<link rel="stylesheet" href="<%=path%>/css/pubmain.css" />
	<link href="<%=request.getContextPath() %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="<%=path%>/js/cms/kkpager/kkpager_blue.css"/>
	<script type="text/javascript" src="<%=path%>/js/jquery1.9.0.min.js"></script>
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
		var expertName = $.trim($("#expertName").val());
		expertName=encodeURI(encodeURI(expertName));
		var topType = $.trim($("#topType").val());
		var deleteFlag = $.trim($("#deleteFlag").val());
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
  			hrefFormer : '${pageContext.request.contextPath}/expert_listData',//链接前部
  			hrefLatter : '.action',		 //链接尾部
  			getLink : function(n){
  				return this.hrefFormer + this.hrefLatter + "?pno="+n+"&expertName="+expertName+"&topType="+topType; 
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

	// 功能按钮区域
	
	//按条件查询
  	function query(){
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/expert_listData.action");
	  	$("#totalForm").submit();
  	}
	
  	//重置
  	function clean(){
  	  	$("#expertName").val("");
		$("#topType").attr("value","-1");
		$("#deleteFlag").attr("value","-1");
  	}
  	
  	//添加
  	function addDataPage(){  		
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/expert_addDataPage.action");
	  	$("#totalForm").submit();
  	}

	//删除	
  	function deleteData(){  		
		var checkboxs=$("input:checkbox[name=id]");
		if(checkboxs.is(":checked")){
			if(confirm("确定要删除所选中项吗?")){
				$("#totalForm").attr("action","${pageContext.request.contextPath}/expert_deleteData.action");
				$("#totalForm").submit();
			}
		}else{
			alert("请选择删除项！");
		}
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
			$("#主键ID").val(checkboxs[idx].value);			 
			$("#totalForm").attr("action","${pageContext.request.contextPath}/expert_updateDataPage.action");
			$("#totalForm").submit();	
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
			    	<span class="now_location">当前位置:</span>[达人榜]
			        <div class="clear"></div>
			    </div>
			    <div class=" margin-bottom-5 mt10">
					<!--  条件检索区 -->
					<span class="margin-left-10" style="font-size: 15px;">
						达人姓名
						<input type="text" id="expertName" name="expertName" value="${expertName}" style="width:150px;padding:5px;" />
					</span>
					<span class="margin-left-10" style="font-size: 15px;">
						达人类别
						<select id="topType" name="topType" style="height:25px;">
							<option value="-1">全部</option>
							<c:forEach var="type" items="${topTtitle}">
								<option value="${type.id }" <c:if test="${topType ==type.id}">selected="selected"</c:if>>${type.typeValue}</option>
							</c:forEach>
						</select>
					</span>
					<span class="margin-left-10" style="font-size: 15px;">
						达人状态
						<select id="deleteFlag" name="deleteFlag" style="height:25px;">
							<option value="-1"></option>
							<option value="0" <c:if test="${deleteFlag == 0}">selected="selected"</c:if>>正常</option>
							<option value="1" <c:if test="${deleteFlag == 1}">selected="selected"</c:if>>禁用</option>
						</select>
					</span>
			   		<span style="float:right;">
			   			<input onclick="clean()" type="button" value="重置" class="btn btn-info" style="width:100px;margin-right:8px;" />
			   			<input onclick="query()" type="button" value="查询" class="btn btn-info" style="width:100px;margin-right:8px;" />
			   	 	</span>
			   	 	<div style="clear:both"></div>			   		
			   </div>
			</div>

			<!-- 检索结果区域 -->	
			<div class="content-box" style="overflow:scroll;">
			  	<div class="content-box-header"></div>
			    <table class="table table-bordered table-striped table-hover">
		      	<tbody>
			        <tr align="center">
			       	 	<td nowrap="nowrap" width="40px"><input type="checkbox" id="isSelectAll"/></td>
						<!--  检索结果表格题头 -->
						<td nowrap="nowrap" width="80px"><strong>达人头像</strong></td>
						<td nowrap="nowrap" width="100px"><strong>达人姓名</strong></td>
						<td nowrap="nowrap" width="150px"><strong>达人昵称</strong></td>
						<td nowrap="nowrap" width="120px"><strong>达人类别</strong></td>
						<td nowrap="nowrap" width="120px"><strong>微信号</strong></td>
						<td nowrap="nowrap" width="50px"><strong>经验</strong></td>
						<td nowrap="nowrap" width="120px"><strong>联系方式</strong></td>
						<td nowrap="nowrap" width="220px"><strong>达人简介</strong></td>
						<td nowrap="nowrap" width="110px"><strong>创建时间</strong></td>
	       			</tr>
		       		<c:forEach var="o" items="${expertlist}" varStatus="s">					
					<tr align="center">
						<td><input type="checkbox" name="id" value="${o.id}"/></td>
						<!--  检索结果表格内容 -->
						<td><img src="${o.expertPortraitUrl }" style="width:30px;height:30px;"/> </td>
						<td>${o.expertName  }</td>
						<td>${o.userId }</td>
						<td>${o.topTitle }</td>
						<td>${o.expertWechatNumber }</td>
						<td>
							${o.expertExperience }<c:if test="${not empty o.expertExperience}">年</c:if>
						</td>
						<td>${o.expertTelNumber }</td>
						<td>${o.expertDiscriptionShort}</td>
						<td><fmt:formatDate value="${o.insertTime }" pattern="yyyy-MM-dd" type="BOTH" dateStyle="long" /></td>
					</tr>					
					</c:forEach>
		     	  </tbody>
			   </table>
			   
			   <!-- 功能按钮区域 -->
			   <div class=" margin-left-20">
			   		<span style="font-size:14px;">操作:</span>
			   		<span class=" margin-left-10">			   	
				   		<input onclick="addDataPage();"     type="button" value="增加" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input onclick="deleteData();"      type="button" value="删除" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input onclick="updateDataPage()"   type="button" value="修改" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input type="hidden" id= "backPageNo" name="backPageNo" />
				   	</span>
			   	</div>
			   	
			</div>
			<!-- 给修改用的 -->
			<input type="hidden" id= "主键ID" name="主键ID" />
		</form>
		
		<div class="pkp">
            <div id="kkpager"></div>
    	</div>
	</div>
</div>
</body>
</html>
