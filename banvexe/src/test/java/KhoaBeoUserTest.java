
import com.thao.Services.DatabaseConnection;
import com.thao.Services.KhoaBeoGheService;
import com.thao.Services.KhoaBeoUserService;
import com.thao.pojo.Ghe;
import com.thao.pojo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author anhkh
 */
public class KhoaBeoUserTest {

    private static Connection conn;
    private static KhoaBeoUserService kbuser;

    @BeforeAll
    public static void BeforeAll() throws SQLException {
        conn = DatabaseConnection.getDBConnection();
        kbuser = new KhoaBeoUserService();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Test
    @DisplayName("Kiểm tra Thêm User không hợp lệ null")
    public void testInsertNull() throws SQLException {
        User user = new User(null, null, null, null, null, true);
        Throwable exception = Assertions.assertThrows(Throwable.class, () -> {
            kbuser.insertUser(user);
        });
        Assertions.assertTrue(exception instanceof SQLIntegrityConstraintViolationException);
    }

    @Test
    @DisplayName("Kiểm tra Thêm User thành công")
    public void testInsertSuccess() throws SQLException {

        User user = new User("UserVodich", "Tran", "Van A", "0123456789", "vana", "123456", false);
        boolean actual = kbuser.insertUser(user);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM User WHERE id=?");
        stm.setString(1, user.getId());
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());
        Assertions.assertEquals(user.getId(), rs.getString("id"));
        Assertions.assertEquals(user.getHo(), rs.getString("ho"));
        Assertions.assertEquals(user.getTen(), rs.getString("ten"));
        Assertions.assertEquals(user.getSdt(), rs.getString("sdt"));
        Assertions.assertEquals(user.getUsername(), rs.getString("username"));
        Assertions.assertEquals(user.getPassword(), rs.getString("password"));
        Assertions.assertEquals(user.isAdmin(), rs.getBoolean("admin"));
    }

    @Test
    @DisplayName("Kiểm tra Cập nhật thông tin User thành công")
    public void testUpdateSuccess() throws SQLException {
        // Tạo một đối tượng User mới và thêm vào DB
        User user = new User("UserVodich", "Tran UPDATE", "Van A UPDATE", "0123456789", "vanaUPDATE", "123456", false);
        boolean actual = kbuser.updateUser(user);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM User WHERE id=?");
        stm.setString(1, user.getId());
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());
        Assertions.assertEquals(user.getId(), rs.getString("id"));
        Assertions.assertEquals(user.getHo(), rs.getString("ho"));
        Assertions.assertEquals(user.getTen(), rs.getString("ten"));
        Assertions.assertEquals(user.getSdt(), rs.getString("sdt"));
        Assertions.assertEquals(user.getUsername(), rs.getString("username"));
        Assertions.assertEquals(user.getPassword(), rs.getString("password"));
        Assertions.assertEquals(user.isAdmin(), rs.getBoolean("admin"));
    }

    @Test
    @DisplayName("Kiểm tra Cập nhật thông tin User thất bại")
    public void testUpdateFailed() throws SQLException {
// Tạo một đối tượng User mới nhưng không thêm vào DB
        User user = new User("UserVodichFaild", "Tran UPDATEFAILD", "Van A UPDATEFAILD", "0123456789", "vanaUPDATE", "123456", false);
        boolean actual = kbuser.updateUser(user);
        Assertions.assertFalse(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM User WHERE id=?");
        stm.setString(1, user.getId());
        ResultSet rs = stm.executeQuery();
        Assertions.assertFalse(rs.next());
    }

    @Test
    @DisplayName("Kiểm tra Xóa User thành công")
    public void testDeleteSuccess() throws SQLException {

        String id = "Uservodich";

        boolean actual = kbuser.deleteUser(id);
        Assertions.assertTrue(actual);

        PreparedStatement stm = conn.prepareStatement("SELECT * FROM User WHERE username=?");
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        Assertions.assertFalse(rs.next());
    }
}
