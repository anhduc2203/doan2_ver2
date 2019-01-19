<%-- 
    Document   : manage_category
    Created on : Dec 20, 2018, 4:28:22 PM
    Author     : AnhDuc
--%>

<%@page import="dao.AuthorDAO"%>
<%@page import="model.Author"%>
<%@page import="java.util.ArrayList"%>
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
        
            AuthorDAO authorDAO = new AuthorDAO();
            ArrayList<Author> listAuthor = authorDAO.getAuthor();
            
        %>
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <div id="wrapper">

            <jsp:include page="menu1.jsp"></jsp:include>
            <div id="rightContent">
                <h3>Quản lí tác giả</h3>
                <br>
                <a href="${root}/admin/insert_author.jsp">Thêm tác giả</a>
                <br><br>
                <%if (session.getAttribute("errdelauthor") != null) {%>
                <div>
                    <p style="color: red">*Không thể xóa tác giả này!</p>
                </div>
                <%
                    }
                    session.removeAttribute("errdelauthor");
                %>
                <table class="data">
                    <tr class="data">
                        <th class="data" width="30px">STT</th>
                        <th class="data">Mã tác giả</th>
                        <th class="data">Tên tác giả</th>
                        <th class="data" width="90px">Chức năng</th>
                    </tr>
                    
                    <%
                        int count = 0;
                        for (Author author: listAuthor){
                            count+=1;
                    %>
                    
                    <tr class="data">
                        <td class="data" width="30px"><%=count%></td>
                        <td class="data"><%=author.getAuthorID()%></td>
                        <td class="data"><%=author.getAuthorName()%></td>
                        <td class="data" width="90px">
                            <center>
                                <a href="${root}/admin/update_author.jsp?authorID=<%=author.getAuthorID()%>">Sửa</a>&nbsp;&nbsp; |&nbsp;&nbsp;
                                <a href="/WebApplication1/ManagerAuthorServlet?commandauthor=delete&authorID=<%=author.getAuthorID()%>">Xóa</a>
                            </center>
                        </td>
                    </tr>
                    
                    <%
                    }
                    %>
                    
                </table>
            </div>
            <div class="clear"></div>
            <jsp:include page="footer.jsp"></jsp:include>

        </div>
        
    </body>
</html>
