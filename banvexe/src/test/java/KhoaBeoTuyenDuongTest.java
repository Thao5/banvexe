
import com.thao.Services.DatabaseConnection;
import com.thao.Services.KhoaBeoGheService;
import com.thao.Services.KhoaBeoTuyenduongService;
import com.thao.pojo.Ghe;
import com.thao.pojo.TuyenDuong;
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
public class KhoaBeoTuyenDuongTest {

    private static Connection conn;
    private static KhoaBeoTuyenduongService kbtds;

    @BeforeAll
    public static void BeforeAll() throws SQLException {
        conn = DatabaseConnection.getDBConnection();
        kbtds = new KhoaBeoTuyenduongService();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Test
    @Order(1)
    @DisplayName("Kiểm tra Thêm TuyenDuong không hợp lệ null")
    public void testInsertNull() throws SQLException {
        TuyenDuong td = new TuyenDuong(null, null, null);
        Throwable exception = Assertions.assertThrows(Throwable.class, () -> {
            kbtds.insertTuyenDuong(td);
        });
        Assertions.assertTrue(exception instanceof SQLIntegrityConstraintViolationException);
    }

    @Test
    @Order(2)
    @DisplayName("Kiểm tra Thêm TuyenDuong thành công")
    public void testInsertSuccess() throws SQLException {
        // Tạo một đối tượng TuyenDuong mới với các thông tin hợp lệ
        TuyenDuong td = new TuyenDuong("TDVODICH", "Vodich1", "Vodich2");
        boolean actual = kbtds.insertTuyenDuong(td);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM Tuyenduong WHERE id=?");
        stm.setString(1, td.getId());
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());
        Assertions.assertEquals(td.getId(), rs.getString("id"));
        Assertions.assertEquals(td.getDiemdi(), rs.getString("diemdi"));
        Assertions.assertEquals(td.getDiemden(), rs.getString("diemden"));

    }

    @Test
    @Order(3)
    @DisplayName("Kiểm tra Tìm kiếm TuyenDuong theo Tên Tuyến Đường")
    public void testSearchByDiemdi() throws SQLException {
        String kw = "Vodich1";
        List<TuyenDuong> tds = kbtds.getLTuyenDuong(kw);
        Assertions.assertEquals(1, tds.size());
        for (TuyenDuong td : tds) {
            Assertions.assertTrue(td.getDiemdi().contains(kw));
        }
    }

    @Test
    @Order(4)
    @DisplayName("Kiểm tra Cập nhật TuyenDuong thành công")
    public void testUpdateSuccess() throws SQLException {

        TuyenDuong td = new TuyenDuong("TDVODICH", "Vodich1UPDATE", "Vodich2UPDATE");
        boolean actual = kbtds.updateTuyenDuong(td);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM Tuyenduong WHERE id=?");
        stm.setString(1, td.getId());
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());
        Assertions.assertEquals(td.getDiemdi(), rs.getString("diemdi"));
        Assertions.assertEquals(td.getDiemden(), rs.getString("diemden"));

    }

    @Test
    @Order(5)
    @DisplayName("Kiểm tra Xóa TuyenDuong thành công")
    public void testDeleteSuccess() throws SQLException {
        String id = "TDVODICH";
        boolean actual = kbtds.deleteTuyenduong(id);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM Tuyenduong WHERE id=?");
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        Assertions.assertFalse(rs.next());
       
    }
}
