<%-- 
    Document   : manage_historyconn
    Created on : Jan 16, 2019, 10:58:08 PM
    Author     : unknown_HUST
--%>

<%@page import="model.HistoryConnect"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.HistoryConnectDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Lịch sử truy cập</title>
        
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/css/mos-style.css" rel='stylesheet' type='text/css' />
        
    </head>
    <body>
        
        <%
            HistoryConnectDAO hcDAO = new HistoryConnectDAO();
            ArrayList<HistoryConnect> listhc = hcDAO.getListHistoryConnect();
            
        %>
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <div id="wrapper">

            <jsp:include page="menu1.jsp"></jsp:include>
            <div id="rightContent">
                <h3>Lịch sử truy cập</h3>
                
                <br><br>
                <table class="data">
                    <tr class="data">
                        <th class="data" width="30px">ID</th>
                        <th class="data" width="90px">Time</th>
                        <th class="data">Mã người dùng</th>
                        <th class="data">Loại</th>
                    </tr>
                    
                    <%
                        
                        for (HistoryConnect hc : listhc){
                            
                        
                    %>
                    
                    <tr class="data">
                        <td class="data" width="30px"><%=hc.getHistoryID()%></td>
                        <td class="data" width="90px"><%=hc.getDate()%></td>
                        <td class="data"><%=hc.getUserID()%></td>
                        <td class="data"><%=hc.getUserRole()%></td>
                    </tr>
                    <%}%>
                </table>
            </div>
            <div class="clear"></div>
            <jsp:include page="footer.jsp"></jsp:include>

        </div>
        
    </body>
</html>
