/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDAO;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Book;

/**
 *
 * @author AnhDuc
 */
public class ManagerBookServlet extends HttpServlet {
    
    BookDAO bookDAO = new BookDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String cmd = request.getParameter("command");
        String bookID = request.getParameter("bookID");
        String url = "";
                
        try {
            switch(cmd){
                case "delete":
                    boolean flag = bookDAO.deleteBook(bookID);
                    if(flag == false){
                        session.setAttribute("errdelbook", "err");
                    }
                    url = "/admin/manage_book.jsp";
                    break;
            }
        } catch (ClassNotFoundException | NumberFormatException e) {
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String cmd = request.getParameter("command");
        String tenSach = request.getParameter("tenSach");
        String giaBan = request.getParameter("giaBan");
        String nxb = request.getParameter("nxb");
        String authorID = request.getParameter("authorID");
        String categoryID = request.getParameter("categoryID");
        String moTa = request.getParameter("moTa");
        String linkAnh = request.getParameter("linkAnh");
        String linkAnh1 = "images/anhbia/" + linkAnh;
        String url = "";
        String error = "";
        
        if (tenSach.equals("")) {
            error = "Thông tin không hợp lệ!";
            request.setAttribute("error", error);
        }
        
        try {
            if (error.length()==0) {
                switch(cmd){
                    case "insert":
                        bookDAO.insertBook(new Book("", tenSach, Float.parseFloat(giaBan), 
                                Integer.parseInt(authorID), nxb, 
                                Integer.parseInt(categoryID), moTa, linkAnh));
                        url = "/admin/manage_book.jsp";
                        break;
                    case "update":
                        bookDAO.updateBook(new Book(request.getParameter("bookID"),
                                tenSach, Float.parseFloat(giaBan), Integer.parseInt(authorID), nxb, 
                                Integer.parseInt(categoryID), moTa, linkAnh1));
                        url = "/admin/manage_book.jsp";
                        break;
                }
            }
            else{
                url = "/admin/insert_category.jsp";
            }
        } catch (ClassNotFoundException | NumberFormatException e) {
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

}
