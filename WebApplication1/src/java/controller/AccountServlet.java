/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.AccountDAO;
import dao.HistoryConnectDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.converter.LocalDateTimeStringConverter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.HistoryConnect;
import tools.CheckPhoneNumberEmailError;
import tools.SendEmail;

/**
 *
 * @author unknown_HUST
 */
public class AccountServlet extends HttpServlet {
    
    //AccountDAO accDAO = new AccountDAO();
        
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
        String command = request.getParameter("command");
        System.out.println("Command la: "+command);
        String url = "";
        Account acc = new Account();
        AccountDAO accDAO = new AccountDAO();
        
        CheckPhoneNumberEmailError checkPhoneEmail = new CheckPhoneNumberEmailError();
        
        HttpSession session = request.getSession();
        switch (command) {
            case "register":
                if(checkPhoneEmail.checkNumberPhone(request.getParameter("phonenumber")) && checkPhoneEmail.checkEmail(request.getParameter("email"))){
                    try {
                        if (accDAO.checkAccount(request.getParameter("username"))) {
                            session.setAttribute("errorusername", "tk ton tai");
                            url = "/register.jsp";
                            RequestDispatcher rd1 = getServletContext().getRequestDispatcher(url);
                            rd1.forward(request, response);
                            System.out.println("Dang ky that bai");
                        } else {
                            url = "/index.jsp";
                            acc.setUserCode("");
                            acc.setUserName(request.getParameter("username"));
                            acc.setUserPass(request.getParameter("password"));
                            acc.setUserRole("KH");
                            acc.setFullName(request.getParameter("name"));
                            acc.setCityOrSchool(request.getParameter("ctyorschool"));
                            acc.setUserEmail(request.getParameter("email"));
                            acc.setPhoneNumber(request.getParameter("phonenumber"));
                            acc.setUserAddres(request.getParameter("address"));
                            acc.setUserCountry(request.getParameter("country"));
                            try {
                                accDAO.insertAccount(acc);
                            } catch (ClassNotFoundException ex) {
                                Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            String diaChi = acc.getFullName();

                            session.setAttribute("acc", acc);

                            RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
                            rd.forward(request, response);
                        }
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    
                }else{
                    if(!checkPhoneEmail.checkNumberPhone(request.getParameter("phonenumber"))){
                        session.setAttribute("errorphone", "incorrect");
                    }
                    if(!checkPhoneEmail.checkEmail(request.getParameter("email"))){
                        session.setAttribute("erroremail", "incorrect");
                    }
                    url = "/register.jsp";
                    RequestDispatcher rd1 = getServletContext().getRequestDispatcher(url);
                    rd1.forward(request, response);
                    System.out.println("Dang ky that bai");
                }
                
                break;
            case "login":
                String user = request.getParameter("username");
                String pass = request.getParameter("password");
                //Account accTemp = new Account();
        
                try {
                    acc = accDAO.login(user, pass, "KH");
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Neu tim thay tai khoan dang nhap
                if (acc != null){
                    try {
                        new HistoryConnectDAO().insertHistoryConnect(new HistoryConnect(Long.toString(new Date(System.currentTimeMillis()).getTime()), new Timestamp(new Date(System.currentTimeMillis()).getTime()), acc.getUserCode(), "KH"));
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    url = "/index.jsp";
                    session.setAttribute("acc", acc);
                    RequestDispatcher rd1 = getServletContext().getRequestDispatcher(url);
                    rd1.forward(request, response);
                    System.out.println("Dang nhap thanh cong");
                } else{
                    session.setAttribute("error", "Username or Password incorrect");
                    url = "/login.jsp";
                    RequestDispatcher rd1 = getServletContext().getRequestDispatcher(url);
                    rd1.forward(request, response);
                    System.out.println("Dang nhap that bai");
                }
                break;
            case "loginAdmin":
                String user1 = request.getParameter("username");
                String pass1 = request.getParameter("password");
        
                try {
                    acc = accDAO.loginAdmin(user1, pass1);
                } catch (ClassNotFoundException ex) {
                    Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
                // Nếu admin đăng nhập thành công
                if (acc != null){
                    try {
                        new HistoryConnectDAO().insertHistoryConnect(new HistoryConnect(Long.toString(new Date(System.currentTimeMillis()).getTime()), new Timestamp(new Date(System.currentTimeMillis()).getTime()), acc.getUserCode(), "admin"));
                    } catch (ClassNotFoundException ex) {
                        Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (SQLException ex) {
                        Logger.getLogger(AccountServlet.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    url = "/admin/index1.jsp";
                    session.setAttribute("accadmin", acc);
                    RequestDispatcher rd2 = getServletContext().getRequestDispatcher(url);
                    rd2.forward(request, response);
                    System.out.println("Dang nhap thanh cong");
                } else{// Đăng nhập thất bại
                    session.setAttribute("error", "Username or Password incorrect");
                    url = "/admin/login.jsp";
                    RequestDispatcher rd2 = getServletContext().getRequestDispatcher(url);
                    rd2.forward(request, response);
                    System.out.println("Dang nhap that bai");
                }
                break;
            case "changepassword":
                String currentpass = request.getParameter("currentpassword");
                String newpass = request.getParameter("newpassword");
                String confirmpass = request.getParameter("confirmnewpassword");
                
                Account currentacc = null;
                currentacc = (Account) session.getAttribute("acc");
                
                if(currentpass.equals(currentacc.getUserPass()) && newpass.equals(confirmpass)){
                    try {
                        accDAO.changePassword(currentacc.getUserCode(), newpass);
                        request.getSession().invalidate();
                        response.sendRedirect("/WebApplication1/login.jsp");
                    } catch (ClassNotFoundException e) {
                    }
                } else {
                    if(!currentpass.equals(currentacc.getUserPass())){
                        session.setAttribute("errcurrentpasswd", "err!");
                    }
                    if(!newpass.equals(confirmpass)){
                        session.setAttribute("errconfirmnewpass", "err!");
                    }
                    url = "/changepasswd.jsp";
                    RequestDispatcher rd1 = getServletContext().getRequestDispatcher(url);
                    rd1.forward(request, response);
                    System.out.println("Doi mat khau that bai");
                }
                break;
            case "forgetpass":
                String username = request.getParameter("usernameforget");
                
                try {
                    if(accDAO.checkAccount(username)){
                        Long currentdate = new Date(System.currentTimeMillis()).getTime();
                        String newpwd = Long.toString(currentdate);
                        String msg = "New your password: " + newpwd;
                        accDAO.forgetPassword(username, newpwd);
                        new SendEmail().sendMail(accDAO.getAccountForgetPass(username).getUserEmail(), "LEO SHOP - Project 2", msg);
                        
                        session.setAttribute("errorforgetpass", "correct");
                        url = "/forgetpassword.jsp";
                        RequestDispatcher rd1 = getServletContext().getRequestDispatcher(url);
                        rd1.forward(request, response);
                    } else{
                        session.setAttribute("errorforgetpass", "incorrect");
                        url = "/forgetpassword.jsp";
                        RequestDispatcher rd1 = getServletContext().getRequestDispatcher(url);
                        rd1.forward(request, response);
                    }
                } catch (IOException | ClassNotFoundException | SQLException | ServletException e) {
                }
                break;
            default:
                System.out.println("Thanh cong");
        }
        
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
