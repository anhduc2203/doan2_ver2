/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AuthorDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Author;

/**
 *
 * @author AnhDuc
 */
public class ManagerAuthorServlet extends HttpServlet {
    
    AuthorDAO authorDAO = new AuthorDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String cmd = request.getParameter("commandauthor");
        String authorID = request.getParameter("authorID");
        String url = "";
                
        try {
            switch(cmd){
                case "delete":
                    boolean flag = authorDAO.deleteAuthor(Integer.parseInt(authorID));
                    if(flag == false){
                        session.setAttribute("errdelauthor", "err");
                    }
                    url = "/admin/manage_author.jsp";
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
        HttpSession session = request.getSession();
        
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String cmd = request.getParameter("commandauthor");
        String tenTacGia = request.getParameter("tenTacGia");
        String url = "";
        String error = "";
        
        if (tenTacGia.equals("")) {
            error = "Vui lòng nhập vào tên tác giả!";
            session.setAttribute("errorauthor", error);
        }
        
        try {
            if (error.length()==0) {
                switch(cmd){
                    case "insert":
                        authorDAO.insertAuthor(new Author(2, tenTacGia));
                        url = "/admin/manage_author.jsp";
                        break;
                    case "update":
                        authorDAO.updateAuthor(new Author((int) Long.parseLong(request.getParameter("authorID")), 
                                tenTacGia));
                        url = "/admin/manage_author.jsp";
                        break;
                }
            }
            else{
                url = "/admin/insert_author.jsp";
            }
        } catch (ClassNotFoundException | NumberFormatException e) {
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
        
    
    }

}
