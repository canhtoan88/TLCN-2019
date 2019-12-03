<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1" />
    <meta charset="utf-8" />
    <title>247 Hostel - Admin</title>
    <link rel="shortcut icon" href="resources/icons/favicon.ico" />

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
                                Cảnh Toàn Nguyễn
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
                            Thống kê
                            <small>
                                <i class="ace-icon fa fa-angle-double-right"></i>
                            </small>
                        </h1>
                    </div><!-- /.page-header -->

                    <div class="row">
                        <div class="col-xs-12">
                            <!-- PAGE CONTENT BEGINS -->

                            <div class="row">
                                <div class="space-6"></div>

                                <div class="col infobox-container">
                                    <div class="infobox infobox-green">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-home"></i>
                                        </div>

                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${hostelsCount }</span>
                                            <div class="infobox-content">nhà trọ</div>
                                        </div>

                                        <!--                                        <div class="stat stat-success">8%</div>-->
                                    </div>

                                    <div class="infobox infobox-blue">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-user"></i>
                                        </div>

                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${usersCount }</span>
                                            <div class="infobox-content">người dùng</div>
                                        </div>

                                        <!--
                                        <div class="badge badge-success">
                                            +32%
                                            <i class="ace-icon fa fa-arrow-up"></i>
                                        </div>
-->
                                    </div>

                                    <div class="infobox infobox-pink">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-check"></i>
                                        </div>

                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${hostelsRentedCount }</span>
                                            <div class="infobox-content">nhà trọ đã cho thuê</div>
                                        </div>
                                        <!--                                        <div class="stat stat-important">4%</div>-->
                                    </div>

                                    <div class="infobox infobox-blue">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-plus-circle"></i>
                                        </div>

                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${hostelsNotRentedCount }</span>
                                            <div class="infobox-content">nhà trọ còn trống</div>
                                        </div>
                                        <!--                                        <div class="stat stat-important">4%</div>-->
                                    </div>

                                    <div class="infobox infobox-red">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-comment"></i>
                                        </div>

                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${commentsCount }</span>
                                            <div class="infobox-content">lượt bình luận</div>
                                        </div>

                                        <!--
                                        <div class="badge badge-success">
                                            +12%
                                            <i class="ace-icon fa fa-arrow-up"></i>
                                        </div>
-->
                                    </div>

                                    <div class="infobox infobox-orange2">
                                        <div class="infobox-icon">
                                            <i class="ace-icon fa fa-star"></i>
                                        </div>

                                        <div class="infobox-data">
                                            <span class="infobox-data-number">${ratesCount }</span>
                                            <div class="infobox-content">lượt đánh giá</div>
                                        </div>

                                        <!--
                                        <div class="badge badge-success">
                                            7.2%
                                            <i class="ace-icon fa fa-arrow-up"></i>
                                        </div>
-->
                                    </div>
                                </div>
                            </div><!-- /.row -->

                            <div class="hr hr32 hr-dotted"></div>

                            <div class="row">

                                <div class="col-sm-6">
                                    <div class="widget-box">
                                        <div class="widget-header widget-header-flat widget-header-small">
                                            <h5 class="widget-title">
                                                <i class="ace-icon fa fa-signal"></i>
                                                Giá thuê trọ
                                            </h5>
                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main">
                                                <div id="piechart-placeholder"></div>
                                            </div><!-- /.widget-main -->
                                        </div><!-- /.widget-body -->
                                    </div><!-- /.widget-box -->
                                </div><!-- /.col -->

                                <div class="col-sm-6">
                                    <div class="widget-box">
                                        <table class="table">
                                            <thead>
                                                <tr>
                                                    <th scope="col">#</th>
                                                    <th scope="col">Giá thuê</th>
                                                    <th scope="col">Số lượng nhà trọ</th>
                                                    <th scope="col">Phần trăm (%)</th>
                                                </tr>
                                            </thead>
                                            <tbody>
                                                <c:forEach items="${hostelRangePriceFull }" var="hostelRPF" varStatus="loop">
                                                    <tr>
                                                        <th>${loop.index + 1 }</th>
                                                        <td>${hostelRPF.getLabel() }</td>
                                                        <td>${hostelRPF.getCount() }</td>
                                                        <td>${hostelRPF.getPercent() }</td>
                                                    </tr>
                                                </c:forEach>
                                            </tbody>
                                        </table>
                                    </div><!-- /.widget-box -->
                                </div><!-- /.col -->
                            </div>

                            <div class="hr hr32 hr-dotted"></div>

                            <div class="row">
                                <div class="col">
                                    <div class="widget-box transparent">
                                        <div class="widget-header widget-header-flat">
                                            <h4 class="widget-title lighter">
                                                <i class="ace-icon fa fa-signal"></i>
                                                Lượt đăng ký mới / Nhà trọ mới
                                                <span style="color: #DA5430;">(<span id="chart2-title-time">2019</span>)</span>
                                            </h4>

                                            <div class="widget-toolbar">
                                                <a href="#" data-action="collapse">
                                                    <i class="ace-icon fa fa-chevron-up"></i>
                                                </a>
                                            </div>

                                            <div class="widget-toolbar no-border">
                                                <select id="type-data-show" onChange="onSlectTypeDataShowChange()">
                                                    <option value="1">Theo tháng</option>
                                                    <option value="0">Theo ngày</option>
                                                    <option value="2">Theo năm</option>
                                                </select>
                                            </div>
                                        </div>

                                        <div class="widget-body">
                                            <div class="widget-main padding-4">

                                                <div>
                                                    <span style="font-size: 0.9em;">Số lượng</span>
                                                </div>

                                                <div id="sales-charts"></div>
                                                <span id="chart2-title-x" style="font-size: 0.9em; float: right;">Tháng</span>
                                            </div><!-- /.widget-main -->
                                        </div><!-- /.widget-body -->
                                    </div><!-- /.widget-box -->
                                </div><!-- /.col -->
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

        <div style="display: none;">
            <input id="hostelsRangePriceCountString" type="text" value="${hostelsRangePriceCountString }">
            <input id="usersRegisterCountByTimeString0" type="text" value="${usersRegisterCountByTime0 }">
            <input id="usersRegisterCountByTimeString1" type="text" value="${usersRegisterCountByTime1 }">
            <input id="usersRegisterCountByTimeString2" type="text" value="${usersRegisterCountByTime2 }">
            <input id="hostelsPostCountByTimeString0" type="text" value="${hostelsPostCountByTime0 }">
            <input id="hostelsPostCountByTimeString1" type="text" value="${hostelsPostCountByTime1 }">
            <input id="hostelsPostCountByTimeString2" type="text" value="${hostelsPostCountByTime2 }">
        </div>
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

    <!--[if lte IE 8]>
		  <script src="../resources/ace-master/assets/js/excanvas.min.js"></script>
		<![endif]-->
    <script src="../resources/ace-master/assets/js/jquery-ui.custom.min.js"></script>
    <script src="../resources/ace-master/assets/js/jquery.ui.touch-punch.min.js"></script>
    <script src="../resources/ace-master/assets/js/jquery.easypiechart.min.js"></script>
    <script src="../resources/ace-master/assets/js/jquery.sparkline.index.min.js"></script>
    <script src="../resources/ace-master/assets/js/jquery.flot.min.js"></script>
    <script src="../resources/ace-master/assets/js/jquery.flot.pie.min.js"></script>
    <script src="../resources/ace-master/assets/js/jquery.flot.resize.min.js"></script>

    <!-- ace scripts -->
    <script src="../resources/ace-master/assets/js/ace-elements.min.js"></script>
    <script src="../resources/ace-master/assets/js/ace.min.js"></script>

    <!-- inline scripts related to this page -->
    <script type="text/javascript">
        //var dataUsersRegister0 = [3, 2, 1, 5, 2, 7, 6, 1, 2, 1, 5, 2, 1, 2, 1, 5, 2, 7, 6, 1, 2, 1, 5, 2, 1, 2, 1, 5, 2, 7, 6];
        //var dataHostels0 = [3, 1, 2, 1, 5, 1, 2, 1, 5, 2, 7, 1, 2, 1, 5, 2, 1, 2, 1, 5, 1, 2, 1, 5, 2, 7, 1, 2, 1, 5, 2];

        //var dataUsersRegister1 = [2, 1, 5, 2, 1, 10, 1, 5, 2, 7, 6, 1];
        //var dataHostels1 = [6, 1, 2, 1, 5, 1, 6, 1, 2, 1, 5, 1];

        //var dataUsersRegister2 = [2, 1, 2, 1, 5];
        //var dataHostels2 = [1, 2, 1, 5, 1];

        var dataUsersRegister0 = [];
        var dataHostels0 = [];

        var dataUsersRegister1 = [];
        var dataHostels1 = [];

        var dataUsersRegister2 = [];
        var dataHostels2 = [];

        var typeDataShow = 1;

        var selectChartViews = [];

        var date = new Date();
        var y = date.getFullYear();
        var m = date.getMonth() + 1;
        var d = date.getDate();








        jQuery(function($) {
            $('.easy-pie-chart.percentage').each(function() {
                var $box = $(this).closest('.infobox');
                var barColor = $(this).data('color') || (!$box.hasClass('infobox-dark') ? $box.css('color') : 'rgba(255,255,255,0.95)');
                var trackColor = barColor == 'rgba(255,255,255,0.95)' ? 'rgba(255,255,255,0.25)' : '#E2E2E2';
                var size = parseInt($(this).data('size')) || 50;
                $(this).easyPieChart({
                    barColor: barColor,
                    trackColor: trackColor,
                    scaleColor: false,
                    lineCap: 'butt',
                    lineWidth: parseInt(size / 10),
                    animate: ace.vars['old_ie'] ? false : 1000,
                    size: size
                });
            })

            $('.sparkline').each(function() {
                var $box = $(this).closest('.infobox');
                var barColor = !$box.hasClass('infobox-dark') ? $box.css('color') : '#FFF';
                $(this).sparkline('html', {
                    tagValuesAttribute: 'data-values',
                    type: 'bar',
                    barColor: barColor,
                    chartRangeMin: $(this).data('min') || 0
                });
            });


            //flot chart resize plugin, somehow manipulates default browser resize event to optimize it!
            //but sometimes it brings up errors with normal resize event handlers
            $.resize.throttleWindow = false;

            var placeholder = $('#piechart-placeholder').css({
                'width': '90%',
                'min-height': '300px'
            });


            var hostelsRangePriceCountString = document.getElementById("hostelsRangePriceCountString").value;
            var hostelsRangePriceCount = hostelsRangePriceCountString.split("-");
            hostelsRangePriceCount.shift();
            //console.log(hostelsRangePriceCount);

            var data = [{
                    label: "Dưới 2 triệu",
                    data: hostelsRangePriceCount[1],
                    color: "#68BC31"
                },
                {
                    label: "2 - 3 triệu",
                    data: hostelsRangePriceCount[1],
                    color: "#3085da"
                },
                {
                    label: "3 - 4 triệu",
                    data: hostelsRangePriceCount[2],
                    color: "#FFFF00"
                },
                {
                    label: "4 - 6 triệu",
                    data: hostelsRangePriceCount[3],
                    color: "#DA5430"
                },
                {
                    label: "6 - 10 triệu",
                    data: hostelsRangePriceCount[4],
                    color: "#fe74ee"
                },
                {
                    label: "Trên 10 triệu",
                    data: hostelsRangePriceCount[5],
                    color: "#FFBF00"
                }
            ]

            function drawPieChart(placeholder, data, position) {

                $.plot(placeholder, data, {
                    series: {
                        pie: {
                            show: true,
                            tilt: 0.8,
                            highlight: {
                                opacity: 0.25
                            },
                            stroke: {
                                color: '#fff',
                                width: 2
                            },
                            startAngle: 2
                        }
                    },
                    legend: {
                        show: true,
                        position: position || "ne",
                        labelBoxBorderColor: null,
                        margin: [-30, 15]
                    },
                    grid: {
                        hoverable: true,
                        clickable: true
                    }
                })
            }
            drawPieChart(placeholder, data);

            /**
            we saved the drawing function and the data to redraw with different position later when switching to RTL mode dynamically
            so that's not needed actually.
            */
            placeholder.data('chart', data);
            placeholder.data('draw', drawPieChart);


            //pie chart tooltip example
            var $tooltip = $("<div class='tooltip top in'><div class='tooltip-inner'></div></div>").hide().appendTo('body');
            var previousPoint = null;

            placeholder.on('plothover', function(event, pos, item) {
                if (item) {
                    if (previousPoint != item.seriesIndex) {
                        previousPoint = item.seriesIndex;
                        //var tip = item.series['label'] + " : " + item.series['percent'] + '%';
                        var tip = item.series['label'] + " : " + hostelsRangePriceCount[item.seriesIndex] + '%';
                        $tooltip.show().children(0).text(tip);
                    }
                    $tooltip.css({
                        top: pos.pageY + 10,
                        left: pos.pageX + 10
                    });
                } else {
                    $tooltip.hide();
                    previousPoint = null;
                }

            });

        })

        function drawChart2() {

            //console.log(selectChartViews);

            $(document).one('ajaxloadstart.page', function(e) {
                $tooltip.remove();
            });

            var selectChartView = selectChartViews[typeDataShow];

            var olderd = date.getFullYear() - selectChartViews[typeDataShow].maxValue + 1;

            var d1 = [];
            if (typeDataShow === "2") {
                for (var i = 0; i < selectChartView.maxValue; i++) {
                    d1.push([olderd + i, selectChartView.data1[i]]);
                }
            } else {
                for (var i = 0; i < selectChartView.maxValue; i++) {
                    d1.push([i + 1, selectChartView.data1[i]]);
                }
            }

            var d2 = [];
            if (typeDataShow === "2") {
                for (var i = 0; i < selectChartView.maxValue; i++) {
                    d2.push([olderd + i, selectChartView.data2[i]]);
                }
            } else {
                for (var i = 0; i < selectChartView.maxValue; i++) {
                    d2.push([i + 1, selectChartView.data2[i]]);
                }
            }


            var sales_charts = $('#sales-charts').css({
                'width': '100%',
                'height': "400px"
            });
            $.plot("#sales-charts", [{
                    label: "Lượt đăng ký mới",
                    data: d1
                },
                {
                    label: "Nhà trọ mới",
                    data: d2
                }
            ], {
                hoverable: true,
                shadowSize: 0,
                series: {
                    lines: {
                        show: true
                    },
                    points: {
                        show: true
                    }
                },
                xaxis: {
                    tickLength: 0,
                    tickDecimals: 0
                },
                yaxis: {
                    ticks: 10,
                    min: selectChartView.min,
                    max: selectChartView.max,
                    tickDecimals: 0
                },
                grid: {
                    backgroundColor: {
                        colors: ["#fff", "#fff"]
                    },
                    borderWidth: 1,
                    borderColor: '#555'
                }
            });


            $('#recent-box [data-rel="tooltip"]').tooltip({
                placement: tooltip_placement
            });

            function tooltip_placement(context, source) {
                var $source = $(source);
                var $parent = $source.closest('.tab-content')
                var off1 = $parent.offset();
                var w1 = $parent.width();

                var off2 = $source.offset();
                //var w2 = $source.width();

                if (parseInt(off2.left) < parseInt(off1.left) + parseInt(w1 / 2)) return 'right';
                return 'left';
            }


            $('.dialogs,.comments').ace_scroll({
                size: 300
            });


            //Android's default browser somehow is confused when tapping on label which will lead to dragging the task
            //so disable dragging when clicking on label
            var agent = navigator.userAgent.toLowerCase();
            if (ace.vars['touch'] && ace.vars['android']) {
                $('#tasks').on('touchstart', function(e) {
                    var li = $(e.target).closest('#tasks li');
                    if (li.length == 0) return;
                    var label = li.find('label.inline').get(0);
                    if (label == e.target || $.contains(label, e.target)) e.stopImmediatePropagation();
                });
            }

            $('#tasks').sortable({
                opacity: 0.8,
                revert: true,
                forceHelperSize: true,
                placeholder: 'draggable-placeholder',
                forcePlaceholderSize: true,
                tolerance: 'pointer',
                stop: function(event, ui) {
                    //just for Chrome!!!! so that dropdowns on items don't appear below other items after being moved
                    $(ui.item).css('z-index', 'auto');
                }
            });
            $('#tasks').disableSelection();
            $('#tasks input:checkbox').removeAttr('checked').on('click', function() {
                if (this.checked) $(this).closest('li').addClass('selected');
                else $(this).closest('li').removeClass('selected');
            });


            //show the dropdowns on top or bottom depending on window height and menu position
            $('#task-tab .dropdown-hover').on('mouseenter', function(e) {
                var offset = $(this).offset();

                var $w = $(window)
                if (offset.top > $w.scrollTop() + $w.innerHeight() - 100)
                    $(this).addClass('dropup');
                else $(this).removeClass('dropup');
            });
        }
        //drawChart2();

        function findMax(array) {
            var max = Number(array[0]);
            for (let i = 1; i < array.length; i++) {
                if (Number(array[i]) > max) {
                    max = Number(array[i]);
                }
            }
            return max;
        }

        function findMaxInMultiData(array1, array2) {
            var max = Number(array1[0]);
            if (findMax(array1) > max) {
                max = findMax(array1);
            }
            if (findMax(array2) > max) {
                max = findMax(array2);
            }
            return max;
        }

        function findMin(array) {
            var min = Number(array[0]);
            for (let i = 1; i < array.length; i++) {
                if (Number(array[i]) < min) {
                    min = Number(array[i]);
                }
            }
            return min;
        }

        function findMinInMultiData(array1, array2) {
            var min = Number(array1[0]);
            if (findMin(array1) < min) {
                min = findMin(array1);
            }
            if (findMin(array2) < min) {
                min = findMin(array2);
            }
            return min;
        }

        function onSlectTypeDataShowChange() {

            typeDataShow = document.getElementById("type-data-show").value;

            var chart2TitleX = document.getElementById("chart2-title-x");
            chart2TitleX.innerHTML = selectChartViews[typeDataShow].label;

            var olderd = date.getFullYear() - selectChartViews[typeDataShow].maxValue + 1;

            var chart2TitleTime = document.getElementById("chart2-title-time");
            if (typeDataShow === "0") {
                chart2TitleTime.innerHTML = m + "/" + y;
            }
            if (typeDataShow === "1") {
                chart2TitleTime.innerHTML = y;
            }
            if (typeDataShow === "2") {
                chart2TitleTime.innerHTML = olderd + " - " + y;
            }

            drawChart2();
        }

        function getData(callback) {
            var usersRegisterCountByTimeString0 = document.getElementById("usersRegisterCountByTimeString0").value;
            var usersRegisterCountByTimeString1 = document.getElementById("usersRegisterCountByTimeString1").value;
            var usersRegisterCountByTimeString2 = document.getElementById("usersRegisterCountByTimeString2").value;
            var hostelsPostCountByTimeString0 = document.getElementById("hostelsPostCountByTimeString0").value;
            var hostelsPostCountByTimeString1 = document.getElementById("hostelsPostCountByTimeString1").value;
            var hostelsPostCountByTimeString2 = document.getElementById("hostelsPostCountByTimeString2").value;

            dataUsersRegister0 = usersRegisterCountByTimeString0.substring(1, usersRegisterCountByTimeString0.length - 1).replace(/\s/g, '').split(",");;
            dataHostels0 = hostelsPostCountByTimeString0.substring(1, hostelsPostCountByTimeString0.length - 1).replace(/\s/g, '').split(",");

            dataUsersRegister1 = usersRegisterCountByTimeString1.substring(1, usersRegisterCountByTimeString1.length - 1).replace(/\s/g, '').split(",");;
            dataHostels1 = hostelsPostCountByTimeString1.substring(1, hostelsPostCountByTimeString1.length - 1).replace(/\s/g, '').split(",");

            dataUsersRegister2 = usersRegisterCountByTimeString2.substring(1, usersRegisterCountByTimeString2.length - 1).replace(/\s/g, '').split(",");;
            dataHostels2 = hostelsPostCountByTimeString2.substring(1, hostelsPostCountByTimeString2.length - 1).replace(/\s/g, '').split(",");

            selectChartViews = [{
                    label: "Ngày",
                    maxValue: dataUsersRegister0.length,
                    data1: dataUsersRegister0,
                    data2: dataHostels0,
                    min: findMinInMultiData(dataUsersRegister0, dataHostels0),
                    max: findMaxInMultiData(dataUsersRegister0, dataHostels0)
                },
                {
                    label: "Tháng",
                    maxValue: dataUsersRegister1.length,
                    data1: dataUsersRegister1,
                    data2: dataHostels1,
                    min: findMinInMultiData(dataUsersRegister1, dataHostels1),
                    max: findMaxInMultiData(dataUsersRegister1, dataHostels1)
                },
                {
                    label: "Năm",
                    maxValue: dataUsersRegister2.length,
                    data1: dataUsersRegister2,
                    data2: dataHostels2,
                    min: findMinInMultiData(dataUsersRegister2, dataHostels2),
                    max: findMaxInMultiData(dataUsersRegister2, dataHostels2)
                }
            ]

            var chart2TitleX = document.getElementById("chart2-title-x");
            chart2TitleX.innerHTML = selectChartViews[typeDataShow].label;

            var date = new Date();
            var y = date.getFullYear();
            var m = date.getMonth() + 1;
            var d = date.getDate();

            var chart2TitleTime = document.getElementById("chart2-title-time");
            chart2TitleTime.innerHTML = y;

            callback();
        }
        getData(drawChart2);

    </script>
</body>

</html>
