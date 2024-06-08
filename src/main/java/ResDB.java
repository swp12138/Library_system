import java.security.PublicKey;
import java.sql.*;
import java.util.ArrayList;

/**
 * 处理借书和还书
 * ELL
 *
 * @since 2024/6/5 10:45
 **/
public class ResDB {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "swp123456";


    public int borrowBook(String readerID, String bookID) {
        String sql = "{call borrow_book(?, ?, ?, curdate())}";
        int resultState = -1; // 默认返回值

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, readerID);
            cstmt.setString(2, bookID);

            cstmt.registerOutParameter(3, Types.INTEGER);

            cstmt.execute();

            // 获取存储过程的返回值
            resultState = cstmt.getInt(3);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return resultState;
    }

    public void returnBook(String readerID, String bookID) {
        String sql = "{call returnbook(?, ?, curdate())}";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, readerID);
            cstmt.setString(2, bookID);

            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ReserveDB> getReservations(String readerID) {
        String sql = "SELECT book.bname, reserve.book_id, reserve.reserve_Date from book, reserve where book.id = reserve.book_ID and reserve.reader_ID = ?";
        ArrayList<ReserveDB> reservations = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, readerID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("bname");
                    String id = rs.getString("book_id");
                    String date = rs.getString("reserve_Date");
                    ReserveDB reservation = new ReserveDB(name, id, date);
                    reservations.add(reservation);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservations;
    }

    public ArrayList<MessageFX> getMessages(String readerID) {
        String sql = "SELECT book.bname, message.book_id, message.borrow_Date, message.re_date from book, message where book.id = message.book_ID and message.reader_ID = ?";
        ArrayList<MessageFX> messages = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, readerID);

            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("bname");
                    String id = rs.getString("book_id");
                    String date = rs.getString("borrow_Date");
                    String returnDate = rs.getString("re_date");
                    MessageFX message = new MessageFX(name, id, date, returnDate);
                    messages.add(message);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return messages;
    }

    public ArrayList<BorrowDB> getBorrows(String readerID) {
        String sql = "SELECT book.bname, borrow.book_id, borrow.borrow_Date, borrow.return_date from book, borrow where book.id = borrow.book_ID and borrow.reader_ID = ?";
        ArrayList<BorrowDB> borrows = new ArrayList<>();
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, readerID);
            try (ResultSet rs = pstmt.executeQuery()) {
                while (rs.next()) {
                    String name = rs.getString("bname");
                    String id = rs.getString("book_id");
                    String date = rs.getString("borrow_Date");
                    String returnDate = rs.getString("return_date");
                    BorrowDB borrow = new BorrowDB(name, id, date);
                    if (returnDate == null) {
                        borrow.setReturnDate("未还");
                    }
                    if (returnDate != null) {
                        borrow.setReturnDate(returnDate);
                    }
                    borrows.add(borrow);
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return borrows;
    }

    public int reserveBook(String readerID, String bookID) {
        String sql = "SELECT res(?, ?, curdate())";
        int reservationState = -1;

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, readerID);
            pstmt.setString(2, bookID);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    reservationState = rs.getInt(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reservationState;
    }

    public void cancelReservation(String readerID, String bookID) {
        String sql = "{call deres(?, ?)}";

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             CallableStatement cstmt = conn.prepareCall(sql)) {

            cstmt.setString(1, readerID);
            cstmt.setString(2, bookID);

            cstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ResDB service = new ResDB();
        //int result = service.borrowBook("R002", "B001");
        // service.returnBook("R001", "B001");
        service.returnBook("R001", "B001");
        //int result = service.reserveBook("R001", "B003");
        // System.out.println("Borrow operation result: " + result);
    }
}

