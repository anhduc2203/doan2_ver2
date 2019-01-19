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
import model.HistoryConnect;

/**
 *
 * @author unknown_HUST
 */
public class HistoryConnectDAO {
    public void insertHistoryConnect(HistoryConnect historyConnect) throws ClassNotFoundException, SQLException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "insert into dbo.HISTORY_CONNECT values(?,?,?,?)";
        PreparedStatement ps = conn.prepareCall(sql);
        
        ps.setString(1, historyConnect.getHistoryID());
        ps.setTimestamp(2, historyConnect.getDate());
        ps.setString(3, historyConnect.getUserID());
        ps.setString(4, historyConnect.getUserRole());
        ps.executeUpdate();
    }
    
    
    public ArrayList<HistoryConnect> getListHistoryConnect() throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "SELECT * FROM HISTORY_CONNECT";
        
        ArrayList<HistoryConnect> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HistoryConnect hc = new HistoryConnect();
                hc.setHistoryID(rs.getString("HistoryID"));
                hc.setDate(rs.getTimestamp("Time"));
                hc.setUserID(rs.getString("UserCode"));
                hc.setUserRole(rs.getString("Role"));
                
                list.add(hc);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
//    public static void main(String[] args) throws ClassNotFoundException {
//        for(HistoryConnect h : new HistoryConnectDAO().getListHistoryConnect()){
//            System.out.println(h.getHistoryID());
//        }
//    }
}
