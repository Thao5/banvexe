
import com.thao.Services.DatabaseConnection;
import com.thao.Services.KhoaBeoBenXeService;
import com.thao.Services.KhoaBeoChuyenXeTuyenDuongSer;
import com.thao.pojo.BenXe;
import com.thao.pojo.ChuyenXeThuocTuyenDuong;
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
public class KhoaBeoChuyenXeTuyenDuongTest {

    private static Connection conn;
    private static KhoaBeoChuyenXeTuyenDuongSer kbcxtd;

    @BeforeAll
    public static void BeforeAll() throws SQLException {
        conn = DatabaseConnection.getDBConnection();
        kbcxtd = new KhoaBeoChuyenXeTuyenDuongSer();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Test
    @Order(1)
    @DisplayName("Kiểm tra Thêm Chuyến xe_id và tuyền đường id không hợp lệ null")
    public void testInsertNull() throws SQLException {
        ChuyenXeThuocTuyenDuong bx = new ChuyenXeThuocTuyenDuong(null, null);
        Throwable exception = Assertions.assertThrows(Throwable.class, () -> {
            kbcxtd.insertChuyenXeThuocTuyenDuong(bx);
        });
        Assertions.assertTrue(exception instanceof SQLIntegrityConstraintViolationException);
    }

    @Test
    @Order(2)
    @DisplayName("Kiểm tra Thêm Chuyến xe_id và tuyền đường id không hợp lệ không tồn tại")
    public void testInsertCXTDNOT() throws SQLException {
        ChuyenXeThuocTuyenDuong bx = new ChuyenXeThuocTuyenDuong("123123", "123123");
        Throwable exception = Assertions.assertThrows(SQLException.class, () -> {
            kbcxtd.insertChuyenXeThuocTuyenDuong(bx);
        });
        //SQLIntegrityConstraintViolationException ngoại lệ được ném khi sai ràng buộc *******
        Assertions.assertTrue(exception instanceof SQLIntegrityConstraintViolationException);
    }

    @Test
    @Order(3)
    @DisplayName("Kiểm tra insert thành công")
    public void testInsertSuccess() throws SQLException {
        ChuyenXeThuocTuyenDuong cxtd = new ChuyenXeThuocTuyenDuong("IDINSERT", "1MOTTEST", "2");
        boolean actual = kbcxtd.insertChuyenXeThuocTuyenDuong(cxtd);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyenxethuoctuyenduong WHERE chuyenxe_id=?");
        stm.setString(1, cxtd.getChuyenxe_id());
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());
        Assertions.assertEquals(cxtd.getTuyenduong_id(), rs.getString("tuyenduong_id"));
        Assertions.assertEquals(cxtd.getChuyenxe_id(), rs.getString("chuyenxe_id"));

    }

    @Test
    @Order(4)
    @DisplayName("Kiểm tra chức năng tìm kiếm")
    public void testSearch() throws SQLException {
        String kw = "1MOTTEST";
        List<ChuyenXeThuocTuyenDuong> cxtds = kbcxtd.getCXTuyenDuong(kw);
        Assertions.assertEquals(1, cxtds.size());
        for (ChuyenXeThuocTuyenDuong cxtd : cxtds) {
            Assertions.assertTrue(cxtd.getChuyenxe_id().contains(kw));
        }
    }

    @Test
    @Order(5)
    @DisplayName("Test cập nhật thông CHUYENXE TUYEN DUONG thành công")
    public void testUpdateCXTDSuccess() throws SQLException {
        ChuyenXeThuocTuyenDuong cxtd = new ChuyenXeThuocTuyenDuong("IDINSERT", "4BONTES", "9e1f1c1e-e68c-4fa1-85c0-67a080f15ea2");
        boolean actual = kbcxtd.updateChuyenXeTuyenDuong(cxtd);
        Assertions.assertTrue(actual);

        PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyenxethuoctuyenduong WHERE chuyenxe_id=?");
        stm.setString(1, cxtd.getChuyenxe_id());
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());

        Assertions.assertEquals(cxtd.getChuyenxe_id(), rs.getString("chuyenxe_id"));
        Assertions.assertEquals(cxtd.getTuyenduong_id(), rs.getString("tuyenduong_id"));
    }

    @Test
    @Order(6)
    @DisplayName("Test cập nhật thông tin CHUYENXE TUYEN DUONG thất bại")
    public void testUpdateCXTDFail() throws SQLException {
        ChuyenXeThuocTuyenDuong cxtd = new ChuyenXeThuocTuyenDuong("ChuyenXeKhongVoDich", "XeKhachVoDich", "TuyenDuongVoDich");
        boolean actual = kbcxtd.updateChuyenXeTuyenDuong(cxtd);
        Assertions.assertFalse(actual);
    }

    @Test
    @Order(7)
    @DisplayName("Test xóa CXTD thành công")
    public void testDeleteCXTDSuccess() throws SQLException {
        String id = "IDINSERT";
        boolean actual = kbcxtd.deleteChuyenXeTuyenDuong(id);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM chuyenxethuoctuyenduong WHERE id=?");
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        Assertions.assertFalse(rs.next());

    }
}
