<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Đăng ký</title>
    <link rel="shortcut icon" href="resources/icons/favicon.ico" />
    
    <link rel="stylesheet" type="text/css" href="resources/css/main.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/boxsaved.css">

    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>

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
                            <li class="right-li current-menu-item">
                                <a href="post" title="Đăng tin"><i class="fa fa-pencil-square-o"></i> Đăng tin</a>
                            </li>
                            <li class="right-li">
                                <a href="sign-in" title="Đăng nhập"><i class="fa fa-pencil-square-o"></i> Đăng nhập</a>
                            </li>
                            <li class="right-li">
                                <a href="sign-up" title="Đăng ký"><i class="fa fa-pencil-square-o"></i> Đăng ký</a>
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
                    <span>
                        <i class="fa fa-home"></i>
                        <a><b>Trang chủ</b></a>
                        <i class="fa fa-angle-double-right"></i>
                        <a>Đăng ký</a>
                    </span>
                </p>
            </div>
            <!-- end title page -->

            <!-- start body -->
            <div class="access_page">
                <div class="container">

                    <div class="page_header">
                        <h1 class="page_title">Đăng ký tài khoản mới</h1>
                    </div>

                    <h4 style="color: red;">${message }</h4>

                    <div class="page_content register_page clearfix">
                        <div class="row">
                            <div class="col-xs-12 col-sm-6 col-md-6">
                                <div class="form_wrapper">

                                    <div id="divMessage"></div>
                                    <form id="frmSubmit" class="form-inner register-form " action="signUp" method="POST">
                                        <div class="form-group">
                                            <label for="FullName" class="control-label">Họ tên <span class="red_require">(*)</span></label>
                                            <input type="text" class="form-control" id="FullName" name="fullname" required="required" placeholder="Họ tên" value="${user.getFullname() }">
                                            <div class="help-block with-errors"></div>
                                        </div>
                                        <div class="form-group">
                                            <label for="Email" class="control-label">Email</label>
                                            <input type="email" class="form-control" id="Email" name="email" placeholder="Email" value="${user.getEmail() }">
                                            <div class="help-block with-errors"></div>
                                        </div>
                                        <div class="form-group">
                                            <label for="Phone" class="control-label">Số điện thoại</label>
                                            <input type="text" class="form-control" id="Phone" name="phone" placeholder="Số điện thoại" value="${user.getPhone() }">
                                            <div class="help-block with-errors"></div>
                                        </div>
                                        <div class="row">
                                            <div class="form-group col-sm-6">
                                                <label for="Password" class="control-label">Mật khẩu <span class="red_require">(*)</span></label>
                                                <input type="password" data-minlength="6" class="form-control" name="password" id="Password" 
                                                required="required" placeholder="Mật khẩu" value="${user.getPassword() }">
                                                <div class="help-block"></div>
                                            </div>
                                            <div class="form-group col-sm-6">
                                                <label for="PasswordConfirm" class="control-label">Nhập lại mật khẩu <span class="red_require">(*)</span></label>
                                                <input type="password" class="form-control" name="confirmPassword" id="PasswordConfirm" 
                                                required="required" placeholder="Nhập lại mật khẩu" value="${confirmPassword }">
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <div class="custom-control custom-checkbox">
                                            <input class="custom-control-input" data-val="true" data-val-required="The Remember field is required." 
                                            id="Remember" name="remember" type="checkbox" value="true" checked="checked">
                                            <label for="Remember">
                                            	Bấm vào nút đăng ký là bạn đã đồng ý với <a href="#"><b>quy định sử dụng</b></a> của chúng tôi
                                            </label>
                                            <div class="help-block with-errors"></div>
                                        </div>
                                        <div class="btn_wrapper">
                                            <button type="submit" class="btn btn-success btn-block"><i class="fa fa-user-plus"></i> ĐĂNG KÝ TÀI KHOẢN</button>
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <!-- end left -->
                            <div class="col-xs-12 col-sm-1 col-md-1 hidden-xs">
                                <div class="middle">
                                    <div class="text-center"><span>Hoặc</span></div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-5 col-md-5">
                                <div class="right">
                                    <p class="font-16 mgb-20">&nbsp;</p>
                                    <div class="social-connect-container">
                                        <div class="sc-buttons">
                                            <a href="#" class="sc-facebook">
                                                <i class="fa fa-facebook" aria-hidden="true"></i>
                                                <span>Đăng ký bằng Facebook</span>
                                            </a>
                                            <a href="#" class="sc-google">
                                                <i class="fa fa-google-plus" aria-hidden="true"></i>
                                                <span>Đăng ký bằng Google+</span>
                                            </a>
                                        </div>
                                    </div>
                                    <p class="font-16">Bạn đã có tài khoản? <a href="#"><b>Đăng nhập ngay</b></a></p>
                                </div>
                            </div>
                            <!-- end right -->
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

</body>

</html>
