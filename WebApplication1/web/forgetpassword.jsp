<%-- 
    Document   : forgetpassword
    Created on : Jan 15, 2019, 9:14:21 PM
    Author     : unknown_HUST
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!DOCTYPE html>
<html>
    <head>
        <title>Forget Your Password ::</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <link href='http://fonts.googleapis.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
        <script type="text/javascript" src="js/jquery1.min.js"></script>
        <!-- start menu -->
        <link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="js/megamenu.js"></script>
        <script>$(document).ready(function () { $(".megamenu").megamenu(); });</script>
        <!-- dropdown -->
        <script src="js/jquery.easydropdown.js"></script>
    </head>
    <body>
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <div class="login">
            <div class="wrap">
                <div class="col_1_of_login span_1_of_login">
                    <h4 class="title">New Account</h4>
                    <p>Nếu bạn chưa có tài khoản hãy tạo một tài khoản mới để sử dụng dịch vụ của chúng tôi!</p>
                    <br>
                    <div class="button1">
                        <a href="register.jsp"><input type="submit" name="Submit" value="Create an Account"></a>
                    </div>
                    <div class="clear"></div>
                </div>
                <div class="col_1_of_login span_1_of_login">
                    <div class="login-title">
                        <h4 class="title">Quên mật khẩu</h4>
                        <div id="loginbox" class="loginbox">
                            <form action="AccountServlet" method="post" name="forgetpass" id="login-form">
                                
                                <fieldset class="input">
                                    <%if (session.getAttribute("errorforgetpass") != null) { %>
                                    <%if (session.getAttribute("errorforgetpass").equals("correct")) { %>
                                    <div>
                                        <p style="color: red">*Một mật khẩu mới đã được gửi vào email của bạn! Kiểm tra email để tiếp tục sử dụng</p>
                                    </div>
                                    <% } %>
                                    <%if (session.getAttribute("errorforgetpass").equals("incorrect")) { %>
                                    <div>
                                        <p style="color: red">*Tài khoản này không tồn tại!</p>
                                    </div>
                                    <% } %>
                                    <% } %>
                                    <p id="login-form-username">
                                        <label for="modlgn_username">Tên đăng nhập</label>
                                        <input id="modlgn_username" type="text" name="usernameforget" class="inputbox" size="18" autocomplete="off">
                                    </p>
                                    
                                    <div class="remember">
                                        <button class="grey" type="hidden" value="forgetpass" name="command">Lấy lại mật khẩu</button>
                                        
                                        <div class="clear"></div>
                                    </div>
                                </fieldset>
                            </form>
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
        </div>
        
        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
