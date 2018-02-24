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
	<title>订单列表</title>
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
  			hrefFormer : '${pageContext.request.contextPath}/order_list',//链接前部
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
  		$("#totalForm").attr("action","${pageContext.request.contextPath}/order_list.action");
	  	$("#totalForm").submit();
  	}
  	//重置
  	function clean(){
		$("#title").attr("value","");
		$("#beginTime").attr("value","");
		$("#endTime").attr("value","");
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
			    	<span class="now_location">当前位置:</span>[订单]
			        <div class="clear"></div>
			    </div>
			    <div class=" margin-bottom-5 mt10">
					<!--  条件检索区 -->
					<span  style="font-size: 15px;margin-left:25px;">
					订单号
					<input type="text" id="title" name="title" value="${title}" style="width:150px;padding:5px;" />
					</span>
					<span class="margin-left-10" style="font-size: 15px;">助销达人
						<input class="margin-left-5" type="text" id="beginTime" name="beginTime" placeholder="开始日期" value="${beginTime}"class="laydate-icon" style="width:163px;padding:3px;" />
					</span>
					<span class="margin-left-10" style="font-size: 15px;">订单状态
						<input type="text" id="endTime" name="endTime" placeholder="结束日期" value="${endTime}"class="laydate-icon" style="width:164px;margin:0px 10px 0px 2px;padding:3px;" />
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
						<td nowrap="nowrap" width="14%"><strong>订单号</strong></td>
						<td nowrap="nowrap" width="8%"><strong>购买用户</strong></td>
						<td nowrap="nowrap" width="8%"><strong>助销达人</strong></td>
						<td nowrap="nowrap" width="5%"><strong>订单状态</strong></td>
						<td nowrap="nowrap" width="10%"><strong>下单时间</strong></td>
						<td nowrap="nowrap" width="10%"><strong>付款时间</strong></td>
						<td nowrap="nowrap" width="5%"><strong>支付方式</strong></td>
						<td nowrap="nowrap" width="5%"><strong>总金额</strong></td>
						<td nowrap="nowrap" width="5%"><strong>应付金额</strong></td>
						<td nowrap="nowrap" width="10%"><strong>物流单号</strong></td>
						<td nowrap="nowrap" width="15%"><strong>收货地址</strong></td>
	       			</tr>
		       		<c:forEach var="o" items="${list}" varStatus="s">					
					<tr align="center">
						<td><input type="checkbox" name="id" value="${o.id}"/></td>
						<!--  检索结果表格内容 -->
						<td><a href="order_list.action?orderId=${o.id}">${o.orderNo }</a></td>
						<td>${o.userName }</td>
						<td>${o.expertName }</td>
						<td>
							<c:choose>
								<c:when test="${o.orderStatus==1 }">待付款</c:when>
								<c:when test="${o.orderStatus==2 }">已付款</c:when>
								<c:when test="${o.orderStatus==3 }">订单关闭</c:when>
								<c:when test="${o.orderStatus==4 }">已发货</c:when>
								<c:when test="${o.orderStatus==5 }">已签收</c:when>
								<c:when test="${o.orderStatus==6 }">订单完成</c:when>
								<c:when test="${o.orderStatus==7 }">退货申请</c:when>
								<c:when test="${o.orderStatus==8 }">退货中</c:when>
								<c:when test="${o.orderStatus==9 }">退货完成</c:when>
							</c:choose>
						</td>
						<td><fmt:formatDate value="${o.orderTime }" type="both" pattern="yyyy-MM-dd HH:mm" dateStyle="long"/></td>
						<td><fmt:formatDate value="${o.payTime }" type="both" pattern="yyyy-MM-dd HH:mm" dateStyle="long"/></td>
						<td>
							<c:choose>
								<c:when test="${o.payChannel==A }">支付宝</c:when>
								<c:otherwise>微信</c:otherwise>
							</c:choose>
						</td>
						<td>${o.totalAmount }</td>
						<td>${o.payment }</td>
						<td><a href="">${o.logisticsNo }</a></td>
						<td title="${o.address }">${o.address }</td>
					</tr>					
					</c:forEach>
		     	  </tbody>
			   </table>
			   <!-- 功能按钮区域 
			   <div class=" margin-left-20">
			   		<span style="font-size:14px;">操作:</span>
			   		<span class=" margin-left-10">			   	
				   		<input onclick="addDataPage();" type="button" value="增加" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input onclick="deleteData();"  type="button" value="删除" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   		<input onclick="updateDataPage()" type="button" value="修改" class="btn btn-info" style="width:80px;margin-right:8px;margin-bottom:8px;" />
				   	</span>
			   	</div>
			</div>-->
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
