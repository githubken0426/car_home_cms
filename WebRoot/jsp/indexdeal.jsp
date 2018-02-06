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
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>顺驾天下经销商平台管理系统</title>
		<meta
			content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no'
			name='viewport' />
		<!-- bootstrap 3.0.2 -->
		<link href="<%=basePath%>css/cms/bootstrap.min.css" rel="stylesheet"
			type="text/css" />
		<!-- font Awesome -->
		<link href="<%=basePath%>css/cms/font-awesome.min.css"
			rel="stylesheet" type="text/css" />
		<!-- Ionicons -->
		<link href="<%=basePath%>css/cms/ionicons.min.css" rel="stylesheet"
			type="text/css" />
		<!-- Theme style -->
		<link href="<%=basePath%>css/cms/AdminLTE.css" rel="stylesheet"
			type="text/css" />
		<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
       	<![endif]-->
		<%--<script type="text/javascript" src="<%=basePath%>js/wonderful.cms.js"></script>--%>
<style>
.nav>li>a:hover,.nav>li>a:focus {
	text-decoration: none;
	background-color: #367fa9
}

.nav .open a:focus {
	background-color: #367fa9;
}

.navbar {
	position: relative;
	min-height: 48px;
	margin-bottom: 20px;
	border: 1px solid transparent
}

.hide {
	display: none;
}
</style>
	</head>
	<body class="skin-blue">
		<!-- header logo: style can be found in header.less -->
		<jsp:include page="headerdeal.jsp" flush="true" />
		<div class="wrapper row-offcanvas row-offcanvas-left">
			<!-- Left side column. contains the logo and sidebar -->
			<jsp:include page="leftdeal.jsp" flush="true" />
			<!-- .right-side -->
			<div class="right-side">
				<div id="ieprompt"
					style="width:100%;height:600px;background:url('${pageContext.request.contextPath}/img/ieprompt.png') no-repeat center center;"></div>
				<iframe id="showMain" frameborder="no" border="0"
					style="width: 100%; height: 700px;"></iframe>
			</div>

		</div>
		<!-- ./wrapper -->

		<!-- jQuery 2.0.2 -->
		<%-- <script src="<%=basePath%>js/cms/jquery.min.js"></script> --%>
		<script src="<%=basePath%>js/jquery1.9.0.min.js"></script>
		<!-- Bootstrap -->
		<script src="<%=basePath%>js/cms/bootstrap.min.js"
			type="text/javascript"></script>
		<!-- AdminLTE App -->
		<script src="<%=basePath%>js/cms/AdminLTE/app.js"
			type="text/javascript"></script>
		<script>$("#showMain").hide();
	
	
	</script>
	</body>
</html>