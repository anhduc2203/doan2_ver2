/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.ConnectDB;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import model.Bill;

/**
 *
 * @author AnhDuc
 */
public class BillDAO {
    
    public void insertBill(Bill bill) throws ClassNotFoundException, SQLException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "insert into BILL values(?,?,?,?,?,?)";
        PreparedStatement ps = conn.prepareCall(sql);
        
        ps.setString(1, bill.getBillID());
        ps.setString(2, bill.getAccountID());
        ps.setDouble(3, bill.getTotal());
        ps.setString(4, bill.getPayment());
        ps.setString(5, bill.getAddress());
        ps.setTimestamp(6, bill.getDate());
        ps.executeUpdate();
    }
    
//    public static void main(String[] args) throws ClassNotFoundException, SQLException {
//
//        Long ID = new Date(System.currentTimeMillis()).getTime();
//        String s = Long.toString(ID);
//        String trim = s.trim();
//        new BillDAO().insertBill(new Bill(trim, "USER000001", 10, "live", "Ha Noi", new Timestamp(new Date(System.currentTimeMillis()).getTime())));
//    }
}
