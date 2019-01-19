<%-- 
    Document   : manage_product
    Created on : Dec 20, 2018, 4:49:43 PM
    Author     : AnhDuc
--%>

<%@page import="dao.BookDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Book"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lí sách</title>
        
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/css/mos-style.css" rel='stylesheet' type='text/css' />
        
    </head>
    <body>
        
        <%
            BookDAO bookDAO = new BookDAO();
            ArrayList<Book> listBook = bookDAO.getListBook();
        %>
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <div id="wrapper">

            <jsp:include page="menu1.jsp"></jsp:include>
            <div id="rightContent">
                <h3>Quản lí sách</h3>
                <br>
                <a href="${root}/admin/insert_book.jsp">Thêm sách mới</a>
                <br><br>
                <%if (session.getAttribute("errdelbook") != null) {%>
                <div>
                    <p style="color: red">*Không thể xóa sách này!</p>
                </div>
                <%
                    }
                    session.removeAttribute("errdelbook");
                %>
                <table class="data">
                    <tr class="data">
                        <th class="data" width="30px">Mã sách</th>
                        <th class="data">Tên sách</th>
                        <th class="data">Giá</th>
                        <th class="data">Mô tả</th>
                        <th class="data" width="75px">Chức năng</th>
                    </tr>
                    
                    <%
                        
                        for (Book book: listBook){
                            
                        
                    %>
                    
                    <tr class="data">
                        
                        <td class="data"><%=book.getBookCode()%></td>
                        <td class="data"><%=book.getBookName()%></td>
                        <td class="data"><%=book.getBookPrice()%></td>
                        <td class="data"><%=book.getBookDescription()%></td>
                        <td class="data" width="75px">
                    <center>
                        <a href="${root}/admin/update_book.jsp?bookID=<%=book.getBookCode()%>">Sửa</a>&nbsp;&nbsp; |&nbsp;&nbsp;
                        <a href="/WebApplication1/ManagerBookServlet?command=delete&bookID=<%=book.getBookCode()%>">Xóa</a>
                    </center>
                    </td>
                    </tr>
                    <%}%>
                </table>
            </div>
            <div class="clear"></div>
            <jsp:include page="footer.jsp"></jsp:include>

        </div>
        
    </body>
</html>
