/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.CategoryDAO;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Category;

/**
 *
 * @author AnhDuc
 */
public class ManagerCategoryServlet extends HttpServlet {

    CategoryDAO categoryDAO = new CategoryDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String cmd = request.getParameter("command");
        String categoryID = request.getParameter("categoryID");
        String url = "";
                
        try {
            switch(cmd){
                case "delete":
                    boolean flag = categoryDAO.deleteCategory(Integer.parseInt(categoryID));
                    if(flag == false){
                        session.setAttribute("errdelcategory", "errr");
                    }
                    url = "/admin/manage_category.jsp";
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
        String cmd = request.getParameter("command");
        String tenDanhMuc = request.getParameter("tenDanhMuc");
        String url = "";
        String error = "";
        
        if (tenDanhMuc.equals("")) {
            error = "Vui lòng nhập vào tên danh mục!";
            session.setAttribute("error", error);
        }
        
        try {
            if (error.length()==0) {
                switch(cmd){
                    case "insert":
                        categoryDAO.insertCategory(new Category(2, tenDanhMuc));
                        url = "/admin/manage_category.jsp";
                        break;
                    case "update":
                        categoryDAO.updateCategory(new Category((int) Long.parseLong(request.getParameter("categoryID")), 
                                tenDanhMuc));
                        url = "/admin/manage_category.jsp";
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
