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
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
    <script type="text/javascript" src="<%=path%>/js/cms/view_image/view_image.js"></script>
    <script type="text/javascript" src="<%=path%>/js/layer/layer.js"></script>
<script type="text/javascript">
   	//添加
	function addSubmit(){
   		var title=$.trim($("#title").val());
   		if(!title){
   			layer.tips('请输入分类标题！', '#title');
   			return false;
   		}
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
		<form enctype="multipart/form-data" action="${pageContext.request.contextPath}/category_add.action" method="post" id="addForm">
			<div class="content-box">
				<div class="content-box-header">
			    	<span class="now_location">当前位置:</span>添加
			        <div class="clear"></div>
			    </div>
		   		<div style="margin:0 auto; margin:10px;">
	            	<table class="table table-bordered" id="myTable" >
						<!-- 数据录入区  -->
						<tr>
							<td width="10%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">分类标题：</td>
							<td width="90%" >
								<input type="text" id="title" name="entity.title" tabindex="1" maxlength="100" style="font-size:14px;padding:8px;width:400px;margin-left:30px;"/>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">分类描述：</td>
							<td >
								<input type="text" id="descriptiion" name="entity.descriptiion" tabindex="1" maxlength="100" style="font-size:14px;padding:8px;width:400px;margin-left:30px;"/>
							</td>
						</tr>
						<tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">分类图片：</td>
							<td colspan="3">
								<input onchange="viewUploadImg(this,'viewResUrlList')" type="file" 
									id="resUrlList" name="resUrlList" tabindex="4" maxlength="300" style="padding:4px;width:405px;margin-left:30px;"/>
								<img style="width:50px;height:50px;display:none;" id="viewResUrlList"/>
							</td>
						</tr>
						<!-- <tr>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">规格：</td>
							<td>
								<input type="text" name="specs" maxlength="40" style="font-size:14px;padding:8px;width:200px;margin-left:30px;"/>
								<input onclick="appendSpec()" type="button" value="追加规格" class="btn btn-info " style="width:80px;"/>
							</td>
							<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">规格属性：</td>
							<td>
								 <table class="table table-bordered" style="margin-bottom:0px;">
			                      <tr>
					     	         <td align="left">
					     	         	<input type="text" name="item" maxlength="40" style="font-size:14px;padding:8px;width:200px;margin-left:30px;"/>
					     	         	<input onclick="appendItem(this)" type="button" value="追加属性" class="btn btn-info " style="width:80px;"/>
					     	         </td>
					             </tr>
					        	</table>
							</td>
						</tr> -->
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
		<form id="backForm" method="post" action="${pageContext.request.contextPath}/category_list.action">
			<input type="hidden" name="pno" value="${currentIndex}" />
			<input type="hidden" name="title" value="${title}" />
		</form>
	</div>
</div>
<script type="text/javascript">
	function appendSpec(){ 
		//var tr=$("#myTable").find("tr:last");
		var tr="";
		tr+="<tr><td></td>";
		tr+="<td>";
			tr+="<input type='text' name='specs' maxlength='40' style='font-size:14px;padding:8px;width:200px;margin-left:30px;'/>"
			tr+="<img src='<%=path%>/img/cms/del.png' onclick='deleteTR(this)' style='margin-left:10px;cursor:pointer;'/>";
		tr+="</td>";
		//td
		tr+="<td></td>";
		tr+="<td>";
		tr+="<table class='table table-bordered' style='margin-bottom:0px;'>";
			tr+="<tr><td align='left'>"
			tr+="<input type='text' name='item' maxlength='40' style='font-size:14px;padding:8px;width:200px;margin-left:30px;'/> "
			tr+="<input onclick='appendItem(this)' type='button' value='追加属性' class='btn btn-info' style='width:80px;'/>";
		tr+="</td></tr>";
		tr+="</table>";
		tr+="</td>";
		
		tr+="<td></td></tr>";
		$("#myTable").append(tr);  
	} 
	
	function deleteTR(ele){
		ele.parentNode.parentNode.remove()
	}
	
	function appendItem(ele){
		var table=ele.parentNode.parentNode.parentNode;
		var tr="";
		tr+="<tr><td>";
		tr+="<input type='text' name='item' maxlength='40' style='font-size:14px;padding:8px;width:200px;margin-left:30px;'/>"
		tr+="<img src='<%=path%>/img/cms/del.png' onclick='deleteTR(this)' style='margin-left:10px;cursor:pointer;'/>";
		tr+="</td></tr>";
		$(table).append(tr);  
	}
</script>
</body>
</html>