<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>247 Hostel - Admin</title>
    <link rel="shortcut icon" href="../resources/icons/favicon.ico" />

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
                <a href="home" class="navbar-brand">
                    <small>
                        <i class="fa fa-home"></i>
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
                            Thông tin cá nhân
                            <small>
                                <i class="ace-icon fa fa-angle-double-right"></i>                                
                            </small>
                        </h1>
                    </div>

                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->

                            <div>
                                <div id="user-profile-1" class="user-profile row">
                                    <div class="col-xs-12 col-sm-3 center">
                                        <div class="space-12"></div>
                                        <div>
                                            <span class="profile-picture">
                                                <img id="avatar" class="editable img-responsive editable-click editable-empty" alt="Alex's Avatar" 
                                                src="../${avatar.getUrl() }">
                                            </span>

                                            <div class="space-4"></div>

                                            <div class="width-80 label label-info label-xlg arrowed-in arrowed-in-right">
                                                <div class="inline position-relative">
                                                    <a href="#" class="user-title-label dropdown-toggle" data-toggle="dropdown">
                                                        <i class="ace-icon fa fa-circle light-green"></i>
                                                        &nbsp;
                                                        <span class="white">${user.getFullname() }</span>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </div>

                                    <div class="col-xs-12 col-sm-9">
                                        <div class="space-12"></div>

                                        <div class="profile-user-info profile-user-info-striped">
                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> Họ tên </div>

                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="fullname">${user.getFullname() }</span>
                                                </div>
                                            </div>

                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> Số điện thoại </div>

                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="phone">${user.getPhone() }</span>
                                                </div>
                                            </div>

                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> Email </div>

                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="email">${user.getEmail() }</span>
                                                </div>
                                            </div>

                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> Địa chỉ </div>

                                                <div class="profile-info-value">
                                                    <i class="fa fa-map-marker light-orange bigger-110"></i>
                                                    <span class="editable editable-click" id="country">${user.getAddress() }</span>
                                                </div>
                                            </div>

                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> Giới tính </div>

                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="gender">${user.isGender() ? "Nam" : "Nữ" }</span>
                                                </div>
                                            </div>

                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> Sinh nhật </div>

                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="birthday">${user.getBirthday() }</span>
                                                </div>
                                            </div>

                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> Ngày bắt đầu </div>

                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="timeStart">${employee.getTimeStart() }</span>
                                                </div>
                                            </div>

                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> Lương </div>

                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="salary">${employee.getSalary() } VND</span>
                                                </div>
                                            </div>

                                            <div class="profile-info-row">
                                                <div class="profile-info-name"> Phòng ban </div>

                                                <div class="profile-info-value">
                                                    <span class="editable editable-click" id="department">${department.getName() }</span>
                                                </div>
                                            </div>
                                        </div>

                                        <div class="space-12"></div>

                                        <div class="container">
                                            <button class="btn btn-info">Cập nhập thông tin</button>
                                        </div>
                                    </div>
                                </div>
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
