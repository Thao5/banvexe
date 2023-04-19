
import com.thao.Services.DatabaseConnection;
import com.thao.Services.KhoaBeoGheService;
import com.thao.Services.KhoaBeoXeKhachService;
import com.thao.pojo.Ghe;
import com.thao.pojo.XeKhach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author anhkh
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class KhoaBeoXeKhachTest {

    private static Connection conn;
    private static KhoaBeoXeKhachService kbxk;

    @BeforeAll
    public static void BeforeAll() throws SQLException {
        conn = DatabaseConnection.getDBConnection();
        kbxk = new KhoaBeoXeKhachService();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Test
    @Order(1)
    @DisplayName("Kiểm tra Thêm Ghế không hợp lệ null")
    public void testInsertNull() throws SQLException {
        XeKhach xk = new XeKhach(0, null);
        Throwable exception = Assertions.assertThrows(Throwable.class, () -> {
            kbxk.insertXeKhach(xk);
        });
        Assertions.assertTrue(exception instanceof SQLIntegrityConstraintViolationException);
    }

    @Test
    @Order(2)
    @DisplayName("Kiểm tra Thêm Xe Khách thành công")
    public void testInsertSuccess() throws SQLException {
        XeKhach xk = new XeKhach("XeKhachVoDich", 0, "XeKhach123");
        boolean actual = kbxk.insertXeKhach(xk);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM XeKhach WHERE id=?");
        stm.setString(1, xk.getId());
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());
        Assertions.assertEquals(xk.getId(), rs.getString("id"));
        Assertions.assertEquals(xk.getSochongoi(), rs.getInt("sochongoi"));
        Assertions.assertEquals(xk.getBienso(), rs.getString("bienso"));
    }

    @Test
    @Order(3)
    @DisplayName("Kiểm tra Tìm kiếm Xe Khách thành công")
    public void testSearchSuccess() throws SQLException {
        String bienso = "XeKhach123";
        List<XeKhach> listXeKhach = kbxk.getXeKhach(bienso);
        Assertions.assertEquals(1, listXeKhach.size());
        for (XeKhach xe : listXeKhach) {
            Assertions.assertTrue(xe.getBienso().contains(bienso));
        }
    }

    @Test
    @Order(4)
    @DisplayName("Kiểm tra Cập nhật thông tin Xe Khách thành công")
    public void testUpdateSuccess() throws SQLException {

        XeKhach xk = new XeKhach("XeKhachVoDich", 30, "XeKhachVoDichUpdate");

        boolean actual = kbxk.updateXeKhach(xk);
        Assertions.assertTrue(actual);

        PreparedStatement stm = conn.prepareStatement("SELECT * FROM XeKhach WHERE id=?");
        stm.setString(1, xk.getId());
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());
        Assertions.assertEquals(xk.getId(), rs.getString("id"));
        Assertions.assertEquals(xk.getSochongoi(), rs.getInt("sochongoi"));
        Assertions.assertEquals(xk.getBienso(), rs.getString("bienso"));
    }

    @Test
    @Order(5)
    @DisplayName("Kiểm tra Cập nhật thông tin Xe Khách thất bại")
    public void testUpdateFail() throws SQLException {

        XeKhach xk = new XeKhach("XeKhachVoDichfaild", 30, "XeKhachVoDichUpdateNotThanhCong");
        boolean actual = kbxk.updateXeKhach(xk);
        Assertions.assertFalse(actual);
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM XeKhach WHERE id=?");
        stm.setString(1, xk.getId());
        ResultSet rs = stm.executeQuery();
        Assertions.assertFalse(rs.next());
    }

    @Test
    @Order(6)
    @DisplayName("Kiểm tra Xóa Xe Khách thành công")
    public void testDeleteSuccess() throws SQLException {
        String id = "XeKhachVoDich";
        boolean actual = kbxk.deleteXeKhach(id);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareStatement("SELECT * FROM XeKhach WHERE id=?");
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        Assertions.assertFalse(rs.next());
    }

}
