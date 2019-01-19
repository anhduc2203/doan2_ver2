/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;
// Kiểm tra tài khoản đã tồn tại chưa

import connect.ConnectDB;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Account;


/**
 *
 * @author unknown_HUST
 */
public class AccountDAO {
    
    // Kiểm tra tài khoản đã tồn tại chưa?
    public boolean checkAccount(String UserName) throws ClassNotFoundException{
        Connection connection = ConnectDB.getConnectionDB();
        String sql = "SELECT * FROM USERS WHERE UserName = '" + UserName + "'";
        
        Statement stmt = null;
        ResultSet result = null;
        
        try {
            stmt = connection.createStatement();
            result = null;
            result = stmt.executeQuery(sql);
            while (result.next()) {
                connection.close();
                return true;
                
            }
        } catch (SQLException ex) {
            System.out.println("\nKHONG TIM THAY HOAC CO LOI XAY RA");
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return false;
    }
    
    //Them tai khoan dang ki
    public void insertAccount(Account acc) throws ClassNotFoundException {
        
        Connection connection = ConnectDB.getConnectionDB();
        String sql = "INSERT INTO USERS VALUES(?,?,?,?,?,?,?,?,?,?)";
        ResultSet rs = null;
        try {
            PreparedStatement ps;
            ps = connection.prepareCall(sql);
            ps.setString(1, acc.getUserCode());
            ps.setString(2, acc.getUserName());
            ps.setString(3, acc.getUserPass());
            ps.setString(4, acc.getUserRole());
            ps.setString(5, acc.getFullName());
            ps.setString(6, acc.getCityOrSchool());
            ps.setString(7, acc.getUserEmail());
            ps.setString(8, acc.getPhoneNumber());
            ps.setString(9, acc.getUserAddres());
            ps.setString(10, acc.getUserCountry());
            
            rs = ps.executeQuery();
            
            System.out.println("Insert succesful!");
            ps.close();
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    // Xử lí đăng nhập
    public Account login(String username, String pass, String role) throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "select * from USERS where UserName=? and Pass=? and UserRole=?";
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            ps.setString(3, role);
            rs = ps.executeQuery();
            while (rs.next()){
                Account acc = new Account();
                acc.setUserCode(rs.getString("UserCode"));
                acc.setUserName(rs.getString("UserName"));
                acc.setUserPass(rs.getString("Pass"));
                acc.setFullName(rs.getString("Name"));
                acc.setCityOrSchool(rs.getString("ctyOrSchool"));
                acc.setUserEmail(rs.getString("Email"));
                acc.setPhoneNumber("PhoneNumber");
                acc.setUserAddres(rs.getString("Address"));
                acc.setUserCountry(rs.getString("Country"));
                String un = acc.getUserName();
                String pa = acc.getUserPass();
                System.out.println("UN: "+un+"PS: "+pa);
                conn.close();
                ps.close();
                return acc;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    // Xử lí đăng nhập cho admin
    public Account loginAdmin(String username, String pass) throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "select * from USERS where UserName=? and Pass=? and UserRole='admin'";
        ResultSet rs = null;
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, username);
            ps.setString(2, pass);
            rs = ps.executeQuery();
            while (rs.next()){
                Account acc = new Account();
                acc.setUserCode(rs.getString("UserCode"));
                acc.setUserName(rs.getString("UserName"));
                acc.setUserPass(rs.getString("Pass"));
                acc.setFullName(rs.getString("Name"));
                acc.setCityOrSchool(rs.getString("ctyOrSchool"));
                acc.setUserEmail(rs.getString("Email"));
                acc.setPhoneNumber("PhoneNumber");
                acc.setUserAddres(rs.getString("Address"));
                acc.setUserCountry(rs.getString("Country"));
                String un = acc.getUserName();
                String pa = acc.getUserPass();
                System.out.println("UN: "+un+"PS: "+pa);
                conn.close();
                ps.close();
                return acc;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public Account getAccount(String accountID) throws SQLException{
        try {
            Connection conn = ConnectDB.getConnectionDB();
            String sql = "select * from USERS where UserCode = ?";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, accountID);
            ResultSet rs = ps.executeQuery();
            Account acc = new Account();
            while (rs.next()) {
                acc.setUserEmail(rs.getString("Email"));
            }
            return acc;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Account getAccountForgetPass(String username) throws SQLException{
        try {
            Connection conn = ConnectDB.getConnectionDB();
            String sql = "SELECT * FROM dbo.USERS WHERE UserName = ?";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            Account acc = new Account();
            while (rs.next()) {
                acc.setUserCode(rs.getString("UserCode"));
                acc.setUserName(rs.getString("UserName"));
                acc.setUserPass(rs.getString("Pass"));
                acc.setUserRole(rs.getString("UserRole"));
                acc.setFullName(rs.getNString("Name"));
                acc.setCityOrSchool(rs.getNString("CtyOrSchool"));
                acc.setUserEmail(rs.getString("Email"));
                acc.setPhoneNumber(rs.getString("PhoneNumber"));
                acc.setUserAddres(rs.getNString("Address"));
                acc.setUserCountry(rs.getNString("Country"));
            }
            return acc;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public Account getAccountUpdate(String accountID) throws SQLException{
        try {
            Connection conn = ConnectDB.getConnectionDB();
            String sql = "SELECT * FROM dbo.USERS WHERE UserCode = ?";
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, accountID);
            ResultSet rs = ps.executeQuery();
            Account acc = new Account();
            while (rs.next()) {
                acc.setUserCode(rs.getString("UserCode"));
                acc.setUserName(rs.getString("UserName"));
                acc.setUserPass(rs.getString("Pass"));
                acc.setUserRole(rs.getString("UserRole"));
                acc.setFullName(rs.getNString("Name"));
                acc.setCityOrSchool(rs.getNString("CtyOrSchool"));
                acc.setUserEmail(rs.getString("Email"));
                acc.setPhoneNumber(rs.getString("PhoneNumber"));
                acc.setUserAddres(rs.getNString("Address"));
                acc.setUserCountry(rs.getNString("Country"));       
            }
            return acc;
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(AccountDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    // Doi mat khau tai khoan:
    public boolean changePassword(String userID, String newpass) throws ClassNotFoundException{
        
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "UPDATE dbo.USERS SET Pass= ? WHERE UserCode= ?";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, newpass);
            ps.setString(2, userID);
            
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {

            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);

        }
        return false;
        
    }
    
    // Doi mat khau tai khoan:
    public boolean forgetPassword(String username, String newpass) throws ClassNotFoundException{
        
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "UPDATE dbo.USERS SET Pass= ? WHERE UserName= ?";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, newpass);
            ps.setString(2, username);
            
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {

            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);

        }
        return false;
        
    }
    
    // Lấy danh sách sản phẩm
    public ArrayList<Account> getListKhachHang() throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "SELECT * FROM dbo.USERS WHERE UserRole = 'KH'";
        
        ArrayList<Account> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Account acc = new Account();
                acc.setUserCode(rs.getString("UserCode"));
                acc.setUserName(rs.getString("UserName"));
                acc.setUserPass(rs.getString("Pass"));
                acc.setUserRole(rs.getString("UserRole"));
                acc.setFullName(rs.getNString("Name"));
                acc.setCityOrSchool(rs.getNString("CtyOrSchool"));
                acc.setUserEmail(rs.getString("Email"));
                acc.setPhoneNumber(rs.getString("PhoneNumber"));
                acc.setUserAddres(rs.getNString("Address"));
                acc.setUserCountry(rs.getNString("Country"));
                
                list.add(acc);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Xóa khach hang khỏi CSDL
    public boolean deleteAccount(String accountID) throws ClassNotFoundException{
        
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "DELETE FROM dbo.USERS WHERE UserCode = ?";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, accountID);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
        
    }
    
    // Cập nhật lại thông tin KH
    public boolean updateAccount(Account acc) throws ClassNotFoundException{
        
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "UPDATE dbo.USERS SET Name = ?, CtyOrSchool = ?, Email = ?, PhoneNumber = ?, Address = ?, Country = ? WHERE UserCode = ?";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setNString(1, acc.getFullName());
            ps.setString(2, acc.getCityOrSchool());
            ps.setString(3, acc.getUserEmail());
            ps.setString(4, acc.getPhoneNumber());
            ps.setNString(5, acc.getUserAddres());
            ps.setString(6, acc.getUserCountry());
            ps.setString(7, acc.getUserCode());
            
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {

            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);

        }
        return false;
        
    }
    
}
