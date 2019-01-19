/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import connect.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    
    // Lay danh sach hoa don
    public ArrayList<BillDetail> getListBillDetailByBillID(String billID) throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "SELECT * FROM BILL_DETAIL WHERE BillCode='"+billID+"'";
        
        ArrayList<BillDetail> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BillDetail billdetail = new BillDetail();
                billdetail.setBillDetailID(rs.getString("BillDetailCode"));
                billdetail.setBookID(rs.getString("BookCode"));
                billdetail.setPrice(rs.getFloat("Price"));
                billdetail.setQuantity(rs.getInt("Quantity"));
                billdetail.setBillID(rs.getString("BillCode"));
                list.add(billdetail);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    // Lay danh sach hoa don theo the loai sach:
    public ArrayList<BillDetail> getListBillDetailByBookCategory(int categoryID) throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "SELECT BD.* FROM dbo.BILL_DETAIL AS BD, dbo.BOOK AS B WHERE BD.BookCode = B.BookCode AND B.BookCategoryID = '"+categoryID+"'";
        
        ArrayList<BillDetail> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                BillDetail billdetail = new BillDetail();
                billdetail.setBillDetailID(rs.getString("BillDetailCode"));
                billdetail.setBookID(rs.getString("BookCode"));
                billdetail.setPrice(rs.getFloat("Price"));
                billdetail.setQuantity(rs.getInt("Quantity"));
                billdetail.setBillID(rs.getString("BillCode"));
                list.add(billdetail);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

     
//    public static void main(String[] args) throws ClassNotFoundException {
//        for(BillDetail b : new BillDetailDAO().getListBillDetailByBookCategory(2)){
//            System.out.println(b.getBillDetailID());
//        }
//    }
}
