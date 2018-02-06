<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/jsp/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<script type="text/javascript">
	function setMainContent(url){
		var showMain = $("#showMain");
		showMain.attr("src","${pageContext.request.contextPath}/"+url);
		$("#ieprompt").hide();
		$("#showMain").show();
	}
</script>
<div class="left-side sidebar-offcanvas">                
    <!-- sidebar: style can be found in sidebar.less -->
    <div class="sidebar" style="display: block;">
        <!-- Sidebar user panel -->
        <div class="user-panel">
            <div class="pull-left image">
                <img src="<%=basePath%>img/cms/avatar3.png" class="img-circle" alt="User Image" />
            </div>
            <div class="pull-left info">
            	<p></p>
                <a href="javascript:void(0);" style="font-size: 14px;">
                	<i class="fa fa-circle text-success"></i>${dealer_user.realName }</a>
            </div>
        </div>
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i>
                    <span>综合业务管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">

                   	<li><a href="javascript:void(0)" onclick="setMainContent('shop_listData.action');">
                   	<i class="fa fa-angle-double-right"></i>店铺管理</a></li>
					
                	<li><a href="javascript:void(0)" onclick="setMainContent('expertTop_listData.action');">
                    	<i class="fa fa-angle-double-right"></i>达人管理</a></li>
                    <li><a href="javascript:void(0)" onclick="setMainContent('article_listData.action');">
                    	<i class="fa fa-angle-double-right"></i>文章管理</a></li>
                    <li><a href="javascript:void(0)" onclick="setMainContent('homeCarousel_listData.action');">
                    	<i class="fa fa-angle-double-right"></i>广告管理</a></li>
                    <li><a href="javascript:void(0)" onclick="setMainContent('promotion_listData.action');">
                    	<i class="fa fa-angle-double-right"></i>促销管理</a></li>
                   	<li><a href="javascript:void(0)" onclick="setMainContent('selfDrivingTrowelling_listData.action');">
                    	<i class="fa fa-angle-double-right"></i>自驾游管理</a></li>
                </ul>
            </li>
        </ul>
    </div>
    <!-- /.div -->
</div>