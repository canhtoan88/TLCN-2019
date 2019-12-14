<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Đăng nhà trọ</title>
    <link rel="shortcut icon" href="resources/icons/favicon.ico" />

    <link rel="stylesheet" type="text/css" href="resources/css/main.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/boxsaved.css">

    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="resources/js/app_script.js"></script>
    
    <link rel="stylesheet" type="text/css" href="resources/css/upload.css">

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
                                    <li><a href="#" title=""><i class="fa fa-key" aria-hidden="true"></i> Đổi mật khẩu</a></li>
                                    <li><a href="#" title=""><i class="fa fa-phone" aria-hidden="true"></i> Đổi số điện thoại</a></li>
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
                            <a>Đăng nhà trọ</a>
                        </span>
                    </span>
                </p>
            </div>
            <!-- end title page -->

            <h4 style="color: red;">${message }</h4>

            <!-- start body -->
            <div class="page-dangtin">
                <div class="container">
                    <div class="row">
                        <div class="col-xs-12 col-md-4 block-right sidebar_huongdan">
                            <div class="hidden-xs">

                                <div class="block_huongdandangtin">
                                    <div class="block_huongdandangtin_header">
                                        Hướng dẫn đăng tin
                                    </div>
                                    <div class="block_huongdandangtin_body">
                                        <ul>
                                            <li><strong>Thông tin có dấu <span class="red_require">(*)</span> là bắt buộc.</strong></li>
                                            <li><strong>Nội dung phải viết bằng tiếng Việt có dấu</strong></li>
                                            <li><strong>Tiêu đề tin không dài quá 100 kí tự</strong></li>
                                            <li>Các bạn nên điền đầy đủ thông tin vào các mục để tin đăng có hiệu quả hơn.</li>
                                            <li>Để tăng độ tin cậy và tin rao được nhiều người quan tâm hơn, hãy nhập đầy đủ thông tin địa chỉ nhà trọ.</li>
                                            <li>Tin đăng có hình ảnh rõ ràng sẽ được xem và gọi gấp nhiều lần so với tin rao không có ảnh. Hãy đăng ảnh để được giao dịch nhanh chóng!</li>
                                        </ul>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="col-xs-12 col-md-8 block-left dang_tin">
                            <form action="postUpload" id="frmSubmit" class="form frm-dangtin" method="POST" enctype="multipart/form-data">
                                <div class=" form_step step1">
                                    <div class="group_fields">
                                        <h3 class="form_title">Thông tin nhà trọ</h3>
                                        <div class="group_fields_body">
                                            <div id="divMessage"></div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-6 col-xs-12">
                                                        <label for="ddlProvince" class="col-sm-12 control-label">Tỉnh / Thành phố <span class="red_require">(*)</span></label>
                                                        <select id="ddlProvince" name="idCity" class="form-control" required="" aria-required="true">
                                                            <option value="0">Tỉnh/Thành phố</option>

                                                            <c:forEach items="${cities }" var="city">
                                                                <option value="${city.getId() }">${city.getName() }</option>
                                                            </c:forEach>
                                                            
                                                        </select>
                                                    </div>
                                                    <div class="col-sm-6 col-xs-12">
                                                        <label for="ddlDistrict" class="col-sm-12 control-label">Quận huyện <span class="red_require">(*)</span></label>
                                                        <select id="ddlDistrict" name="idDistrict" class="form-control" required="" aria-required="true">
                                                            <option value="">Quận/Huyện</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-6 col-xs-12">
                                                        <label for="ddlStreet" class="col-sm-12 control-label">Tên đường <span class="red_require">(*)</span></label>
                                                        <select id="ddlStreet" name="idStreet" class="form-control" required="" aria-required="true">
                                                            <option value="">Tên đường</option>

                                                        </select>
                                                    </div>
                                                    <div class="col-sm-6 col-xs-12">
                                                        <label for="hostelNumber" class="col-sm-12 control-label">Số nhà <span class="red_require">(*)</span></label>
                                                        <input type="text" class="form-control" name="hostelNumber" id="hostelNumber" required="" aria-required="true" placeholder="Số nhà">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-6">
                                                        <label for="Price" class="col-sm-12 control-label">Giá cho thuê <span class="red_require">(*)</span></label>
                                                        <div class="input-group">
                                                            <input type="number" class="form-control" name="price" id="Price" min="0" max="999999999" placeholder="Giá thuê /tháng">
                                                            <span class="input-group-addon" id="basic-addon2">VND /tháng</span>
                                                        </div>
                                                    </div>
                                                    <div class="col-sm-6">
                                                        <label for="Area" class="col-sm-12 control-label">Diện tích <span class="red_require">(*)</span></label>
                                                        <div class="input-group">
                                                            <input type="number" class="form-control" name="space" id="Area" min="1" max="999" placeholder="Diện tích">
                                                            <span class="input-group-addon" id="basic-addon2">m²</span>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <label for="tieude" class="col-sm-12 control-label">Tiêu đề tin (Không quá 100 ký tự) <span class="red_require">(*)</span></label>
                                                        <input type="text" class="form-control" name="title" id="Title" placeholder="Hãy đặt tiêu đề đầy đủ nghĩa, khách sẽ quan tâm hơn" maxlength="100" required="" aria-required="true">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-12">
                                                        <label for="noidung" class="col-sm-12 control-label">Nội dung <span class="red_require">(*)</span></label>
                                                        <textarea name="description" class="form-control edtior-noidung" id="Detail" rows="8" required="" aria-required="true" placeholder="Thêm mô tả về nhà trọ.."></textarea>
                                                        <input type="hidden" id="noidung_html">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="group_fields">
                                        <h3 class="form_title">THÔNG TIN NGƯỜI CHO THUÊ</h3>
                                        <div class="group_fields_body">
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-6 col-xs-12">
                                                        <label for="FullName" class="col-sm-12 control-label">Họ tên <span class="red_require">(*)</span></label>
                                                        <input type="text" class="form-control" name="fullname" id="FullName" required="" aria-required="true" placeholder="Họ tên" value="${user.getFullname() }" readonly="readonly">
                                                    </div>
                                                    <div class="col-sm-6 col-xs-12">
                                                        <label for="Mobile" class="col-sm-12 control-label">Số điện thoại <span class="red_require">(*)</span></label>
                                                        <input type="text" class="form-control" name="phone" id="Mobile" minlength="10" maxlength="20" required="" aria-required="true" placeholder="Số điện thoại" value="${user.getPhone() }" readonly="readonly">
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="form-group">
                                                <label for="Address" class="col-sm-12 control-label">Địa chỉ <span class="red_require">(*)</span></label>
                                                <input type="text" class="form-control" name="address" id="Address" required="" aria-required="true" placeholder="Địa chỉ" value="${user.getAddress() }" readonly="readonly">
                                            </div>
                                        </div>
                                    </div>
                                    <div class="group_fields">
                                        <h3 class="form_title">Hình ảnh nhà trọ</h3>
                                        <div class="group_fields_body">
                                            <div class="alert alert-success">Tin đăng có hình ảnh rõ ràng sẽ được xem và gọi gấp nhiều lần so với tin rao không có ảnh. Hãy đăng ảnh để được giao dịch nhanh chóng. Bạn có thể tải 10 ảnh và mỗi ảnh dung lượng không quá 4MB</div>
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-12">

                                                        <label for="thumbnail-post" class="col-sm-12 control-label">Chọn hình ảnh để đăng</label>
                                                        <p>Định dạng ảnh hỗ trợ: .jpg .png .jpeg .gif</p>
                                                        <div id="fileupload">
                                                            <div id="uploadimage" class="clearfix">
                                                                <input id="secleimg" multiple="" class="fileupload" type="file" name="imagesFile">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="group_fields">
                                        <h3 class="form_title">Video nhà trọ</h3>
                                        <div class="group_fields_body">
                                            <div class="form-group">
                                                <div class="row">
                                                    <div class="col-sm-12">

                                                        <label for="thumbnail-post" class="col-sm-12 control-label">Chọn video để đăng</label>
                                                        <p>Định dạng video hỗ trợ: .mp4</p>
                                                        <div id="fileupload2">
                                                            <div id="uploadimage" class="clearfix default-theme">
                                                                <input id="secleimg2" class="fileupload" type="file" name="videosFile">
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <div class="row">
                                        <div class="col-sm-12">
                                            <button type="submit" class="btn btn-default btn-post">Đăng tin</button>
                                        </div>
                                    </div>
                                </div>
                            </form>
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
