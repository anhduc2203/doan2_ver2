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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
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
        ps.setNString(5, bill.getAddress());
        ps.setTimestamp(6, bill.getDate());
        ps.executeUpdate();
    }
    
    
    // Admin huy don hang
    public void cancelBill(Bill bill) throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        
        
    }
    
    // Lay danh sach hoa don
    public ArrayList<Bill> getListBill() throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "SELECT * FROM BILL";
        
        ArrayList<Bill> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Bill bill = new Bill();
                bill.setBillID(rs.getString("BillCode"));
                bill.setAccountID(rs.getString("UserCode"));
                bill.setTotal(rs.getFloat("Total"));
                bill.setPayment(rs.getString("Payment"));
                bill.setAddress(rs.getString("Address"));
                bill.setDate(rs.getTimestamp("Date"));
                list.add(bill);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
}
