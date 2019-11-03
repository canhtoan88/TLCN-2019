<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <title>247 hostel</title>
    <link rel="shortcut icon" href="resources/icons/favicon.ico" />

    <!-- Boostrap -->
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css" />
    <script src="resources/bootstrap/jquery/jquery-3.4.1.min.js"></script>
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>

    <link rel="stylesheet" type="text/css" href="resources/css/home_style.css">
    <script type="text/javascript" src="resources/js/app_script.js"></script>


    <script>
        var idCity = 0;
        var idDistrict = 0;
        var idStreet = 0;
        var idRangePrice = -1;
        var idRangeSpace = -1;
        var distance = 20000;

        var iconUserUrl = "resources/icons/map-marker-user.png";
        var iconHostelUrl = "resources/icons/logo.png";

        var hcmObj = {
            lat: 10.772941,
            lng: 106.698140,
            type: "user"
        }

        var map, infoWindow, service;
        var myGeolocationObj = hcmObj;
        var zoom;

        var markers = [];
        var hostels = [];


        /* zoom = 11;
	     distance = 20000;
	     zoom = 12;
	     distance = 10000;
	     zoom = 13;
	     distance = 5000;
	     zoom = 14;
	     distance = 2000;
	     zoom = 15;
	     distance = 1000; */


        // Initialize and add the map
        function initMap() {

            zoom = 13;
            distance = 5000; // meter

            createMap();

            // Try HTML5 geolocation.
            infoWindow = new google.maps.InfoWindow;
            if (navigator.geolocation) {
                navigator.geolocation.getCurrentPosition(function(position) {
                    myGeolocationObj = {
                        lat: position.coords.latitude,
                        lng: position.coords.longitude,
                        type: "user"
                    };

                    // console.log("Geolocation founded.");

                    createFullMap();
                }, function() {
                    // Not founded Geolocation

                    // console.log("Not founded Geolocation.");

                    myGeolocationObj = hcmObj;
                    createFullMap();
                });
            } else {
                // Browser not support Geolocation

                // console.log("Browser not support Geolocation.");

                myGeolocationObj = hcmObj;
                createFullMap();
            }
        }

        function createMap() {
            map = new google.maps.Map(document.getElementById('map'), {
                center: {
                    lat: myGeolocationObj.lat,
                    lng: myGeolocationObj.lng
                },
                zoom: zoom
            });

            createMarker(myGeolocationObj, "user");
        }

        function createMarker(obj) {

            let icon = {};
            if (obj.type == "hostel") {
                icon = {
                    url: iconHostelUrl, // url
                    scaledSize: new google.maps.Size(32, 32), // scaled size
                    origin: new google.maps.Point(0, 0), // origin
                    anchor: new google.maps.Point(0, 0) // anchor
                };
            } else {
                icon = {
                    url: iconUserUrl, // url
                    scaledSize: new google.maps.Size(50, 50), // scaled size
                    origin: new google.maps.Point(0, 0), // origin
                    anchor: new google.maps.Point(0, 0) // anchor
                };
            }

            let marker = new google.maps.Marker({
                position: {
                    lat: obj.lat,
                    lng: obj.lng
                },
                map: map,
                icon: icon,
                animation: google.maps.Animation.DROP
            });

            if (obj.type == "hostel") {
                marker.addListener('click', function() {

                    getMarkerPopupContent(marker, obj.idHostel);

                    //                    console.log("lat="+marker.getPosition().lat()+"&lng="+marker.getPosition().lng()+"&idHostel="+obj.idHostel);
                });
            }

            // push into markers
            markers.push(marker);
        }

        function createMarkers(objs) {
            // Add some markers to the map.
            // Note: The code uses the JavaScript Array.prototype.map() method to
            // create an array of markers based on a given "locations" array.
            // The map() method here has nothing to do with the Google Maps API.
            let markers = objs.map(function(obj, i) {
                createMarker(obj);
            });

            // Add a marker clusterer to manage the markers.
            let markerCluster = new MarkerClusterer(map, markers);
        }

        function clearMarkers() {
            //Loop through all the markers and remove
            for (let i = 0; i < markers.length; i++) {
                markers[i].setMap(null);
            }
            markers = [];
            hostels = [];
        };

        function createCircleOnMap() {
            // Construct the circle for each value in citymap.
            // Note: We scale the area of the circle based on the population.
            // Add the circle for this city to the map.
            let cityCircle = new google.maps.Circle({
                strokeColor: '#4CAF50',
                strokeOpacity: 0.8,
                strokeWeight: 1,
                fillColor: '#b0ffb3',
                fillOpacity: 0.2,
                map: map,
                center: {
                    lat: myGeolocationObj.lat,
                    lng: myGeolocationObj.lng
                },
                radius: distance // meters
            });
        }

        function createFullMap() {
            clearMarkers();
            createMap();
            createCircleOnMap();
            getHostelsNearLocation(createMarkers);
        }

        function getHostelsNearLocation(callback) {

            let lat = myGeolocationObj.lat;
            let lng = myGeolocationObj.lng;
            let _idRangePrice = idRangePrice;
            let _idRangeSpace = idRangeSpace;
            let _distance = distance;

            $.ajax({
                type: "GET",
                url: "http://localhost:8080/247hostel/api/getHostelsNearLocation",
                data: {
                    lat: lat,
                    lng: lng,
                    idRangePrice: _idRangePrice,
                    idRangeSpace: _idRangeSpace,
                    distance: _distance
                },
                success: function(value) {

                    // Tạo thành mảng với mỗi phần tử ngăn bởi ";"
                    let strHostels = value.split(";");
                    strHostels.pop();

                    // Tạo thành mảng postions và idHostels
                    strHostels.map((strHostel, i) => {
                        let strHostelLocation = strHostel.split("-");

                        // Lấy giá trị hostel
                        let hostel = {
                            lat: parseFloat(strHostelLocation[0]),
                            lng: parseFloat(strHostelLocation[1]),
                            idHostel: parseFloat(strHostelLocation[2]),
                            type: "hostel"
                        };

                        // Thêm vào mảng
                        hostels.push(hostel);
                    });

                    callback(hostels);
                }
            });
        };

        function searchPlace(address) {

            let request = {
                query: address,
                fields: ['name', 'geometry']
            };

            service = new google.maps.places.PlacesService(map);

            service.findPlaceFromQuery(request, function(results, status) {
                if (status === google.maps.places.PlacesServiceStatus.OK) {

                    myGeolocationObj = {
                        lat: results[0].geometry.location.lat(),
                        lng: results[0].geometry.location.lng(),
                        type: "user"
                    };

                    createFullMap();
                }
            });
        };

        function getMarkerPopupContent(marker, idHostel) {

            $.ajax({
                type: "GET",
                url: "http://localhost:8080/247hostel/api/getMarkerPopupContent",
                data: {
                    idHostel: idHostel,
                },
                success: function(value) {

                    showMarkerPopup(marker, value);
                }
            });
        }


        function showMarkerPopup(marker, value) {
            infowindow = new google.maps.InfoWindow({
                content: value
            });
            infowindow.open(map, marker);

            //            console.log(value);
        }

    </script>
    <script src="https://developers.google.com/maps/documentation/javascript/examples/markerclusterer/markerclusterer.js">
    </script>
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyD9mzilMG4xBbeqNYQpBzLysB2YMxWVNfs&libraries=places&language=vi&callback=initMap" async defer>
    </script>
</head>

<body>
    <div class="home">
        <div class="home-left">
            <div class="logo">
                <img class="logo-image" alt="247 hostel" src="resources/icons/logo.svg" /> <span class="logo-title">247
                    Hostel</span>
            </div>
            <div class="field-filter">
                <div class="container">
                    <div class="form-group field-filter-form-search">
                        <input type="text" class="form-control" id="input-search" placeholder="Tìm kiếm.." onkeypress="searchLocation(event)">
                    </div>
                </div>
                <div class="container">
                    <form>
                        <div class="row field-filter-form">
                            <div class="col-4 field-filter-form-label">Tỉnh/Thành phố</div>
                            <div class="col-8">
                                <select id="ddlProvince" name="province" class="form-control">
                                    <option value="0">Tỉnh/Thành phố</option>

                                    <c:forEach items="${cities }" var="city">
                                        <option value="${city.getId() }">${city.getName() }</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="row field-filter-form">
                            <div class="col-4 field-filter-form-label">Quận/Huyện</div>
                            <div class="col-8">
                                <select id="ddlDistrict" name="district" class="form-control">
                                    <option value="0">Quận/Huyện</option>
                                </select>
                            </div>
                        </div>
                        <div class="row field-filter-form">
                            <div class="col-4 field-filter-form-label">Tên đường</div>
                            <div class="col-8">
                                <select id="ddlStreet" name="street" class="form-control">
                                    <option value="0">Tên đường</option>

                                </select>
                            </div>
                        </div>
                        <div class="row field-filter-form">
                            <div class="col-4 field-filter-form-label">Giá</div>
                            <div class="col-8">
                                <select id="ddlPrice" name="rangePrice" class="form-control">
                                    <option value="-1">Tất cả</option>
                                    <option value="0">dưới 2 triệu</option>
                                    <option value="1">2 - 3 triệu</option>
                                    <option value="2">3 - 4 triệu</option>
                                    <option value="3">4 - 6 triệu</option>
                                    <option value="4">6 - 10 triệu</option>
                                    <option value="5">trên 10 triệu</option>
                                </select>
                            </div>
                        </div>
                        <div class="row field-filter-form">
                            <div class="col-4 field-filter-form-label">Diện tích</div>
                            <div class="col-8">
                                <select id="ddlSpace" name="rangeSpace" class="form-control">
                                    <option value="-1">Tất cả</option>
                                    <option value="0">dưới 20 m2</option>
                                    <option value="1">20 - 30 m2</option>
                                    <option value="2">30 - 40 m2</option>
                                    <option value="3">40 - 60 m2</option>
                                    <option value="4">60 - 100 m2</option>
                                    <option value="5">trên 100 m2</option>
                                </select>
                            </div>
                        </div>
                        <div class="row field-filter-form">
                            <div class="col-4 field-filter-form-label">Bán kính</div>
                            <div class="col-8">
                                <select id="ddlDistance" name="distance" class="form-control">
                                    <option value="20000">Tất cả</option>
                                    <option value="1000">1 km</option>
                                    <option value="2000">2 km</option>
                                    <option value="5000">5 km</option>
                                    <option value="10000">10 km</option>
                                    <option value="20000">20 km</option>
                                </select>
                            </div>
                        </div>
                    </form>
                    <!-- <div class="field-submit">
                        <button type="button" class="btn btn-success" id="field-submit-search">Tìm kiếm</button>
                    </div> -->
                </div>
            </div>
            <hr class="field-filter-divide" />
            <div class="field-results">
                <div class="container field-results-title">
                    <div class="field-results-title-subtitle">Kết quả</div>
                    <select id="field-results-title-dropdown" class="field-results-title-dropdown">
                        <option value="0">Mới nhất</option>
                        <option value="1">Phổ biến nhất</option>
                        <option value="2">Giá tăng dần</option>
                        <option value="3">Giá giảm dần</option>
                        <option value="4">Diện tích tăng dần</option>
                        <option value="5">Diện tích giảm dần</option>
                        <option value="6">Bán kính tăng dần</option>
                        <option value="7">Bán kính giảm dần</option>
                    </select>
                    <hr class="field-filter-divide-2" />
                </div>
                <div class="field-results-list" id="field-results-list">
                    <div class="container">

                        <c:forEach items="${posts }" var="post">

                            <div class="field-results-item">
                                <a href="hostel-detail/${post.getHostel().getId() }" target="_blank">
                                    <div class="field-results-item-img">
                                        <img src="${post.getHostelAvatar().getUrl() }" alt="" />
                                    </div>
                                </a>
                                <div class="field-results-item-desc">
                                    <div class="field-results-item-desc-title">
                                        <a href="hostel-detail/${post.getHostel().getId() }" target="_blank">
                                            ${post.getHostel().getTitle() }
                                        </a>
                                    </div>
                                    <div>
                                        <span class="field-results-item-desc-price">Giá: ${post.getPriceHostel() } tr/tháng</span>
                                        <span class="field-results-item-desc-space">${post.getHostel().getSpace() }/m2</span>
                                        <span class="field-results-item-desc-time-up">${post.getTimestamp() } </span>
                                    </div>
                                    <div class="field-results-item-desc-sub-desc">${post.getHostel().getDescription() }</div>
                                    <div>
										<span class="field-results-item-desc-rate">

                                            <c:forEach begin="1" end="${post.getRateAvg() }">
                                                <img alt="" src="resources/icons/star_liked.svg" />
                                            </c:forEach>

                                            <c:forEach begin="${post.getRateAvg() + 1}" end="5">
                                                <img alt="" src="resources/icons/star_not_liked.svg" />
                                            </c:forEach>


                                        </span>
                                        <span class="field-results-item-desc-contact">
                                            <img alt="" src="resources/icons/phone_color.svg" />
                                            <span class="field-results-item-desc-contact-phone">
                                                <a href="tel:${post.getUser().getPhone() }" data-phone="${post.getUser().getPhone() }">${post.getUser().getPhone() }</a>
                                            </span>
                                        </span>
                                    </div>
                                </div>
                                <hr class="field-results-item-hr" />
                            </div>

                        </c:forEach>

                    </div>
                </div>
            </div>
            <hr class="field-filter-divide" />
            <div class="footer">
                <div class="footer-title">
                    <img alt="247 hostel" src="resources/icons/logo.svg" />
                    <span>247 hostel</span>
                </div>
                <div class="footer-title-sub">Website thuê và cho thuê phòng trọ uy tín nhất Việt Nam.</div>
                <div class="footer-content">Giúp bạn tìm kiếm, thuê và cho thuê nhà trọ với chất lượng tốt nhất!</div>
            </div>
        </div>
        <div class="home-right">
            <div id="map"></div>

        </div>

        <%
				if (session.getAttribute("user") != null) {
			%>
        <div class="dropdown-user" id="dropdown-user" onmouseleave="disableDropdownUser()">
            <div class="dropdown-user-img">
                <img alt="" src="${avatar.getUrl() }" class="rounded-circle" onclick="onUserDropdownClicked()" />
            </div>
            <div class="dropdown-user-body" id="dropdown-user-body">
                <div class="dropdown-user-body-sub"></div>
                <div class="dropdown-user-body-main">
                    <div class="dropdown-user-body-content">
                        <a href="user-info">
                            <img class="dropdown-user-body-content-image" alt="" src="resources/icons/user.svg" />
                            <span class="dropdown-user-body-content-title">
                                <b>${user.getFullname() }</b>
                            </span>
                        </a>
                    </div>
                    <hr class="dropdown-user-body-content-divide" />
                    <div class="dropdown-user-body-content">
                        <a href="user-info">
                            <img class="dropdown-user-body-content-image" alt="" src="resources/icons/edit.svg" />
                            <span class="dropdown-user-body-content-title"> Thông tin cá nhân </span>
                        </a>
                    </div>
                    <hr class="dropdown-user-body-content-divide" />
                    <div class="dropdown-user-body-content">
                        <a href="post">
                            <img class="dropdown-user-body-content-image" alt="" src="resources/icons/add.svg" />
                            <span class="dropdown-user-body-content-title"> Đăng nhà trọ </span>
                        </a>
                    </div>
                    <hr class="dropdown-user-body-content-divide" />
                    <div class="dropdown-user-body-content">
                        <a href="user-manage-post">
                            <img class="dropdown-user-body-content-image" alt="" src="resources/icons/list.svg" />
                            <span class="dropdown-user-body-content-title"> Quản lý nhà trọ </span>
                        </a>
                    </div>
                    <hr class="dropdown-user-body-content-divide" />
                    <div class="dropdown-user-body-content">
                        <a href="change-password">
                            <img class="dropdown-user-body-content-image" alt="" src="resources/icons/key.svg" />
                            <span class="dropdown-user-body-content-title"> Đổi mật khẩu </span>
                        </a>
                    </div>
                    <hr class="dropdown-user-body-content-divide" />
                    <div class="dropdown-user-body-content">
                        <a href="#">
                            <img class="dropdown-user-body-content-image" alt="" src="resources/icons/contact.svg" />
                            <span class="dropdown-user-body-content-title"> Liên hệ hỗ trợ </span>
                        </a>
                    </div>
                    <hr class="dropdown-user-body-content-divide" />
                    <div class="dropdown-user-body-content">
                        <a href="signOut">
                            <img class="dropdown-user-body-content-image" alt="" src="resources/icons/logout.svg" />
                            <span class="dropdown-user-body-content-title"> Thoát </span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <%
				} else {
			%>
        <div class="dropdown-user" id="dropdown-user" onmouseleave="disableDropdownUser()">
            <div class="dropdown-user-img">
                <img src="resources/icons/menu.svg" class="rounded-circle" onclick="onUserDropdownClicked()" />
            </div>
            <div class="dropdown-user-body" id="dropdown-user-body">
                <div class="dropdown-user-body-sub"></div>
                <div class="dropdown-user-body-main">
                    <div class="dropdown-user-body-content">
                        <a href="sign-in">
                            <img class="dropdown-user-body-content-image" alt="" src="resources/icons/login.svg" />
                            <span class="dropdown-user-body-content-title"> Đăng nhập </span>
                        </a>
                    </div>
                    <hr class="dropdown-user-body-content-divide" />
                    <div class="dropdown-user-body-content">
                        <a href="sign-up">
                            <img class="dropdown-user-body-content-image" alt="" src="resources/icons/signup.svg" />
                            <span class="dropdown-user-body-content-title"> Đăng ký </span>
                        </a>
                    </div>
                    <hr class="dropdown-user-body-content-divide" />
                    <div class="dropdown-user-body-content">
                        <a href="#">
                            <img class="dropdown-user-body-content-image" alt="" src="resources/icons/lock.svg" />
                            <span class="dropdown-user-body-content-title"> Quên mật khẩu </span>
                        </a>
                    </div>
                    <hr class="dropdown-user-body-content-divide" />
                    <div class="dropdown-user-body-content">
                        <a href="#">
                            <img class="dropdown-user-body-content-image" alt="" src="resources/icons/contact.svg" />
                            <span class="dropdown-user-body-content-title"> Liên hệ hỗ trợ</span>
                        </a>
                    </div>
                </div>
            </div>
        </div>
        <%
				}
			%>
        <script>
            function onUserDropdownClicked() {
                let x = document.getElementById("dropdown-user-body");
                if (x.style.display === "block") {
                    x.style.display = "none";
                } else {
                    x.style.display = "block";
                }
            }

            function disableDropdownUser() {
                let x = document.getElementById("dropdown-user-body");
                if (x.style.display === "block") {
                    x.style.display = "none";
                }
            }

        </script>
    </div>

    </div>

</body>

</html>
