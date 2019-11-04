<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="vi">

<head>
    <meta charset="UTF-8">
    <title>Không tìm thấy trang</title>
    <link rel="shortcut icon" href="resources/icons/favicon.ico" />

    <!-- Boostrap -->
    <link rel="stylesheet" href="resources/bootstrap/css/bootstrap.min.css" />
    <script src="resources/bootstrap/jquery/jquery-3.4.1.min.js"></script>
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>
    
    <style>
	    
	    a {
	    	text-decoration: none;
	    	margin: auto 1em;
	    }
	    
	    .go-back {
	    	padding: 0.5em 1em;
	    	background-color: orange;
	    	color: #fff;
	    }
	    
	    .go-home {
	    	padding: 0.5em 1em;
	    	background-color: green;
	    	color: #fff;
	    }
	    
	    .container-content {
	    	margin: 5em auto;
	    }
	    
	    .error-footer {
	    	margin: 3em auto;
	    }
    
    </style>

</head>

<body>
	
	<div class="container container-content">
		<h2>404 - Không tìm thấy trang yêu cầu</h2>
		<hr />
		<div class="error-footer">
			<a href="#"><span class="go-back">Trở lại</span></a>
			<a href="home"><span class="go-home">Về trang chủ</span></a>
		</div>
	</div>
	
</body>

</html>
