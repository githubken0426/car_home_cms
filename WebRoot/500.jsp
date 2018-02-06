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
	<title>沃精彩平台管理系统</title>
        <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'/>
        <!-- bootstrap 3.0.2 -->
        <link href="<%=basePath%>css/cms/bootstrap.min.css" rel="stylesheet" type="text/css" />
        <!-- font Awesome -->
        <link href="<%=basePath%>css/cms/font-awesome.min.css" rel="stylesheet" type="text/css" />
        <!-- Ionicons -->
        <link href="<%=basePath%>css/cms/ionicons.min.css" rel="stylesheet" type="text/css" />
        <!-- Theme style -->
        <link href="<%=basePath%>css/cms/AdminLTE.css" rel="stylesheet" type="text/css" />
		<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.3.0/respond.min.js"></script>
       	<![endif]-->
		<style>
		.nav>li>a:hover,.nav>li>a:focus{text-decoration:none;background-color:#367fa9}.nav .open a:focus{background-color:#367fa9;}.navbar{position:relative;min-height:48px;margin-bottom:20px;border:1px solid transparent}
		.hide{display:none;}
		</style>
    </head>
    <body class="skin-blue">
        <!-- Right side column. Contains the navbar and content of the page -->
        <aside class="right-side" style="margin-left:0;">
                <!-- Content Header (Page header) -->
                <section class="content-header">
                    <h1>
                        500 Error Page
                    </h1>
                </section>

                <!-- Main content -->
                <section class="content">
                 
                    <div class="error-page">
                        <h2 class="headline">500</h2>
                        <div class="error-content" style="margin-top:40px;">
                            <h3><i class="fa fa-warning text-yellow"></i> 哎呀！系统出错了。</h3>
                            <p>
                               	 我们会尽快解决此问题。
                            </p>
                        </div>
                    </div><!-- /.error-page -->

                </section><!-- /.content -->
		</aside>
		
		
		
		
        <!-- jQuery 2.0.2 -->
        <%-- <script src="<%=basePath%>js/cms/jquery.min.js"></script> --%>
        <script src="<%=basePath%>js/jquery1.9.0.min.js"></script>
        <!-- Bootstrap -->
        <script src="<%=basePath%>js/cms/bootstrap.min.js" type="text/javascript"></script>
        <!-- AdminLTE App -->
        <script src="<%=basePath%>js/cms/AdminLTE/app.js" type="text/javascript"></script>
    </body>
</html>