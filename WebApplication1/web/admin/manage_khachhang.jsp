<%-- 
    Document   : manage_khachhang
    Created on : Jan 15, 2019, 8:22:47 AM
    Author     : unknown_HUST
--%>

<%@page import="model.Account"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lí khách hàng</title>
        
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/css/mos-style.css" rel='stylesheet' type='text/css' />
        
    </head>
    <body>
        
        <%
            AccountDAO accDAO = new AccountDAO();
            ArrayList<Account> listKH = accDAO.getListKhachHang();
        %>
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <div id="wrapper">

            <jsp:include page="menu1.jsp"></jsp:include>
            <div id="rightContent">
                <h3>Quản lí khách hàng</h3>
                
                <br><br>
                <table class="data">
                    <tr class="data">
                        <th class="data">Tài khoản</th>
                        <th class="data">Mật khẩu</th>
                        <th class="data">Full name</th>
                        <th class="data">Email</th>
                        <th class="data">SĐT</th>
                        <th class="data" width="75px"></th>
                    </tr>
                    
                    <%
                        
                        for (Account acc : listKH){
                            
                        
                    %>
                    
                    <tr class="data">
                        
                        <td class="data"><%=acc.getUserName()%></td>
                        <td class="data"><%=acc.getUserPass()%></td>
                        <td class="data"><%=acc.getFullName()%></td>
                        <td class="data"><%=acc.getUserEmail()%></td>
                        <td class="data"><%=acc.getPhoneNumber()%></td>
                        <td class="data" width="75px">
                            <center>
                                <a href="${root}/admin/update_khachhang.jsp?accountID=<%=acc.getUserCode()%>">Sửa</a>&nbsp;&nbsp;&nbsp;&nbsp;
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
