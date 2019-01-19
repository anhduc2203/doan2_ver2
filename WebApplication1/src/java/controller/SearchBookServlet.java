/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BookDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Book;

/**
 *
 * @author unknown_HUST
 */
public class SearchBookServlet extends HttpServlet {

    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        BookDAO bookDAO = new BookDAO();
        
        HttpSession session = request.getSession();
        String keyword = request.getParameter("search");
        session.setAttribute("keyword", keyword);
        
        
        if(request.getParameter("searchbook").equals("searchbook")){
            try {
                ArrayList<Book> listbooksearch = new ArrayList<>();
                listbooksearch = bookDAO.searchBooks(keyword);
                
                session.setAttribute("listbooksearch", listbooksearch);

                RequestDispatcher rd = getServletContext().getRequestDispatcher("/searchbook.jsp");
                rd.forward(request, response);
            } catch (IOException | ClassNotFoundException | ServletException e) {
            }
        }
        
        
    }

    
}
