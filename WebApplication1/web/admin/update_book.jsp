<%-- 
    Document   : insert_category
    Created on : Dec 20, 2018, 5:12:11 PM
    Author     : AnhDuc
--%>

<%@page import="model.Category"%>
<%@page import="dao.CategoryDAO"%>
<%@page import="model.Author"%>
<%@page import="dao.AuthorDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Book"%>
<%@page import="dao.BookDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cập nhật sách</title>
        
        <c:set var="root" value="${pageContext.request.contextPath}"/>
        <link href="${root}/css/mos-style.css" rel='stylesheet' type='text/css' />
        
        <script src="<c:url value="/ckeditor/ckeditor.js" />"></script>
    </head>
    <body>
        
        <%
 
            AuthorDAO authorDAO = new AuthorDAO();
            ArrayList<Author> listAuthor = authorDAO.getAuthor();
            CategoryDAO categoryDAO = new CategoryDAO();
            ArrayList<Category> listCategory = categoryDAO.getCategory();
            String error = "";
            if (request.getParameter("error") != null){
                error = (String) request.getParameter("error");
            }
            BookDAO bookDAO = new BookDAO();
            Book book = bookDAO.getBookByBookID(request.getParameter("bookID"));
        %>
        
        <jsp:include page="header.jsp"></jsp:include>
        
        <div id="wrapper">

            <jsp:include page="menu1.jsp"></jsp:include>
            
            <div id="rightContent">
                <h3>Cập nhật sách</h3>
                <form action="/WebApplication1/ManagerBookServlet" method="post">
                    <table width="95%">
                        <tr>
                            <td><b>Tên sách</b></td>
                            <td><input type="text" class="sedang" name="tenSach" value="<%=book.getBookName()%>"><%=error%></td>
                        </tr>
                        <tr>
                            <td><b>Giá bán</b></td>
                            <td><input type="text" class="sedang" name="giaBan" value="<%=book.getBookPrice()%>"><%=error%></td>
                        </tr>
                        <tr>
                            <td><b>Nhà xuất bản</b></td>
                            <td><input type="text" class="sedang" name="nxb" value="<%=book.getNxb()%>"><%=error%></td>
                        </tr>
                        <tr>
                            <td><b>Tác giả</b></td>
                            <td>
                                <select name="authorID"><%=error%>
                                    <option selected>-- Chọn tác giả --</option>    
                                    <%
                                        for (Author author: listAuthor){
                                    %>
                                            <option value="<%=author.getAuthorID()%>"><%=author.getAuthorName()%></option>
                                    <%}%>
                                </select>
                            </td>                        
                        </tr>
                        <tr>
                            <td><b>Thể loại</b></td>

                            <td>
                                <select name="categoryID"><%=error%>
                                    <option selected>-- Chọn thể loại --</option>
                                    <%
                                        for (Category category: listCategory){
                                    %>
                                            <option value="<%=category.getCategoryID()%>"><%=category.getCategoryName()%></option>
                                    <%}%>
                                </select>
                            </td>

                        </tr>
                        <tr>
                            <td>
                                <b>Mô tả sách</b>
                            </td>
                            <td>
                                <textarea class="form-textarea" id="noiDung" name="moTa"><%=error%></textarea>
                                <script type="text/javascript" language="javascript">
                                   CKEDITOR.replace('noiDung', {width: '500px',height: '200px'});
                                </script>
                            </td>
                        </tr>
                        <tr><td><b>Hình ảnh</b></td><td><input type="file" class="sedang" name="linkAnh"><%=error%></td></tr>

                        <tr>
                            <td></td>
                            <td>
                                <input type="hidden" name="command" value="update">
                                <input type="hidden" name="bookID" value="<%=request.getParameter("bookID")%>">
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
