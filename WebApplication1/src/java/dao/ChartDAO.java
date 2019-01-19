/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Category;
import model.Value;

/**
 *
 * @author AnhDuc
 */
public class ChartDAO {
    
    // Sử dụng sql ở Book và Category
    public ArrayList<Value> getAll() throws ClassNotFoundException{
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            BookDAO bookDAO = new BookDAO();
            ArrayList<Value> list = new ArrayList<>();
            for (Category category: categoryDAO.getCategory()){
                list.add(new Value(category.getCategoryName(), 
                        bookDAO.getListBookByCategory(category.getCategoryID()).size()));
            }
            return list;
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ChartDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return null;
        
    }
    
    // Sử dụng sql ở Book và Category
    public ArrayList<Value> getAllBillDetail() throws ClassNotFoundException{
        try {
            CategoryDAO categoryDAO = new CategoryDAO();
            BillDetailDAO billdetailDAO = new BillDetailDAO();
            ArrayList<Value> list = new ArrayList<>();
            for (Category category: categoryDAO.getCategory()){
                list.add(new Value(category.getCategoryName(), 
                        billdetailDAO.getListBillDetailByBookCategory(category.getCategoryID()).size()));
            }
            return list;
        } catch (ClassNotFoundException e) {
            Logger.getLogger(ChartDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        
        return null;
        
    }
    
//    public static void main(String[] args) throws ClassNotFoundException {
////        System.out.println(new ChartDAO().getAll());
//        for (Value v: new ChartDAO().getAllBillDetail()){
//            System.out.println(v.getName()+"-"+v.getValue());
//        }
//    }
    
}
