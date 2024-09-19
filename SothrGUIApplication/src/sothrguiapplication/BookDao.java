package sothrguiapplication;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDao 
{
    public static Connection getConnection() {
        Connection con = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/sothr", "root", "spider18");
        } 
        catch (ClassNotFoundException | SQLException ex)
        {           
        
        }
        
        return con;
    }
    
    public static int addBook(Book book)
    {
       int status = 0;
       Connection con = BookDao.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("INSERT INTO books(title, author, price, pages, publisher, category, contain_pictures,contains_cd, number_available) VALUES (?, ?, ?, ?, ?, ?, ?,?,?)");
            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setFloat(3, book.getPrice());
            ps.setInt(4, book.getPages());
            ps.setString(5, book.getPublisher());
            ps.setString(6, book.getCategory());
            ps.setString(7, book.getContain_pictures());
            ps.setString(8, book.getContain_cd());
            ps.setInt(9, book.getNumber_available());
            status=ps.executeUpdate();                
            con.close();
        } catch (SQLException ex) {            
        }
        return status;
    }
    
    public static int updatePrice(int id,float price)
    {
       int status = 0;
       Connection con = BookDao.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE books SET price=? WHERE book_id=?");
            ps.setFloat(1, price);
            ps.setInt(2, id);
            status=ps.executeUpdate();                
            con.close();
        } catch (SQLException ex) {            
        }
        return status;  
    }
    
    public static int updateNumberAvailableBooks(int id,int number_available){
       int status = 0;
       Connection con = BookDao.getConnection();
        try {
            PreparedStatement ps = con.prepareStatement("UPDATE books SET number_available=? WHERE book_id=?");
            ps.setInt(1, id);
            ps.setInt(2, number_available);
            status=ps.executeUpdate();                
            con.close();
        } catch (SQLException ex) {            
        }
        return status;  
    }
    
     public List<Book> getAvailableBooks()
    {
       List<Book> booklist=new ArrayList();
       Connection con=BookDao.getConnection();
       
       
       try{
          PreparedStatement ps = con.prepareStatement("SELECT * FROM books WHERE number_available>0");
          ResultSet rs=ps.executeQuery(); 
          while(rs.next()){
              Book book=new Book();
              book.setId(rs.getInt(1));
              book.setTitle(rs.getString(2));
              book.setAuthor(rs.getString(3));
              book.setPrice(rs.getFloat(4));
              book.setPages(rs.getInt(5));
              book.setPublisher(rs.getString(6));
              book.setCategory(rs.getString(7));
              book.setContain_pictures(rs.getString(8));
              book.setContain_cd(rs.getString(9));
              book.setNumber_available(rs.getInt(10));
              booklist.add(book);
          }
          con.close();
      }
      catch (SQLException ex) {}
      
      return booklist;
    }
    
    public List<Book> getAllBooks()
    {
       List<Book> booklist=new ArrayList();
       Connection con=BookDao.getConnection();
       
       try{
          PreparedStatement ps = con.prepareStatement("SELECT * FROM books");
          ResultSet rs=ps.executeQuery(); 
          while(rs.next()){
              Book book=new Book();
              book.setId(rs.getInt(1));
              book.setTitle(rs.getString(2));
              book.setAuthor(rs.getString(3));
              book.setPrice(rs.getFloat(4));
              book.setPages(rs.getInt(5));
              book.setPublisher(rs.getString(6));
              book.setCategory(rs.getString(7));
              book.setContain_pictures(rs.getString(8));
              book.setContain_cd(rs.getString(9));
              book.setNumber_available(rs.getInt(10));
              booklist.add(book);
          }
          con.close();
      }
      catch (SQLException ex) {}
      
      return booklist;
    }

    public List<Book> getBooksByCategory(String category)
    {
       List<Book> booklist=new ArrayList();
       Connection con=BookDao.getConnection();
       
       try{
          PreparedStatement ps = con.prepareStatement("SELECT * FROM books WHERE category=?");
          ps.setString(1, category);
          ResultSet rs=ps.executeQuery(); 
          while(rs.next()){
              Book book=new Book();
              book.setId(rs.getInt(1));
              book.setTitle(rs.getString(2));
              book.setAuthor(rs.getString(3));
              book.setPrice(rs.getFloat(4));
              book.setPages(rs.getInt(5));
              book.setPublisher(rs.getString(6));
              book.setCategory(rs.getString(7));
              book.setContain_pictures(rs.getString(8));
              book.setContain_cd(rs.getString(9));
              book.setNumber_available(rs.getInt(10));
              booklist.add(book);
          }
          con.close();
      }
      catch (SQLException ex) {}
      
      return booklist;
    }
    
    public Book getBookByBookID(int book_code)
    {
       Book book=new Book();
       Connection con=BookDao.getConnection();
       
       try{
          PreparedStatement ps = con.prepareStatement("SELECT * FROM books WHERE book_id=?");
          ps.setInt(1, book_code);
          ResultSet rs=ps.executeQuery(); 
          while(rs.next()){
              book.setId(rs.getInt(1));
              book.setTitle(rs.getString(2));
              book.setAuthor(rs.getString(3));
              book.setPrice(rs.getFloat(4));
              book.setPages(rs.getInt(5));
              book.setPublisher(rs.getString(6));
              book.setCategory(rs.getString(7));
              book.setContain_pictures(rs.getString(8));
              book.setContain_cd(rs.getString(9));
              book.setNumber_available(rs.getInt(10));
          }
          con.close();
      }
      catch (SQLException ex) {}
      
      return book;
    }
    
    public List <Book> getBooksByAuthor(String author)
    {
       List<Book> booklist=new ArrayList();
       Connection con=BookDao.getConnection();
       
       try{
          PreparedStatement ps = con.prepareStatement("SELECT * FROM books WHERE author=?");
          ps.setString(1,author);
          ResultSet rs=ps.executeQuery(); 
          while(rs.next()){
              Book book=new Book();
              book.setId(rs.getInt(1));
              book.setTitle(rs.getString(2));
              book.setAuthor(rs.getString(3));
              book.setPrice(rs.getFloat(4));
              book.setPages(rs.getInt(5));
              book.setPublisher(rs.getString(6));
              book.setCategory(rs.getString(7));
              book.setContain_pictures(rs.getString(8));
              book.setContain_cd(rs.getString(9));
              book.setNumber_available(rs.getInt(10));
              booklist.add(book);
          }
          con.close();
      }
      catch (SQLException ex) {}
      
      return booklist;
    }
    
    public int orderBook(int book_code)
    {
      int status=0;
      Connection con=BookDao.getConnection();
      Book b=this.getBookByBookID(book_code);
      
      if(b!=null && b.getNumber_available()>0)
      {
        try
        {
          PreparedStatement ps = con.prepareStatement("UPDATE books SET number_available=number_available-1 WHERE book_id=?");
          ps.setInt(1, book_code);
          status=ps.executeUpdate();                
          con.close();
        }
        catch(SQLException ex){}
      }
      
      return status;
    }
    
    public int cancelOrder(int book_code)
    {
      int status=0;
      Connection con=BookDao.getConnection();
      Book b=this.getBookByBookID(book_code);
      
      if(b!=null)
      {
        try
        {
          PreparedStatement ps = con.prepareStatement("UPDATE books SET number_available=number_available+1 WHERE book_id=?");
          ps.setInt(1, book_code);
          status=ps.executeUpdate();                
          con.close();
        }
        catch(SQLException ex){}
      }
      
      return status;
    }
    
    public int deleteBook(int book_code)
    {
      int status=0;
      Connection con=BookDao.getConnection();
      
       try
        {
          PreparedStatement ps = con.prepareStatement("DELETE FROM books WHERE book_id=?");
          ps.setInt(1, book_code);
          status=ps.executeUpdate();                
          con.close();
        }
        catch(SQLException ex){}
      
      return status;
    }
}