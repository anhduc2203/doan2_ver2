<%-- 
    Document   : searchbook
    Created on : Jan 14, 2019, 11:29:15 PM
    Author     : unknown_HUST
--%>

<%@page import="model.Author"%>
<%@page import="model.Category"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Book"%>
<%@page import="dao.AuthorDAO"%>
<%@page import="dao.BookDAO"%>
<%@page import="dao.CategoryDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>English Book</title>
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <link href="css/style.css" rel="stylesheet" type="text/css" media="all" />
        <link href="css/form.css" rel="stylesheet" type="text/css" media="all" />
        <link href='http://fonts.googleapis.com/css?family=Exo+2' rel='stylesheet' type='text/css'>
        <script src="js/jquery1.min.js"></script>
        <!-- start menu -->
        <link href="css/megamenu.css" rel="stylesheet" type="text/css" media="all" />
        <script type="text/javascript" src="js/megamenu.js"></script>
        <script>$(document).ready(function () {
                $(".megamenu").megamenu();
            });</script>
        <script type="text/javascript" src="js/jquery.jscrollpane.min.js"></script>
        <script type="text/javascript" id="sourcecode">
            $(function () {
                $('.scroll-pane').jScrollPane();
            });
        </script>
    </head>
    <body>
        <%
            CategoryDAO categoryDAO = new CategoryDAO();
            BookDAO bookDAO = new BookDAO();
            AuthorDAO authorDAO = new AuthorDAO();
            String categoryID = "";
            ArrayList<Book> listbooksearch = (ArrayList<Book>) session.getAttribute("listbooksearch");
            int size = listbooksearch.size();
            
        %>
        
        <jsp:include page="header.jsp"></jsp:include>

        <div class="mens">
            <div class="main">
                <div class="wrap">
                <%
                    if(size == 0){
                %>
                    <div class="cont span_2_of_3">
                        
                        <h2 class="head">Kết quả tìm kiếm: <%=session.getAttribute("keyword")%></h2>
                        <div class="mens-toolbar">
                    
                            <div class="clear"></div>
                        </div>
                        <div class="register_account">
                            <div class="wrap">
                                <p class="cart">Không tìm thấy kết quả thích hợp!</p>
                            </div>
                        </div>
                    </div>
                <% } else { %>
                    <div class="cont span_2_of_3">
                        
                        <h2 class="head">Kết quả tìm kiếm: <%=session.getAttribute("keyword")%></h2>
                        <div class="mens-toolbar">
                    
                            <div class="clear"></div>
                        </div>
                        
                        <%
                            
                            int sohang, sodu;
                            
                            if((size%3) == 0){
                                sohang = size/3;
                            
                            System.out.println("so hang la   "+ sohang);
                            for(int i = 0; i < sohang; i++){
                        %>
                        
                        <div class="top-box">
                            <%
                                //bookDAO.getListBookByCategory(Integer.parseInt(categoryID)).get(index);
                                for(int j = i*3; j < (i*3+3); j++){
                                    Book b = listbooksearch.get(j);
                                    if(b == null) break;
                            %>
                            
                            <div class="col_1_of_3 span_1_of_3">
                                
                                    <div class="inner_content clearfix">
                                        <a href="single.jsp?bookID=<%=b.getBookCode() %>">
                                        <div class="product_image">
                                            <img src="<%=b.getBookImage() %>" alt="<%=b.getBookName() %>" />
                                        </div>
                                        </a>
                                        <div class="price">
                                            <a href="single.jsp?bookID=<%=b.getBookCode() %>">
                                            <div class="cart-left">
                                                <p class="title"><%=b.getBookName() %></p>
                                                <div class="price1">
                                                    <span class="actual">$<%=b.getBookPrice() %></span>
                                                </div>
                                            </div>
                                            </a>
                                            <a href="CartServlet?command=plus&bookID=<%=b.getBookCode()%>">
                                            <div class="cart-right"> </div>
                                            </a>
                                            <div class="clear"></div>
                                        </div>
                                    </div>
                                
                            </div>
                            <%
                                }
                            %>                           
                            <div class="clear"></div>
                        </div>
                            
                        <%
                            }
                            }else{
                                sohang = size/3 + 1;
                                sodu = size%3;
                                    
                                int k;
                                for(k = 0; k < (sohang - 1); k++){
                        %>
                        
                        <div class="top-box">
                            <%
                                //bookDAO.getListBookByCategory(Integer.parseInt(categoryID)).get(index);
                                for(int j = k*3; j < (k*3+3); j++){
                                    Book b = listbooksearch.get(j);
                                    if(b == null) break;
                            %>
                            
                            <div class="col_1_of_3 span_1_of_3">
                                
                                    <div class="inner_content clearfix">
                                        <a href="single.jsp?bookID=<%=b.getBookCode() %>">
                                        <div class="product_image">
                                            <img src="<%=b.getBookImage() %>" alt="<%=b.getBookName() %>" />
                                        </div>
                                        </a>
                                        <div class="price">
                                            <a href="single.jsp?bookID=<%=b.getBookCode() %>">
                                            <div class="cart-left">
                                                <p class="title"><%=b.getBookName() %></p>
                                                <div class="price1">
                                                    <span class="actual">$<%=b.getBookPrice() %></span>
                                                </div>
                                            </div>
                                            </a>
                                            <a href="CartServlet?command=plus&bookID=<%=b.getBookCode()%>">
                                            <div class="cart-right"> </div>
                                            </a>
                                            <div class="clear"></div>
                                        </div>
                                    </div>
                                
                            </div>
                            <%
                                }
                            %>                           
                            <div class="clear"></div>
                        </div>
                            
                        
                        <%
                                }
                        %>
                        <div class="top-box">
                            <%
                                //bookDAO.getListBookByCategory(Integer.parseInt(categoryID)).get(index);
                                for(int j = k*3; j < (k*3+sodu); j++){
                                    Book b = listbooksearch.get(j);
                                    if(b == null) break;
                            %>
                            
                            <div class="col_1_of_3 span_1_of_3">
                                
                                    <div class="inner_content clearfix">
                                        <a href="single.jsp?bookID=<%=b.getBookCode() %>">
                                        <div class="product_image">
                                            <img src="<%=b.getBookImage() %>" alt="<%=b.getBookName() %>" />
                                        </div>
                                        </a>
                                        <div class="price">
                                            <a href="single.jsp?bookID=<%=b.getBookCode() %>">
                                            <div class="cart-left">
                                                <p class="title"><%=b.getBookName() %></p>
                                                <div class="price1">
                                                    <span class="actual">$<%=b.getBookPrice() %></span>
                                                </div>
                                            </div>
                                            </a>
                                            <a href="CartServlet?command=plus&bookID=<%=b.getBookCode()%>">
                                            <div class="cart-right"> </div>
                                            </a>
                                            <div class="clear"></div>
                                        </div>
                                    </div>
                                
                            </div>
                            <%
                                }
                            %>                           
                            <div class="clear"></div>
                        </div>
                        <% } %>
                        
                        
                        
                    </div>
                <% } %>        
                        
                    <div class="rsidebar span_1_of_left">
                        <h5 class="m_1">Categories</h5>
                        <ul class="kids">
                            <%
                                for(Category c : categoryDAO.getCategory()){
                            %>
                            <li><a href="engbook.jsp?category=<%=c.getCategoryID() %>"><%=c.getCategoryName() %></a></li>
                            <%
                                }
                            %>
                        </ul>
<!--             //            <section class="sky-form">
                            <h4>Price</h4>
                            <div class="row row1 scroll-pane">
                                <div class="col col-4">
                                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>... - $50</label>
                                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>$50 - $100</label>
                                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>$100 - ...</label>
                                </div>
                                <input type="button" >
                            </div>
                        </section>-->
                        <section class="sky-form">
                            <h4>Author Name</h4>
                            <div class="row row1 scroll-pane">
                                <div class="col col-4">
                                    <ul class="kids">
                                        <%
                                            for(Author author : authorDAO.getAuthor()){
                                        %>
                                        <li><a href="engbookbyauthor.jsp?author=<%=author.getAuthorID() %>"><%=author.getAuthorName() %></a></li>
                                        <%
                                            }
                                        %>
                                    </ul>
                                </div>
                            </div>
                        </section>
                        
<!--                        <section class="sky-form">
                            <h4>Frame Size</h4>
                            <div class="row row1 scroll-pane">
                                <div class="col col-4">
                                    <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Small</label>
                                </div>
                                <div class="col col-4">
                                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Medium</label>
                                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Large</label>
                                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Medium</label>
                                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Large</label>
                                </div>
                            </div>
                        </section>-->
<!--                        <section class="sky-form">
                            <h4>Frame Type</h4>
                            <div class="row row1 scroll-pane">
                                <div class="col col-4">
                                    <label class="checkbox"><input type="checkbox" name="checkbox" checked=""><i></i>Full Rim</label>
                                </div>
                                <div class="col col-4">
                                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Rim Less</label>
                                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Half Rim</label>
                                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Rim Less</label>
                                    <label class="checkbox"><input type="checkbox" name="checkbox"><i></i>Half Rim</label>
                                </div>
                            </div>
                        </section>-->
<!--                        <section class="sky-form">
                            <h4>Colors</h4>
                            <ul class="color-list">
                                <li><a href="#"> <span class="color1"> </span>
                                        <p class="red">Red</p>
                                    </a></li>
                                <li><a href="#"> <span class="color2"> </span>
                                        <p class="red">Green</p>
                                    </a></li>
                                <li><a href="#"> <span class="color3"> </span>
                                        <p class="red">Blue</p>
                                    </a></li>
                                <li><a href="#"> <span class="color4"> </span>
                                        <p class="red">Yellow</p>
                                    </a></li>
                                <li><a href="#"> <span class="color5"> </span>
                                        <p class="red">Violet</p>
                                    </a></li>
                                <li><a href="#"> <span class="color6"> </span>
                                        <p class="red">Orange</p>
                                    </a></li>
                                <li><a href="#"> <span class="color7"> </span>
                                        <p class="red">Gray</p>
                                    </a></li>
                            </ul>
                        </section>-->
                    </div>
                    <div class="clear"></div>
                </div>
            </div>
        </div>
        <script src="js/jquery.easydropdown.js"></script>

        <jsp:include page="footer.jsp"></jsp:include>
    </body>
</html>
