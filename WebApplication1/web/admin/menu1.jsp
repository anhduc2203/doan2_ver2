<%-- 
    Document   : menu
    Created on : Dec 20, 2018, 2:57:24 PM
    Author     : AnhDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>menu</title>
        
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/css/mos-style.css" rel='stylesheet' type='text/css' />
    </head>
    <body>
<!--        Giao diện đã đăng nhập-->
        <div id="leftBar">
            <ul>
                <li><a href="${root}/WebApplication1/admin/index1.jsp">Trang chủ</a></li>
                <li><a href="${root}/WebApplication1/admin/manage_khachhang.jsp">Khách hàng</a></li>
                <li><a href="${root}/WebApplication1/admin/manage_category.jsp">Danh mục</a></li>
                <li><a href="${root}/WebApplication1/admin/manage_author.jsp">Tác giả</a></li>
                <li><a href="${root}/WebApplication1/admin/manage_book.jsp">Sách</a></li>
                <li><a href="${root}/WebApplication1/admin/manage_bill.jsp">Hóa đơn</a></li>
                <li><a href="/WebApplication1/ChartServlet">Thống kê</a></li>
                <li><a href="${root}/WebApplication1/admin/manage_historyconn.jsp">Truy cập</a></li>
            </ul>
        </div>
        
    </body>
</html>
