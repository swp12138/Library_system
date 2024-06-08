import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * 连接admin
 * ELL
 *
 * @since 2024/6/2 13:53
 **/
public class AdminsDB {
    private static final String URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASSWORD = "swp123456";
    // 获取所有的管理员信息
    public ArrayList<Admins> getAllAdmins() {
        ArrayList<Admins> adminsList = new ArrayList<>();
        String sql = "SELECT * FROM admins";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                String name = rs.getString("aname");
                String password = rs.getString("pass");
                String id = rs.getString("id");
                int state = rs.getInt("state");
                Admins admin = new Admins(name, password, id, state);
                adminsList.add(admin);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return adminsList;
    }

    // 加载驱动
    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load JDBC driver", e);
        }
    }

    // 创建管理员
    public void createAdmin(Admins admin) {
        String sql = "INSERT INTO admins (aname, pass, id, state) VALUES (?, ?, ?, ?)";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getPassword());
            stmt.setString(3, admin.getId());
            stmt.setInt(4, admin.getState());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 读取管理员（根据ID）
    public Admins readAdmin(String id) {
        String sql = "SELECT * FROM admins WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Admins(rs.getString("aname"),
                            rs.getString("pass"),
                            rs.getString("id"),
                            rs.getInt("state"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // 更新管理员信息
    public void updateAdmin(Admins admin, String id) {
        String sql = "UPDATE admins SET aname = ?, pass = ?, state = ?, id = ? WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, admin.getName());
            stmt.setString(2, admin.getPassword());
            stmt.setInt(3, admin.getState());
            stmt.setString(4, admin.getId());
            stmt.setString(5, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // 删除管理员（根据ID）
    public void deleteAdmin(String id) {
        String sql = "DELETE FROM admins WHERE id = ?";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
