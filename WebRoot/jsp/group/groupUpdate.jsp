<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/jsp/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<%response.setHeader("Pragma","No-cache");
response.setHeader("Cache-Control","no-cache");
response.setDateHeader("Expires", 0);
response.flushBuffer();%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>修改用户组</title>
	<link rel="stylesheet" href="<%=path %>/css/pubmain.css" />
	<link href="<%=request.getContextPath() %>/css/commen.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath() %>/css/global.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="<%=path %>/js/jquery1.9.0.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/wonderful.cms.js"></script>
   	
  <script type="text/javascript">
   var nameA = true;
  $(function(){
  	//判断用户组是否存在
	  $("#groupName").bind({
	  	focus:function(){
	  		$("#nameMsg").html("");
	  	},blur:function(){
	  		var groupName = $.trim($("#groupName").val());
	  		var groupId=$.trim($("#groupId").val());
			if(groupName.length >0){
				$.ajax({
			        type:"POST",  
			        dataType:"json",  
			        data:{name:groupName,id:groupId},
			        async:true,
			        url: "${pageContext.request.contextPath}/group_getByName.action",  
			        success: function (data) {
			        	if(data==1){
			        		$("#nameMsg").html("<font color='red'>* 已存在!</font>");
			        		nameA = false;
			        	}else{
			        		$("#nameMsg").html("<font color='green'>* 可用!</font>");
			        		nameA = true;
			        	}
			        } 
			    });
			}else{
				$("#nameMsg").html("<font color='red'>* 不能为空!</font>");
				nameA = false;
			}
			return nameA;
	  	}
  	});
  	
});
   //提交
   function doSubmit(){
   	  if(nameA){
   	  	$("#updateForm").submit();
   	  }else{
   	  	alert("信息输入有误，无法保存！");
   	  }
    }
  	//table隔行换色
  	window.onload=function changeTableColor(){
		var tr=$("#functionTable tr");
       	for(var i=0;i<tr.length;i++){
       		if(tr[i].rowIndex % 2==0)
         		tr[i].style.background="#f1f1f1";
       	}
      }
      
      //全选
	function selectAll(){
		var checkboxs=$("input[name='funcid']");
		 for (var i=0;i<checkboxs.length;i++) {
		  if(!(checkboxs[i].checked)){
		  	checkboxs[i].checked=true;
		  }
		 }
	}
	//反选
	function inverseAll() {
	 	var checkboxs=$("input[name='funcid']");
	 	for (var i=0;i<checkboxs.length;i++) {
	  		var e=checkboxs[i];
	  		e.checked=!e.checked;
	 	}
	}
	
	//父权限
	function fatherChecked(index){
		var fatherCheckBoxs=$("#father"+index);
		var checkboxs=$("input[class='son"+index+"']");
	 	for (var i=0;i<checkboxs.length;i++) {
	  		var e=checkboxs[i];
	  		e.checked=fatherCheckBoxs[0].checked;
	 	}
	}
	//子权限，子权限选中，父权限也要被选中
	function sonChecked(index,sonIndex){
		var isCheck=false;
		var sonCheckBoxs=$("input[class='son"+index+"']");
	 	for (var i=0;i<sonCheckBoxs.length;i++) {
	  		if(sonCheckBoxs[i].checked){
	  			isCheck=true;
	  		}
	 	}
	 	var sonerCheckBoxs=$("input[class='soner"+sonIndex+"']");
	 	for (var i=0;i<sonerCheckBoxs.length;i++) {
	  		sonerCheckBoxs[i].checked =sonCheckBoxs[0].checked;
	 	}
		var fatherCheckBoxs=$("#father"+index);
	 	fatherCheckBoxs[0].checked=isCheck;
	}
	/*
		(子子权限)子权限下的小权限
		子子权限选中后，父权限和子权限也要被选中
	*/
	function sonerChecked(fatherIndex,sonIndex){
		var isCheck=false;
		var sonerCheckBoxs=$("input[class='soner"+sonIndex+"']");
	 	for (var i=0;i<sonerCheckBoxs.length;i++) {
	  		if(sonerCheckBoxs[i].checked){
	  			isCheck=true;
	  		}
	 	}
	 	var sonCheckBoxs=$("#son"+sonIndex);
	 	for (var i=0;i<sonCheckBoxs.length;i++) {
	  		if(sonCheckBoxs[i].checked){
	  			isCheck=true;
	  		}
	 	}
	 	sonCheckBoxs[0].checked=isCheck;
	 	var fatherCheckBoxs=$("#father"+fatherIndex);
	 	fatherCheckBoxs[0].checked=isCheck;
	}
  </script>
  </head>
  
  <body>
   	<div id="middle">
  		<div class="right"  id="mainFrame">
			<form action="${pageContext.request.contextPath}/group_updateGroup.action" method="post" id="updateForm">
			    <div class="content-box">
				    <div class="content-box-header">
				    	<span class="now_location">当前位置:</span> 修改用户组
				        <div class="clear"></div>
				    </div>
			   		<div style="width:100%; margin:0 auto; margin-top:30px;">
			            <table class="table table-bordered" >
			              <tr>
			                <td width="12%" align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">组名：</td>
			                <td >
			                	<input type="text" name="group.groupName" value="${group.groupName}" id="groupName" tabindex="1"  style="width:200px;margin-left:30px;"/>
			                	<span id="nameMsg" style="margin-left:15px;"></span>
			                	<input type="hidden" id="groupId" name="group.id" value="${group.id }"/>
			                </td>
			              </tr>
			              <tr>
			              	<td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">权限：</td>
			              	<td align="left" nowrap="nowrap" height="30px">
			              		<div style="margin-left:35px;font:bold 16px ;">
									<a href="javascript:void(0);" onclick="selectAll()">全&nbsp;选</a>
									<a href="javascript:void(0);" onclick="inverseAll()"style="margin-left:20px">反&nbsp;选</a>
								</div>
			              	</td>
			              </tr>
			              <tr>
			                <td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px"></td>
			                <td>
			                	<table class="table table-bordered" id="functionTable">
			                		<c:forEach items="${functionList}" var="f" varStatus="fatherIndex">
										<c:if test="${f.parentFuncId == '0'}">
											<tr>
												<td height="28" width="15%">
													<label style="cursor:pointer; font-size:16px;margin-left:28px;" >
														<input id="father${fatherIndex.index }" onclick="fatherChecked('${fatherIndex.index }')" name="funcid" type="checkbox" value="${f.id}" style="margin: 0px;"
														<c:forEach var="func" items="${groupFuncIds}">
															<c:if test="${f.id==func}">checked="checked"</c:if>
														</c:forEach> />
														<span class=" margin-bottom-5">${f.funcName}</span>
													</label>
													<c:forEach items="${functionList}" var="funSmall" varStatus="sonIndex">
														<c:if test="${ f.id == funSmall.parentFuncId}">
															<label style="cursor:pointer;font-size:13px;margin-left:10px; ">
															 	<input id="son${sonIndex.index }" class="son${fatherIndex.index }" onclick="sonChecked('${fatherIndex.index }','${sonIndex.index }')"  name="funcid" type="checkbox" value="${funSmall.id}" style="margin: 0px;"
															 	<c:forEach var="func" items="${groupFuncIds}">
																	<c:if test="${funSmall.id==func}">checked="checked"</c:if>
																</c:forEach>/>
															 	<span class=" margin-bottom-5">${funSmall.funcName}</span>
															</label>
															<c:forEach items="${functionList}" var="funSmaller" >
																<c:if test="${funSmall.id == funSmaller.parentFuncId }">
																	<label style="cursor:pointer;font-size:13px;margin-left:10px; ">
																	 	<input class="soner${sonIndex.index }" onclick="sonerChecked('${fatherIndex.index }','${sonIndex.index }')" name="funcid" type="checkbox" value="${funSmaller.id}" style="margin: 0px;"
																	 	<c:forEach var="func" items="${groupFuncIds}">
																			<c:if test="${funSmaller.id==func}">checked="checked"</c:if>
																		</c:forEach>/>
																	 	<span class=" margin-bottom-5">${funSmaller.funcName}</span>
																	</label>
																</c:if>
															</c:forEach>
														</c:if>
													</c:forEach>
												</td>
											</tr>		
										</c:if>
									</c:forEach>
			                	</table>
			                </td>
			              </tr>
			              <tr>
			                <td align="right" nowrap="nowrap" bgcolor="#f1f1f1" height="40px">备注：</td>
			                <td>
			                	<textarea id="roupContent" name="group.groupContent"  style="width:700px;height:80px;margin-left:30px;resize: none;">${group.groupContent} </textarea>
			                </td>
			              </tr>
			              </table>
			              <table  class="margin-bottom-20 table  no-border" style="margin-top:50px;">
			                <tr>
			     	         <td class="text-center">
			     	         	<input type="button" value="确定" class="btn btn-info " style="width:80px;" onclick="doSubmit()" />
			     	         </td>
			     	         <td align="left">
			     	         	<input type="button" value="返回" class="btn btn-info " style="width:80px;"  onclick="history.back()"/>
			     	         </td>
			     	         <td></td>
			               </tr>
			            </table>
			         </div> 
				</div>
			</form>
	  </div>
    </div>
  </body>
</html>
