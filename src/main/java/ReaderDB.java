import java.util.ArrayList;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 * 读者
 * ELL
 *
 * @since 2024/6/3 19:24
 **/
public class ReaderDB {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "swp123456";

    public ArrayList<Reader> getAllReaders() {
        ArrayList<Reader> readers = new ArrayList<>();
        String sql = "SELECT * FROM reader";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("rname");
                int age = rs.getInt("age");
                String phone = rs.getString("phone");
                String password = rs.getString("pass");
                Reader reader = new Reader(name, id, age, phone, password);
                readers.add(reader);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return readers;
    }

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }

    public void createReader(Reader reader) {
        String sql = "INSERT INTO reader (rname, pass, id, state, phone, age) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reader.getName());
            stmt.setString(2, reader.getPassword());
            stmt.setString(3, reader.getId());
            stmt.setInt(4, 0);
            stmt.setString(5, reader.getPhone());
            stmt.setInt(6, reader.getAge());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReader(Reader reader, String id) {
        String sql = "UPDATE reader SET rname = ?, pass = ?, age = ?, id = ?, phone = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, reader.getName());
            stmt.setString(2, reader.getPassword());
            stmt.setInt(3, reader.getAge());
            stmt.setString(4, reader.getId());
            stmt.setString(5, reader.getPhone());
            stmt.setString(6, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteReader(String id) {
        String sql = "DELETE FROM reader WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ArrayList<Reader> readers = new ReaderDB().getAllReaders();
        for (Reader reader : readers) {
            System.out.println(reader.getId());
        }
        ReaderDB readerDB = new ReaderDB();
        readerDB.updateReader(new Reader("Nick", "R001", 18, "114514", "123456"), "R001");
    }

    public void updateReaderId(String newId, String oldId) {
        String sql = "update reader set id = ? where id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newId);
            stmt.setString(2, oldId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReaderPassword(String newPassword, String id) {
        String sql = "update reader set pass = ? where id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, newPassword);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateReaderAge(int age, String id) {
        String sql = "update reader set age = ? where id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, age);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateReaderName(String name, String id) {
        String sql = "update reader set rname = ? where id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, name);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void updateReaderPhone(String phone, String id) {
        String sql = "update reader set phone = ? where id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, phone);
            stmt.setString(2, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
