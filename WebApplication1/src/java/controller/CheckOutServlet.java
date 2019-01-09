/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import dao.BillDAO;
import dao.BillDetailDAO;
import dao.BookDAO;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import model.Account;
import model.Bill;
import model.BillDetail;
import model.Cart;
import model.Item;
import tools.SendEmail;

/**
 *
 * @author AnhDuc
 */
public class CheckOutServlet extends HttpServlet {

    private BillDAO billDAO = new BillDAO();
    private BillDetailDAO billDetailDAO = new BillDetailDAO();
    
   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String payment = request.getParameter("payment");
        String address = request.getParameter("address");
        HttpSession session = request.getSession(); 
        Cart cart = (Cart) session.getAttribute("cart");
        Account account = (Account) session.getAttribute("acc");
        
        try {
            Long ID = new Date(System.currentTimeMillis()).getTime();
            String s = Long.toString(ID);
            String billCode = s.trim();
            
            Bill bill = new Bill();
            bill.setBillID(billCode);
            bill.setAddress(address);
            bill.setPayment(payment);
            bill.setTotal(cart.totalCart());
            bill.setAccountID(account.getUserCode());
            bill.setDate(new Timestamp(new Date(System.currentTimeMillis()).getTime()));
            billDAO.insertBill(bill);
            
            String msg = "Order Code: " + billCode + "\nPayment: " + payment + "\nTotal: " + cart.totalCart() + "\nOrder:";
            BookDAO bookDAO = new BookDAO();
            for (Map.Entry<String, Item> list: cart.getCartItem().entrySet()){
                msg = msg + "\n\t+ " + bookDAO.getBookByBookID(list.getValue().getBook().getBookCode()).getBookName() + " x " + list.getValue().getQuantity();
                billDetailDAO.insertBillDetail(new BillDetail("", billCode,
                        list.getValue().getBook().getBookCode(),
                        list.getValue().getBook().getBookPrice(),
                        list.getValue().getQuantity()));
            }  
            
            new SendEmail().sendMail(account.getUserEmail(), "LEO SHOP - Project 2", msg);
            
            cart = new Cart();
            session.setAttribute("cart", cart);
                    
        } catch (ClassNotFoundException | SQLException e) {
        }
        response.sendRedirect("/WebApplication1/thongbao.jsp");
    }

    

}
