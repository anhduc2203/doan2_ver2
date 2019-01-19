<%-- 
    Document   : login
    Created on : Dec 20, 2018, 4:56:55 PM
    Author     : AnhDuc
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Đăng nhập</title>
        
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/css/mos-style.css" rel='stylesheet' type='text/css' />
    </head>
    <body>
        
        <div id="header">
            <div class="inHeaderLogin"></div>
        </div>

        <div id="loginForm">
            <div class="headLoginForm">
                Đăng nhập với tư cách admin
            </div>
            <div class="fieldLogin">
                <form action="/WebApplication1/AccountServlet" method="post" name="loginAdmin">
                        <%if (session.getAttribute("error") != null) {%>
                        <div>
                            <p style="color: red"><%=session.getAttribute("error")%></p>
                        </div>
                        <%}%>
                        <label>Username</label><br>
                        <input type="text" class="login" name="username" autocomplete="off"><br>
                        <label>Password</label><br>
                        <input type="password" class="login" name="password" autocomplete="off"><br>
                        <button class="button" type="submit" value="loginAdmin" name="command">Login</button>
                </form>
            </div>
        </div>
        
    </body>
</html>
