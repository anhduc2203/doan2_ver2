<%-- 
    Document   : changepasswd
    Created on : Jan 13, 2019, 9:01:18 PM
    Author     : unknown_HUST
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Change Password ::</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <link href='http://fonts.googleapis.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery1.min.js"></script>
        <!-- start menu -->
        <link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="js/megamenu.js"></script>
        <script>$(document).ready(function () { $(".megamenu").megamenu(); });</script>
        <script src="js/jquery.easydropdown.js"></script>
        
        
    </head>
    <body>
        <jsp:include page="header.jsp"></jsp:include>
        
        <div class="register_account">
            <div class="wrap">
                <h4 class="title">Change password</h4>
                <form action="AccountServlet" method="POST">
                    <div class="col_1_of_2 span_1_of_2">
                        <div><p>Current Password:</p></div>
                        <%if (session.getAttribute("errcurrentpasswd") != null) {%>
                        <div>
                            <p style="color: red">*Mật khẩu hiện tại không đúng!</p>
                        </div>
                        <%}%>
                        <div><input type="password" name="currentpassword" id="currentpassword" value="Current Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Current Password';}"></div>
                        <div><p>New Password:</p></div>
                        <div><input type="password" name="newpassword" id="newpassword" value="New Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'New Password';}"></div>
                        <div><p>Confirm New Password:</p></div>
                        <%if (session.getAttribute("errconfirmnewpass") != null) {%>
                        <div>
                            <p style="color: red">*Xác nhận không đúng!</p>
                        </div>
                        <%}%>
                        <div><input type="password" name="confirmnewpassword" id="confirmnewpassword" value="Confirm New Password" onfocus="this.value = '';" onblur="if (this.value == '') {this.value = 'Confirm New Password';}"></div>

                        
                        <br>
                        <button class="grey" type="hidden" value="changepassword" name="command">Save</button>
                        <!--button class="grey">Create Account</button-->
                    </div>

                    <div class="clear"></div>
                </form> 
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>

