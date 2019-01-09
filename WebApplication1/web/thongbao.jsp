<%-- 
    Document   : thongbao
    Created on : Jan 8, 2019, 10:19:51 PM
    Author     : unknown_HUST
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Notifications</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
	<link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
	<link href="css/form.css" rel="stylesheet" type="text/css" media="all" />
	<link href='http://fonts.googleapis.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
	<script type="text/javascript" src="js/jquery1.min.js"></script>
	<!-- start menu -->
	<link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
	<script type="text/javascript" src="js/megamenu.js"></script>
	<script>$(document).ready(function () { $(".megamenu").megamenu(); });</script>
	<!--start slider -->
	<link rel="stylesheet" href="css/fwslider.css" media="all">
	<script src="js/jquery-ui.min.js"></script>
	<script src="js/css3-mediaqueries.js"></script>
	<script src="js/fwslider.js"></script>
	<!--end slider -->
	<script src="js/jquery.easydropdown.js"></script>
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        <div class="register_account">
		<div class="wrap">
			<h4 class="title">Notifications</h4>
			<p class="cart">Đã xử lý đơn hàng của bạn, vui lòng kiểm tra email.<br>Click<a href="index.jsp"> here</a> to continue shopping</p>
		</div>
	</div>
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
