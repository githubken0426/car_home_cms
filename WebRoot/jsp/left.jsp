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
                	<i class="fa fa-circle text-success"></i>${login_user.realName }</a>
            </div>
        </div>
        <!-- sidebar menu: : style can be found in sidebar.less -->
        <ul class="sidebar-menu">
          </li>
         <c:if test="${menu00}">
            <li class="treeview">
                <a href="javascript:void(0);">
                    <i class="fa fa-bar-chart-o"></i>
                    <span>系统管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                	<c:if test="${menu0002}">
                    	<li><a href="javascript:void(0);"onclick="setMainContent('user_queryAllUser.action');"><i class="fa fa-angle-double-right"></i>系统用户</a></li>
                    </c:if>
                    <c:if test="${menu0001}">
                    	<li><a href="javascript:void(0);"onclick="setMainContent('group_queryAllGroup.action');"><i class="fa fa-angle-double-right"></i>用户组管理</a></li>
                	</c:if>
                </ul>
            </li>
            </c:if>
			<c:if test="${menu01}">
            <li class="treeview">
                <a href="#">
                    <i class="fa fa-edit"></i>
                    <span>综合业务管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
					<c:if test="${menu0106}">
                    <li><a href="javascript:void(0)" onclick="setMainContent('city_listData.action');">
                    	<i class="fa fa-angle-double-right"></i>城市管理</a></li>
                    </c:if>	
                	<c:if test="${menu0101}">
                    	<li><a href="javascript:void(0)" onclick="setMainContent('dealerUser_listData.action');">
                    	<i class="fa fa-angle-double-right"></i>经销商管理</a></li>
					</c:if>
					<c:if test="${menu0102}">
						<li><a href="javascript:void(0)" onclick="setMainContent('apiUser_listData.action');">
	                    <i class="fa fa-angle-double-right"></i>普通用户管理</a></li>
                    </c:if>	
                    <c:if test="${menu0103}">
                    <li><a href="javascript:void(0)" onclick="setMainContent('information_listData.action');">
                    	<i class="fa fa-angle-double-right"></i>资讯管理</a></li>
                    </c:if>
                    <c:if test="${menu0107}">
                    <li><a href="javascript:void(0)" onclick="setMainContent('question_listData.action');">
                    	<i class="fa fa-angle-double-right"></i>问题管理</a></li>
                    </c:if>
                    <c:if test="${menu0104}">
                    <li><a href="javascript:void(0)" onclick="setMainContent('version_listData.action');">
                    	<i class="fa fa-angle-double-right"></i>应用版本管理</a></li>
                    </c:if>	
                    <c:if test="${menu0105}">
                    <li><a href="javascript:void(0)" onclick="setMainContent('feedback_listData.action');">
                    	<i class="fa fa-angle-double-right"></i>意见反馈管理</a></li>
                    </c:if>
                </ul>
            </li>
            </c:if>
            
             <li class="treeview">
                <a href="javascript:void(0);">
                    <i class="fa fa-bar-chart-o"></i>
                    <span>商城管理</span>
                    <i class="fa fa-angle-left pull-right"></i>
                </a>
                <ul class="treeview-menu">
                	<c:if test="${menu0002}">
						<li><a href="javascript:void(0);"
							onclick="setMainContent('expert_listData.action');">
							<i class="fa fa-angle-double-right"></i>商城达人管理</a>
						</li>
					</c:if>
					<c:if test="${menu0002}">
						<li><a href="javascript:void(0);"
							onclick="setMainContent('adver_listData.action');">
							<i class="fa fa-angle-double-right"></i>广告管理</a>
						</li>
					</c:if>
					<c:if test="${menu0001}">
						<li><a href="javascript:void(0);"
							onclick="setMainContent('brand_list.action');">
							<i class="fa fa-angle-double-right"></i>品牌管理</a>
						</li>
					</c:if>
					<c:if test="${menu0001}">
						<li><a href="javascript:void(0);"
							onclick="setMainContent('goods_list.action');">
							<i class="fa fa-angle-double-right"></i>商品管理</a>
						</li>
					</c:if>
					<c:if test="${menu0001}">
						<li><a href="javascript:void(0);"
							onclick="setMainContent('order_list.action');">
							<i class="fa fa-angle-double-right"></i>订单管理</a>
						</li>
					</c:if>
				</ul>
            </li>
        </ul>
    </div>
    <!-- /.div -->
</div>