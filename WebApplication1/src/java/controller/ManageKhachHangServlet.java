/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Account;

/**
 *
 * @author unknown_HUST
 */
public class ManageKhachHangServlet extends HttpServlet {

    AccountDAO accDAO = new AccountDAO();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");
        response.setCharacterEncoding("utf-8");
        String cmd = request.getParameter("command");
        String accountID = request.getParameter("accountID");
        String url = "";
                
        try {
            switch(cmd){
                case "delete":
                    accDAO.deleteAccount(accountID);
                    url = "/admin/manage_khachhang.jsp";
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
        String accountID = request.getParameter("accountID");
        
        String fullname = request.getParameter("tenKH");
        String school = request.getParameter("ctyorSchool");
        String email = request.getParameter("email");
        String phonenumber = request.getParameter("phone");
        String addr = request.getParameter("addr");
        String country = request.getParameter("country");
        
        String url = "";
        String error = "";
        
        try {
            if (error.length()==0) {
                switch(cmd){
                    case "insert":
                        accDAO.insertAccount(new Account());
                        url = "/admin/manage_khachhang.jsp";
                        break;
                    case "update":
                        accDAO.updateAccount(new Account(accountID, "abc", "123456", "KH", fullname, school, email, phonenumber, addr, country));
                        url = "/admin/manage_khachhang.jsp";
                        break;
                }
            }
            else{
                url = "/admin/manage_khachhang.jsp";
            }
        } catch (ClassNotFoundException | NumberFormatException e) {
        }
        
        RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
        rd.forward(request, response);
    }

   

}
