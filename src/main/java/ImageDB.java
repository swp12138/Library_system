/**
 * 图片类
 * ELL
 *
 * @since 2024/6/5 19:38
 **/
import java.io.*;
import java.sql.*;

public class ImageDB {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/library";
    private static final String USER = "root";
    private static final String PASS = "swp123456";

    public void writeImageToDB(File imageFile, String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "UPDATE reader SET pic = ? WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setBlob(1, new FileInputStream(imageFile));
            pstmt.setString(2, id);
            pstmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se2) {
                System.out.println("数据库连接关闭失败");
            }
        }
    }

    public void readImageFromDB(String id) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DriverManager.getConnection(DB_URL, USER, PASS);
            String sql = "SELECT pic FROM reader WHERE id = ?";
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Blob blob = rs.getBlob("pic");
                byte[] bytes = blob.getBytes(1, (int)blob.length());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream("./src/main/pic/" + id + ".png"));
                stream.write(bytes);
                stream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null)
                    pstmt.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException se2) {
                System.out.println("数据库连接关闭失败");
            }
        }
    }
    public static void main(String[] args) {
        File file = new File("./src/main/pic/tx.jpg");
        ImageDB imageDB = new ImageDB();
        imageDB.writeImageToDB(file,"R001");
        imageDB.readImageFromDB("R001");
    }
}
