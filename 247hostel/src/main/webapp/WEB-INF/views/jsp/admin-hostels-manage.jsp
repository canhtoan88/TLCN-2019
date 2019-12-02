<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>247 Hostel - Admin</title>

    <meta name="description" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0" />

    <!-- bootstrap & fontawesome -->
    <link rel="stylesheet" href="../resources/ace-master/assets/css/bootstrap.min.css" />
    <link rel="stylesheet" href="../resources/ace-master/assets/font-awesome/4.5.0/css/font-awesome.min.css" />

    <!-- page specific plugin styles -->

    <!-- text fonts -->
    <link rel="stylesheet" href="../resources/ace-master/assets/css/fonts.googleapis.com.css" />

    <!-- ace styles -->
    <link rel="stylesheet" href="../resources/ace-master/assets/css/ace.min.css" class="ace-main-stylesheet" id="main-ace-style" />

    <!--[if lte IE 9]>
			<link rel="stylesheet" href="../resources/ace-master/assets/css/ace-part2.min.css" class="ace-main-stylesheet" />
		<![endif]-->
    <link rel="stylesheet" href="../resources/ace-master/assets/css/ace-skins.min.css" />
    <link rel="stylesheet" href="../resources/ace-master/assets/css/ace-rtl.min.css" />

    <!--[if lte IE 9]>
		  <link rel="stylesheet" href="../resources/ace-master/assets/css/ace-ie.min.css" />
		<![endif]-->

    <!-- inline styles related to this page -->

    <!-- ace settings handler -->
    <script src="../resources/ace-master/assets/js/ace-extra.min.js"></script>

    <!-- HTML5shiv and Respond.js for IE8 to support HTML5 elements and media queries -->

    <!--[if lte IE 8]>
		<script src="../resources/ace-master/assets/js/html5shiv.min.js"></script>
		<script src="../resources/ace-master/assets/js/respond.min.js"></script>
		<![endif]-->

	<style>
		.title {
		  	max-width: 200px;
			text-transform: uppercase;			
		}
		
		.select-censored-0 {
			
		}
		
		.select-censored-1 {
			background-color: #87B87F;
			color: white;
		}
		
		.select-censored--1 {
			background-color: #D15B47;
			color: white;
		}
		
		.select-view {
			text-align: right;
			padding-right: 100px;
			padding-bottom: 10px;
		}
	</style>

    <script>
    	var isCensoredMain = -2;
    	
        function getHostels(page) {

        	$.ajax({
        		type : "GET",
        		url : "http://localhost:8080/247hostel/api/getHostels",
        		data : {
        			page : page,
        			isCensored: isCensoredMain
        		},
        		success : function(value) {
        			
        			if(value !== ""){
        				$("#table-tbody").html(value)
        			}
        		}
        	});
        }

        function updateCensored(idHostel) {
        	var selected = document.getElementById("select-censored-"+idHostel);
        	isCensoredMain = selected.value;
        	var className = "select-censored-" + isCensoredMain;
			selected.setAttribute("class", className);
        	
			//alert(idHostel + "-" + isCensored + "-" + className);
			
			$.ajax({
        		type : "GET",
        		url : "http://localhost:8080/247hostel/api/updateCensored",
        		data : {
        			idHostel: idHostel,
        			isCensored: isCensoredMain
        		},
        		success : function(value) {
        			
        			if(value === "true"){
						alert("Cập nhập thông tin thành công!");
                	}
        			else{
						alert("Cập nhập thông tin thất bại!");
                	}
        		}
        	});
        }

        function changeSelectHostelsView(){
        	var selected = document.getElementById("select-censored-view");
        	isCensoredMain = selected.value;
        	var className = "select-censored-" + isCensoredMain;
			selected.setAttribute("class", className);

			getHostels(1);
        }
    </script>
</head>

<body class="no-skin">
    <div id="navbar" class="navbar navbar-default          ace-save-state">
        <div class="navbar-container ace-save-state" id="navbar-container">
            <button type="button" class="navbar-toggle menu-toggler pull-left" id="menu-toggler" data-target="#sidebar">
                <span class="sr-only">Toggle sidebar</span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>

                <span class="icon-bar"></span>
            </button>

            <div class="navbar-header pull-left">
                <a href="index.html" class="navbar-brand">
                    <small>
                        <i class="fa fa-leaf"></i>
                        247 Hostel
                    </small>
                </a>
            </div>

            <div class="navbar-buttons navbar-header pull-right" role="navigation">
                <ul class="nav ace-nav">
                    <li class="grey dropdown-modal">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <i class="ace-icon fa fa-tasks"></i>
                            <span class="badge badge-grey">4</span>
                        </a>

                        <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                            <li class="dropdown-header">
                                <i class="ace-icon fa fa-check"></i>
                                4 công việc cần hoàn thành
                            </li>

                            <li class="dropdown-content">
                                <ul class="dropdown-menu dropdown-navbar">
                                    <li>
                                        <a href="#">
                                            <div class="clearfix">
                                                <span class="pull-left">Kiểm duyệt nhà trọ</span>
                                                <span class="pull-right">65%</span>
                                            </div>

                                            <div class="progress progress-mini">
                                                <div style="width:65%" class="progress-bar"></div>
                                            </div>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="#">
                                            <div class="clearfix">
                                                <span class="pull-left">Cập nhập nhà trọ</span>
                                                <span class="pull-right">35%</span>
                                            </div>

                                            <div class="progress progress-mini">
                                                <div style="width:35%" class="progress-bar progress-bar-danger"></div>
                                            </div>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="#">
                                            <div class="clearfix">
                                                <span class="pull-left">Cập nhập thông tin cá nhân</span>
                                                <span class="pull-right">55%</span>
                                            </div>

                                            <div class="progress progress-mini">
                                                <div style="width:55%" class="progress-bar progress-bar-warning"></div>
                                            </div>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="#">
                                            <div class="clearfix">
                                                <span class="pull-left">Phản hồi người dùng</span>
                                                <span class="pull-right">90%</span>
                                            </div>

                                            <div class="progress progress-mini progress-striped active">
                                                <div style="width:90%" class="progress-bar progress-bar-success"></div>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </li>

                            <li class="dropdown-footer">
                                <a href="#">
                                    Xem chi tiết nhiệm vụ
                                    <i class="ace-icon fa fa-arrow-right"></i>
                                </a>
                            </li>
                        </ul>
                    </li>

                    <li class="purple dropdown-modal">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <i class="ace-icon fa fa-bell icon-animated-bell"></i>
                            <span class="badge badge-important">8</span>
                        </a>

                        <ul class="dropdown-menu-right dropdown-navbar navbar-pink dropdown-menu dropdown-caret dropdown-close">
                            <li class="dropdown-header">
                                <i class="ace-icon fa fa-exclamation-triangle"></i>
                                8 Thông báo
                            </li>

                            <li class="dropdown-content">
                                <ul class="dropdown-menu dropdown-navbar navbar-pink">
                                    <li>
                                        <a href="#">
                                            <div class="clearfix">
                                                <span class="pull-left">
                                                    <i class="btn btn-xs no-hover btn-pink fa fa-comment"></i>
                                                    Bình luận
                                                </span>
                                                <span class="pull-right badge badge-info">+12</span>
                                            </div>
                                        </a>
                                    </li>

                                    <li>
                                        <a href="#">
                                            <i class="btn btn-xs btn-primary fa fa-user"></i>
                                            Phản hồi
                                        </a>
                                    </li>

                                    <li>
                                        <a href="#">
                                            <div class="clearfix">
                                                <span class="pull-left">
                                                    <i class="btn btn-xs no-hover btn-success fa fa-shopping-cart"></i>
                                                    Online
                                                </span>
                                                <span class="pull-right badge badge-success">+82</span>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                            </li>

                            <li class="dropdown-footer">
                                <a href="#">
                                    Xem tất cả thông báo
                                    <i class="ace-icon fa fa-arrow-right"></i>
                                </a>
                            </li>
                        </ul>
                    </li>

                    <li class="green dropdown-modal">
                        <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <i class="ace-icon fa fa-envelope icon-animated-vertical"></i>
                            <span class="badge badge-success">1</span>
                        </a>

                        <ul class="dropdown-menu-right dropdown-navbar dropdown-menu dropdown-caret dropdown-close">
                            <li class="dropdown-header">
                                <i class="ace-icon fa fa-envelope-o"></i>
                                1 Tin nhắn
                            </li>

                            <li class="dropdown-content">
                                <ul class="dropdown-menu dropdown-navbar">
                                    <li>
                                        <a href="#" class="clearfix">
                                            <img src="../resources/ace-master/assets/images/avatars/avatar.png" class="msg-photo" alt="Alex's Avatar" />
                                            <span class="msg-body">
                                                <span class="msg-title">
                                                    <span class="blue">Nhi:</span>
                                                    Xin chào ...
                                                </span>

                                                <span class="msg-time">
                                                    <i class="ace-icon fa fa-clock-o"></i>
                                                    <span>a moment ago</span>
                                                </span>
                                            </span>
                                        </a>
                                    </li>
                                </ul>
                            </li>

                            <li class="dropdown-footer">
                                <a href="#">
                                    Xem tất cả tin nhắn
                                    <i class="ace-icon fa fa-arrow-right"></i>
                                </a>
                            </li>
                        </ul>
                    </li>

                    <li class="light-blue dropdown-modal">
                        <a data-toggle="dropdown" href="#" class="dropdown-toggle">
                            <img class="nav-user-photo" src="../${avatar.getUrl() }" alt="Jason's Photo" />
                            <span class="user-info">
                                <small>Welcome,</small>
                                ${user.getFullname() }
                            </span>

                            <i class="ace-icon fa fa-caret-down"></i>
                        </a>

                        <ul class="user-menu dropdown-menu-right dropdown-menu dropdown-yellow dropdown-caret dropdown-close">
                            <li>
                                <a href="index">
                                    <i class="ace-icon fa fa-cog"></i>
                                    Cài đặt
                                </a>
                            </li>

                            <li>
                                <a href="user-profile">
                                    <i class="ace-icon fa fa-user"></i>
                                    Thông tin cá nhân
                                </a>
                            </li>

                            <li class="divider"></li>

                            <li>
                                <a href="signOut">
                                    <i class="ace-icon fa fa-power-off"></i>
                                    Thoát
                                </a>
                            </li>
                        </ul>
                    </li>
                </ul>
            </div>
        </div><!-- /.navbar-container -->
    </div>

    <div class="main-container ace-save-state" id="main-container">
        <script type="text/javascript">
            try {
                ace.settings.loadState('main-container')
            } catch (e) {}
        </script>

        <div id="sidebar" class="sidebar                  responsive                    ace-save-state">
            <script type="text/javascript">
                try {
                    ace.settings.loadState('sidebar')
                } catch (e) {}
            </script>

            <div class="sidebar-shortcuts" id="sidebar-shortcuts">
                <div class="sidebar-shortcuts-large" id="sidebar-shortcuts-large">
                    <button class="btn btn-success">
                        <i class="ace-icon fa fa-signal"></i>
                    </button>

                    <button class="btn btn-info">
                        <i class="ace-icon fa fa-pencil"></i>
                    </button>

                    <button class="btn btn-warning">
                        <i class="ace-icon fa fa-users"></i>
                    </button>

                    <button class="btn btn-danger">
                        <i class="ace-icon fa fa-cogs"></i>
                    </button>
                </div>

                <div class="sidebar-shortcuts-mini" id="sidebar-shortcuts-mini">
                    <span class="btn btn-success"></span>

                    <span class="btn btn-info"></span>

                    <span class="btn btn-warning"></span>

                    <span class="btn btn-danger"></span>
                </div>
            </div><!-- /.sidebar-shortcuts -->

            <ul class="nav nav-list">
                <li class="">
                    <a href="index">
                        <i class="menu-icon fa fa-tachometer"></i>
                        <span class="menu-text"> Trang chủ </span>
                    </a>

                    <b class="arrow"></b>
                </li>

                <li class="">
                    <a href="user-profile">
                        <i class="menu-icon fa fa-user"></i>
                        <span class="menu-text"> Thông tin cá nhân </span>
                    </a>

                    <b class="arrow"></b>
                </li>

                <li class="">
                    <a href="users-manage">
                        <i class="menu-icon fa fa-users"></i>
                        <span class="menu-text"> Quản lý user </span>
                    </a>

                    <b class="arrow"></b>
                </li>

                <li class="">
                    <a href="hostels-manage">
                        <i class="menu-icon fa fa-list-alt"></i>
                        <span class="menu-text"> Quản lý nhà trọ </span>
                    </a>

                    <b class="arrow"></b>
                </li>

                <li class="">
                    <a href="analytics">
                        <i class="menu-icon fa fa-area-chart"></i>
                        <span class="menu-text"> Thống kê </span>
                    </a>

                    <b class="arrow"></b>
                </li>

                <li class="">
                    <a href="signOut">
                        <i class="menu-icon fa fa-sign-out"></i>
                        <span class="menu-text"> Thoát </span>
                    </a>

                    <b class="arrow"></b>
                </li>
            </ul><!-- /.nav-list -->

            <div class="sidebar-toggle sidebar-collapse" id="sidebar-collapse">
                <i id="sidebar-toggle-icon" class="ace-icon fa fa-angle-double-left ace-save-state" data-icon1="ace-icon fa fa-angle-double-left" data-icon2="ace-icon fa fa-angle-double-right"></i>
            </div>
        </div>

        <div class="main-content">
            <div class="main-content-inner">
                <div class="breadcrumbs ace-save-state" id="breadcrumbs">
                    <ul class="breadcrumb">
                        <li>
                            <i class="ace-icon fa fa-home home-icon"></i>
                            <a href="#">Trang chủ</a>
                        </li>

                        <li>

                        </li>
                    </ul><!-- /.breadcrumb -->

                    <div class="nav-search" id="nav-search">
                        <form class="form-search">
                            <span class="input-icon">
                                <input type="text" placeholder="Search ..." class="nav-search-input" id="nav-search-input" autocomplete="off" />
                                <i class="ace-icon fa fa-search nav-search-icon"></i>
                            </span>
                        </form>
                    </div><!-- /.nav-search -->
                </div>

                <div class="page-content">

                    <div class="page-header">
                        <h1>
                            Quản lý nhà trọ
                            <i class="ace-icon fa fa-angle-double-right"></i>
                        </h1>
                    </div>
                    
                    <div class="container">
                    <div class="row">
                    	<div class="col select-view">
                    		<span>HIỂN THỊ: </span>
                    		<select id="select-censored-view" class="select-censored-0" onChange="changeSelectHostelsView()">
                                  	<option value="-2">Tất cả</option>
                                  	<option value="0">Đang đợi phê duyệt</option>
								    <option value="1">Đã phê duyệt</option>
								    <option value="-1">Không được phê duyệt</option>
						  	</select>
                    	</div>
                    </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->
                            <table class="table">
                                <thead>
                                    <tr>
                                        <th scope="col">ID</th>
                                        <th scope="col">Tiêu đề</th>
                                        <th scope="col">Giá (vnd)</th>
                                        <th scope="col">Diện tích (m2)</th>
                                        <th scope="col">Kiểm duyệt</th>
                                        <th scope="col">Đã cho thuê</th>
                                        <th scope="col">Timestamp</th>
                                        <th scope="col"></th>
                                    </tr>
                                </thead>
                                <tbody id="table-tbody">
                                	<c:forEach items="${hostels }" var="hostel">
                                    <tr>
                                        <td>${hostel.getId() }</td>
                                        <td class="title"><span><a href="../hostel-detail/${hostel.getId() }" target="_blank">${hostel.getTitle() }</a></span></td>
                                        <td>${hostel.getPrice() }</td>
                                        <td>${hostel.getSpace() }</td>
                                        <td>
                                    	<select id="select-censored-${hostel.getId() }" class="select-censored-${hostel.getIsCensored() }" 
                                    		onChange="updateCensored(${hostel.getId() })">
	                                    	<c:choose>
											  <c:when test="${hostel.getIsCensored() == 0}">
											    <option value="0">Đang đợi phê duyệt</option>
											    <option value="1">Đã phê duyệt</option>
											    <option value="-1">Không được phê duyệt</option>
											  </c:when>
											  <c:when test="${hostel.getIsCensored() == 1}">
											    <option value="1">Đã phê duyệt</option>
											    <option value="0">Đang đợi phê duyệt</option>
											    <option value="-1">Không được phê duyệt</option>
											  </c:when>
											  <c:otherwise>
											    <option value="-1">Không được phê duyệt</option>
											    <option value="0">Đang đợi phê duyệt</option>
											    <option value="1">Đã phê duyệt</option>
											  </c:otherwise>
											</c:choose>
									  	</select>                                    
                                        </td>
                                        <td>
                                        	<c:choose>
											  <c:when test="${hostel.isIsRented() == true}">
											  	<button class="btn btn-xs btn-success">Đã cho thuê</button>
											  </c:when>
											  <c:otherwise>
											  	<button class="btn btn-xs btn-white btn-default">Chưa cho thuê</button>
											  </c:otherwise>
											</c:choose>
                                        </td>
                                        <td>${hostel.getTimestamp() }</td>
                                        <td>
                                            <div class="hidden-sm hidden-xs btn-group">
                                                <button class="btn btn-xs btn-success">
                                                    <i class="ace-icon fa fa-check bigger-120"></i>
                                                </button>

                                                <button class="btn btn-xs btn-info">
                                                    <i class="ace-icon fa fa-pencil bigger-120"></i>
                                                </button>

                                                <button class="btn btn-xs btn-danger">
                                                    <i class="ace-icon fa fa-trash-o bigger-120"></i>
                                                </button>

                                                <button class="btn btn-xs btn-warning">
                                                    <i class="ace-icon fa fa-flag bigger-120"></i>
                                                </button>
                                            </div>
                                        </td>
                                    </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                            <div>
                                <ul class="pagination">
                                    <li class="disabled">
                                        <a href="#">
                                            <i class="ace-icon fa fa-angle-double-left"></i>
                                        </a>
                                        
                                    </li>

                                    <li class="active">
                                        <a onclick="getHostels(1)">1</a>
                                    </li>

                                    <li>
                                        <a onclick="getHostels(2)">2</a>
                                    </li>

                                    <li>
                                        <a onclick="getHostels(3)">3</a>
                                    </li>

                                    <li>
                                        <a onclick="getHostels(4)">4</a>
                                    </li>

                                    <li>
                                        <a onclick="getHostels(5)">5</a>
                                    </li>

                                    <li>
                                        <a href="#">
                                            <i class="ace-icon fa fa-angle-double-right"></i>
                                        </a>
                                    </li>
                                </ul>
                            </div>
                            <!-- PAGE CONTENT ENDS -->
                        </div><!-- /.col -->
                    </div><!-- /.row -->
                </div><!-- /.page-content -->
            </div>
        </div><!-- /.main-content -->

        <div class="footer">
            <div class="footer-inner">
                <div class="footer-content">
                    <span class="bigger-120">
                        <span class="blue bolder">247 Hostel</span>
                        &copy; 2019
                    </span>

                    &nbsp; &nbsp;
                    <span class="action-buttons">
                        <a href="#">
                            <i class="ace-icon fa fa-twitter-square light-blue bigger-150"></i>
                        </a>

                        <a href="#">
                            <i class="ace-icon fa fa-facebook-square text-primary bigger-150"></i>
                        </a>

                        <a href="#">
                            <i class="ace-icon fa fa-rss-square orange bigger-150"></i>
                        </a>
                    </span>
                </div>
            </div>
        </div>

        <a href="#" id="btn-scroll-up" class="btn-scroll-up btn btn-sm btn-inverse">
            <i class="ace-icon fa fa-angle-double-up icon-only bigger-110"></i>
        </a>
    </div><!-- /.main-container -->

    <!-- basic scripts -->

    <!--[if !IE]> -->
    <script src="../resources/ace-master/assets/js/jquery-2.1.4.min.js"></script>

    <!-- <![endif]-->

    <!--[if IE]>
<script src="../resources/ace-master/assets/js/jquery-1.11.3.min.js"></script>
<![endif]-->
    <script type="text/javascript">
        if ('ontouchstart' in document.documentElement) document.write("<script src='assets/js/jquery.mobile.custom.min.js'>" + "<" + "/script>");
    </script>
    <script src="../resources/ace-master/assets/js/bootstrap.min.js"></script>

    <!-- page specific plugin scripts -->

    <!-- ace scripts -->
    <script src="../resources/ace-master/assets/js/ace-elements.min.js"></script>
    <script src="../resources/ace-master/assets/js/ace.min.js"></script>

    <!-- inline scripts related to this page -->
</body>

</html>
