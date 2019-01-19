/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Time;
import java.sql.Timestamp;

/**
 *
 * @author unknown_HUST
 */
public class HistoryConnect {
    private String historyID;
    private Timestamp date;
    private String userID;
    private String userRole;

    public HistoryConnect() {
    }

    public HistoryConnect(String historyID, Timestamp date, String userID, String userRole) {
        this.historyID = historyID;
        this.date = date;
        this.userID = userID;
        this.userRole = userRole;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    

    public String getHistoryID() {
        return historyID;
    }

    public void setHistoryID(String historyID) {
        this.historyID = historyID;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }
    
    
}
