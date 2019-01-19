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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Book;

/**
 *
 * @author unknown_HUST
 */
public class BookDAO {
    
    //get danh sach san pham dua vao ma danh muc:
    public ArrayList<Book> getListBookByCategory(int categoryID) throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "SELECT * FROM dbo.BOOK WHERE BookCategoryID='"+categoryID+"'";
        ArrayList<Book> list = new ArrayList<>();
        
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBookCode(rs.getString("BookCode"));
                book.setBookName(rs.getString("Name"));
                book.setBookPrice(rs.getFloat("Price"));
                book.setAuthor(rs.getInt("AuthorID"));
                book.setNxb(rs.getString("NXB"));
                //book.setBookCategory(rs.getInt("BookCategoryID"));
                book.setBookDescription(rs.getString("BookDesciption"));
                book.setBookImage(rs.getString("BookImage"));
                list.add(book);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    //Tìm kiếm sách:
    public ArrayList<Book> searchBooks(String keyword) throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String skeyword = "%" + keyword + "%";
        String sql = "SELECT * FROM dbo.BOOK WHERE BookCode LIKE '"+skeyword+"' OR BookDesciption LIKE '"+skeyword+"' OR Name LIKE '"+skeyword+"' OR NXB LIKE '"+skeyword+"'";
        ArrayList<Book> list = new ArrayList<>();
        
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBookCode(rs.getString("BookCode"));
                book.setBookName(rs.getString("Name"));
                book.setBookPrice(rs.getFloat("Price"));
                book.setAuthor(rs.getInt("AuthorID"));
                book.setNxb(rs.getString("NXB"));
                book.setBookCategory(rs.getInt("BookCategoryID"));
                book.setBookDescription(rs.getString("BookDesciption"));
                book.setBookImage(rs.getString("BookImage"));
                list.add(book);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    public ArrayList<Book> getListTenBookByCategory(int categoryID) throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "SELECT TOP 10 * FROM dbo.BOOK WHERE BookCategoryID='"+categoryID+"'";
        ArrayList<Book> list = new ArrayList<>();
        
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBookCode(rs.getString("BookCode"));
                book.setBookName(rs.getString("Name"));
                book.setBookPrice(rs.getFloat("Price"));
                book.setAuthor(rs.getInt("AuthorID"));
                book.setNxb(rs.getString("NXB"));
                //book.setBookCategory(rs.getInt("BookCategoryID"));
                book.setBookDescription(rs.getString("BookDesciption"));
                book.setBookImage(rs.getString("BookImage"));
                list.add(book);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    // Lấy danh sách sản phẩm
    public ArrayList<Book> getListBook() throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "SELECT * FROM BOOK";
        
        ArrayList<Book> list = new ArrayList<>();
        try {
            PreparedStatement ps = (PreparedStatement) conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setBookCode(rs.getString("BookCode"));
                book.setBookName(rs.getString("Name"));
                book.setBookPrice(rs.getFloat("Price"));
                book.setNxb(rs.getString("NXB"));
                book.setBookDescription(rs.getString("BookDesciption"));
                list.add(book);
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    //get danh sach san pham dua vao ma tacgia:
    public ArrayList<Book> getListBookByAuthor(int authorID) throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "SELECT * FROM dbo.BOOK WHERE AuthorID='"+authorID+"'";
        ArrayList<Book> list = new ArrayList<>();
        
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBookCode(rs.getString("BookCode"));
                book.setBookName(rs.getString("Name"));
                book.setBookPrice(rs.getFloat("Price"));
                book.setAuthor(rs.getInt("AuthorID"));
                book.setNxb(rs.getString("NXB"));
                book.setBookCategory(rs.getInt("BookCategoryID"));
                book.setBookDescription(rs.getString("BookDesciption"));
                book.setBookImage(rs.getString("BookImage"));
                list.add(book);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
    
    
    //lay thong tin san pham theo ma san pham:
    public Book getBookByBookID(String bookID) throws ClassNotFoundException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "SELECT * FROM dbo.BOOK WHERE BookCode='"+bookID+"'";
        Book b = new Book();
        
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Book book = new Book();
                book.setBookCode(rs.getString("BookCode"));
                book.setBookName(rs.getString("Name"));
                book.setBookPrice(rs.getFloat("Price"));
                book.setAuthor(rs.getInt("AuthorID"));
                book.setNxb(rs.getString("NXB"));
                book.setBookCategory(rs.getInt("BookCategoryID"));
                book.setBookDescription(rs.getString("BookDesciption"));
                book.setBookImage(rs.getString("BookImage"));
                //list.add(book);
                b = book;
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return b;
    }
    
    
    // Thêm sách mới 
    public boolean insertBook(Book b) throws ClassNotFoundException{

        Connection conn = ConnectDB.getConnectionDB();
        String sql = "insert into BOOK values(?,?,?,?,?,?,?,?)";
        try {    
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, b.getBookCode());
            ps.setString(2, b.getBookName());
            ps.setFloat(3, b.getBookPrice());
            ps.setInt(4, b.getAuthor());
            ps.setString(5, b.getNxb());
            ps.setInt(6, b.getBookCategory());
            ps.setString(7, b.getBookDescription());
            ps.setString(8, b.getBookImage());
            
            return ps.executeUpdate() == 1;
            
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    
    // Xóa sách khỏi CSDL
    public boolean deleteBook(String bookID) throws ClassNotFoundException{
        
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "delete from BOOK where BookCode = ?";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, bookID);
            return ps.executeUpdate() == 1;
        } catch (SQLException e) {
            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return false;
        
    }
    
    // Cập nhật lại thông tin sách trong CSDL
    public boolean updateBook(Book b) throws ClassNotFoundException{
        
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "update BOOK set BookCode = ?, Name = ?, Price = ?, AuthorID = ?, NXB = ?, BookCategoryID = ?, BookDesciption = ?, BookImage = ? where BookCode = ?";
        try {
            PreparedStatement ps = conn.prepareCall(sql);
            ps.setString(1, b.getBookCode());
            ps.setString(2, b.getBookName());
            ps.setFloat(3, b.getBookPrice());
            ps.setInt(4, b.getAuthor());
            ps.setString(5, b.getNxb());
            ps.setInt(6, b.getBookCategory());
            ps.setString(7, b.getBookDescription());
            ps.setString(8, b.getBookImage());
            ps.setString(9, b.getBookCode());
            return ps.executeUpdate() == 1;

        } catch (SQLException e) {

            Logger.getLogger(CategoryDAO.class.getName()).log(Level.SEVERE, null, e);

        }
        return false;
        
    }
    
    // Tìm mã tác giả của sách
    public int findAuthorID(String authorName) throws ClassNotFoundException, SQLException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "select * from AUTHOR where AuthorName = '"+authorName+"'";
        PreparedStatement ps = conn.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return rs.getInt("AuthorID");
        }
        return 0;
    }
    
    // Tìm mã thể loại của sách
    public int findCategoryID(String categoryName) throws ClassNotFoundException, SQLException{
        Connection conn = ConnectDB.getConnectionDB();
        String sql = "select * from CATEGORY where categoryName = '"+categoryName+"'";
        PreparedStatement ps = conn.prepareCall(sql);
        ResultSet rs = ps.executeQuery();
        if (rs.next()){
            return rs.getInt("CategoryID");
        }
        return 0;
    }
    
    
    public static void main(String[] args) throws ClassNotFoundException {
        
        for(Book b : new BookDAO().searchBooks("hust")){
            System.out.println(b.getBookName());
        }
    }
}
