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
	<title>新增</title>
	<link rel="stylesheet" href="<%=path %>/css/pubmain.css" />
	<link href="<%=path %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=path %>/css/global.css" rel="stylesheet" type="text/css"/>
	<link href="<%=path %>/js/webuploader/webuploader.css" rel="stylesheet" type="text/css"/>
    <link href="<%=path %>/js/webuploader/preview.css" rel="stylesheet" type="text/css"/>
    
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
    <script type="text/javascript" src="<%=path%>/js/webuploader/webuploader.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/webuploader/previewSmall.js"></script>
    <script type="text/javascript" src="<%=path%>/js/webuploader/previewBig.js"></script>
    <script type="text/javascript" src="<%=path%>/js/webuploader/previewDetail.js"></script>
<script type="text/javascript">
function getBrandByCtegory(ele) {
	$("#brandId").empty();
	var optA="<option value='-1'>请选择品牌</option>";
	$("#brandId").append(optA);
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
   	//添加
	function addSubmit(){
   		/* var title=$.trim($("#title").val());
   		if(!title){
   			layer.tips('请输入广告标题！', '#title');
   			return false;
   		}
   		var url=$.trim($("#url").val());
		if(!url){
			layer.tips('请输入广告链接！', '#url');
			return false;
   		} */
		$("#addForm").submit();	
	}
    //返回
  	function turnBack(){
  		$("#backForm").submit();
  	}
  </script>
</head>
<body>

<div id="middle">
	<div class="right"  id="mainFrame">
		<form action="${pageContext.request.contextPath}/goods_add.action" method="post" id="addForm">
			<div class="content-box">
				<div class="content-box-header">
			    	<span class="now_location">当前位置:</span>添加
			        <div class="clear"></div>
			    </div>
		   		<div style="margin:0 auto; margin:10px;">
	            	<table class="table table-bordered" >
						<!-- 数据录入区  -->
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品品牌：</td>
							<td width="40%">
								<input type="hidden" name="entity.categoryId" value="${addCategoryId}" />
								<select id="brandId" name="entity.brandId" style="height:25px;margin-left:30px;width:200px;">
									<c:forEach var="brand" items="${brandList}">
										<option value="${brand.id }">
											${brand.cnName}
										</option>
									</c:forEach>
								</select>
							</td>
							<td width="10%"  align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px"></td>
							<td width="40%" >
								
							</td>
						</tr>
						
						<c:forEach var="spec" items="${specList}">
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">${spec.name}：</td>
							<td colspan="3">
								<c:forEach var="specItem" items="${spec.items}" varStatus="index">
									<label style="display: inline;<c:if test="${index.index==0}">margin-left: 25px;</c:if>" >
			                			<input type="radio" name="${specItem.specId}" value="${specItem.id}" 
			                				<c:if test="${index.index==0}">checked='checked'</c:if> />
			                			<span>${specItem.item} </span>
			                		</label>
								</c:forEach>
							</td>
						</tr>
						</c:forEach>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品标题：</td>
							<td colspan="3">
								<input name="entity.goodsTitle" type="text" style="margin-left: 30px;width:60%;" />
							</td>
						</tr>
						
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">原价：</td>
							<td>
								<input name="entity.primePrice" type="text" style="margin-left: 30px;width:200px;" />(元)
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">促销价：</td>
							<td>
								<input name="entity.promotionPrice" type="text" style="margin-left: 30px;width:200px;" />(元)
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">进货价：</td>
							<td>
								<input name="entity.costPrice" type="text" style="margin-left: 30px;width:200px;" />(元)
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">积分抵值：</td>
							<td>
								<input name="entity.score" type="text" style="margin-left: 30px;width:200px;" />
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品重量：</td>
							<td>
								<input name="entity.weight" type="text" style="margin-left: 30px;width:200px;" />(kg)
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品产地：</td>
							<td>
								<input name="entity.productArea" type="text" style="margin-left: 30px;width:200px;" />
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">库存：</td>
							<td>
								<input name="entity.stock" type="text" style="margin-left: 30px;width:200px;" />
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">已售数量：</td>
							<td>
								<input name="entity.soldNumber" type="text" style="margin-left: 30px;width:200px;" />
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">设置搜索标签：</td>
							<td>
								<input name="entity.searchTag" type="text" style="margin-left: 30px;width:200px;" />
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品状态：</td>
							<td>
								<label style="margin-left: 35px; display: inline;">
					              <input type="radio" name="entity.status" checked="checked" value="0" style="margin:0px"/>
					              <span style="margin-left: 10px;">上架</span>
					             </label>
					             <label style="margin-left: 25px;display: inline;">
					               <input type="radio" name="entity.status" value="1" style="margin:0px"/>
					                <span style="margin-left: 10px;">下架</span>
					             </label>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">热门产品：</td>
							<td>
								<label style="margin-left: 35px; display: inline;">
			                		<input type="radio" name="entity.isHot" checked="checked" value="Y" style="margin:0px"/>
			                		<span style="margin-left: 10px;">是</span>
			                	</label>
			                	<label style="margin-left: 25px;display: inline;">
			                		<input type="radio" name="entity.isHot" value="N" style="margin:0px"/>
			                		<span style="margin-left: 10px;">否</span>
			                	</label>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">新品：</td>
							<td>
								<label style="margin-left: 35px; display: inline;">
			                		<input type="radio" name="entity.isNew" checked="checked" value="Y" style="margin:0px"/>
			                		<span style="margin-left: 10px;">是</span>
			                	</label>
			                	<label style="margin-left: 25px;display: inline;">
			                		<input type="radio" name="entity.isNew" value="N" style="margin:0px"/>
			                		<span style="margin-left: 10px;">否</span>
			                	</label>
							</td>
						</tr>
						
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品简介：</td>
							<td colspan="3">
								<textarea name="entity.goodsSynopsis" style="height:50px;margin-left: 30px;width:60%;"></textarea>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品描述：</td>
							<td colspan="3">
								<textarea name="entity.goodsDescription" style="height:70px;margin-left: 30px;width:60%;"></textarea>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品小图：</td>
							<td colspan="3">
								<div class="container"  style="margin-left: 30px">
						            <div id="uploaderSmall" class="uploader">
						                <div class="queueList">
						                    <div id="dndAreaSmall" class="placeholder">
						                        <div id="filePickerSmall"></div>
						                    </div>
						                </div>
						                <div class="statusBar" style="display:none;">
						                    <div class="progress">
						                        <span class="text">0%</span>
						                        <span class="percentage"></span>
						                    </div>
						                    <div class="info"></div>
						                    <div class="btns">
						                        <div id="filePickerSmall2" class="filePicker2"></div>
						                        <div class="uploadBtn">开始上传</div>
						                    </div>
						                </div>
						            </div>
						        </div>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品大图：</td>
							<td colspan="3">
								<div class="container"  style="margin-left: 30px">
						            <div id="uploaderBig" class="uploader">
						                <div class="queueList">
						                    <div id="dndAreaBig" class="placeholder">
						                        <div id="filePickerBig"></div>
						                    </div>
						                </div>
						                <div class="statusBar" style="display:none;">
						                    <div class="progress">
						                        <span class="text">0%</span>
						                        <span class="percentage"></span>
						                    </div>
						                    <div class="info"></div>
						                    <div class="btns">
						                        <div id="filePickerBig2" class="filePicker2"></div>
						                        <div class="uploadBtn">开始上传</div>
						                    </div>
						                </div>
						            </div>
						        </div>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">商品详情图：</td>
							<td colspan="3">
								<div class="container"  style="margin-left: 30px">
						            <div id="uploaderDetail" class="uploader">
						                <div class="queueList">
						                    <div id="dndAreaDetail" class="placeholder">
						                        <div id="filePickerDetail"></div>
						                    </div>
						                </div>
						                <div class="statusBar" style="display:none;">
						                    <div class="progress">
						                        <span class="text">0%</span>
						                        <span class="percentage"></span>
						                    </div>
						                    <div class="info"></div>
						                    <div class="btns">
						                        <div id="filePickerDetail2" class="filePicker2"></div>
						                        <div class="uploadBtn">开始上传</div>
						                    </div>
						                </div>
						            </div>
						        </div>
							</td>
						</tr>
	            	</table>
	            	 <table  class="margin-bottom-20 table  no-border" style="margin-top:20px;">
			              <tr>
			     	         <td align="right">
			     	         	<input type="button" value="保存" class="btn btn-info " style="width:80px;" onclick="addSubmit()" />
			     	         </td>
			     	         <td align="center">
			     	         	<input type="button" value="返回" class="btn btn-info " style="width:80px;"  onclick="turnBack()"/>
			     	         </td>
			     	         <td></td>
			             </tr>
			        </table>
	         	</div> 
			</div>
		</form>
			
		<!-- 返回，记录列表页数据 -->
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/goods_list.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="categoryId" value="${categoryId}" />
			<input type="hidden" name="brandId" value="${brandId}" />
			<input type="hidden" name="title" value="${title}" />
			<input type="hidden" name="beginTime" value="${beginTime}" />
			<input type="hidden" name="endTime" value="${endTime}" />
		</form>
	</div>
</div>
</body>
</html>
