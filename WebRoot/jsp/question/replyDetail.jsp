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
	<title>回复详情</title>
	<link rel="stylesheet" href="<%=path %>/css/pubmain.css" />
	<link href="<%=request.getContextPath() %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/cms/view_image/view_image.js"></script>   	
<script type="text/javascript">
	
   	// 返回
  	function turnBack(){
  		$("#backForm").submit();
  	}
  	function updateStatus(id,name){
  	  	if(confirm("此项回复的展示将会受到影响，确定保存吗？")){
	  		var val=$("input[name="+name+"]:checked").val();
	  		$.ajax({
				type:"post",
				data:{id:id,deleteFlag:val},
				async:true,
				dataType:"json",
				url:"${pageContext.request.contextPath}/reply_updateReplyStatus.action",
				success:function(data){
					if(data==1)
						alert("修改成功！");
					else
						alert("修改失败！");
				}
	  	  	});
  	  	}
  	}
</script>
<style type="">
.viewImags{width:120px;float:left;}
</style>
</head>

<body>
<div id="middle">
	<div class="right"  id="mainFrame">
		<form action="${pageContext.request.contextPath}/question_updateData.action" enctype="multipart/form-data" method="post" id="updateForm">
			<div class="content-box">
			<!-- 问题区 -->
				<div class="content-box-header">
					<span class="now_location">当前位置:</span>问题详情
				    <div class="clear"></div>
			    </div>
			    <div style="margin:0 auto; margin:10px;">
	            	<table class="table table-bordered" >
						<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">问题描述：</td>
							<td width="85%">
								<div style=" margin:10px 0px 0px 30px;white-space:normal;">
									${question.content }
								</div>
								<p style="margin-left:300px;font-size:10px;color:gray;">
										[${question.nickName} | 
										<fmt:formatDate value="${question.insertTime}" type="both" pattern="yyyy-MM-dd HH:mm" dateStyle="long"/>]
								</p>
								<div style="margin:10px 0px 10px 30px;">
									<div class="viewImags">
										<c:if test="${not empty question.displayList[0]}">
											<img src="${question.displayList[0] }"style="width:100px;height:100px;"/> 
										</c:if>
									</div>
									<div class="viewImags">
										<c:if test="${not empty question.displayList[1]}">
											<img src="${question.displayList[1] }"style="width:100px;height:100px;"/> 
										</c:if>
									</div>
									<div class="viewImags">
										<c:if test="${not empty question.displayList[2]}">
											<img src="${question.displayList[2] }"style="width:100px;height:100px;"/> 
										</c:if>
									</div>
									<div class="viewImags">
										<c:if test="${not empty question.displayList[3]}">
											<img src="${question.displayList[3] }"style="width:100px;height:100px;"/> 
										</c:if>
									</div>
									<div class="viewImags">
										<c:if test="${not empty question.displayList[4]}">
											<img src="${question.displayList[4] }"style="width:100px;height:100px;"/> 
										</c:if>
									</div>
									<div class="viewImags">
										<c:if test="${not empty question.displayList[5]}">
											<img src="${question.displayList[5] }"style="width:100px;height:100px;"/> 
										</c:if>
									</div>
								</div>
							</td>
						</tr>
	            	</table>
	         	</div>	
	         	
	         	<!-- 回复区 -->
				<div class="content-box-header">
					<span class="now_location">当前位置:</span>回复详情
				    <div class="clear"></div>
			    </div>
			    <div style="margin:0 auto; margin:10px;">
	            	<table class="table table-bordered" >
	            	<c:if test="${empty replyList}">
	            		<tr><td colspan="4" align="center" >没有回复</td></tr>
	            	</c:if>
	            		<c:forEach var="reply" items="${replyList}" varStatus="index">
	            		<tr>
							<td width="15%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">${reply.userId }：</td>
							<td width="40%">
								<div style="margin-left:30px;">
									<p style="margin:0px;white-space:normal;">${reply.content }</p>
									<div style="float:right;font-size:10px;color:gray;">
										[${reply.userId} 回复 ${reply.toUserId} |
										<fmt:formatDate value="${reply.insertTime}" type="both" pattern="yyyy-MM-dd HH:mm" dateStyle="long"/>]
									</div>
								</div>
							</td>
							<td colspan="2" style=" border-left:0px;">
								<c:choose>
									<c:when test="${reply.deleteFlag==0}">
										 <label style="margin-left: 35px; display: inline;">
												<input type="radio" name="flag${index.index }" checked="checked"  value="0" style="margin-top: 0px;" />
												<span style="margin-left: 10px;">启&nbsp;用</span>
											</label>
											<label style="margin-left: 25px; display: inline;">
												<input type="radio" name="flag${index.index }" value="1"/>
												<span style="margin-left: 10px;">禁&nbsp;用</span>
											</label>
										</c:when>
										<c:otherwise>
											<label style="margin-left: 35px; display: inline;">
												<input type="radio" name="flag${index.index }"  value="0" />
												<span style="margin-left: 10px;">启&nbsp;用</span>
											</label>
											<label style="margin-left: 25px; display: inline;">
												<input type="radio" name="flag${index.index }" checked="checked" value="1" />
												<span style="margin-left: 10px;">禁&nbsp;用</span>
											</label>
										</c:otherwise>
								</c:choose>
								<input type="button" value="保存" class="btn btn-info " style="width:80px;margin-left:50px;" 
										onclick="updateStatus('${reply.id}','flag${index.index }')" />
							</td>
						</tr>
	            		</c:forEach>
	            	</table>
	         	</div>	
	         	<table class="margin-bottom-20 table  no-border" style="margin-top:20px;">
			        <tr>
			     	    <td align="center">
			     	      <input type="button" value="返回" class="btn btn-info " style="width:80px;"  onclick="turnBack()"/>
			     	    </td>
			        </tr>
			     </table>         	
			</div>
		</form>

		<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/question_and_articleAction!listData.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="content" value="${content}" />
			<input type="hidden" name="beginTime" value="${beginTime}" />
			<input type="hidden" name="endTime" value="${endTime}" />
			<input type="hidden" name="deleteFlag" value="${deleteFlag}" />
		</form>
	</div>
</div>
</body>
</html>
