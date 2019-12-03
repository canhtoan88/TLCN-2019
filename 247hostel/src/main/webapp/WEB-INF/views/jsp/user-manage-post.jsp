<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Quản lý nhà trọ</title>
    <link rel="shortcut icon" href="resources/icons/favicon.ico" />

    <link rel="stylesheet" type="text/css" href="resources/css/main.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="resources/css/boxsaved.css">

    <script type="text/javascript" src="resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="resources/js/app_script.js"></script>

    <style>
        .isRented {
            padding: 2px 4px;
            cursor: pointer;
        }

        .isRented-True {
            background-color: #FA5858;
            color: white;
        }

        .isRented-False {
            background-color: #4CAF50;
            color: white;
        }

		.pagination li a{
			cursor: pointer;
		}
		
		.i-button-delete i {
			cursor: pointer;
		}
    </style>

    <script>
        $(document).ready(function() {
            $('.span-click').on('click', function(e) {

                var idHostel = $(this).data("value");

                var c = confirm("Xác nhận thay đổi trạng thái nhà trọ");
                if (c == true) {
                    $.ajax({
                        type: "GET",
                        url: "http://localhost:8080/247hostel/api/changeRentedStatus",
                        data: {
                            idHostel: idHostel,
                        },
                        success: function(value) {

                            if (value == "false") {

                                alert("Thay đổi trạng thái thất bại");
                            } else {

                                alert("Thay đổi trạng thái thành công");

                                location.reload();
                            }
                        }
                    });
                }
            });
        });

        $(document).ready(function() {
            $('.fa-trash-o-click').on('click', function(e) {

                var idHostel = $(this).data("value");

                var c = confirm("Xác nhận xóa nhà trọ: " + idHostel);
                if (c == true) {
                    $.ajax({
                        type: "GET",
                        url: "http://localhost:8080/247hostel/api/deleteHostel",
                        data: {
                            idHostel: idHostel,
                        },
                        success: function(value) {

                            if (value == 'false') {

                                alert("Xóa nhà trọ thất bại");
                            } else {

                                alert("Xóa nhà trọ thành công");

                                location.reload();
                            }
                        }
                    });
                }
            });
        });

        function userGetHostels(page) {

        	//alert(page);

        	$.ajax({
        		type : "GET",
        		url : "http://localhost:8080/247hostel/api/userGetHostels",
        		data : {
        			page : page
        		},
        		success : function(value) {

        			// alert(value);
        			
        			$("#table-tbody").html(value)
        		}
        	});
        }
    </script>

</head>

<body>
    <div class="wrapper">

        <!-- start header -->
        <header class="header">

            <!-- start header menu fixed -->
            <div class="header-menu hidden-xs header-menu-fixed">
                <div class="container">
                    <nav class="navbar">
                        <ul class="navbar-nav">
                            <li class="current-menu-item"><a href="home" title="Trang chủ"><i class="fa fa-home" aria-hidden="true"></i> Trang chủ</a></li>
                            <li class="menu-item-has-children user-manager"><a title="Quản lý tài khoản"><i class="fa fa-user-circle" aria-hidden="true"></i> Quản lý tài khoản</a>
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
                            <li class="right-li current-menu-item"><a href="post" title="Đăng tin"><i class="fa fa-pencil-square-o"></i> Đăng tin</a></li>
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
                            <a>Quản lý nhà trọ</a>
                        </span>
                    </span>
                </p>
            </div>
            <!-- end title page -->

            <!-- start body -->
            <div class="access_page">
                <div class="container">

                    <div class="page_header">
                        <h1 class="page_title">Quản lý tin đăng</h1>
                    </div>

                    <div class="page_content register_page intro_page clearfix">
                        <ul class="nav nav-tabs tab_huongdan" role="tablist">
                            <li id="thongtincanhan" class="active">
                                <a href="/thong-tin-tai-khoan.html"><i class="fa fa-list" aria-hidden="true"></i> Quản lý tin đăng</a>
                            </li>
                        </ul>

                        <div class="tab-content">

                            <div id="quanlytindang" class="tab-pane fade active in">
                                <div class="table-responsive">
                                    <table class="table table-bordered table-hover">
                                        <thead>
                                            <tr>
                                                <td class="text-center">Mã nhà trọ</td>
                                                <td class="text-center">Tiêu đề</td>
                                                <td class="text-center">Ngày đăng</td>
                                                <td class="text-center">Đã kiểm duyệt</td>
                                                <td class="text-center">Đã cho thuê</td>
                                                <td class="text-center">Sửa</td>
                                                <td class="text-center">Xóa</td>
                                            </tr>
                                        </thead>
                                        <tbody id="table-tbody">
                                            <c:forEach items="${hostels }" var="hostel">
                                                <tr>
                                                    <td class="text-center">${hostel.getId() }</td>
                                                    <td class="td-title" style="max-width: 250px !important;">
                                                        <a href="hostel-detail/${hostel.getId() }" target="_blank">${hostel.getTitle() }</a>
                                                    </td>
                                                    <td class="text-center">${hostel.getTimestamp() }</td>
                                                    <c:choose>
                                                        <c:when test="${hostel.getIsCensored() == -1}">
                                                            <td class="text-center" style="color: red;">Không được phê duyệt</td>
                                                        </c:when>
                                                        <c:when test="${hostel.getIsCensored() == 1}">
                                                            <td class="text-center" style="color: blue;">Đã phê duyệt</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="text-center">Đang đợi phê duyệt..</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${hostel.isIsRented() == true}">
                                                            <td class="text-center">
                                                                <span id="${hostel.getId() }" class="span-click" data-value="${hostel.getId() }">
                                                                    <span class="isRented isRented-True" style="padding: 5px 10px;">Đã cho thuê</span>
                                                                </span>
                                                            </td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="text-center">
                                                                <span id="${hostel.getId() }" class="span-click" data-value="${hostel.getId() }">
                                                                    <span class="isRented isRented-False" style="padding: 5px 10px;">Chưa cho thuê</span>
                                                                </span>
                                                            </td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td class="text-center">
                                                        <span class="i-button i-button-edit" id="">
                                                            <a href="post-edit/${hostel.getId() }" target=""><i class="fa fa-pencil-square-o" aria-hidden="true"></i></a>
                                                        </span>
                                                    </td>
                                                    <td class="text-center">
                                                        <span class="i-button i-button-delete" id="">
                                                            <i class="fa fa-trash-o fa-trash-o-click" aria-hidden="true" data-value="${hostel.getId() }"></i>
                                                        </span>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>

                                    <div class="paging-container">
                                        <ul class="pagination">
                                            <li><a onclick="userGetHostels(1)">1</a></li>
                                            <li><a onclick="userGetHostels(2)">2</a></li>
                                            <li><a onclick="userGetHostels(3)">3</a></li>
                                            <li><a onclick="userGetHostels(4)">4</a></li>
                                            <li><a onclick="userGetHostels(5)">5</a></li>
                                            <li><a>»</a></li>
                                        </ul>
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
                        <a type="button" class="btn btn-success" href="post">ĐĂNG TIN
                            NGAY</a>
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
