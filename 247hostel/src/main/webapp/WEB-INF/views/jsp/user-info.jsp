<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Thông tin cá nhân</title>
    <link rel="shortcut icon" href="resources/icons/favicon.ico" />

    <link rel="stylesheet" type="text/css" href="resources/css/main.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/boxsaved.css">

    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="resources/js/app_script.js"></script>

    <style type="text/css">
        .other-info {
            color: red;
            font-weight: bold;
        }

    </style>

</head>

<body>

    <div class="wrapper">

        <!-- start header -->
        <header class="header">
        
        	<!-- mobile header -->
           	<div class="header-banner">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12 col-sm-12 col-md-4">
                            <div class="logo">
                                <a href="home">
                                    <img src="resources/icons/logo.png" alt="">
                                    <strong>iHostel</strong>
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <!-- start header menu fixed -->
            <div class="header-menu hidden-xs header-menu-fixed">
                <div class="container">
                    <nav class="navbar">
                        <ul class="navbar-nav">
                            <li class="current-menu-item">
                                <a href="home" title="Trang chủ"><i class="fa fa-home" aria-hidden="true"></i> Trang chủ</a>
                            </li>
                            <li class="menu-item-has-children user-manager">
                                <a title="Quản lý tài khoản"><i class="fa fa-user-circle" aria-hidden="true"></i> Quản lý tài khoản</a>
                                <ul class="sub-menu">
                                    <li><a href="user-info" title=""><i class="fa fa-user" aria-hidden="true"></i> Thông tin cá nhân</a></li>
                                    <li><a href="user-manage-post" title=""><i class="fa fa-list-alt" aria-hidden="true"></i> Quản lý nhà trọ</a></li>
                                    <hr />
                                    <li><a href="change-password" title=""><i class="fa fa-key" aria-hidden="true"></i> Đổi mật khẩu</a></li>
                                    <li><a href="change-email" title=""><i class="fa fa-envelope" aria-hidden="true"></i> Đổi email</a></li>
                                    <li><a href="change-phone" title=""><i class="fa fa-phone" aria-hidden="true"></i> Đổi số điện thoại</a></li>
                                    <li><a href="signOut" title=""><i class="fa fa-sign-out" aria-hidden="true"></i> Thoát</a></li>
                                    <hr />
                                    <li><a href="#" title=""><i class="fa fa-question-circle" aria-hidden="true"></i> Liên hệ hỗ trợ</a></li>
                                </ul>
                            </li>
                            <li class="right-li current-menu-item">
                                <a href="post" title="Đăng tin"><i class="fa fa-pencil-square-o"></i> Đăng tin</a>
                            </li>
                        </ul>
                    </nav>
                </div>
            </div>
            <!-- end header menu fixed -->

        </header>
        <!-- end header -->

        <!-- start main body -->
        <div class="main-container">

            <!-- start title page -->
            <div class="container">
                <p id="breadcrumbs" class="title-page">
                    <span xmlns:v="http://rdf.data-vocabulary.org/#">
                        <span typeof="v:Breadcrumb">
                            <i class="fa fa-home"></i>
                            <a><b>Trang chủ</b></a>
                            <i class="fa fa-angle-double-right"></i>
                            <a>Thông tin cá nhân</a>
                        </span>
                    </span>
                </p>
            </div>
            <!-- end title page -->

            <!-- start body -->
            <div class="access_page">
                <div class="container">

                    <div class="page_header">
                        <h1 class="page_title">Thông tin cá nhân</h1>
                    </div>

                    <div class="page_content register_page intro_page clearfix">
                        <ul class="nav nav-tabs tab_huongdan" role="tablist">
                            <li id="thongtincanhan" class="active"><a href="/thong-tin-tai-khoan.html"><i class="fa fa-user" aria-hidden="true"></i> Thông tin cá nhân</a></li>
                        </ul>
                        <div class="tab-content">
                            <div id="thong_tin_ca_nhan" class="tab-pane fade active in">
                                <div class="row user_profile_wrapper">
                                    <div class="user_profile_left col-xs-12 col-md-8">
                                        <div class="user_profile_form">
                                            <form id="frmSubmit" class="form-horizontal" action="updateUserInfo" method="POST">
                                                <label class="big_title_label">Thông tin cá nhân</label>                                                

                                                <div class="form-group">
                                                    <label class="title_label control-label col-md-3 col-sm-4 col-xs-12"></label>
                                                    <div class=" col-md-9 col-sm-8 col-xs-12">
                                                        <h4 style="color: red;">${message }</h4>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="title_label control-label col-md-3 col-sm-4 col-xs-12" for="fullname">Họ tên</label>
                                                    <div class=" col-md-9 col-sm-8 col-xs-12">
                                                        <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-user" aria-hidden="true"></i></span>
                                                            <input class="form-control" type="text" id="fullname" name="fullname" placeholder="Họ tên" value="${user.getFullname() }">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="title_label control-label col-md-3 col-sm-4 col-xs-12" for="email">Email</label>
                                                    <div class=" col-md-9 col-sm-8 col-xs-12">
                                                        <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-envelope" aria-hidden="true"></i></span>
                                                            <input class="form-control" type="text" id="email" name="email" placeholder="Email" disabled value="${user.getEmail() }">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="title_label control-label col-md-3 col-sm-4 col-xs-12" for="phone">Số điện thoại</label>
                                                    <div class=" col-md-9 col-sm-8 col-xs-12">
                                                        <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-phone" aria-hidden="true"></i></span>
                                                            <input class="form-control" type="text" id="phone" name="phone" placeholder="Số điện thoại" disabled value="${user.getPhone() }">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="title_label control-label col-md-3 col-sm-4 col-xs-12" for="address">Địa chỉ</label>
                                                    <div class=" col-md-9 col-sm-8 col-xs-12">
                                                        <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-map-marker" aria-hidden="true"></i></span>
                                                            <input class="form-control" type="text" id="address" name="address" placeholder="Địa chỉ" value="${user.getAddress() }">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="title_label control-label col-md-3 col-sm-4 col-xs-12" for="birthday">Sinh nhật</label>
                                                    <div class=" col-md-9 col-sm-8 col-xs-12">
                                                        <div class="input-group">
                                                            <span class="input-group-addon"><i class="fa fa-calendar" aria-hidden="true"></i></span>
                                                            <input class="form-control" type="date" id="birthday" name="birthday" placeholder="Sinh nhật" value="${user.getBirthday() }">
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="title_label control-label col-md-3 col-sm-4 col-xs-12" for="gender">Giới tính</label>
                                                    <div class=" col-md-9 col-sm-8 col-xs-12">
                                                        <div class="input-group" style="padding-top: 0.5em;">
                                                            <label style="padding-left: 2em;"><input type="radio" name="gender" value="male" 
                                                            	${user.isGender() ? "checked" : "" }> Nam</label>
															<label style="padding-left: 2em;"><input type="radio" name="gender" value="female" 
																${user.isGender() ? "" : "checked" }> Nữ</label>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <label class="title_label control-label col-md-3 col-sm-4 col-xs-12"></label>
                                                    <div class=" col-md-9 col-sm-8 col-xs-12">
                                                        <a href="#"><span class="btn_change_phone"><b>Đổi mật khẩu</b></span></a>
                                                    </div>
                                                </div>

                                                <div class="form-group">
                                                    <div class="col-md-9 col-md-offset-3 col-sm-8 col-xs-12">
                                                        <button type="submit" id="wp-submit-update-profile" class="btn btn-danger btn-block btn_save_profile"><i class="fa fa-check-circle" aria-hidden="true"></i> CẬP NHẬP</button>
                                                    </div>
                                                </div>


                                            </form>
                                        </div>
                                    </div>
                                    <div class="user_profile_right col-xs-12 col-md-4">
                                        <div class="user_profile_account">
                                            <div class="user_profile_account_header">
                                                Thông tin khác
                                            </div>
                                            <div class="user_profile_account_body">
                                                <ul>
                                                    <li><a href="user-manage-post"><i class="fa fa-angle-right" aria-hidden="true"></i> Số lượng nhà trọ: <span class="other-info">${countHouse }</span></a></li>
                                                    <li><a href="user-manage-post"><i class="fa fa-angle-right" aria-hidden="true"></i> Số lượng nhà trọ đã cho thuê: <span class="other-info">${countHouseIsRented }</span></a></li>
                                                    <li><a href="user-manage-post"><i class="fa fa-angle-right" aria-hidden="true"></i> Số lượng nhà trọ chưa cho thuê: <span class="other-info">${countHouseIsNotRented }</span></a></li>
                                                    <li><a href="user-manage-post"><i class="fa fa-angle-right" aria-hidden="true"></i> Số lượng nhà trọ đợi phê duyệt: <span class="other-info">${countHouseIsWaittingCensored }</span></a></li>
                                                    <li><a href="user-manage-post"><i class="fa fa-angle-right" aria-hidden="true"></i> Số lượng nhà trọ đã phê duyệt: <span class="other-info">${countHouseIsCensored }</span></a></li>
                                                    <li><a href="user-manage-post"><i class="fa fa-angle-right" aria-hidden="true"></i> Số lượng nhà trọ chưa phê duyệt: <span class="other-info">${countHouseIsNotCensored }</span></a></li>
                                                    <hr />
                                                    <li>Ngày tham gia: <span>${user.getTimeRegister() }</span></li>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- end body -->

            <!-- start back to top -->
            <a class="back-to-top" style="display: none;"><i class="fa fa-angle-up"></i></a>
            <!-- end back to top -->

        </div>
        <!-- end main body -->

        <!-- start footer -->
        <div class="footer">

            <!-- start footer whyus -->
            <div class="container">
                <div class="whyus_block text-center">
                    <div class="whyus_header">
                        <h3>Bạn đang có phòng trọ / căn hộ cho thuê?</h3>
                    </div>
                    <div class="whyus_body">
                        <p>Không phải lo tìm người cho thuê, phòng trống kéo dài</p>
                        <a type="button" class="btn btn-success" href="post">ĐĂNG TIN NGAY</a>
                    </div>
                </div>
            </div>
            <!-- end footer whyus -->

            <!-- start footer -->
            <footer class="page-footer">
                <div class="footer-deco"></div>
                <div class="footer-content">
                    <div class="container">
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-3">
                                <div class="footer-col">
                                    <h2>Liên kết</h2>
                                    <ul class="social-link">
                                        <li><a href="#"><i class="fa fa-facebook-square"></i> Facebook</a></li>
                                        <li><a href="#"><i class="fa fa-google-plus-square"></i> Google+</a></li>
                                        <li><a href="#"><i class="fa fa-youtube-play"></i> Youtube</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-3">
                                <div class="footer-col">
                                    <h2>Di động</h2>
                                    <ul class="thanhtoan">
                                        <li><a href="#"><img src="resources/images/app_store.png" alt=""></a></li>
                                        <li><a href="#"><img src="resources/images/google_play.png" alt=""></a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-3">
                                <div class="footer-col">
                                    <h2>Hỗ trợ khách hàng</h2>
                                    <ul class="support-link">
                                        <li><a href="#">Trung tâm trợ giúp</a></li>
                                        <li><a href="#">An toàn mua bán</a></li>
                                        <li><a href="#">Quy định cần biết</a></li>
                                        <li><a href="#">Liên hệ hỗ trợ</a></li>
                                    </ul>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6 col-md-3">
                                <div class="footer-col">
                                    <h2>Về chúng tôi</h2>
                                    <ul class="about-link">
                                           <li><a href="#" rel="nofollow">Giới thiệu</a></li>
                                           <li><a href="#" rel="nofollow">Quy định, điều khoản sử dụng</a></li>
                                           <li><a href="#" rel="nofollow">Hướng dẫn đăng tin</a></li>
                                           <li><a href="#" rel="nofollow">Liên hệ</a></li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </footer>
            <!-- end footer -->

        </div>
        <!-- end footer -->

    </div>


    <script type="text/javascript" src="resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="resources/js/owl.carousel.min.js"></script>
    <script type="text/javascript" src="resources/js/select2.full.min.js"></script>
    <script type="text/javascript" src="resources/js/validator.js"></script>
    <script type="text/javascript" src="resources/js/main.js"></script>
    <script type="text/javascript" src="resources/js/common.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery.cookie-1.4.1.js"></script>
    <script type="text/javascript" src="resources/js/lightgallery.min.js"></script>
    <script type="text/javascript" src="resources/js/lightslider.min.js"></script>
    <script type="text/javascript" src="resources/js/lg-thumbnail.js"></script>
    <script type="text/javascript" src="resources/js/lg-fullscreen.js"></script>
    <script type="text/javascript" src="resources/js/lg-autoplay.js"></script>
    <script type="text/javascript" src="resources/js/lg-zoom.js"></script>
    <script type="text/javascript" src="resources/js/jquery.mousewheel.min.js"></script>

</body>

</html>
