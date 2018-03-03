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
    <script type="text/javascript" src="<%=path %>/js/layer/layer.js"></script>
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
		var orderNo = $.trim($("#orderNo").val());
		var orderStatus = $.trim($("#orderStatus").val());
		var expertId = $.trim($("#expertId").val());
		
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
			getLink : function(n) {
				return this.hrefFormer + this.hrefLatter + "?pno=" + n
						+ "&orderNo=" + orderNo + "&orderStatus=" + orderStatus
						+ "&expertId=" + expertId;
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
			click : function() {
				var checkboxs = $("input:checkbox[name=id]");
				if ($("#isSelectAll").is(":checked")) {
					for (var i = 0; i < checkboxs.length; i++) {
						if (!(checkboxs[i].checked)) {
							checkboxs[i].checked = true;
						}
					}
				} else {
					for (var i = 0; i < checkboxs.length; i++) {
						if ((checkboxs[i].checked)) {
							checkboxs[i].checked = false;
						}
					}
				}
			}
		});
		//默认选中
		if('${orderStatus}'){
			$("#orderStatus").find("option").each(function(){
				if($(this).val()=="${orderStatus}"){
					$(this).attr("selected","selected");
				}
			});
		}
	});
	//按条件查询
	function query() {
		$("#totalForm").attr("action","${pageContext.request.contextPath}/order_list.action");
		$("#totalForm").submit();
	}
	//重置
	function clean() {
		$("#orderNo").attr("value", "");
		$("#orderStatus").attr("value", "-1");
		$("#expertId").attr("value", "-1");
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
					<input type="text" id="orderNo" name="orderNo" value="${orderNo}" style="width:150px;padding:5px;" />
					</span>
					<span class="margin-left-10" style="font-size: 15px;">助销达人
						<select id="expertId" name="expertId" style="height: 30px;width:150px;font-size: 14px;">
							<option value="-1">请选择达人...</option>
							<c:forEach var="expert" items="${expertList}" varStatus="s">
									<option value="${expert.id }"
										<c:if test='${expertId ==expert.id}'>selected='selected'</c:if>>
									${expert.expertName }
								</option>
							</c:forEach>
						</select>
					</span>
					<span class="margin-left-10" style="font-size: 15px;">订单状态
						<select id="orderStatus" name="orderStatus" style="height: 30px;width:150px;font-size: 14px;">
							<option value="-1">请选择订单状态...</option>
							<option value="1">待付款</option>
							<option value="2">已付款</option>
							<option value="3">订单关闭</option>
							<option value="4">已发货</option>
							<option value="5">已签收</option>
							<option value="6">订单完成</option>
							<option value="7">退货申请</option>
							<option value="8">退货中</option>
							<option value="9">退货完成</option>
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
						<td nowrap="nowrap" width="13%"><strong>订单号</strong></td>
						<td nowrap="nowrap" width="10%"><strong>购买用户</strong></td>
						<td nowrap="nowrap" width="10%"><strong>助销达人</strong></td>
						<td nowrap="nowrap" width="5%"><strong>订单状态</strong></td>
						<td nowrap="nowrap" width="10%"><strong>下单时间</strong></td>
						<td nowrap="nowrap" width="10%"><strong>付款时间</strong></td>
						<td nowrap="nowrap" width="5%"><strong>支付方式</strong></td>
						<td nowrap="nowrap" width="6%"><strong>总金额</strong></td>
						<td nowrap="nowrap" width="6%"><strong>实付金额</strong></td>
						<td nowrap="nowrap" width="12%"><strong>物流单号</strong></td>
						<td nowrap="nowrap" width="8%"><strong>操 作</strong></td>
	       			</tr>
		       		<c:forEach var="o" items="${list}" varStatus="s">					
					<tr align="center">
						<td><input type="checkbox" name="id" value="${o.id}"/></td>
						<!--  检索结果表格内容 -->
						<td title="订单详情"><a href="${pageContext.request.contextPath}/order_detail.action?orderId=${o.id}">${o.orderNo }</a></td>
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
								<c:when test="${o.orderStatus==10 }">用户取消</c:when>
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
						<td>
							<a href="javascript:void(0);" onclick="logisticsDetail('${o.id}','${o.address}')">${o.logisticsNo }</a>
						</td>
						<td>
							<c:if test="${o.orderStatus==2 }">
							<a href="javascript:void(0);" onclick="shipping('${o.id}','${o.addressId}','${o.address}')">发 货</a>
							</c:if>
						</td>
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
			   	</div>-->
			</div>
		</form>
		<div class="pkp">
            <div id="kkpager"></div>
    	</div>
	</div>
</div>
<!-- 发货 -->
<form id="shipForm">
<div id="shipping" style="display: none">
	<table class="table table-condensed" style="margin-bottom:0px;">
	    <tr>
	    	<td width="15%" align="center" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">物流单号</td>
			<td width="45%" >
				<input name="logistics.logisticsNo" type="text" style="margin-left: 30px;width:200px;"/>
			</td>
			<td width="15%" align="center" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">时间</td>
			<td width="25%">
				
			</td>
		</tr>
		<tr>
	    	<td width="15%" align="center" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">地址</td>
			<td colspan="3">
				<span style="margin-left:30px;" id="address"></span>
			</td>
		</tr>
	</table>
</div>
</form>
<!-- 物流详情 -->
<div id="logisticsDetail" style="display: none">
	<table class="table table-condensed" style="margin-bottom:0px;"></table>
</div>

<script type="text/javascript">
//发货
function shipping(orderId,addressId,address){
	$("#address").text(address);
	layer.open({
		title : '<i class="icon-location-pin"></i>发货:<strong>填写物流信息</strong>',
		type : 1,
		area: ['700px', '250px'],
		btn: ["<i class='fa fa-ban'></i> 确定","<i class='fa fa-ban'></i> 取消"],
		closeBtn: 1,
		content : $("#shipping")
	});
}

//物流详情
function logisticsDetail(orderId,address){
	var $tobdy=$("#logisticsDetail table");
	$tobdy.empty();
	var addressInfo = "<tr>";
	addressInfo+="<td style='text-align:left;'width='75%'><span style='margin-left:10px;color:gray;'> 收货人";
	addressInfo+=address ;
	addressInfo+="</span></td>";
	addressInfo+= "<td width='25%'></td></tr>";
	$tobdy.append(addressInfo);
	$.ajax({
		url:'${pageContext.request.contextPath}/order_logisticsDetail.action',
		type:'GET',
		async:true,
		data:{orderId:orderId},
	    timeout:5000,    //超时时间
	    dataType:'json', //返回的数据格式：json/xml/html/script/jsonp/text
	    success:function(data,textStatus,jqXHR){
	    	var json = eval(data.details);
	    	$.each(json,function(key,value){
					var info = "<tr>";
					info+="<td style='text-align:left;'><span style='margin-left:10px;'>";
					info+=value.description ;
					info+="</span></td>";
					info+="<td style='text-align:center;'><span style='color:gray;'>";
					var time=formateDate(value.createTime.time);
					info+=time+"</span></td>"; 
					info+= "</tr>";
	    			$tobdy.append(info);
	    	});
	    },complete:function(){
	    	layer.open({
	    		title : '<i class="icon-location-pin"></i>当前位置 / <strong>物流详情</strong>',
	    		type : 1,
	    		area: ['700px', '350px'],
	    		btn: ["<i class='fa fa-ban'></i> 确定"],
	    		closeBtn: 1,
	    		content : $("#logisticsDetail")
	    	});
		}
	});
}

function formateDate(time){
	var date = new Date(time);
	Y = date.getFullYear() + '-';
	M = (date.getMonth()+1 < 10 ? '0'+(date.getMonth()+1) : date.getMonth()+1) + '-';
	D = date.getDate() + ' ';
	h = date.getHours() + ':';
	m = date.getMinutes();
	return (Y+M+D+h+m); 
}	
</script>
</body>
</html>
