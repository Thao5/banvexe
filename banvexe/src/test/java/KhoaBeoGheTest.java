
import com.thao.Services.DatabaseConnection;
import com.thao.Services.KhoaBeoChuyenXeTuyenDuongSer;
import com.thao.Services.KhoaBeoGheService;
import com.thao.pojo.ChuyenXeThuocTuyenDuong;
import com.thao.pojo.Ghe;
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
public class KhoaBeoGheTest {

    private static Connection conn;
    private static KhoaBeoGheService kbgs;

    @BeforeAll
    public static void BeforeAll() throws SQLException {
        conn = DatabaseConnection.getDBConnection();
        kbgs = new KhoaBeoGheService();
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
        Ghe g = new Ghe(null, null, false, null, null);
        Throwable exception = Assertions.assertThrows(Throwable.class, () -> {
            kbgs.insertGhe(g);
        });
        Assertions.assertTrue(exception instanceof SQLIntegrityConstraintViolationException);
    }

    @Test
    @Order(2)
    @DisplayName("Kiểm tra Thêm Ghế thành công với id ")
    public void testInsertGheSuccess() throws SQLException {
        Ghe g = new Ghe("A111", "GHEVODICH", false, null, "9f3d347b-7c22-4d85-a3b8-f95f902844d6");
        boolean actual = kbgs.insertGhe(g);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM ghe WHERE id=?");
        stm.setString(1, g.getId());
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());
        Assertions.assertEquals(g.getName(), rs.getString("name"));
        Assertions.assertEquals(g.isTrangthai(), rs.getBoolean("trangthai"));
        Assertions.assertEquals(g.getXekhach_id(), rs.getString("xekhach_id"));
        Assertions.assertEquals(g.getVe_id(), rs.getString("ve_id"));
    }

    @Test
    @Order(3)
    @DisplayName("Kiểm tra Tìm kiếm Ghế thành công với name")
    public void testFindGheByKwSuccess() throws SQLException {
        String kw = "GHEVODICH";
        List<Ghe> ghes = kbgs.getListGhe(kw);
        Assertions.assertEquals(1, ghes.size());
        for (Ghe cxtd : ghes) {
            Assertions.assertTrue(cxtd.getName().contains(kw));
        }
    }

    @Test
    @Order(4)
    @DisplayName("Kiểm tra Cập nhật Ghế thành công với id")
    public void testUpdateGheSuccess() throws SQLException {
        Ghe g = new Ghe("A111", "GHEVODICHuPDATE", false, null, "9f3d347b-7c22-4d85-a3b8-f95f902844d6");
        boolean actual = kbgs.updateGhe(g);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM ghe WHERE id=?");
        stm.setString(1, g.getId());
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());
        Assertions.assertEquals(g.getName(), rs.getString("name"));
        Assertions.assertEquals(g.isTrangthai(), rs.getBoolean("trangthai"));
        Assertions.assertEquals(g.getXekhach_id(), rs.getString("xekhach_id"));
        Assertions.assertEquals(g.getVe_id(), rs.getString("ve_id"));
    }

    @Test
    @Order(5)
    @DisplayName("Kiểm tra Cập nhật Ghế thất bại với id không tồn tại")
    public void testUpdateGheFailure() throws SQLException {
        Ghe g = new Ghe("GheKhongVodich", "GHEVODICHuPDATE", false, null, "9f3d347b-7c22-4d85-a3b8-f95f902844d6");
        boolean actual = kbgs.updateGhe(g);
        Assertions.assertFalse(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM ghe WHERE id=?");
        stm.setString(1, g.getId());
        ResultSet rs = stm.executeQuery();
        Assertions.assertFalse(rs.next());
    }

    @Test
    @Order(6)
    @DisplayName("Kiểm tra Xóa Ghế thành công với id")
    public void testDeleteGheSuccess() throws SQLException {
        String id = "A111";
        boolean actual = kbgs.deleteGhe(id);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM ghe WHERE id=?");
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        Assertions.assertFalse(rs.next());
    }

}
