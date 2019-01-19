/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import tools.CheckPhoneNumberEmailError;
import tools.SendEmail;

/**
 *
 * @author unknown_HUST
 */
public class NewsLettersServlet extends HttpServlet {

    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        
        String email = request.getParameter("emailsignup");
        
        CheckPhoneNumberEmailError check = new CheckPhoneNumberEmailError();
        
        HttpSession secsion = request.getSession();
        
        if (check.checkEmail(email)) {
            new SendEmail().sendMail(email, "Book Store - Project 2", "Bạn đã đăng ký email thành công.Kể từ bây giờ chúng tôi sẽ gửi tin tức cho bạn thông qua email này!");
            secsion.setAttribute("nofication", "Email Subscribed!");
            
        } else {
            secsion.setAttribute("nofication", "Email không hợp lệ!");
        }
        RequestDispatcher rd1 = getServletContext().getRequestDispatcher("/index.jsp");
        rd1.forward(request, response);
    }

    
}
