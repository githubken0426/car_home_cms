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
	<title>品牌列表</title>
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
		var cnName = $.trim($("#cnName").val());
		cnName=encodeURI(encodeURI(cnName));
		var categoryId = $.trim($("#categoryId").val());
		
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
  			hrefFormer : '${pageContext.request.contextPath}/brand_list',//链接前部
  			hrefLatter : '.action',		 //链接尾部
  			getLink : function(n){
  				return this.hrefFormer + this.hrefLatter + "?pno="+ n +"&cnName="+cnName+"&categoryId="+categoryId ;
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
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/brand_list.action");
	  	$("#totalForm").submit();
  	}
  	//重置
  	function clean(){
		$("#categoryId").attr("value","-1");
		$("#cnName").attr("value","");
  	}
  	//添加
  	function addDataPage(){  		
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/brand_addDataPage.action");
	  	$("#totalForm").submit();
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
			$("#totalForm").attr("action","${pageContext.request.contextPath}/brand_updateDataPage.action");
			$("#totalForm").submit();	
  		}
  	}
  	
  	//批量修改状态
  	function deleteData(){
  		var checkboxs=$("input:checkbox[name=id]");
  		if(checkboxs.is(":checked")){
	  		$("#totalForm").attr("action","${pageContext.request.contextPath}/brand_deleteBatch.action");
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
			    	<span class="now_location">当前位置:</span>[品牌]
			        <div class="clear"></div>
			    </div>
			    <div class=" margin-bottom-5 mt10">
					<!--  条件检索区 -->
					<span class="margin-left-10" style="font-size: 15px;">
						所属分类
						<select id="categoryId" name="categoryId" style="height:25px;">
							<option value="-1">全部</option>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id }" <c:if test='${categoryId ==category.id}'>selected='selected'</c:if>>
									${category.title}
								</option>
							</c:forEach>
						</select>
					</span>
					<span style="font-size: 15px;margin-left:25px;">
						中文名称<input type="text" id="cnName" name="cnName" value="${cnName}" style="width:150px;padding:5px;" />
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
			       	 	<td nowrap="nowrap" width="30px"><input type="checkbox" id="isSelectAll"/></td>
						<!--  检索结果表格题头 -->
						<td nowrap="nowrap" width="50px"><strong>类别</strong></td>
						<td nowrap="nowrap" width="50px"><strong>中文名称</strong></td>
						<td nowrap="nowrap" width="50px"><strong>英文名称</strong></td>
						<td nowrap="nowrap" width="100px"><strong>logo</strong></td>
						<td nowrap="nowrap" width="10px"><strong>品牌介绍</strong></td>
						<td nowrap="nowrap" width="200px"><strong>产品图片</strong></td>
	       			</tr>
		       		<c:forEach var="o" items="${list}" varStatus="s">					
					<tr align="center">
						<td><input type="checkbox" name="id" value="${o.id}"/></td>
						<!--  检索结果表格内容 -->
						<td>${o.categoryName }</td>
						<td>${o.cnName }</td>
						<td>${o.enName }</td>
						<td>${o.logo }</td>
						<td>${o.descrption }</td>
						<td>${o.picture }</td>
					</tr>					
					</c:forEach>
		     	  </tbody>
			   </table>
			   <!-- 功能按钮区域 -->
			   <div class=" margin-left-20">
			   		<span style="font-size:14px;">操作:</span>
			   		<span class=" margin-left-10">			   	
				   		<input onclick="addDataPage();" type="button" value="增加" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input onclick="deleteData();"  type="button" value="删除" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input onclick="updateDataPage()" type="button" value="修改" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   	</span>
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
