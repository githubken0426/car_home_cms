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
   	<script type="text/javascript" src="<%=path %>/js/cms/laydate/laydate.js"></script>
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
  		var loginPhone = $.trim($("#loginPhone").val());
		var deleteFlag = $.trim($("#deleteFlag").val());
  		var beginTime = $.trim($("#beginTime").val());
		var endTime = $.trim($("#endTime").val());
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
  			hrefFormer : '${pageContext.request.contextPath}/apiUser_listData',//链接前部
  			hrefLatter : '.action',		 //链接尾部
  			getLink : function(n){
  				return this.hrefFormer + this.hrefLatter + "?pno="+n+"&loginPhone="+loginPhone
  						+"&deleteFlag="+deleteFlag+"&beginTime="+beginTime+"&endTime="+endTime;
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
  	});
	//按条件查询
	function query(){
		$("#totalForm").attr("action","${pageContext.request.contextPath}/apiUser_listData.action");
	  	$("#totalForm").submit();
	}
	//重置
	function clean(){
		$("#deleteFlag").attr("value","-1");
		$("#loginPhone").attr("value","");
		$("#beginTime").attr("value","");
		$("#endTime").attr("value","");
	}
	
  	//添加页面
  	function addDataPage(){
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/apiUser_addDataPage.action");
	  	$("#totalForm").submit();
  	}
  	
  	//修改页面
  	function updateDataPage(){
  		var checkboxs=$("input:checkbox[name=userId]");
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
			$("#totalForm").attr("action","${pageContext.request.contextPath}/apiUser_updateDataPage.action");
			$("#totalForm").submit();	
  		}
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
			    	<span class="margin-left-10" style="font-size: 15px;">
						账号
						<input type="text" id="loginPhone" name="loginPhone" value="${loginPhone}" style="width:150px;padding:5px;" />
					</span>
			   		<span class="margin-left-10" style="font-size: 15px;">
						用户状态
					<select id="deleteFlag" name="deleteFlag" style="height:25px;">
						<option value="-1"></option>
						<option value="0" <c:if test="${deleteFlag == 0}">selected="selected"</c:if>>正常</option>
						<option value="1" <c:if test="${deleteFlag == 1}">selected="selected"</c:if>>禁用</option>
					</select>
					</span>
					<span class="cxinp" style="margin-left:10px;">
					注册时间
					<input type="text" id="beginTime" name="beginTime" placeholder="开始日期" 
						value="${beginTime}"class="laydate-icon" style="width:150px;padding:3px;" />
					 至
					<input type="text" id="endTime" name="endTime" placeholder="结束日期" 
						value="${endTime}"class="laydate-icon" style="width:150px;margin:0px 10px 0px 2px;padding:3px;" />
			   		</span>
			   		
			   		<span style="float:right;">
			   			<input onclick="clean()" type="button" value="重置" class="btn btn-info" style="width:100px;margin-right:8px;" />
			   			<input onclick="query()" type="button" value="查询" class="btn btn-info" style="width:100px;margin-right:8px;" />
			   	 	</span>
			   	 	<div style="clear:both"></div>	
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
		       	 <td nowrap="nowrap" width="10%"><strong>用户头像</strong></td>
		         <td nowrap="nowrap" width="15%"><strong>账&nbsp;号</strong></td>
		         <td nowrap="nowrap" width="10%"><strong>真实姓名</strong></td>
		         <td nowrap="nowrap" width="15%"><strong>用户昵称</strong></td>
		         <td nowrap="nowrap" width="10%"><strong>性别</strong></td>
		         <td nowrap="nowrap" width="10%"><strong>用户类型</strong></td>
		         <td nowrap="nowrap" width="10%"><strong>状&nbsp;态</strong></td>
		         <td width="15%" nowrap="nowrap"><strong>注册时间</strong></td>
		       </tr>
		        <c:forEach var="user" items="${userList}" varStatus="s">
						<tr align="center">
							<td><input type="checkbox" name="userId" value="${user.userId}"/></td>
							<td>
								<c:if test="${!empty user.avatarUrl}">
									<img src="${ftpServerIp}${user.avatarUrl }" style="width:30px;height:30px;"/> 
								</c:if>
							</td>
							<td>${user.loginPhone }</td>
						   	<td>${user.realName }</td>
						   	<td>${user.nickname }</td>
						   	<td>
						   		<c:choose>
						   			<c:when test="${user.sex==0 }">男</c:when>
						   			<c:when test="${user.sex==1 }">女</c:when>
						   			<c:otherwise></c:otherwise>
						   		</c:choose>
						   	</td>
						   	<td>
						   		<c:choose>
						   			<c:when test="${user.isExpert==1 }"><font color="red">达人</font></c:when>
						   			<c:otherwise>普通</c:otherwise>
						   		</c:choose>
						   	</td>
						   	<td>
						   		<c:choose>
						   			<c:when test="${user.deleteFlag==0 }">正常</c:when>
						   			<c:otherwise>禁用</c:otherwise>
						   		</c:choose>
						   	</td>
						   	<td><fmt:formatDate value="${user.insertTime}" pattern="yyyy-MM-dd" type="both" dateStyle="long"/> </td>
						 </tr>
					</c:forEach>
			     </tbody>
			   </table>
			   <%--<div class=" margin-left-20">
			   	<span style="font-size:14px;">操作:</span>
			   	<span class=" margin-left-10">
			   		<input onclick="addDataPage();"type="button" value="增加" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   	<input onclick="deleteData();" type="button" value="删除" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   	<input onclick="updateDataPage()"type="button" value="修改" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
			   	 </span>
			   </div>
			--%>
			</div>
		</form>
		<div class="pkp">
            <div id="kkpager"></div>
    	</div>
	 </div>
	 </div>
	 
<script type="text/javascript">
  !function(){
	laydate.skin('molv');
		laydate({
			elem : '#beginTime',
			istoday : true,
			format : 'YYYY-MM-DD',
			max : laydate.now()
		});
		laydate({
			elem : '#endTime',
			istime: true,
			istoday : false,
			format : 'YYYY-MM-DD'
		});
	}();
</script>
  </body>
</html>
