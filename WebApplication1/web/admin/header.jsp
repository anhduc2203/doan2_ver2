<%-- 
    Document   : header
    Created on : Dec 20, 2018, 2:31:08 PM
    Author     : AnhDuc
--%>

<%@page import="model.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>header</title>
    </head>
    <body>
        <%
            Account account = null;
        %>
        <div id="header">
            <div class="inHeader">
                <div class="mosAdmin">
                    <%
                        if (session.getAttribute("accadmin") != null){
                            account = (Account) session.getAttribute("accadmin");
                    %>
                    <li class="active"><a href="#">Hello, <%=account.getUserName() %></a></li>
                    <a href="/WebApplication1/index.jsp">Website</a>
                    <%}else{%>
                        <li class="active"><a href="#">Hello, Admin, Bạn chưa đăng nhập!</a></li>
                        <a href="/WebApplication1/index.jsp">Website</a> | <a href="login.jsp">Đăng nhập</a>
                    <%}%>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        
        
    </body>
</html>
