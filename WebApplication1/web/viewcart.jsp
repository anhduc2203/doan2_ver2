<%-- 
    Document   : viewcart
    Created on : Jan 9, 2019, 10:41:20 AM
    Author     : unknown_HUST
--%>

<%@page import="dao.AuthorDAO"%>
<%@page import="java.util.Map"%>
<%@page import="model.Item"%>
<%@page import="dao.CategoryDAO"%>
<%@page import="model.Account"%>
<%@page import="model.Cart"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Cart</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
        
        <link href="css/mos-style.css" rel='stylesheet' type='text/css' />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
    </head>
    <body>
        <%
            CategoryDAO categoryDAO = new CategoryDAO();
            AuthorDAO authorDAO = new AuthorDAO();
            Account account = null;
            
            Cart cart = (Cart) session.getAttribute("cart");
            if(cart == null){
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
        %>
        <jsp:include page="header.jsp"></jsp:include>
        <br><br><br>
        <%
            if(cart.cartItem.size() == 0){
        %>
        <div class="register_account">
            <div class="wrap">
                <h4 class="title">Shopping cart is empty</h4>
                <p class="cart">You have no items in your shopping cart.<br>Click <a href="index.jsp">here</a> to continue shopping.</p>
            </div>
        </div>
        <%  }
            else {
        %>
        <div>    
            <div>
                <table class="data">
                    <tr class="data">
                        <th class="data" width="10px">STT</th>
                        <th class="data" width="50px">Image</th>
                        <th class="data" width="200px">Tên Sách</th>
                        <th class="data" width="50px">Tác giả</th>
                        <th class="data" width="30px">Giá</th>
                        <th class="data" width="10px">Số lượng</th>
                        <th class="data" width="50px"></th>
                    </tr>

                <%
                    int count = 0;
                    for (Map.Entry<String, Item> list : cart.getCartItem().entrySet()) {
                        count += 1;
                %>

                    <tr class="data">
                        <td class="data" width="10px">
                            <div>
                                <p style="padding: 40px;"><%=count%></p>
                            </div>
                        </td>
                        <td class="data" width="50px">
                            <div>
                                <img src="<%=list.getValue().getBook().getBookImage()%>" width="60" height="90">
                            </div>
                        </td>
                        <td class="data" width="200px">
                            <div>
                                <p style="padding: 40px;"><%=list.getValue().getBook().getBookName()%></p>
                            </div>
                        </td>
                        <td class="data" width="50px">
                            <div>
                                <p style="padding: 40px;"><%=authorDAO.getAuthorByID(list.getValue().getBook().getAuthor())%></p>
                            </div>
                        </td>
                        <td class="data" width="30px">
                            <div>
                                <p style="padding: 40px;"><%=list.getValue().getBook().getBookPrice()%></p>
                            </div>
                        </td>
                        <td class="data" width="10px">
                            <div>
                                <p style="padding: 40px;"><%=list.getValue().getQuantity()%></p>
                            </div>
                        </td>
                        <td class="data" width="50px">
                            <div style="padding: 40px;">
                                <center>
                                    <a href="ViewCartServlet?cmd=add&bookID=<%=list.getValue().getBook().getBookCode()%>">Add</a>&nbsp; |&nbsp;<a href="ViewCartServlet?cmd=sub&bookID=<%=list.getValue().getBook().getBookCode()%>">Sub</a>&nbsp; |&nbsp;<a href="ViewCartServlet?cmd=del&bookID=<%=list.getValue().getBook().getBookCode()%>">Del</a>
                                </center>
                            </div>
                        </td>
                    </tr>

                <%
                    }
                %>
                    <tr class="data">
                        <td class="data" width="10px"></td>
                        <td class="data" width="50px"></td>
                        <td class="data" width="200px"></td>
                        <td class="data" width="50px"></td>
                        <td class="data" width="30px"></td>
                        <td class="data" width="10px"></td>
                        <td class="data" width="50px">
                            <div style="padding: 40px">
                                <span>Total:</span>
                                <span>$<%=cart.totalCart()%></span>
                            </div>
                        </td>
                    </tr>

                </table>
            </div>
                
            <!-- Total -->
            <div>
                <br>
                <div style="position: absolute; left: 42%;">
                    <a href="checkout.jsp" class="btncheckout">Proceed to Checkout</a>
                </div>
                <br> <br>
               
                <div class="clear"></div>
            </div>
        </div>
        <%}%>
    </body>
</html>
