import java.sql.*;
import java.util.ArrayList;

/**
 * 书的DB类
 * ELL
 *
 * @since 2024/6/2 19:32
 **/
public class BookDB {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "swp123456";

    public ArrayList<Book> getAllBooks(){
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("bname");
                String author = rs.getString("author");
                float price = rs.getFloat("price");
                int borrow_times = rs.getInt("borrow_Times");
                int reserve_times = rs.getInt("reserve_Times");
                int bStatus = rs.getInt("bstatus");
                Book book = new Book(name, author, id, price, bStatus, borrow_times, reserve_times);
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public ArrayList<Book> getBooks(String keyword){
        ArrayList<Book> books = new ArrayList<>();
        String sql = "SELECT * FROM book WHERE bname LIKE ? OR author LIKE ?";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // 设置参数
            pstmt.setString(1, "%" + keyword + "%"); // 第一个问号对应的参数
            pstmt.setString(2, "%" + keyword + "%"); // 第二个问号对应的参数

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("bname");
                    String author = rs.getString("author");
                    float price = rs.getFloat("price");
                    int borrowTimes = rs.getInt("borrow_Times");
                    int reserveTimes = rs.getInt("reserve_Times");
                    int bStatus = rs.getInt("bstatus");

                    Book book = new Book(name, author, id, price, bStatus, borrowTimes, reserveTimes);
                    books.add(book);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    public void insertBook(Book book) {
        String sql = "INSERT INTO book (id, bname, author, price, bstatus, borrow_Times, reserve_Times) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getId());
            pstmt.setString(2, book.getName());
            pstmt.setString(3, book.getAuthor());
            pstmt.setFloat(4, book.getPrice());
            pstmt.setInt(5, book.getBStatus());
            pstmt.setInt(6, book.getBorrowTimes());
            pstmt.setInt(7, book.getReserveTimes());

            pstmt.executeUpdate(); // Execute the SQL statement
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateBook(Book book, String id) {
        String sql = "UPDATE book SET bname=?, author=?, price=?, bstatus=?, borrow_Times=?, reserve_Times=?, id=? WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, book.getName());
            pstmt.setString(2, book.getAuthor());
            pstmt.setFloat(3, book.getPrice());
            pstmt.setInt(4, book.getBStatus());
            pstmt.setInt(5, book.getBorrowTimes());
            pstmt.setInt(6, book.getReserveTimes());
            pstmt.setString(7, book.getId()); // Use the ID to identify which record to update
            pstmt.setString(8, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteBook(String id) {
        String sql = "DELETE FROM book WHERE id=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, id);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
