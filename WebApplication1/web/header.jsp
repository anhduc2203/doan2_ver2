<%-- 
    Document   : header
    Created on : Nov 22, 2018, 10:59:33 AM
    Author     : unknown_HUST
--%>

<%@page import="org.apache.catalina.ant.ListTask"%>
<%@page import="model.Item"%>
<%@page import="java.util.Map"%>
<%@page import="model.Cart"%>
<%@page import="model.Account"%>
<%@page import="model.Category"%>
<%@page import="dao.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>header</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/form.css" rel="stylesheet" type="text/css" media="all" />
        <link href='http://fonts.googleapis.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery1.min.js"></script>
        <!-- start menu -->
        <link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="js/megamenu.js"></script>
        <script>$(document).ready(function () {
                $(".megamenu").megamenu();
            });</script>
        <!--start slider -->
        <link rel="stylesheet" href="css/fwslider.css" media="all">
        <script src="js/jquery-ui.min.js"></script>
        <script src="js/css3-mediaqueries.js"></script>
        <script src="js/fwslider.js"></script>
        <!--end slider -->
        <script src="js/jquery.easydropdown.js"></script>
        
        <!--Plugin Fb Comment-->
        <div id="fb-root"></div>
        <script>(function(d, s, id) {
          var js, fjs = d.getElementsByTagName(s)[0];
          if (d.getElementById(id)) return;
          js = d.createElement(s); js.id = id;
          js.src = 'https://connect.facebook.net/vi_VN/sdk.js#xfbml=1&version=v3.2&appId=723680054670657&autoLogAppEvents=1';
          fjs.parentNode.insertBefore(js, fjs);
        }(document, 'script', 'facebook-jssdk'));</script>
    </head>
    <body>

        <%
            CategoryDAO categoryDAO = new CategoryDAO();
            Account account = null;
            
            Cart cart = (Cart) session.getAttribute("cart");
            if(cart == null){
                cart = new Cart();
                session.setAttribute("cart", cart);
            }
        %>

        <div class="header-top">
            <div class="wrap">
                <div class="header-top-left">
                    <div class="box">
                        <select tabindex="4" class="dropdown">
                            <!--<option value="" class="label" value="">Language :</option> -->
                            <option value="1">English</option>

                        </select>
                    </div>
                    <div class="box1">
                        <select tabindex="4" class="dropdown">
                            <option value="" class="label" value="">Currency :</option>
                            <option value="1">$ Dollar</option>

                        </select>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="cssmenu">
                    <ul>
                        <%
                        if (session.getAttribute("acc") != null){
                            account = (Account) session.getAttribute("acc");
                        %>
                        <li class="active"><a href="#">Hello: <%=account.getUserName() %></a></li> |
                        <li><a href="changepasswd.jsp">Change Password</a></li> |
                        <li><a href="checkout.jsp">Checkout</a></li> |
                        <li><a href="LogoutServlet">Logout -></a></li>
                        <%}else{%>
                        <li class="active"><a href="login.jsp">Account</a></li> |
                        <!--<li><a href="checkout.html">Wishlist</a></li> | -->
                        <li><a href="checkout.jsp">Checkout</a></li> |
                        <li><a href="login.jsp">Log In</a></li> |
                        <li><a href="register.jsp">Sign Up</a></li>
                        <%}%>
                    </ul>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        <div class="header-bottom">
            <div class="wrap">
                <div class="header-bottom-left">
                    <div class="logo">
                        <a href="index.jsp"><img src="images/logo.png" alt="" /></a>
                    </div>
                    <div class="menu">
                        <ul class="megamenu skyblue">
                            <li class="active grid"><a href="index.jsp">Home</a></li>
                            <li><a class="color4" href="#">Category</a>
                                <div class="megapanel">
                                    <div class="row">
                                        <div class="col1">
                                            <div class="h_nav">
                                                <h4>Contact </h4>
                                                <ul>
                                                    <%
                                                        for (Category c : categoryDAO.getCategory()) {
                                                    %>
                                                    <li><a href="engbook.jsp?category=<%=c.getCategoryID() %>"><%=c.getCategoryName() %></a></li>
                                                    <%
                                                        }
                                                    %>
                                                </ul>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </li>
                            
                            <li><a class="color6" href="#">Other</a></li>
                            <li><a class="color7" href="checkout.jsp">Purchase</a></li>
                        </ul>
                    </div>
                </div>
                <div class="header-bottom-right">
                    <div class="search">
                        <form action="SearchBookServlet" method="post">
                        <input type="text" name="search" class="textbox" value="Search book..." onfocus="this.value = '';" onblur="if (this.value == '') {
                                    this.value = 'Search book...';
                                }">
                        <input type="submit" value="searchbook" id="searchbook" name="searchbook">
                        <div id="response"> </div>
                        </form>
                    </div>
                    <div class="tag-list">
                            <!--<ul class="icon1 sub-icon1 profile_img">
                            <li><a class="active-icon c1" href="#"> </a>
                                <ul class="sub-icon1 list">
                                    <li>
                                        <h3>sed diam nonummy</h3><a href=""></a>
                                    </li>
                                    <li>
                                        <p>Lorem ipsum dolor sit amet, consectetuer <a href="">adipiscing elit, sed diam</a></p>
                                    </li>
                                </ul>
                            </li>
                        </ul>-->
                        
                        <!--Phan gio hang-->
                        <ul class="icon1 sub-icon1 profile_img">
                            <li><a class="active-icon c2" href="viewcart.jsp"></a></li>
                        </ul>
                              
                        <ul class="last">
                            <li><a href="#">Cart(<%=cart.countItem()%>)</a></li>
                        </ul>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>

    </body>
</html>
