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
	<title>店铺表</title>
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
		/* var shopName = $.trim($("#shopName").val());
		shopName = encodeURI(encodeURI(shopName)); */
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
  			hrefFormer : '${pageContext.request.contextPath}/shop!listData',//链接前部
  			hrefLatter : '.action',		 //链接尾部
  			getLink : function(n){
  				return this.hrefFormer + this.hrefLatter + "?pno="+n+"&shopName=${shopName}&type=${type}&deleteFlag=${deleteFlag}&province=${province}&city=${city}&district=${district}&detailAddress=${detailAddress}&telNumber=${telNumber}"; //+"&groupId="+groupId;
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
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/shop!listData.action");
	  	$("#totalForm").submit();
  	}
	
  	//重置
  	function clean(){

		// 清空条件检索区的参数

		$("#shopName").attr("value","");
		$("#detailAddress").attr("value","");
		$("#telNumber").attr("value","");
		$("#type").attr("value","");
		$("#deleteFlag").attr("value","");
  	}
  	
  	//添加
  	function addDataPage(){  		
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/shop!addDataPage.action");
	  	$("#totalForm").submit();
  	}

	//删除	
  	function deleteData(){  		
		var checkboxs=$("input:checkbox[name=id]");
		if(checkboxs.is(":checked")){
			if(confirm("确定要删除所选中项吗?")){
				$("#totalForm").attr("action","${pageContext.request.contextPath}/shop!deleteData.action");
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
			$("#totalForm").attr("action","${pageContext.request.contextPath}/shop_updateDataPage.action");
			$("#totalForm").submit();	
  		}
  	}
  	
  	//批量修改状态
  	function changeStatus(status){
  		var checkboxs=$("input:checkbox[name=id]");
  		if(checkboxs.is(":checked")){
  			$("#userStatus").val(status);
	  		$("#totalForm").attr("action","${pageContext.request.contextPath}/shop!updateDataBatch.action");
	  		$("#totalForm").submit();
  		}else{
  			alert("请选择修改项！");
  		}
  	}
  	
  	// 样本下载
  	function exportall() {
		window.location.href = "${pageContext.request.contextPath}/shop!exportAll.action";
	 }
  	
  </script>
  <style type="">
  	.footer{margin-top:0px;}
  </style>
  </head>
  
<body>
<div id="middle">
	<div class="right"  id="mainFrame">
		<form action="" method="post" id="totalForm">

			<!-- 查询条件区域 -->
			<div class="content-box">
			    <div class="content-box-header">
			    	<span class="now_location">当前位置:</span>[店铺表]
			        <div class="clear"></div>
			    </div>
			    <div class=" margin-bottom-5 mt10">
			    
			    	

					<!--  条件检索区 -->

					<span style="font-size: 15px;margin-left:32px;">
						公司名字
						<input type="text" id="shopName" name="shopName" value="${shopName}"class="laydate-icon" style="width:160px;padding:5px;" />
					</span>
					<span class="margin-left-10" style="font-size: 15px;">
						公司电话
						<input type="text" id="telNumber" name="telNumber" value="${telNumber}"class="laydate-icon" style="width:165px;padding:5px;" />
					</span>
					<span class="margin-left-10" style="font-size: 15px;">
						店铺状态
						<select class="margin-left-5" id="deleteFlag" name="deleteFlag" style="height:25px;">
				   	 		<c:choose>
							   <c:when test="${deleteFlag == 0}">
							   		<option value=""></option>
							   		<option value="0" selected="selected">正常</option>
							   		<option value="1">禁用</option>
							   </c:when>
							   <c:when test="${deleteFlag == 1}">
							   		<option value=""></option>
							   		<option value="0">正常</option>
							   		<option value="1" selected="selected">禁用</option>
							   </c:when>
							   <c:otherwise>  
							     	<option value="" selected="selected"></option>
							   		<option value="0">正常</option>
							   		<option value="1">禁用</option>
							   </c:otherwise>
						   </c:choose>
				   		</select>
					</span>
					

					<%-- <span class="margin-left-10" style="font-size: 15px;">
						详细地址_省
						<input type="text" id="province" name="province" value="${province}"class="laydate-icon" style="width:150px;padding:5px;" />
					</span> --%>
					
					<!-- <span class=" margin-left-10" style="font-size: 15px;float:left;">文件导入:&nbsp;</span>
					<span style="float:left;"> -->
					<!-- <span class="margin-left-10" style="font-size: 15px;">
						文件导入
						<input name="uploadFile" id="uploadFile" type="file"/>
						<input type="submit" onclick="changeCheck()" value="上&nbsp;&nbsp;传" class="btn btn-info" style="width:90px;padding:0px 4px;margin-right:8px;" />
					</span> -->
					<!-- 清楚浮动 -->
			   	 	<div style="clear:both"></div>
			   		</br>
					<%-- <span class="margin-left-10" style="font-size: 15px;">
						详细地址_市
						<input type="text" id="city" name="city" value="${city}"class="laydate-icon" style="width:150px;padding:5px;" />
					</span> --%>

					<%-- <span class="margin-left-10" style="font-size: 15px;">
						详细地址_区
						<input type="text" id="district" name="district" value="${district}"class="laydate-icon" style="width:160px;padding:5px;" />
					</span> --%>

					<span style="font-size: 15px;margin-left:28px;">
						服务类型
						<select class="margin-left-5" id="type" name="type" style="height:25px;">
				   	 		<c:choose>
							   <c:when test="${type == 2000}">
							   		<option value=""></option>
							   		<option value="2000" selected="selected">救援服务</option>
							   		<option value="4100">洗车服务</option>
							   		<option value="5100">修车服务</option>
							   		<option value="6100">保养服务</option>
							   		<option value="7100">轮胎服务</option>
							   </c:when>
							   <c:when test="${type == 4100}">
							   		<option value=""></option>
							   		<option value="2000">救援服务</option>
							   		<option value="4100" selected="selected">洗车服务</option>
							   		<option value="5100">修车服务</option>
							   		<option value="6100">保养服务</option>
							   		<option value="7100">轮胎服务</option>
							   </c:when>
							   <c:when test="${type == 5100}">
							   		<option value=""></option>
							   		<option value="2000">救援服务</option>
							   		<option value="4100">洗车服务</option>
							   		<option value="5100" selected="selected">修车服务</option>
							   		<option value="6100">保养服务</option>
							   		<option value="7100">轮胎服务</option>
							   </c:when>
							   <c:when test="${type == 6100}">
							   		<option value=""></option>
							   		<option value="2000">救援服务</option>
							   		<option value="4100">洗车服务</option>
							   		<option value="5100">修车服务</option>
							   		<option value="6100" selected="selected">保养服务</option>
							   		<option value="7100">轮胎服务</option>
							   </c:when>
							   <c:when test="${type == 7100}">
							   		<option value=""></option>
							   		<option value="2000">救援服务</option>
							   		<option value="4100">洗车服务</option>
							   		<option value="5100">修车服务</option>
							   		<option value="6100">保养服务</option>
							   		<option value="7100" selected="selected">轮胎服务</option>
							   </c:when>
							   <c:otherwise>  
							     	<option value="" selected="selected"></option>
							   		<option value="2000">救援服务</option>
							   		<option value="4100">洗车服务</option>
							   		<option value="5100">修车服务</option>
							   		<option value="6100">保养服务</option>
							   		<option value="7100">轮胎服务</option>
							   </c:otherwise>
						   </c:choose>
				   		</select>
					</span>
					<span class="margin-left-10" style="font-size: 15px;">
						详细地址
						<input type="text" id="detailAddress" name="detailAddress" value="${detailAddress}"class="laydate-icon" style="width:500px;padding:5px;" />
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
			  	<div class="content-box-header" style="width:2464px"></div>
			    <table class="table table-bordered table-striped table-hover">
		      	<tbody>
			        <tr align="center">
			       	 	<td nowrap="nowrap" width="40px"><input type="checkbox" id="isSelectAll"/></td>
			       	 	<!-- 
						<td nowrap="nowrap" width="220px"><strong>id</strong></td>
						-->
						

						<!--  检索结果表格题头 -->

						<td nowrap="nowrap" width="100px"><strong>救援服务</strong></td>
						<td nowrap="nowrap" width="100px"><strong>洗车服务</strong></td>
						<td nowrap="nowrap" width="100px"><strong>修车服务</strong></td>
						<td nowrap="nowrap" width="100px"><strong>保养服务</strong></td>
						<td nowrap="nowrap" width="100px"><strong>轮胎服务</strong></td>
						<td nowrap="nowrap" width="70px"><strong>状态</strong></td>
						<td nowrap="nowrap" width="300px"><strong>公司名字</strong></td>
						<td nowrap="nowrap" width="100px"><strong>公司评分</strong></td>
						<td nowrap="nowrap" width="300px"><strong>公司描述</strong></td>
						<td nowrap="nowrap" width="120px"><strong>公司所在地经度</strong></td>
						<td nowrap="nowrap" width="120px"><strong>公司所在地纬度</strong></td>
						<td nowrap="nowrap" width="100px"><strong>详细地址_省</strong></td>
						<td nowrap="nowrap" width="100px"><strong>详细地址_市</strong></td>
						<td nowrap="nowrap" width="300px"><strong>详细地址</strong></td>
						<td nowrap="nowrap" width="300px"><strong>公司电话列表</strong></td>
						<!-- <td nowrap="nowrap" width="220px"><strong>公司详情图片</strong></td> -->
<!-- 						<td nowrap="nowrap" width="220px"><strong>是否置顶</strong></td>
						<td nowrap="nowrap" width="220px"><strong>显示优先级</strong></td> -->

	       			</tr>
		       		
		       		<c:forEach var="o" items="${ShopList}" varStatus="s">					
					<tr align="center">
						<td><input type="checkbox" name="id" value="${o.id}"/></td>
						

						<!--  检索结果表格内容 -->

						<td>${o.rescueService }</td>
						<td>${o.cleanService }</td>
						<td>${o.repairService }</td>
						<td>${o.maintainService }</td>
						<td>${o.tyreService }</td>
						<td><c:choose>
								<c:when test="${o.deleteFlag==0 }">正常</c:when>
								<c:otherwise><span style="color:gray;">禁用</span></c:otherwise>
							</c:choose>
						</td>
						<td>${o.shopName }</td>
						<td>${o.shopScore }</td>
						<td>${o.shopDescription }</td>
						<td>${o.longitude }</td>
						<td>${o.latitude }</td>
						<td>${o.province }</td>
						<td>${o.city }</td>
						<td>${o.detailAddress }</td>
						<td>${o.telNumberList }</td>
						<%-- <td>${o.displayPicUrlList }</td>
						<td>${o.isTop }</td>
						<td>${o.displayPriority }</td> --%>

					</tr>					
					</c:forEach>
		     	  </tbody>
			   </table>
			   
			   <!-- 功能按钮区域 -->
			   <div style="display:inline;margin-left:30px;" id="div1">
			   		<span style="font-size:14px;">操作:</span>
			   		<span class=" margin-left-10">			   	
				   		<input onclick="addDataPage();"     type="button" value="增加" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input onclick="deleteData();"      type="button" value="删除" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input onclick="updateDataPage()"   type="button" value="修改" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input onclick="exportall()"   type="button" value="样本Excel下载" class="btn btn-info" style="width:100px;margin-right:8px;margin-bottom:8px;" />
					</span>
				</div>
				</div>
			   </form>
			   
			   <div style="margin-left:30px;" id="div2" >  	
				   	<iframe id="file_upload_return" width="0" height="0" name="file_upload_return"></iframe>
				   	 <form action="${pageContext.request.contextPath}/shop!doUpload.action" target="file_upload_return" method="post" enctype="multipart/form-data" id="totalForm">
				   	 	<span style="font-size: 15px;margin-left:20px;float:left;">文件导入:&nbsp;</span>
    					<span style="float:left;">
	    					<input name="uploadFile" id="uploadFile" type="file"/>
	    					<input type="submit" value="上&nbsp;&nbsp;传" class="btn btn-info" style="width:90px;padding:0px 4px;margin-right:8px;" />
    					</span>
				   	 </form>
			   	</div>
			   
		<div class="pkp">
            <div id="kkpager"></div>
    	</div>
	</div>
</div>
<script type="text/javascript">
$("#file_upload_return").load(function(){//获取iframe中的内容
	 var body = $(window.frames['file_upload_return'].document.body);
	 var data = body[0].innerHTML;

		 if (data == "108") {
			 alert("导入的Excel救援服务不能是空！"); 
		 } else if (data == "109") {
			 alert("导入的Excel修车服务不能是空！"); 
		 } else if (data == "110") {
			 alert("导入的Excel洗车服务不能是空！"); 
		 } else if (data == "111") {
			 alert("导入的Excel保养服务不能是空！"); 
		 } else if (data == "112") {
			 alert("导入的Excel轮胎服务不能是空！"); 
		 } else if (data == "113") {
			 alert("导入的Excel店铺名称不能是空！"); 
		 } else if (data == "114") {
			 alert("导入的Excel店铺评分不能是空！"); 
		 } else if (data == "115") {
			 alert("导入的Excel经度不能是空！"); 
		 } else if (data == "116") {
			 alert("导入的Excel纬度不能是空！"); 
		 } else if (data == "117") {
			 alert("导入的Excel省不能是空！"); 
		 } else if (data == "118") {
			 alert("导入的Excel市不能是空！"); 
		 } else if (data == "119") {
			 alert("导入的Excel区不能是空！"); 
		 } else if (data == "120") {
			 alert("导入的Excel详细地址不能是空！"); 
		 } else if (data == "121") {
			 alert("导入的Excel电话不能是空！"); 
		 } else if (data == "122") {
			 alert("导入的Excel救援类型不能是空！"); 
		 } else if (data == "123") {
			 alert("导入的Excel救援经验不能是空！"); 
		 } else if (data == "1") {
			 alert("数据导入成功！",{
				    btn: ['确定'], //按钮
				    //shade: .3 //不显示遮罩
				}, function(index){
					window.location.reload();
				});
		 } else if (data == "-1") {
			 alert("请选择要导入的Excel文件！");
			 $("#uploadFile")[0].focus();
		 } else if (data == "-2") {
			 alert("请选择Excel文件！");
		 }
});
</script>
</body>
</html>
