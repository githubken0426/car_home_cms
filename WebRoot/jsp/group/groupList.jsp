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
	<title>用户组信息列表</title>
	<link rel="stylesheet" href="<%=path%>/css/pubmain.css" />
	<link href="<%=request.getContextPath() %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="<%=path%>/js/cms/kkpager/kkpager_blue.css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/cms/kkpager/kkpager.js"></script>
   	
  <script type="text/javascript">
  //删除用户
  function deleteGroup(groupId){
  	if(confirm("确定要删除此用户组吗？")){
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/group_deleteGroup.action?id="+groupId);
  		$("#totalForm").submit();
  	}
  }
  
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
  		if(!pageNo){
  			pageNo = 1;
  		}
  		//初始化分页控件
  		kkpager.init({
  			pno : pageNo,
  			total : totalPage,			 //总页码
  			totalRecords : totalRecords, //总数据条数
  			hrefFormer : '${pageContext.request.contextPath}/group_queryAllGroup',//链接前部
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
  	});
  	//添加页面
  	function addPage(){
  		window.location.href="${pageContext.request.contextPath}/group_addPage.action";
  	}
  	
  	//修改页面
  	function updatePage(groupId){
  		window.location.href="${pageContext.request.contextPath}/group_updatePage.action?id="+groupId;
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
			<div class="content-box">
			    <div class="content-box-header">
			    	<span class="now_location">当前位置:</span> [用户组列表]
			        <div class="clear"></div>
			    </div>
			    <div class=" margin-bottom-5 mt10">
			   	<span class=" margin-left-10">
			   		<input onclick="addPage();" type="button" value="添加用户组" class="btn btn-info" style="width:80px;margin-right:8px;" />
			   	 </span>
			   </div>
			 </div>
		 <div class="content-box">
		   <div class="content-box-header"></div>
		   <table class="table table-bordered table-striped table-hover">
		     <tbody>
		       <tr align="center">
		         <td nowrap="nowrap" width="15%"><strong>组&nbsp;名</strong></td>
		         <td nowrap="nowrap" width="20%"><strong>备&nbsp;注</strong></td>
		         <td width="20%" nowrap="nowrap"><strong> 操&nbsp;作 </strong></td>
		       </tr>
		        <c:forEach var="group" items="${groupList}" varStatus="s">
						<tr align="center">
						   	<td>${group.groupName }</td>
						   	<td>${group.groupContent }</td>
						   	<td>
						   		<span style="width:40px;display:inline-block;">
							   		<a href="javascript:void(0)" title="修改" onclick="updatePage('${group.id}');return false;">修改</a>
						   		</span>
						   		<c:if test="${group.groupType!=-1 }">
						   		<span style="width:40px;display:inline-block;">
						   			<a href="javascript:void(0)" onclick="deleteGroup('${group.id}');return false;">删除</a>
						   		</span>
						   		</c:if>
					         </td>
						 </tr>
					</c:forEach>
			     </tbody>
			   </table>
			</div>
		</form>
		<div class="pkp">
            <div id="kkpager"></div>
    	</div>
	 </div>
	 </div>
  </body>
</html>
