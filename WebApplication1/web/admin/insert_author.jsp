<%-- 
    Document   : insert_category
    Created on : Dec 20, 2018, 5:12:11 PM
    Author     : AnhDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lí tác giả</title>
        
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/css/mos-style.css" rel='stylesheet' type='text/css' />
    </head>
    <body>
        
        <%
            String error = "";
            if (session.getAttribute("errorauthor") != null){
                error = (String) session.getAttribute("errorauthor");
            }
        
        %>
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <div id="wrapper">

            <jsp:include page="menu1.jsp"></jsp:include>
            
            <div id="rightContent">
                <h3>Danh sách tác giả</h3>
                <form action="/WebApplication1/ManagerAuthorServlet" method="post">
                <table width="95%">
                    <tr>
                        <td style="float: right"><b>Danh sách tác giả</b></td>
                        <td><input type="text" class="sedang" name="tenTacGia"><%=error%></td>
                    </tr>
                    <tr><td></td><td>
                            <input type="hidden" name="commandauthor" value="insert">
                            <input type="submit" class="button" value="Lưu dữ liệu">
                        </td></tr>
                </table>
                </form>
            </div>
            
            <div class="clear"></div>
            <jsp:include page="footer.jsp"></jsp:include>

        </div>
        
    </body>
</html>
