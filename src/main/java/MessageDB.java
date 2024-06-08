import java.sql.*;
import java.util.ArrayList;

/**
 * 违规信息处理
 * ELL
 *
 * @since 2024/6/3 16:20
 **/
public class MessageDB {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "swp123456";
    public ArrayList<Message> getAllmessages() {
        ArrayList<Message> messageList = new ArrayList<>();
        String sql = "SELECT * FROM message";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
             while (rs.next()) {
                String book_id = rs.getString("book_ID");
                String reader_id = rs.getString("reader_ID");
                String borrow_date = rs.getString("borrow_Date");
                String re_date = rs.getString("re_date");
                String state = rs.getString("state");
                Message m = new Message(book_id, reader_id, borrow_date, re_date, state);
                messageList.add(m);
             }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return messageList;
    }

    public void deleteMessage(String book_id, String reader_id, String borrow_date) {
        String sql = "DELETE FROM message WHERE book_ID=? AND reader_ID=? AND borrow_Date=?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, book_id);
            stmt.setString(2, reader_id);
            stmt.setString(3, borrow_date);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main() {
        MessageDB DB = new MessageDB();
        ArrayList<Message> mm = DB.getAllmessages();
        DB.deleteMessage("1", "1", "2024-06-03");
        System.out.println(mm.get(0).getBorrow_date());
    }
}
