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
	<title>问题墙</title>
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
		var content = $.trim($("#content").val());
			content=encodeURI(encodeURI(content));
		var beginTime = $.trim($("#beginTime").val());
		var endTime = $.trim($("#endTime").val());
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
  			hrefFormer : '${pageContext.request.contextPath}/question_listData',//链接前部
  			hrefLatter : '.action',		 //链接尾部
  			getLink : function(n){
  				return this.hrefFormer + this.hrefLatter + "?pno="+n +"&deleteFlag="+deleteFlag
  				+"&content="+content+"&beginTime="+beginTime +"&endTime="+endTime;
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
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/question_listData.action");
	  	$("#totalForm").submit();
  	}
	
  	//重置
  	function clean(){
		$("#content").attr("value","");
		$("#beginTime").attr("value","");
		$("#endTime").attr("value","");
		$("#deleteFlag").attr("value","-1");
  	}
  	
  	//添加
  	function addDataPage(){  		
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/question_addDataPage.action");
	  	$("#totalForm").submit();
  	}

	//删除	
  	function deleteData(){  		
		var checkboxs=$("input:checkbox[name=id]");
		if(checkboxs.is(":checked")){
			if(confirm("删除问题后,回复也将被删除。确定删除吗?")){
				$("#totalForm").attr("action","${pageContext.request.contextPath}/question_deleteData.action");
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
			$("#id").val(checkboxs[idx].value);			 
			$("#totalForm").attr("action","${pageContext.request.contextPath}/question_updateDataPage.action");
			$("#totalForm").submit();	
  		}
  	}
  	
  	//批量修改状态
  	function changeStatus(status){
  		var checkboxs=$("input:checkbox[name=id]");
  		if(checkboxs.is(":checked")){
  			$("#userStatus").val(status);
	  		$("#totalForm").attr("action","${pageContext.request.contextPath}/question_updateDataBatch.action");
	  		$("#totalForm").submit();
  		}else{
  			alert("请选择修改项！");
  		}
  	}
  	function replyDetail(id){
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/reply_questionDetail.action?id="+id);
  		$("#totalForm").submit();
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
			    	<span class="now_location">当前位置:</span>[问题墙]
			        <div class="clear"></div>
			    </div>
			    <div class=" margin-bottom-5 mt10">
					<!--  条件检索区 -->
					<span class="margin-left-10" style="font-size: 15px;">
						标题名
						<input type="text" id="content" name="content" value="${content}" style="width:150px;padding:5px;" />
					</span>
					<span class="margin-left-10" style="font-size: 15px;">
						状态
						<select id="deleteFlag" name="deleteFlag" style="height:25px;">
							<option value="-1"></option>
							<option value="0" <c:if test="${deleteFlag == 0}">selected="selected"</c:if>>正常</option>
							<option value="1" <c:if test="${deleteFlag == 1}">selected="selected"</c:if>>禁用</option>
						</select>
					</span>
					<span class="cxinp" style="margin-left:10px;">
						发表时间
						<input type="text" id="beginTime" name="beginTime" placeholder="开始日期" value="${beginTime}"class="laydate-icon" style="width:150px;padding:3px;" />
					 	至
						<input type="text" id="endTime" name="endTime" placeholder="结束日期" value="${endTime}"class="laydate-icon" style="width:150px;margin:0px 10px 0px 2px;padding:3px;" />
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
			       	 	<td nowrap="nowrap" width="40px">
			       	 		<input type="checkbox" id="isSelectAll"/>
			       	 	</td>
						<td nowrap="nowrap" width="80px"><strong>账号</strong></td>
						<td nowrap="nowrap" width="120px"><strong>昵称</strong></td>
						<td nowrap="nowrap" width="220px"><strong>问题标题</strong></td>
						<td nowrap="nowrap" width="120px"><strong>回复数量</strong></td>
						<td nowrap="nowrap" width="80px"><strong>状态</strong></td>
						<td nowrap="nowrap" width="120px"><strong>提问时间</strong></td>
						<td nowrap="nowrap" width="120px"><strong>回复详情</strong></td>
	       			</tr>
		       		
		       		<c:forEach var="o" items="${QuestionArticleList}" varStatus="s">					
					<tr align="center">
						<td><input type="checkbox" name="id" value="${o.id}"/></td>
						<!--  检索结果表格内容 -->
						<td>${o.loginPhone }</td>
						<td>${o.nickName }</td>
						<td>${o.content }</td>
						<td>${o.replyNum}</td>
						<td>
							<c:choose>
								<c:when test="${o.deleteFlag==0 }">正常</c:when>
								<c:otherwise><span style="color:gray;">禁用</span></c:otherwise>
							</c:choose>
						</td>
						<td><fmt:formatDate value="${o.insertTime}" type="both" pattern="yyyy-MM-dd" dateStyle="long"/> </td>
						<td>
							<a onclick="replyDetail('${o.id}')" href="javascript:void(0);" style="cursor: pointer;">查看回复详情</a>
						</td>
					</tr>					
					</c:forEach>
		     	  </tbody>
			   </table>
			   
			   <!-- 功能按钮区域 -->
			   <div class=" margin-left-20">
			   		<span style="font-size:14px;">操作:</span>
			   		<span class=" margin-left-10">			   	
				   		<%--<input onclick="addDataPage();"type="button" value="增加" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;"/>--%>
				   		<input onclick="deleteData();" type="button" value="删除" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;"/>
				   		<%--<input onclick="updateDataPage()"type="button" value="修改" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;"/>
				   	--%></span>
			   	</div>
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
