<%-- 
    Document   : manage_billdetail
    Created on : Jan 12, 2019, 12:19:45 AM
    Author     : unknown_HUST
--%>

<%@page import="dao.BookDAO"%>
<%@page import="dao.AccountDAO"%>
<%@page import="model.BillDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dao.BillDetailDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Quản lí hóa đơn</title>
        
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/css/mos-style.css" rel='stylesheet' type='text/css' />
        
    </head>
    <body>
        
        <%
            String billID = request.getParameter("billID");
            BillDetailDAO billdetailDAO = new BillDetailDAO();
            ArrayList<BillDetail> listBillDetail = billdetailDAO.getListBillDetailByBillID(billID);
            BookDAO bookDAO = new BookDAO();
        %>
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <div id="wrapper">

            <jsp:include page="menu1.jsp"></jsp:include>
            <div id="rightContent">
                <h3>Chi tiết hóa đơn: <%=billID%></h3>
                
                <br><br>
                <table class="data">
                    <tr class="data">
                        <th class="data" width="30px">ID</th>
                        <th class="data">Tên sách</th>
                        <th class="data">Giá sách</th>
                        <th class="data">Số lượng Order</th>
                    </tr>
                    
                    <%
                        
                        for (BillDetail billdetail : listBillDetail){
                            
                        
                    %>
                    
                    <tr class="data">
                        <td class="data" width="30px"><%=billdetail.getBillDetailID() %></td>
                        <td class="data"><%=bookDAO.getBookByBookID(billdetail.getBookID()).getBookName()%></td>
                        <td class="data"><%=billdetail.getPrice()%></td>
                        <td class="data"><%=billdetail.getQuantity()%></td>
                    </tr>
                    <%}%>
                </table>
            </div>
            <div class="clear"></div>
            <jsp:include page="footer.jsp"></jsp:include>

        </div>
        
    </body>
</html>
