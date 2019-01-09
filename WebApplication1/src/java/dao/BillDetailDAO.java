/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import model.BillDetail;

/**
 *
 * @author AnhDuc
 */
public class BillDetailDAO {
    
    public void insertBillDetail(BillDetail bd) throws SQLException, ClassNotFoundException{
        
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "insert into BILL_DETAIL values(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareCall(sql);
        
        ps.setString(1, bd.getBillDetailID());
        ps.setString(2, bd.getBillID());
        ps.setString(3, bd.getBookID());
        ps.setDouble(4, bd.getPrice());
        ps.setInt(5, bd.getQuantity());
        
        ps.executeUpdate();
    }
    
    
//    public static void main(String[] args) throws SQLException, ClassNotFoundException {
//        new BillDetailDAO().insertBillDetail(new BillDetail("", "BILL000001", "BOOK000001", 10, 1));
//    }
}
