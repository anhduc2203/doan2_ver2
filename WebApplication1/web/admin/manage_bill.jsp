<%-- 
    Document   : manage_product
    Created on : Dec 20, 2018, 4:49:43 PM
    Author     : AnhDuc
--%>

<%@page import="dao.AccountDAO"%>
<%@page import="dao.BillDAO"%>
<%@page import="model.Bill"%>
<%@page import="java.util.ArrayList"%>
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
            BillDAO billDAO = new BillDAO();
            ArrayList<Bill> listBill = billDAO.getListBill();
            AccountDAO accountDAO = new AccountDAO();
        %>
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <div id="wrapper">

            <jsp:include page="menu1.jsp"></jsp:include>
            <div id="rightContent">
                <h3>Quản lí hóa đơn</h3>
                
                <br><br>
                <table class="data">
                    <tr class="data">
                        <th class="data" width="30px">Mã hóa đơn</th>
                        <th class="data">Khách hàng</th>
                        <th class="data">Tổng tiền</th>
                        <th class="data">Phương thức</th>
                        <th class="data">Địa chỉ giao hàng</th>
                        <th class="data">Ngày Order</th>
                        <th class="data" width="75px">Chức năng</th>
                    </tr>
                    
                    <%
                        
                        for (Bill bill : listBill){
                            
                        
                    %>
                    
                    <tr class="data">
                        <td class="data" width="30px"><%=bill.getBillID()%></td>
                        <td class="data"><%=bill.getAccountID()%></td>
                        <td class="data"><%=bill.getTotal()%></td>
                        <td class="data"><%=bill.getPayment()%></td>
                        <td class="data"><%=bill.getAddress()%></td>
                        <td class="data"><%=bill.getDate()%></td>
                        <td class="data" width="75px">
                            <center>
                                <a href="${root}/admin/manage_billdetail.jsp?billID=<%=bill.getBillID()%>">Chi tiết</a>
                                
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
