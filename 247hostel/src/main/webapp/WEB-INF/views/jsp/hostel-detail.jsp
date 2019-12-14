<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Chi tiết nhà trọ</title>
    <link rel="shortcut icon" href="../resources/icons/favicon.ico" />
    
    <link rel="stylesheet" type="text/css" href="../resources/css/main.min.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/select2.min.css">
    <link rel="stylesheet" type="text/css" href="../resources/css/boxsaved.css">
    
    <script type="text/javascript" src="../resources/js/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="../resources/js/app_script.js"></script>

    <style>
        .title-house {
            margin: 20px 0;
        }

        .title-house h3 {
            color: #f3051b !important;
            text-transform: uppercase;
            font-weight: bold;
            padding: 40px auto;
        }

        .div-map {
            padding: 0px;
            margin: 0px;
            width: 100%;
            height: 80vh;
        }

        .map-iframe {
            height: 100%;
            width: 100%;
        }
        
        .field-results-item-desc-rate {
        	display: flex;
        	flex-direction: row;
        }
        
        .field-results-item-desc-rate div {
        	width: 3.5em;
        	height: 2.5em;
        	text-align: center;
        }
        
        .field-results-item-desc-rate img {
        	height: 100%;
        }
        
        .field-results-item-desc-rate img:hover {
        	cursor: pointer;
        }

    </style>
    
    <script>
	    function getUrlLocation(idCity, idDistrict, idStreet, houseNumber) {
	
	    	$.ajax({
	    		type : "GET",
	    		url : "http://localhost:8080/247hostel/api/getUrlLocation",
	    		data : {
	    			idCity : idCity,
	    			idDistrict : idDistrict,
	    			idStreet : idStreet,
	    			houseNumber : houseNumber
	    		},
	    		success : function(value) {
	
	    			$("#div-map").empty();
	    			$("#div-map").append(value);
	    		}
	    	});
	    };
	    
	    function rateHostel(idHostel, idUser, rate) {

		    if(!idUser){
				let r = confirm("Vui lòng đăng nhập để thực hiện đánh giá. \nĐăng nhập ngay:");

				if(r) {
					window.location.href = "../sign-in";
				}
			}
		    else {
	
		    	$.ajax({
		    		type : "GET",
		    		url : "http://localhost:8080/247hostel/api/rateHostel",
		    		data : {
		    			idHostel: idHostel,
		    			idUser: idUser,
		    			rate: rate
		    		},
		    		success : function(value) {
		
		    			$("#field-results-item-desc-rate-id").empty();
		    			$("#field-results-item-desc-rate-id").append(value);
		    		}
		    	});

		    }
	    };

    	
    </script>
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
                                <a href="../home">
                                    <img src="../resources/icons/logo.png" alt="">
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
                                <a href="../home" title="Trang chủ"><i class="fa fa-home" aria-hidden="true"></i> Trang chủ</a>
                            </li>
                            <%
                            	if(session.getAttribute("user") != null) {
                            %>
                            <li class="menu-item-has-children user-manager">
                                <a title="Quản lý tài khoản"><i class="fa fa-user-circle" aria-hidden="true"></i> Quản lý tài khoản</a>
                                <ul class="sub-menu">
                                    <li><a href="../user-info" title=""><i class="fa fa-user" aria-hidden="true"></i> Thông tin cá nhân</a></li>
                                    <hr />
                                    <li><a href="" title=""><i class="fa fa-plus-circle" aria-hidden="true"></i> Đăng nhà nhà trọ</a></li>
                                    <li><a href="../user-manage-post" title=""><i class="fa fa-list-alt" aria-hidden="true"></i> Quản lý nhà trọ</a></li>
                                    <hr />
                                    <li><a href="#" title=""><i class="fa fa-key" aria-hidden="true"></i> Đổi mật khẩu</a></li>
                                    <li><a href="../signOut" title=""><i class="fa fa-sign-out" aria-hidden="true"></i> Thoát</a></li>
                                    <hr />
                                    <li><a href="#" title=""><i class="fa fa-question-circle" aria-hidden="true"></i> Liên hệ hỗ trợ</a></li>
                                </ul>
                            </li>
                            <li class="right-li current-menu-item">
                                <a href="../post" title="Đăng tin"><i class="fa fa-pencil-square-o"></i> Đăng tin</a>
                            </li>
                            <%
                            	} else {
                            %>
                            <li class="right-li current-menu-item">
                                <a href="../post" title="Đăng tin"><i class="fa fa-pencil-square-o"></i> Đăng tin</a>
                            </li>
                            <li class="right-li">
                                <a href="../sign-in" title="Đăng nhập"><i class="fa fa-pencil-square-o"></i> Đăng nhập</a>
                            </li>
                            <li class="right-li">
                                <a href="../sign-up" title="Đăng ký"><i class="fa fa-pencil-square-o"></i> Đăng ký</a>
                            </li>
                            <%
                            	}
                            %>
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
                        <a>Tìm kiếm</a>
                        <i class="fa fa-angle-double-right"></i>
                        <a>Chi tiết nhà trọ</a>
                    </span>
                </p>
            </div>
            <!-- end title page -->

            <!-- start body -->
            <div class="container">

                <div class="title-house">
                    <h3>${post.getHostel().getTitle() }</h3>
                </div>

                <div class="hidden-xs">
                    <div class="navigation_post">
                        <div class="navigation_post_left">
                            <ul>
                                <li><a class="click-to-post" href="#_thongtinchung">Thông tin chung</a></li>
                                <li><a class="click-to-post" href="#_motachitiet">Mô tả chi tiết</a></li>
                                <li><a class="click-to-post" href="#_hinhanh">Hình ảnh</a></li>
                                <li><a class="click-to-post" href="#_video">Video</a></li>
                            </ul>
                        </div>
                        <div class="navigation_post_right">
                            <ul>
                                <li>
	                                <a class="btn_contact_now_phone" href="tel:${post.getUser().getPhone() }">
		                                <i class="fa fa-phone" aria-hidden="true"></i> 
		                                <strong>${post.getUser().getPhone() }</strong>
	                                </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>

                <div class="ds_phongtro">
                    <div class="row">
                        <div class="col-xs-12 col-md-8 block-left">

                            <div class="view-detail">
                                <div class="block-content" id="_thongtinchung">
                                    <div class="block-content-meta">
                                        <div class="post_summary_wrapper hidden-xs">
                                            <div class="post_summary-header">
                                                <h4>Thông tin chung</h4>
                                            </div>
                                            <div class="post_summary clearfix">
                                                <div class="summary_row clearfix">
                                                    <div class="post_summary_left fullwidth">
                                                        <div class="summary_item_headline">Địa chỉ:</div>
                                                        <div class="summary_item_info">
	                                                        ${post.getHostel().getHostelNumber()}, 
	                                                        ${post.getStreet().getName() }, 
	                                                        ${post.getDistrict().getName() }, 
	                                                        ${post.getCity().getName() }
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="summary_row clearfix">
                                                    <div class="post_summary_left">
                                                        <div class="summary_item_headline">Loại tin rao:</div>
                                                        <div class="summary_item_info">Cho thuê phòng trọ</div>
                                                    </div>
                                                    <div class="post_summary_right">
                                                        <div class="summary_item_headline">Mã tin:</div>
                                                        <div class="summary_item_info">${post.getHostel().getId() }</div>
                                                    </div>
                                                </div>
                                                <div class="summary_row clearfix">
                                                    <div class="post_summary_left">
                                                        <div class="summary_item_headline">Người đăng:</div>
                                                        <div class="summary_item_info"><b>${post.getUser().getFullname() }</b></div>
                                                    </div>
                                                    <div class="post_summary_right">
                                                        <div class="summary_item_headline">Điện thoại:</div>
                                                        <div class="summary_item_info summary_item_info_phone"> 
                                                        	<a href="tel:${post.getUser().getPhone() }" class="js-get-phone" data-phone="${post.getUser().getPhone() }">${post.getUser().getPhone() }</a>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="summary_row clearfix">
                                                    <div class="post_summary_left">
                                                        <div class="summary_item_headline">Ngày cập nhật:</div>
                                                        <div class="summary_item_info">${post.getHostel().getTimestamp() }</div>
                                                    </div>
                                                    <div class="post_summary_right">
                                                        <div class="summary_item_headline">Diện tích:</div>
                                                        <div class="summary_item_info summary_item_info_area">${post.getHostel().getSpace() } m²</div>
                                                    </div>
                                                </div>
                                                <div class="summary_row clearfix">
                                                    <div class="post_summary_left">
                                                        <div class="summary_item_headline">Trạng thái:</div>
                                                        <div class="summary_item_info">
                                                            <c:choose>
                                                                <c:when test="${post.getHostel().isIsRented() == false }">
                                                                    <span style="color: green;"><b>Chưa cho thuê</b></span>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <span style="color: red;"><b>Đã cho thuê</b></span>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                    </div>
                                                    <div class="post_summary_right">
                                                        <div class="summary_item_headline">Giá cho thuê:</div>
                                                        <div class="summary_item_info summary_item_info_price">${post.getPriceHostel() } tr/tháng</div>
                                                    </div>
                                                </div>
                                                <div class="summary_row clearfix">
	                                                 <div class="post_summary_left fullwidth">
	                                                     <div class="summary_item_headline">Đánh giá:</div>
	                                                     <div class="summary_item_info">
		                                                      <span class="field-results-item-desc-rate" id="field-results-item-desc-rate-id">
		                                                      
		                                                     	<%
									                            	if(session.getAttribute("user") != null) {
									                            %>
									                            
									                            	<c:forEach begin="1" end="${rate }" varStatus="i">
						                                                <div><img onclick="rateHostel(${post.getHostel().getId() }, ${user.getId()  }, ${i.index})" alt="" src="../resources/icons/star_liked.svg" /></div>
						                                            </c:forEach>
						
						                                            <c:forEach begin="${rate + 1}" end="5" varStatus="i">
						                                                <div><img onclick="rateHostel(${post.getHostel().getId() }, ${user.getId() }, ${i.index})" alt="" src="../resources/icons/star_not_liked.svg" /></div>
						                                            </c:forEach>

									                            <%
									                            	} else {
									                            %>
									                            
									                            	<c:forEach begin="1" end="5" varStatus="i">
						                                                <div><img onclick="rateHostel(${post.getHostel().getId() }, null, ${i.index})" alt="" src="../resources/icons/star_not_liked.svg" /></div>
						                                            </c:forEach>

									                            <%
									                            	}
									                            %>

						                                            						
						
						                                        </span>
	                                                     </div>
	                                                 </div>
	                                             </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="block-content" id="_motachitiet">
                                    <div class="post_summary_wrapper">
                                        <div class="post_summary-header">
                                            <h4>Mô tả chi tiết</h4>
                                        </div>
                                        <div class="post_summary-content">
                                            <p class="house-description">${post.getHostel().getDescription() }</p>
                                        </div>
                                    </div>
                                </div>

                                <div class="block-content" id="_hinhanh">
                                    <div class="post_summary_wrapper">
                                        <div class="post_summary-header">
                                            <h4>Hình ảnh</h4>
                                        </div>
                                        <div class="post_summary-content">
                                            <div class="post-photos">
                                                <ul>
                                                    <c:forEach items="${post.getImages() }" var="image">
                                                        <li>
                                                            <img src="../${image.getUrl() }">
                                                        </li>
                                                    </c:forEach>
                                                </ul>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="block-content" id="_video">
                                    <div class="post_summary_wrapper">
                                        <div class="post_summary-header">
                                            <h4>Video</h4>
                                        </div>
                                        <div class="post_summary-content">
                                        	<ul>
                                                <c:forEach items="${post.getVideos() }" var="video">
                                                    <li>
			                                            <video style="width: 100%;" controls>
			                                                <source src="../${video.getUrl() }" type="video/mp4">
			                                            </video>
                                                    </li>
                                                </c:forEach>
                                            </ul>
                                        </div>
                                    </div>
                                </div>

                                <div class="block-content" id="_video">
                                    <div class="post_summary_wrapper">
                                        <div class="post_summary-header">
                                            <h4>Google map</h4>
                                        </div>
                                        <div class="post_summary-content">
                                            <div class="div-map" id="div-map">
                                                <iframe onload="getUrlLocation(${post.getCity().getId() }, ${post.getDistrict().getId() }, ${post.getStreet().getId() }, ${post.getHostel().getHostelNumber()})" class="map-iframe" id="" src="" frameborder="0" scrolling="no" marginheight="0" marginwidth="0"></iframe>
                                            </div>
                                        </div>
                                    </div>
                                </div>

                                <div class="article_share">
                                    <h4 class="sharing_label"><b>Chia sẻ tin đăng đến bạn bè</b></h4>
                                    <div class="social-share">
                                        <ul>
                                            <li><a href="#"><img src="../resources/images/facebook.png" alt=""></a></li>
                                            <li><a href="#"><img src="../resources/images/twitter.png" alt=""></a></li>
                                            <li><a href="#"><img src="../resources/images/google-plus.png" alt=""></a></li>
                                        </ul>
                                    </div>
                                </div>

                                <div class="article_copy_link input-group">
                                    <input class="form-control article_copy_link_input" id="article_copy_link_input" type="text" readonly="" value="http://localhost:8080/247hostel/hostel-detail/${post.getHostel().getId()}">
                                    <div class="input-group-addon"><button class="btn btn_copy_link2" data-clipboard-target="#article_copy_link_input">Copy link</button></div>
                                </div>

                            </div>


                        </div>
                        <div class="col-xs-12 col-md-4 sidebar-right">


                            <div class="sidebar-box">
                                <h3 class="sidebarbox-title">
                                    <span><i class="fa fa-bars"></i>Thuê phòng trọ tại ${post.getCity().getName() }</span>
                                </h3>
                                <div class="sidebarbox-body">
                                    <ul class="box-lastnews">
                                        <c:forEach items="${districts }" var="district">
                                            <li>
                                                <a href="#" title="">Cho thuê phòng trọ tại ${district.getName() }</a>
                                            </li>
                                        </c:forEach>
                                    </ul>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <div class="ds_phongtro">
                    <div class="row">
                        <div class="col-xs-12 col-md-8 block-left">


                            <div class="list-all-new">

                                <ul class="nav nav-tabs local-tab">
                                    <li class="active">
                                        <a data-toggle="tab" href="#" aria-expanded="true">Nhà trọ: <span>${post.getDistrict().getName() }, ${post.getCity().getName() }</span></a>
                                    </li>
                                </ul>

                                <!-- start item -->
                                <c:forEach items="${posts }" var="p">
                                    <div class="news-item item-vip0">
                                        <div class="news-thumb">
                                            <a href="${p.getHostel().getId() }" title="">
                                                <img src="../${p.getHostelAvatar().getUrl() }" alt="">
                                            </a>
                                        </div>
                                        <div class="news-info">
                                            <h3 class="news-title">
                                                <a href="${p.getHostel().getId() }" title="">${p.getHostel().getTitle()}</a>
                                            </h3>
                                            <div class="room-detail">
                                                <span class="localtion">
                                                    Địa chỉ: <b>${p.getDistrict().getName() }, ${p.getCity().getName() }</b>
                                                    <span class="mgl-10">Diện tích: <b>${p.getHostel().getSpace()} m<sup>2</sup></b></span>
                                                    <span class="room-detail-item">
                                                        <span class="time">${p.getHostel().getTimestamp()}</span>
                                                    </span>
                                                </span>
                                            </div>
                                            <div class="price"><span>Giá: ${p.getPriceHostel() } tr/tháng</span></div>
                                            <p class="news-desc">${p.getHostel().getDescription()}<span>..</span></p>
                                        </div>
                                    </div>
                                </c:forEach>
                                <!-- end item -->

                            </div>

                        </div>
                        <div class="col-xs-12 col-md-4 sidebar-right">



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
                        <a type="button" class="btn btn-success" href="../post">ĐĂNG TIN NGAY</a>
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
                                        <li><a href="#"><img src="../resources/images/app_store.png" alt=""></a></li>
                                        <li><a href="#"><img src="../resources/images/google_play.png" alt=""></a></li>
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
                                        <ul>
                                            <li><a href="#" rel="nofollow">Giới thiệu</a></li>
                                            <li><a href="#" rel="nofollow">Quy định, điều khoản sử dụng</a></li>
                                            <li><a href="#" rel="nofollow">Hướng dẫn đăng tin</a></li>
                                            <li><a href="#" rel="nofollow">Liên hệ</a></li>
                                        </ul>
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


    <script type="text/javascript" src="../resources/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="../resources/js/owl.carousel.min.js"></script>
    <script type="text/javascript" src="../resources/js/select2.full.min.js"></script>
    <script type="text/javascript" src="../resources/js/validator.js"></script>
    <script type="text/javascript" src="../resources/js/main.js"></script>
    <script type="text/javascript" src="../resources/js/common.min.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.cookie-1.4.1.js"></script>
    <script type="text/javascript" src="../resources/js/lightgallery.min.js"></script>
    <script type="text/javascript" src="../resources/js/lightslider.min.js"></script>
    <script type="text/javascript" src="../resources/js/lg-thumbnail.js"></script>
    <script type="text/javascript" src="../resources/js/lg-fullscreen.js"></script>
    <script type="text/javascript" src="../resources/js/lg-autoplay.js"></script>
    <script type="text/javascript" src="../resources/js/lg-zoom.js"></script>
    <script type="text/javascript" src="../resources/js/jquery.mousewheel.min.js"></script>


</body>

</html>
