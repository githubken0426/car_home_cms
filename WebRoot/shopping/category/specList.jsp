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
	<title>分类规格列表</title>
	<link rel="stylesheet" href="<%=path%>/css/pubmain.css" />
	<link href="<%=request.getContextPath() %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" href="<%=path%>/js/cms/kkpager/kkpager_blue.css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/cms/kkpager/kkpager.js"></script>
	<script type="text/javascript" src="<%=path%>/js/cms/view_image/view_image.js"></script>
	<script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
	    		
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
		var categoryId = $.trim($("#searchCategory").val());
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
  			hrefFormer : '${pageContext.request.contextPath}/spec_list',//链接前部
  			hrefLatter : '.action',		 //链接尾部
  			getLink : function(n){
  				return this.hrefFormer + this.hrefLatter + "?pno="+ n +"&categoryId="+categoryId;
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
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/spec_list.action");
	  	$("#totalForm").submit();
  	}
  	//重置
  	function clean(){
		$("#title").attr("value","");
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
			    	<span class="now_location">当前位置:</span>[商品分类]
			        <div class="clear"></div>
			    </div>
			    <div class=" margin-bottom-5 mt10">
					<!--  条件检索区 -->
					<span  style="font-size: 15px;margin-left:25px;">
						标题 
						<select id="searchCategory" name="searchCategory" style="height:25px;width:120px;">
							<option value="-1">全部</option>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id }" <c:if test='${searchCategory ==category.id}'>selected='selected'</c:if>>${category.title}</option>
							</c:forEach>
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
			       	 	<td nowrap="nowrap" width="5%"><input type="checkbox" id="isSelectAll"/></td>
						<!--  检索结果表格题头 -->
						<td nowrap="nowrap" width="10%"><strong>所属分类</strong></td>
						<td nowrap="nowrap" width="10%"><strong>规格名称</strong></td>
						<td nowrap="nowrap" width="75%"><strong>规格选项</strong></td>
	       			</tr>
		       		<c:forEach var="o" items="${specList}" varStatus="s">					
					<tr align="center">
						<td><input type="checkbox" name="id" value="${o.id}"/></td>
						<!--  检索结果表格内容 -->
						<td>${o.title }</td>
						<td>${o.name }</td>
						<td>
							<c:forEach var="item" items="${o.items}" varStatus="index">
								<span>[${item.item}]</span>
							</c:forEach>
						</td>
					</tr>					
					</c:forEach>
		     	  </tbody>
			   </table>
			   <!-- 功能按钮区域 -->
			   <div class=" margin-left-20">
			   		<span style="font-size:14px;">操作:</span>
			   		<span class=" margin-left-10">			   	
				   		<input onclick="addDataPage();" type="button" value="增加" 
				   			class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   			<input onclick="updateDataPage();" type="button" value="修改" 
				   			class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input onclick="deleteData();"  type="button" value="删除" 
				   			class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   	</span>
			   	</div>
			</div>
		</form>
		<div class="pkp">
            <div id="kkpager"></div>
    	</div>
	</div>
</div>
<!-- 新增区域 -->
	<div id="addDiv" style="display: none">
		<form id="addForm" method="post" action="<%=basePath%>/category_add.action" enctype="multipart/form-data">
			<table id="addTable" class="table table-condensed">
				<tr>
					<td width="15%" align="center">选择分类</td>
					<td width="30%">
						<select id="categoryId" name="entity.categoryId" style="height:30px;width:180px;">
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id }" >${category.title}</option>
							</c:forEach>
						</select>
					</td>
					<td width="15%" align="center">规格名称</td>
					<td width="40%">
						<input type="text" id="addName" name="entity.name" tabindex="1" maxlength="100" 
							style="font-size:14px;padding:8px;width:180px;"/>
					</td>
				</tr>
				<tr>
					<td align="center">规格选项</td>
					<td>
						<input type="text" name="items" tabindex="1" maxlength="100" 
							style="font-size:14px;padding:8px;width:163px;"/>
					</td>
					<td align="center">
						<a onclick="appendItem('addTable','')" href="javascript:void(0);">追加选项</a>
					</td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
	<!-- 修改区域 -->
	<div id="updateDiv" style="display: none">
		<form id="updateForm" method="post" action="<%=basePath%>/category_update.action" enctype="multipart/form-data">
			<table id="updateTable" class="table table-condensed">
				<tr>
					<td width="15%" align="center">选择分类</td>
					<td width="30%">
						<select id="updateCategory" name="entity.categoryId" style="height:30px;width:180px;">
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id }" >${category.title}</option>
							</c:forEach>
						</select>
					</td>
					<td width="15%" align="center">规格名称</td>
					<td width="40%">
						<input type="text" id="updateName" name="entity.name" tabindex="1" maxlength="100" 
							style="font-size:14px;padding:8px;width:180px;"/>
					</td>
				</tr>
				<tr>
					<td align="center">规格选项</td>
					<td>
						<input id="firstName" type="text" name="items" tabindex="1" maxlength="100" 
							style="font-size:14px;padding:8px;width:163px;"/>
					</td>
					<td align="center">
						<a onclick="appendItem('updateTable','')" href="javascript:void(0);">追加选项</a>
					</td>
					<td></td>
				</tr>
			</table>
		</form>
	</div>
<script type="text/javascript">
function addDataPage(){
	layer.open({
		title: '<i class="icon-location-pin"></i>当前位置 / <strong>新增分类</strong>',
		type : 1,
		area: ['40rem', '20rem'],
		btn: ["<i class='fa fa-dot-circle-o'></i> 确定","<i class='fa fa-ban'></i> 返回"],
		closeBtn: 1,
		content : $("#addDiv"),
		yes: function(index, layero){
			if(!$.trim($("#addTitle").val())){
				layer.tips('请输入分类标题！','#addTitle');
				return ;
			}
			$("#addForm").submit();
			layer.close(index);
		}
	});
}

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
	}
	var id=checkboxs[idx].value;
	$.ajax({
        type:"POST",  
        dataType:"json",  
        data:{id:id},
        async:true,
        url: "${pageContext.request.contextPath}/spec_selectByPrimaryKey.action",  
        success: function (data) {
        	var obj = eval(data);
        	var name=obj.name;
        	$("#updateName").val(name);
        	var category=obj.categoryId;
        	$("#updateCategory option").each(function(){
        		if($(this).val()==category)
        			$(this).attr("selected","selected");
        	});
        	var items=eval(obj.items);
        	for(var i=0;i<items.length;i++){
        		var item=items[i].item;
        		if(i==0){
        			$("#firstName").val(item);
        			continue;
        		}
        		appendItem('updateTable',item);
        	}
        } 
    });
	layer.open({
		title: '<i class="icon-location-pin"></i>当前位置 / <strong>修改分类</strong>',
		type : 1,
		area: ['40rem', '20rem'],
		btn: ["<i class='fa fa-dot-circle-o'></i> 确定","<i class='fa fa-ban'></i> 返回"],
		closeBtn: 1,
		content : $("#updateDiv"),
		yes: function(index, layero){
			if(!$.trim($("#updateName").val())){
				layer.tips('请输入规格名称！','#updateName');
				return ;
			}
			$("#updateForm").submit();
			layer.close(index);
		}
	});
}

	//批量修改状态
	function deleteData(){
		var checkboxs=$("input:checkbox[name=id]");
		if(checkboxs.is(":checked")){
			if(confirm("删除所选数据后,商品将无法关联此分类！确定要删除吗?")){
				$("#totalForm").attr("action","${pageContext.request.contextPath}/category_deleteBatch.action");
		  		$("#totalForm").submit();
			}
		}else{
			alert("请选择要删除的数据！");
		}
	}
	
function appendItem(item,val){
	var tr = "<tr><td></td>";
		tr += "<td>";
		tr += "<input type='text' name='items'style='font-size:14px;padding:8px;width:163px;'value='"+val+"'/>";
		tr += "</td>";
		tr += "<td align='center'><img onclick='deleteItem(this)' src='<%=path%>/img/cms/del.png' style='cursor:pointer;'/></td>";
		tr += "<td></td>";
		tr += "</tr>";
	$('#'+item).append(tr);
}
//删除tr
function deleteItem(item){
	var tr=item.parentNode.parentNode;
	tr.parentNode.removeChild(tr); 
}
</script>
</body>
</html>
