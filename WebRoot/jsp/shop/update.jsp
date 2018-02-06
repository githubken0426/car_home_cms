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
	<title>修改</title>
	<link rel="stylesheet" href="<%=path %>/css/pubmain.css" />
	<link href="<%=request.getContextPath() %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
	<script type="text/javascript" src="<%=path%>/js/cms/view_image/view_image.js"></script>
   	
<script type="text/javascript">

	

	// 定义js check变量  

	var rescueServiceIsOK = true;
	var repairServiceIsOK = true;
	var cleanServiceIsOK = true;
	var maintainServiceIsOK = true;
	var tyreServiceIsOK = true;
	var shopPicUrlIsOK = true;
	var shopNameIsOK = true;
	var shopScoreIsOK = true;
	var shopDescriptionIsOK = true;
	var longitudeIsOK = true;
	var latitudeIsOK = true;
	var provinceIsOK = true;
	var cityIsOK = true;
	var districtIsOK = true;
	var detailAddressIsOK = true;
	var telNumberListIsOK = true;
	var displayPicUrlListIsOK = true;
	var isTopIsOK = true;
	var displayPriorityIsOK = true;

	
	$(function(){		
		
		var rescueService1 = $("#rescueService1").val();
		var rescueService2 = $("#rescueService2").val();
		var rescueService3 = $("#rescueService3").val();
		var rescueService4 = $("#rescueService4").val();
		var rescueService5 = $("#rescueService5").val();
		if (rescueService1 == 1) {
			$("#rescueService1").attr("checked",true);
		}
		if (rescueService2 == 2) {
			$("#rescueService2").attr("checked",true);
		}
		if (rescueService3 == 3) {
			$("#rescueService3").attr("checked",true);
		}
		if (rescueService4 == 4) {
			$("#rescueService4").attr("checked",true);
		}
		if (rescueService5 == 5) {
			$("#rescueService5").attr("checked",true);
		}
		
		var repairService = $("#repairService").val();
		var cleanService = $("#cleanService").val();
		var maintainService = $("#maintainService").val();
		var tyreService = $("#tyreService").val();
		if (repairService == 5100) {
			$("#repairService").attr("checked",true);
		}
		if (cleanService == 4100) {
			$("#cleanService").attr("checked",true);
		}
		if (maintainService == 6100) {
			$("#maintainService").attr("checked",true);
		}
		if (tyreService == 7100) {
			$("#tyreService").attr("checked",true);
		}
		
		// 定义js check必录项和长度校验  

		// 救援服务
		$("#rescueService").bind({
			focus:function(){
				$("#rescueServiceMsg").html("");
			},blur:function(){
				var rescueService = $.trim($("#rescueService").val());
				rescueServiceIsOK = true;
				return true;
			}
		});

		// 修车服务
		$("#repairService").bind({
			focus:function(){
				$("#repairServiceMsg").html("");
			},blur:function(){
				var repairService = $.trim($("#repairService").val());
				repairServiceIsOK = true;
				return true;
			}
		});

		// 洗车服务
		$("#cleanService").bind({
			focus:function(){
				$("#cleanServiceMsg").html("");
			},blur:function(){
				var cleanService = $.trim($("#cleanService").val());
				cleanServiceIsOK = true;
				return true;
			}
		});

		// 保养服务
		$("#maintainService").bind({
			focus:function(){
				$("#maintainServiceMsg").html("");
			},blur:function(){
				var maintainService = $.trim($("#maintainService").val());
				maintainServiceIsOK = true;
				return true;
			}
		});

		// 轮胎服务
		$("#tyreService").bind({
			focus:function(){
				$("#tyreServiceMsg").html("");
			},blur:function(){
				var tyreService = $.trim($("#tyreService").val());
				tyreServiceIsOK = true;
				return true;
			}
		});

		// 公司图片
		$("#shopPicUrl").bind({
			focus:function(){
				$("#shopPicUrlMsg").html("");
			},blur:function(){
				var shopPicUrl = $.trim($("#shopPicUrl").val());
				if(shopPicUrl.length > 500){
					$("#shopPicUrlMsg").html("<font color='red'>公司图片 超长(500位)!</font>");
					shopPicUrlIsOK = false;
					return false;
				}
				shopPicUrlIsOK = true;
				return true;
			}
		});

		// 公司名字
		$("#shopName").bind({
			focus:function(){
				$("#shopNameMsg").html("");
			},blur:function(){
				var shopName = $.trim($("#shopName").val());
				if(shopName.length > 32){
					$("#shopNameMsg").html("<font color='red'>公司名字 超长(32位)!</font>");
					shopNameIsOK = false;
					return false;
				}
				if(shopName.length > 32){
					$("#shopNameMsg").html("<font color='red'>请填写公司名字!</font>");
					shopNameIsOK = false;
					return false;
				}
				shopNameIsOK = true;
				return true;
			}
		});

		// 公司评分
		$("#shopScore").bind({
			focus:function(){
				$("#shopScoreMsg").html("");
			},blur:function(){
				var shopScore = $.trim($("#shopScore").val());
				if(shopScore.length > 8){
					$("#shopScoreMsg").html("<font color='red'>公司评分 超长(8位)!</font>");
					shopScoreIsOK = false;
					return false;
				}
				if(shopScore.length == 0){
					$("#shopScoreMsg").html("<font color='red'>请填写公司评分!</font>");
					shopScoreIsOK = false;
					return false;
				}
				shopScoreIsOK = true;
				return true;
			}
		});

		// 公司描述
		$("#shopDescription").bind({
			focus:function(){
				$("#shopDescriptionMsg").html("");
			},blur:function(){
				var shopDescription = $.trim($("#shopDescription").val());
				if(shopDescription.length > 1000){
					$("#shopDescriptionMsg").html("<font color='red'>公司描述 超长(1000位)!</font>");
					shopDescriptionIsOK = false;
					return false;
				}
				shopDescriptionIsOK = true;
				return true;
			}
		});

		// 公司所在地经度
		$("#longitude").bind({
			focus:function(){
				$("#longitudeMsg").html("");
			},blur:function(){
				var longitude = $.trim($("#longitude").val());
				if(longitude.length > 50){
					$("#longitudeMsg").html("<font color='red'>公司所在地经度 超长(50位)!</font>");
					longitudeIsOK = false;
					return false;
				}
				if(longitude.length == 0){
					$("#longitudeMsg").html("<font color='red'>请填写经度!</font>");
					longitudeIsOK = false;
					return false;
				}
				longitudeIsOK = true;
				return true;
			}
		});

		// 公司所在地纬度
		$("#latitude").bind({
			focus:function(){
				$("#latitudeMsg").html("");
			},blur:function(){
				var latitude = $.trim($("#latitude").val());
				if(latitude.length > 50){
					$("#latitudeMsg").html("<font color='red'>公司所在地纬度 超长(50位)!</font>");
					latitudeIsOK = false;
					return false;
				}
				if(latitude.length == 0){
					$("#latitudeMsg").html("<font color='red'>请填写纬度!</font>");
					latitudeIsOK = false;
					return false;
				}
				latitudeIsOK = true;
				return true;
			}
		});

		// 详细地址_省
		$("#province").bind({
			focus:function(){
				$("#provinceMsg").html("");
			},blur:function(){
				var province = $.trim($("#province").val());
				if(province.length > 8){
					$("#provinceMsg").html("<font color='red'>详细地址_省 超长(8位)!</font>");
					provinceIsOK = false;
					return false;
				}
				if(province.length == 0){
					$("#provinceMsg").html("<font color='red'>请填写所在省!</font>");
					provinceIsOK = false;
					return false;
				}
				provinceIsOK = true;
				return true;
			}
		});

		// 详细地址_市
		$("#city").bind({
			focus:function(){
				$("#cityMsg").html("");
			},blur:function(){
				var city = $.trim($("#city").val());
				if(city.length > 8){
					$("#cityMsg").html("<font color='red'>详细地址_市 超长(8位)!</font>");
					cityIsOK = false;
					return false;
				}
				if(city.length == 0){
					$("#cityMsg").html("<font color='red'>请填写所在市!</font>");
					cityIsOK = false;
					return false;
				}
				cityIsOK = true;
				return true;
			}
		});

		// 详细地址_区
		$("#district").bind({
			focus:function(){
				$("#districtMsg").html("");
			},blur:function(){
				var district = $.trim($("#district").val());
				if(district.length > 8){
					$("#districtMsg").html("<font color='red'>详细地址_区 超长(8位)!</font>");
					districtIsOK = false;
					return false;
				}
				if(district.length == 0){
					$("#districtMsg").html("<font color='red'>请填写所在区!</font>");
					districtIsOK = false;
					return false;
				}
				districtIsOK = true;
				return true;
			}
		});

		// 详细地址
		$("#detailAddress").bind({
			focus:function(){
				$("#detailAddressMsg").html("");
			},blur:function(){
				var detailAddress = $.trim($("#detailAddress").val());
				if(detailAddress.length > 200){
					$("#detailAddressMsg").html("<font color='red'>详细地址 超长(200位)!</font>");
					detailAddressIsOK = false;
					return false;
				}
				if(detailAddress.length == 0){
					$("#detailAddressMsg").html("<font color='red'>请填写所在详细地址!</font>");
					detailAddressIsOK = false;
					return false;
				}
				detailAddressIsOK = true;
				return true;
			}
		});

		// 公司电话列表
		$("#telNumberList").bind({
			focus:function(){
				$("#telNumberListMsg").html("");
			},blur:function(){
				var telNumberList = $.trim($("#telNumberList").val());
				if(telNumberList.length > 200){
					$("#telNumberListMsg").html("<font color='red'>公司电话列表 超长(200位)!</font>");
					telNumberListIsOK = false;
					return false;
				}
				if(telNumberList.length == 0){
					$("#telNumberListMsg").html("<font color='red'>请填写公司电话!</font>");
					telNumberListIsOK = false;
					return false;
				}
				telNumberListIsOK = true;
				return true;
			}
		});

		// 公司详情图片
		$("#displayPicUrlList").bind({
			focus:function(){
				$("#displayPicUrlListMsg").html("");
			},blur:function(){
				var displayPicUrlList = $.trim($("#displayPicUrlList").val());
				if(displayPicUrlList.length > 1500){
					$("#displayPicUrlListMsg").html("<font color='red'>公司详情图片 超长(1500位)!</font>");
					displayPicUrlListIsOK = false;
					return false;
				}
				displayPicUrlListIsOK = true;
				return true;
			}
		});

		// 是否置顶
		$("#isTop").bind({
			focus:function(){
				$("#isTopMsg").html("");
			},blur:function(){
				var isTop = $.trim($("#isTop").val());
				isTopIsOK = true;
				return true;
			}
		});

		// 显示优先级
		$("#displayPriority").bind({
			focus:function(){
				$("#displayPriorityMsg").html("");
			},blur:function(){
				var displayPriority = $.trim($("#displayPriority").val());
				displayPriorityIsOK = true;
				return true;
			}
		});

	
  	});
  
   	// 更新
	function updateSubmit(){
		
		// 提交前校验
		if ( 1==1 && rescueServiceIsOK && repairServiceIsOK && cleanServiceIsOK && maintainServiceIsOK && tyreServiceIsOK && shopPicUrlIsOK && shopNameIsOK && shopScoreIsOK && shopDescriptionIsOK && longitudeIsOK && latitudeIsOK && provinceIsOK && cityIsOK && districtIsOK && detailAddressIsOK && telNumberListIsOK && displayPicUrlListIsOK && isTopIsOK && displayPriorityIsOK){
			$("#updateForm").submit();
		} else {
			alert("信息输入有误，无法保存！");
		}		
	}
   	
	// 删除
	function deleteSubmit(){
		
		$("#picFlag").attr("value","");
		$("#shopPicUrl").attr("value","");
		
		var img = document.getElementById("viewPortrait");
		img.src = "";
		img.style.display = 'none';
	}
	
	// 删除
	function deleteSubmit1(){
		
		$("#picFlag1").attr("value","");
		$("#displayPicUrl1").attr("value","");
		
		var img = document.getElementById("viewPortrait1");
		img.src = "";
		img.style.display = 'none';
	}
	
	// 删除
	function deleteSubmit2(){
		
		$("#picFlag2").attr("value","");
		$("#displayPicUrl2").attr("value","");
		
		var img = document.getElementById("viewPortrait2");
		img.src = "";
		img.style.display = 'none';
	}
	
	// 删除
	function deleteSubmit3(){
		
		$("#picFlag3").attr("value","");
		$("#displayPicUrl3").attr("value","");
		
		var img = document.getElementById("viewPortrait3");
		img.src = "";
		img.style.display = 'none';
	}
	
	// 删除
	function deleteSubmit4(){
		
		$("#picFlag4").attr("value","");
		$("#displayPicUrl4").attr("value","");
		
		var img = document.getElementById("viewPortrait4");
		img.src = "";
		img.style.display = 'none';
	}
	
	// 删除
	function deleteSubmit5(){
		
		$("#picFlag5").attr("value","");
		$("#displayPicUrl5").attr("value","");
		
		var img = document.getElementById("viewPortrait5");
		img.src = "";
		img.style.display = 'none';
	}
	
	// 救援1改变
	function upperCase1(){
		
		if ($('#rescueService1').attr('checked')) {
			$('#rescueService1').attr("value","1");
		}
	}
	
	// 救援2改变
	function upperCase2(){
		
		if ($('#rescueService2').attr('checked')) {
			$('#rescueService2').attr("value","2");
		}
	}
	
	// 救援3改变
	function upperCase3(){
		
		if ($('#rescueService3').attr('checked')) {
			$('#rescueService3').attr("value","3");
		}
	}
	
	// 救援4改变
	function upperCase4(){
		
		if ($('#rescueService4').attr('checked')) {
			$('#rescueService4').attr("value","4");
		}
	}
	
	// 救援5改变
	function upperCase5(){
		
		if ($('#rescueService5').attr('checked')) {
			$('#rescueService5').attr("value","5");
		}
	}
	
	// 修车改变
	function upperCaseRepair(){
		
		if ($('#repairService').attr('checked')) {
			$('#repairService').attr("value","5100");
		}
	}
	
	// 洗车改变
	function upperCaseClean(){
		
		if ($('#cleanService').attr('checked')) {
			$('#cleanService').attr("value","4100");
		}
	}
	
	// 保养改变
	function upperCaseMain(){
		
		if ($('#maintainService').attr('checked')) {
			$('#maintainService').attr("value","6100");
		}
	}
	
	// 轮胎改变
	function upperCaseTyre(){
		
		if ($('#tyreService').attr('checked')) {
			$('#tyreService').attr("value","7100");
		}
	}
	
   	// 返回
  	function turnBack(){
  		$("#backForm").submit();
  	}
</script>
</head>
  
<body>
<div id="middle">
	<div class="right"  id="mainFrame">
		<form action="${pageContext.request.contextPath}/shop!updateData.action" method="post" id="updateForm" enctype="multipart/form-data">
			<div class="content-box">
				<div class="content-box-header">
					<span class="now_location">当前位置:</span>修改
				    <div class="clear"></div>
			    </div>
				    
			    <div style="margin:0 auto; margin:10px;">
			   		<table  class="margin-bottom-20 table  no-border" style="width:300px;margin-top:20px;">
					 	<tr>
							<td class="text-center">
								<input type="button" value="保存" class="btn btn-info " style="width:80px;" onclick="updateSubmit()" />
							</td>
							<td align="left">
								<input type="button" value="返回" class="btn btn-info " style="width:80px;"  onclick="turnBack()"/>
						   	</td>
					   		<td></td>
					   	</tr>
					</table>
			    
	            	<table class="table table-bordered" >
	            		<input type="hidden" id="id" name="entity.id" value="${entity.id }" />
	            		<input type="hidden" id="rescueId" name="rescueId" value="${rescueId }" />
	            		

						<!-- 数据修改  -->
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">救援服务：</td>
							<td width="40%">
							<input type="checkbox" style="margin-left:30px;" name="rescueService1" id="rescueService1" value="${rescueService1}" onblur="upperCase1()" tabindex="1"/><span>现场抢修</span>
							<input type="checkbox" style="margin-left:10px;" name="rescueService2" id="rescueService2" value="${rescueService2}" onblur="upperCase2()" tabindex="2"/><span>拖车</span>
							<input type="checkbox" style="margin-left:10px;" name="rescueService3" id="rescueService3" value="${rescueService3}" onblur="upperCase3()" tabindex="3"/><span>紧急加水</span>
							<input type="checkbox" style="margin-left:10px;" name="rescueService4" id="rescueService4" value="${rescueService4}" onblur="upperCase4()" tabindex="4"/><span>紧急送油</span>
							<input type="checkbox" style="margin-left:10px;" name="rescueService5" id="rescueService5" value="${rescueService5}" onblur="upperCase5()" tabindex="5"/><span>配钥匙</span>
							<span id="rescueServiceMsg" style="margin-left:15px;"></span>
							</td>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">服务：</td>
							<td width="40%">
							<input type="checkbox" style="margin-left:30px;" name="repairService" id="repairService" onblur="upperCaseRepair()" value="${entity.repairService}" tabindex="6"/><span>修车服务</span>
							<input type="checkbox" style="margin-left:30px;" name="cleanService" id="cleanService" onblur="upperCaseClean()" value="${entity.cleanService}" tabindex="7"/><span>洗车服务</span>
							<input type="checkbox" style="margin-left:30px;" name="maintainService" id="maintainService" onblur="upperCaseMain()" value="${entity.maintainService}" tabindex="8"/><span>保养服务</span>
							<input type="checkbox" style="margin-left:30px;" name="tyreService" id="tyreService" onblur="upperCaseTyre()" value="${entity.tyreService}" tabindex="9"/><span>轮胎服务</span>
							<span id="repairServiceMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<%-- <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">救援经验：</td>
							<td colspan="3">
							<input type="text" id="experience" name="experience" value="${experience}" tabindex="10" style="width:200px;margin-left:30px;"/>
							<span>年</span>
							<span id="experienceMsg" style="margin-left:15px;"></span>
							</td> --%>
							<td  width="120px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">状态：</td>
							<td colspan="3">
								<c:choose>
									<c:when test="${entity.deleteFlag==0}">
										<label style="margin-left: 35px; display: inline;">
										<input type="radio" name="entity.deleteFlag" checked="checked" tabindex="6" value="0"
											style="margin-top: 0px;" />
										<span style="margin-left: 10px;">启&nbsp;用</span>
										</label>
										<label style="margin-left: 25px; display: inline;">
										<input type="radio" name="entity.deleteFlag" value="1"
											tabindex="7" style="margin-top: 0px;" />
										<span style="margin-left: 10px;">禁&nbsp;用</span>
										</label>
									</c:when>
									<c:otherwise>
										<label style="margin-left: 35px; display: inline;">
										<input type="radio" name="entity.deleteFlag" tabindex="6" value="0"
											style="margin-top: 0px;" />
										<span style="margin-left: 10px;">启&nbsp;用</span>
										</label>
										<label style="margin-left: 25px; display: inline;">
										<input type="radio" name="entity.deleteFlag" checked="checked" value="1"
											tabindex="7" style="margin-top: 0px;" />
										<span style="margin-left: 10px;">禁&nbsp;用</span>
										</label>
									</c:otherwise>
								</c:choose>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">产品简介：</td>
							<td colspan="3">
								<textarea placeholder="简介在1000字以内有效" name="entity.productDescription" tabindex="11" style="width:75%;height:100px;margin:5px 0px 5px 30px;">${productDescription}</textarea>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">公司名字：</td>
							<td width="40%">
							<input type="text" id="shopName" name="entity.shopName" value="${entity.shopName }" tabindex="12" style="width:200px;margin-left:30px;"/>
							<span id="shopNameMsg" style="margin-left:15px;"></span>
							</td>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">公司星级：</td>
							<td width="40%">
							<input type="text" id="shopScore" name="entity.shopScore" value="${entity.shopScore }" tabindex="13" style="width:200px;margin-left:30px;"/>
							<span id="shopScoreMsg" style="margin-left:15px;"></span>
							<span>注意：请填写1～5的整数</span>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">公司所在地经度：</td>
							<td width="40%">
							<input type="text" id="longitude" name="entity.longitude" value="${entity.longitude }" tabindex="14" style="width:200px;margin-left:30px;"/>
							<span id="longitudeMsg" style="margin-left:15px;"></span>
							</td>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">公司所在地纬度：</td>
							<td width="40%">
							<input type="text" id="latitude" name="entity.latitude" value="${entity.latitude }" tabindex="15" style="width:200px;margin-left:30px;"/>
							<span id="latitudeMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">公司描述：</td>
							<td colspan="3">
							<textarea placeholder="简介在1000字以内有效" name="entity.shopDescription" tabindex="16" style="width:75%;height:100px;margin:5px 0px 5px 30px;">${entity.shopDescription }</textarea>
							</td>
						</tr>
						<tr>
							<%-- <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">详细地址_省：</td>
							<td width="40%">
							<input type="text" id="province" name="entity.province" tabindex="17" value="${entity.province }" style="width:200px;margin-left:30px;"/>
							<span id="provinceMsg" style="margin-left:15px;"></span>
							</td> --%>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">详细地址_市：</td>
							<td colspan="3">
							<input type="hidden" id="cityId" name="cityId" value="${cityId}" />
							<input type="text" readonly="readonly" id="city" name="entity.city" tabindex="18" value="${entity.city }" style="width:200px;margin-left:30px;"/>
							<%-- <select id="cityId" name="cityId" tabindex="18" style="width:200px;height:25px;margin-left:30px;">
					     		<c:forEach var="city" items="${cityList}">
					     		    <option value="${city.cityCode}" <c:if test="${entity.cityCode==city.cityCode}">selected="selected"</c:if>>${city.cityName}</option>
					     		</c:forEach>
					     	</select> --%>  
							<span id="cityMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<%-- <td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">详细地址_区：</td>
							<td width="40%">
							<input type="text" id="district" name="entity.district" tabindex="19" value="${entity.district }" style="width:200px;margin-left:30px;"/>
							<span id="districtMsg" style="margin-left:15px;"></span>
							</td> --%>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">详细地址：</td>
							<td colspan="3">
							<input type="text" id="detailAddress" name="entity.detailAddress" value="${entity.detailAddress }"  tabindex="20" style="width:600px;margin-left:30px;"/>
							<span id="detailAddressMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">公司电话列表：</td>
							<td colspan="3">
							<input type="text" id="telNumberList" name="entity.telNumberList" value="${entity.telNumberList }" tabindex="21" style="width:600px;margin-left:30px;"/>
							<span id="telNumberListMsg" style="margin-left:15px;"></span>
							<span>注意：电话号码之间用半角逗号(,)隔开</span>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">公司图片：</td>
							<td colspan="3">
							<div style="margin:10px 0px 10px 0px;">
							<div style="height:60px;width:450px;float:left;">
							<input type="hidden" name="picFlag" id="picFlag" value="${entity.shopPicUrl }" />
							 <!-- <input type="file" id="shopPicUrl" name="myFile" style="width:400px;margin-left:30px;"/> -->
							 <input onchange="viewUploadImg(this,'viewPortrait')" type="file" id="shopPicUrl" value="${entity.shopPicUrl }" name="myFile" tabindex="22" 
								style="width: 350px; margin-left: 30px;" />
							<%-- <img src="${entity.shopPicUrl }" 
								<c:if test="${empty entity.shopPicUrl}">style="display:none;width:50px;height:50px;border:0px;"</c:if> 
								style="width:50px;height:50px;border:0px;" id="viewPortrait"/> --%>
							<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deleteSubmit()" />
							<img src="${ftpServerIp}${entity.shopPicUrl }" 
							<c:if test="${empty entity.shopPicUrl}">style="display:none;width:50px;height:50px;"</c:if> 
								style="width:50px;height:50px;" id="viewPortrait"/>
							 <span id="shopPicUrlMsg" style="margin-left:15px;"></span>
							</div></div>
							</td>
						</tr>
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">公司详情图片：</td>
							<td colspan="3">
							<div style="margin:10px 0px 10px 0px;">
							<div style="height:60px;width:450px;float:left;">
							<!-- <input type="file" id="displayPicUrl1" name="displayPicUrl1" style="width:400px;margin-left:30px;"/> -->
							<input type="hidden" name="picFlag1" id="picFlag1" value="${entity.displayPicUrl1 }" />
							<input onchange="viewUploadImg(this,'viewPortrait1')" type="file" id="displayPicUrl1" name="displayPicUrl1" tabindex="8"
								style="width: 350px; margin-left: 30px;" />
							<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deleteSubmit1()" />
							<img src="${ftpServerIp}${entity.displayPicUrl1 }"
							<c:if test="${empty entity.displayPicUrl1}">style="display:none;width:50px;height:50px;"</c:if> 
								style="width:50px;height:50px;" id="viewPortrait1"/>
							</div>	
							<div style="height:60px;width:450px;float:left;">
							<!-- <input type="file" id="displayPicUrl2" name="displayPicUrl2" style="width:400px;margin-left:30px;"/> -->
							<input type="hidden" name="picFlag2" id="picFlag2" value="${entity.displayPicUrl2 }" />
							<input onchange="viewUploadImg(this,'viewPortrait2')" type="file" id="displayPicUrl2" name="displayPicUrl2" tabindex="8"
								style="width: 350px; margin-left: 120px;" />
							<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deleteSubmit2()" />
							<img src="${ftpServerIp}${entity.displayPicUrl2 }" 
							<c:if test="${empty entity.displayPicUrl2}">style="display:none;width:50px;height:50px;"</c:if> 
								style="width:50px;height:50px;" id="viewPortrait2"/>
							</div>
							</div>
							
							<div style="margin:10px 0px 10px 0px;">
							<div style="height:60px;width:450px;float:left;">
							<input type="hidden" name="picFlag3" id="picFlag3" value="${entity.displayPicUrl3 }" />
							<!-- <input type="file" id="displayPicUrl3" name="displayPicUrl3" style="width:400px;margin-left:30px;"/> -->
							<!-- <input type="file" id="displayPicUrl4" name="displayPicUrl4" style="width:400px;margin-left:30px;"/> -->
							<input onchange="viewUploadImg(this,'viewPortrait3')" type="file" id="displayPicUrl3" name="displayPicUrl3" tabindex="8"
								style="width: 350px; margin-left: 30px;" />
							<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deleteSubmit3()" />
							<img src="${ftpServerIp}${entity.displayPicUrl3 }"
							<c:if test="${empty entity.displayPicUrl3}">style="display:none;width:50px;height:50px;"</c:if>  
								style="width:50px;height:50px;" id="viewPortrait3"/>
							</div>
							<div style="height:60px;width:450px;float:left;">	
							<input type="hidden" name="picFlag4" id="picFlag4" value="${entity.displayPicUrl4 }" />	
							<input onchange="viewUploadImg(this,'viewPortrait4')" type="file" id="displayPicUrl4" name="displayPicUrl4" tabindex="8"
								style="width: 350px; margin-left: 120px;" />
							<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deleteSubmit4()" />
							<img src="${ftpServerIp}${entity.displayPicUrl4 }"
							<c:if test="${empty entity.displayPicUrl4}">style="display:none;width:50px;height:50px;"</c:if>  
								style="width:50px;height:50px;" id="viewPortrait4"/>
							</div>
							</div>
							
							<div style="margin:10px 0px 10px 0px;">
							<div style="height:60px;width:450px;float:left;">
							<!-- <input type="file" id="displayPicUrl5" name="displayPicUrl5" style="width:400px;margin-left:30px;"/> -->
							<input type="hidden" name="picFlag5" id="picFlag5" value="${entity.displayPicUrl5 }" />	
							<input onchange="viewUploadImg(this,'viewPortrait5')" type="file" id="displayPicUrl5" name="displayPicUrl5" tabindex="8"
								style="width: 350px; margin-left: 30px;" />
							<input type="button" value="删除" class="btn btn-info " style="width:80px;" onclick="deleteSubmit5()" />
							<img src="${ftpServerIp}${entity.displayPicUrl5 }" 
							<c:if test="${empty entity.displayPicUrl5}">style="display:none;width:50px;height:50px;"</c:if>  
								style="width:50px;height:50px;" id="viewPortrait5"/>
							</div>
							</div>
							</td>
						</tr>
						<%-- <tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">是否置顶：</td>
							<td>
							<input type="text" id="isTop" name="entity.isTop"  value="${entity.isTop }"  tabindex="18" style="width:400px;margin-left:30px;"/>
							<span id="isTopMsg" style="margin-left:15px;"></span>
							</td>
						</tr>
						<tr>
							<td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">显示优先级：</td>
							<td>
							<input type="text" id="displayPriority" name="entity.displayPriority"  value="${entity.displayPriority }"  tabindex="19" style="width:400px;margin-left:30px;"/>
							<span id="displayPriorityMsg" style="margin-left:15px;"></span>
							</td>
						</tr> --%>
	            	  	
	            	  	<!-- 
	            	  	<tr>
			                <td width="200px" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">orgid：</td>
			                <td>
			    				<input type="hidden" id="id" name="config.id" value="${config.id }" />
			                	<input type="text" id="orgid" name="config.orgid" value="${config.orgid }" tabindex="1" style="width:400px;margin-left:30px;"/>
			                	<span id="orgidMsg" style="margin-left:15px;"></span>
			                </td>
		              	</tr>
						-->
	            	</table>
	            	
	         	</div>	         	
			</div>
		</form>

		<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/shop!listData.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="groupId" value="${groupId}" />
		</form>
	</div>
</div>
</body>
</html>
