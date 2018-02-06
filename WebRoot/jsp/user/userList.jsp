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
	<title>用户信息列表</title>
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
  		var groupId= $("#groupSelected").val();
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
  			hrefFormer : '${pageContext.request.contextPath}/user_queryAllUser',//链接前部
  			hrefLatter : '.action',		 //链接尾部
  			getLink : function(n){
  				return this.hrefFormer + this.hrefLatter + "?pno="+n+"&groupId="+groupId;
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
  				var checkboxs=$("input:checkbox[name=userId]");
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
  		//批量删除
  		$("#deleteBatch").bind({
  			click:function(){
  				var checkboxs=$("input:checkbox[name=userId]");
  				if(checkboxs.is(":checked")){
  					if(confirm("确定要删除所选中项吗?")){
  						$("#totalForm").attr("action","${pageContext.request.contextPath}/user_deleteUser.action");
  						$("#totalForm").submit();
  					}
  				}else{
  					alert("请选择删除项！");
  				}
  			}
  		});
  		//按照用户组查询用户
  		$("#groupSelected").bind({
  			change:function(){
  				$("#totalForm").attr("action","${pageContext.request.contextPath}/user_queryAllUser.action");
	  			$("#totalForm").submit();
  			}
  		});
  	});
  	//添加页面
  	function addUserPage(){
  		//window.location.href="user_addUserPage.action";
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/user_addUserPage.action");
	  	$("#totalForm").submit();
  	}
  	
  	//修改页面
  	function updateUser(userId){
  		//window.location.href="user_updateUserPage.action?id="+userId;
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/user_updateUserPage.action?id="+userId);
	  	$("#totalForm").submit();
  	}
  	
  	//批量修改状态
  	function changeStatus(status){
  		var checkboxs=$("input:checkbox[name=userId]");
  		if(checkboxs.is(":checked")){
  			$("#userStatus").val(status);
	  		$("#totalForm").attr("action","${pageContext.request.contextPath}/user_updateUserStatus.action");
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
			<div class="content-box">
			    <div class="content-box-header">
			    	<span class="now_location">当前位置:</span> [用户列表]
			        <div class="clear"></div>
			    </div>
			    <div class=" margin-bottom-5 mt10">
				    <span class=" margin-left-10">
				   		<input onclick="addUserPage();" type="button" value="添加用户" class="btn btn-info" style="width:80px;margin-right:8px;" />
				   	 </span>
			   		<span class=" margin-left-10" style="font-size: 15px;">用户组: </span>
			   	 	<select class="margin-left-5" id="groupSelected" name="groupId" style="height:25px;">
			   			<option value="-1">全部用户</option>
			   			<c:forEach items="${groupList}" var="group">
			   				<option value="${group.id }" <c:if test="${groupId ==group.id}">selected="selected"</c:if>>${group.groupName }</option>
			   			</c:forEach>
			   		</select>
			   </div>
			 </div>
		<div class="content-box">
		  <div class="content-box-header"></div>
		   <table class="table table-bordered table-striped table-hover">
		     <tbody>
		       <tr align="center">
		       	 <td nowrap="nowrap" width="5%">
		       		<input type="checkbox" id="isSelectAll"/>
		       	 </td>
		         <td nowrap="nowrap" width="20%"><strong>姓名</strong></td>
		         <td nowrap="nowrap" width="10%"><strong>账&nbsp;号</strong></td>
		         <td nowrap="nowrap" width="10%"><strong>用户组</strong></td>
		         <td nowrap="nowrap" width="11%"><strong>登陆时间</strong></td>
		         <td nowrap="nowrap" width="11%"><strong>登陆IP</strong></td>
		         <td nowrap="nowrap" width="8%"><strong>登陆次数</strong></td>
		         <td nowrap="nowrap" width="10%"><strong>状&nbsp;态</strong></td>
		         <td width="15%" nowrap="nowrap"><strong> 操&nbsp;作 </strong></td>
		       </tr>
		        <c:forEach var="user" items="${userList}" varStatus="s">
					<c:if test="${user.id !=login_user.id}">
						<tr align="center">
							<td><input type="checkbox" name="userId" value="${user.id}"/></td>
						   	<td>${user.realName }</td>
						   	<td>${user.userName }</td>
						   	<td>${user.groupName }</td>
						   	<td><fmt:formatDate value="${user.loginTime}" pattern="yyyy-MM-dd HH:mm"/></td>
						   	<td>${user.loginIp }</td>
						   	<td>${user.loginNum }</td>
						   	<td><c:choose>
						   			<c:when test="${user.status==0 }">
						   				<img src="<%=basePath%>img/cms/status_1.gif"/>
						   			</c:when>
						   			<c:otherwise>
						   				<img src="<%=basePath%>img/cms/status_0.gif"/>
						   			</c:otherwise>
						   		</c:choose>
						   	</td>
						   	<td>
						   		<span style="width:40px;display:inline-block;">
							   		<a href="javascript:void(0)" title="修改" onclick="updateUser('${user.id}');return false;">修改</a>
						   		</span>
					         </td>
						 </tr>
					  </c:if>
					</c:forEach>
			     </tbody>
			   </table>
			   <div class=" margin-left-20">
			   	<span style="font-size:14px;">操作:</span>
			   	<span class=" margin-left-10">
			   		<input id="deleteBatch" type="button" value="删除" class="btn btn-info" style="padding:0px 4px;margin-right:8px;" />
			   		<input onclick="changeStatus(0)" type="button" value="启用" class="btn btn-info" style="padding:0px 4px;margin-right:8px;" />
			   		<input onclick="changeStatus(1)" type="button" value="禁用" class="btn btn-info" style="padding:0px 4px;margin-right:8px;" />
			   		<input type="hidden" id= "userStatus" name="userStatus" />
			   		<input type="hidden" id= "backPageNo" name="backPageNo" />
			   	 </span>
			   </div>
			</div>
		</form>
		<div class="pkp">
            <div id="kkpager"></div>
    	</div>
	 </div>
	 </div>
  </body>
</html>
