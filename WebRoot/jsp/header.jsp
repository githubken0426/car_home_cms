<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="/jsp/common/common.jsp"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<div class="header">
            <a href="javascript:void(0);" class="logo">
                <!-- Add the class icon to your logo image or logo icon to add the margining -->
            </a>
            <!-- Header Navbar: style can be found in header.less -->
            <div class="navbar navbar-static-top" role="navigation">
                <!-- Sidebar toggle button-->
                <a href="#" class="navbar-btn sidebar-toggle" data-toggle="offcanvas" role="button">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </a>
                <div class="navbar-right">
                    <ul class="nav navbar-nav">
                        <!-- 退出登录 -->
                        <li class="dropdown user user-menu">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                                <i class="glyphicon glyphicon-user"></i>
                                <span> <i class="caret"></i></span>
                            </a>
                            <ul class="dropdown-menu">
                                <!-- User image -->
                                <li class="user-header bg-light-blue">
                                    <img src="<%=basePath%>img/cms/avatar3.png" class="img-circle" alt="User Image" />
                                    <p style="text-shadow: none;">
                                        ${login_user.realName }
                                        <small id="currentTime"></small>
                                        <%--<fmt:formatDate value="${login_user.loginTime}" pattern="yyyy年MM月dd日 HH:mm"/>--%>
                                    	<script type="text/javascript">
                                    	function getTime(){
                                    		var dTime=document.getElementById('currentTime');
                                    		var objDate=new Date();
                                    		var year=objDate.getFullYear();
                                    		var month=objDate.getMonth()+1;
                                    			if(month<10)month="0"+month;
                                    		var day=objDate.getDate();
                                    			if(day<10)day="0"+day;
                                    		var hour=objDate.getHours();
                                    			if(hour<10)hour="0"+hour;
                                    		var minutes=objDate.getMinutes();
                                    			if(minutes<10)minutes="0"+minutes;
                                    		var seconds=objDate.getSeconds();
                                    			if(seconds<10)seconds="0"+seconds;
                                    		var currentTime=year+"/"+month+"/"+day;
                                    		currentTime+=' 星期'+'日一二三四五六'.charAt(new Date().getDay())+" ";
                                    		if(hour>=0 && hour<=12){
                                    			currentTime+="上午";
                                    		}
                                    		else{
                                    			currentTime+="下午";
                                        	}
                                    		currentTime+=hour+":";
                                    		currentTime+=minutes+":";
                                    		currentTime+=seconds;
                                    		
                                    		dTime.innerHTML=currentTime;
                                        }
                                		setInterval("getTime()",1000);
                                    	</script>
                                    </p>
                                </li>
                                <!-- Menu Body 
                                <li class="user-body">
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Followers</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Sales</a>
                                    </div>
                                    <div class="col-xs-4 text-center">
                                        <a href="#">Friends</a>
                                    </div>
                                </li>-->
                                <!-- Menu Footer-->
                                <li class="user-footer">
                                    <div class="pull-left">
                                        <a href="javascript:void(0);"onclick="setMainContent('user_changePasswordPage.action');" class="btn btn-default btn-flat">修改密码</a>
                                    </div>
                                    <div class="pull-right">
                                        <a href="javascript:void(0);" onclick="window.location.href='signin_logOut.action'" class="btn btn-default btn-flat">退出登录</a>
                                    </div>
                                </li>
                            </ul>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
