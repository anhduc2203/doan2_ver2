<%-- 
    Document   : update_khachhang
    Created on : Jan 15, 2019, 8:55:02 AM
    Author     : unknown_HUST
--%>

<%@page import="model.Account"%>
<%@page import="dao.AccountDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật</title>
        
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/css/mos-style.css" rel='stylesheet' type='text/css' />
        
        <script src="<c:url value="/ckeditor/ckeditor.js" />"></script>
    </head>
    <body>
        
        <%
            
            String error = "";
            if (request.getParameter("error") != null){
                error = (String) request.getParameter("error");
            }
            AccountDAO accDAO = new AccountDAO();
            Account acc = accDAO.getAccountUpdate(request.getParameter("accountID"));
        %>
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <div id="wrapper">

            <jsp:include page="menu1.jsp"></jsp:include>
            
            <div id="rightContent">
                <h3>Cập nhật khách hàng</h3>
                <form action="/WebApplication1/ManageKhachHangServlet" method="post">
                    <table width="95%">
                        <tr>
                            <td><b>Full name</b></td>
                            <td><input type="text" class="sedang" name="tenKH" value="<%=acc.getFullName()%>"><%=error%></td>
                        </tr>
                        <tr>
                            <td><b>Company or School</b></td>
                            <td><input type="text" class="sedang" name="ctyorSchool" value="<%=acc.getCityOrSchool()%>"><%=error%></td>
                        </tr>
                        <tr>
                            <td><b>Email</b></td>
                            <td><input type="text" class="sedang" name="email" value="<%=acc.getUserEmail()%>"><%=error%></td>
                        </tr>
                        <tr>
                            <td><b>Phone number</b></td>
                            <td><input type="text" class="sedang" name="phone" value="<%=acc.getPhoneNumber()%>"><%=error%></td>
                        </tr>
                        <tr>
                            <td><b>Address</b></td>
                            <td><input type="text" class="sedang" name="addr" value="<%=acc.getUserAddres()%>"><%=error%></td>
                        </tr>
                        <tr>
                            <td><b>Country</b></td>

                            <td>
                                <select name="country"><%=error%>
                                    <option selected>-- Select --</option>
                                    <option value="Viet nam">Viet nam</option>
                                    <option value="USA">USA</option>
                                    <option value="Korea">Korea</option>
                                    <option value="Japan">Japan</option>
                                </select>
                            </td>

                        </tr>
                        
                        
                        <tr>
                            <td></td>
                            <td>
                                <input type="hidden" name="command" value="update">
                                <input type="hidden" name="accountID" value="<%=request.getParameter("accountID")%>">
                                <input type="submit" class="button" value="Lưu">
<!--                                <input type="hidden" name="command" value="insert">
                                <input type="submit" class="button" value="Lưu">-->
                            </td>
                        </tr>
                    </table>
                </form>
            </div>
            
            <div class="clear"></div>
            <jsp:include page="footer.jsp"></jsp:include>

        </div>
        
    </body>
</html>
