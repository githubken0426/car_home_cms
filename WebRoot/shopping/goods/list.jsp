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
	<title>商品列表</title>
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
		var title = $.trim($("#title").val());
		title=encodeURI(encodeURI(title));
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
  			hrefFormer : '${pageContext.request.contextPath}/goods_list',//链接前部
  			hrefLatter : '.action',		 //链接尾部
  			getLink : function(n){
  				return this.hrefFormer + this.hrefLatter + "?pno="+ n +"&title="+title+"&beginTime="+beginTime +"&endTime="+endTime;
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
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/goods_list.action");
	  	$("#totalForm").submit();
  	}
  	//重置
  	function clean(){
  		$("#categoryId").attr("value","-1");
		$("#brandId").attr("value","-1");
		$("#title").attr("value","");
		$("#beginTime").attr("value","");
		$("#endTime").attr("value","");
  	}
  	//添加
  	function addDataPage(){  		
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/goods_list.action");
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
			$("#totalForm").attr("action","${pageContext.request.contextPath}/goods_list.action");
			$("#totalForm").submit();	
  		}
  	}
  	
  	//批量修改状态
  	function deleteData(){
  		var checkboxs=$("input:checkbox[name=id]");
  		if(checkboxs.is(":checked")){
	  		$("#totalForm").attr("action","${pageContext.request.contextPath}/goods_list.action");
	  		$("#totalForm").submit();
  		}else{
  			alert("请选择修改项！");
  		}
  	}
 
	function getBrandByCtegory(ele) {
		if (ele.value!=-1) {
			$.ajax({
				type : "POST",
				dataType : "json",
				data : {
					categoryId : ele.value
				},
				async : true,
				url : "${pageContext.request.contextPath}/brand_getBrandByCtegory.action",			
				success : function(data) {
					$("#brandId").empty();
					var optA="<option value='-1'>请选择品牌</option>";
					 $("#brandId").append(optA);
					 var json = eval(data); //数组       
		             $.each(json, function (index, item) {
		                 //循环获取数据  
		                 var name = json[index].cnName;
		                 var id = json[index].id;
		                 var opt="<option value="+id+">"+name+"</option>";
		                 $("#brandId").append(opt);
		             });
				}		
			});
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
			    	<span class="now_location">当前位置:</span>[商品]
			        <div class="clear"></div>
			    </div>
			    <div class=" margin-bottom-5 mt10">
			    	<span  style="font-size: 15px;margin-left:25px;">
						所属分类
						<select onchange="getBrandByCtegory(this)" id="categoryId" name="categoryId" style="height:25px;">
							<option value="-1">请选择分类</option>
							<c:forEach var="category" items="${categoryList}">
								<option value="${category.id }" <c:if test='${categoryId ==category.id}'>selected='selected'</c:if>>
									${category.title}
								</option>
							</c:forEach>
						</select> -
						<select id="brandId" name="brandId" style="height:25px;">
							<option value="-1">请选择品牌</option>
							<c:forEach var="brand" items="${brandList}">
								<option value="${brand.id }" <c:if test='${brandId ==brand.id}'>selected='selected'</c:if>>
									${brand.cnName}
								</option>
							</c:forEach>
						</select>
					</span>
					<!--  条件检索区 -->
					<span  style="font-size: 15px;margin-left:25px;">
						商品标题
						<input type="text" id="title" name="title" value="${title}" style="width:150px;padding:5px;" />
					</span>
					<span class="margin-left-10" style="font-size: 15px;">上架时间</span>
						<input class="margin-left-5" type="text" id="beginTime" name="beginTime" placeholder="开始日期" value="${beginTime}"class="laydate-icon" style="width:163px;padding:3px;" />
						 至
						<input type="text" id="endTime" name="endTime" placeholder="结束日期" value="${endTime}"class="laydate-icon" style="width:164px;margin:0px 10px 0px 2px;padding:3px;" />
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
			       	 	<td nowrap="nowrap" width="3%"><input type="checkbox" id="isSelectAll"/></td>
						<!--  检索结果表格题头 -->
						<td nowrap="nowrap" width="10%"><strong>商品编号</strong></td>
						<td nowrap="nowrap" width="15%"><strong>商品标题</strong></td>
						<td nowrap="nowrap" width="7%"><strong>商品图片</strong></td>
						<td nowrap="nowrap" width="8%"><strong>商品促销价</strong></td>
						<td nowrap="nowrap" width="6%"><strong>可用积分</strong></td>
						<td nowrap="nowrap" width="7%"><strong>商品库存</strong></td>
						<td nowrap="nowrap" width="7%"><strong>已售数量</strong></td>
						<td nowrap="nowrap" width="5%"><strong>热销</strong></td>
						<td nowrap="nowrap" width="5%""><strong>新品</strong></td>
						<td nowrap="nowrap" width="10%""><strong>上架时间</strong></td>
						<td nowrap="nowrap" width="10%"><strong>下架时间</strong></td>
						<td nowrap="nowrap" width="7%"><strong>商品状态</strong></td>
	       			</tr>
		       		<c:forEach var="o" items="${list}" varStatus="s">					
					<tr align="center">
						<td><input type="checkbox" name="id" value="${o.id}"/></td>
						<!--  检索结果表格内容 -->
						<td title="商品详情">
							<a href="">${o.skuCode }</a>
						</td>
						<td title="${o.goodsTitle }">${o.goodsTitle }</td>
						<td>
							<c:if test="${not empty o.smallPictureList[0]}">
								<img src="${o.smallPictureList[0]}" style="width:40px;height:40px;"/>
							</c:if>
						</td>
						<td>${o.promotionPrice }</td>
						<td>${o.score }</td>
						<td>${o.stock }</td>
						<td>${o.soldNumber }</td>
						<td>
							<c:choose>
								<c:when test="${o.isHot=='Y' }">是</c:when>
								<c:otherwise>否</c:otherwise>
							</c:choose>
						</td>
						<td>
							<c:choose>
								<c:when test="${o.isNew=='Y' }">是</c:when>
								<c:otherwise>否</c:otherwise>
							</c:choose>
						</td>
						<td><fmt:formatDate value="${o.upTime }" type="both" pattern="yyyy-MM-dd" dateStyle="long"/> </td>
						<td><fmt:formatDate value="${o.downTime }" type="both" pattern="yyyy-MM-dd" dateStyle="long"/> </td>
						<td>
							<c:choose>
								<c:when test="${o.status==0 }">在售</c:when>
								<c:when test="${o.status==1 }">下架</c:when>
								<c:otherwise>预售</c:otherwise>
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
