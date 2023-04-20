
import com.thao.Services.DatabaseConnection;
import com.thao.Services.KhoaBeoChuyenXeService;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.Ve;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.provider.CsvFileSource;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author anhkh
 */
public class KhoaBeoAdminTest {

    private static Connection conn;
    private static KhoaBeoChuyenXeService kbcx;

    @BeforeAll
    public static void BeforeAll() throws SQLException {
        conn = DatabaseConnection.getDBConnection();
        kbcx = new KhoaBeoChuyenXeService();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @Test
    @DisplayName("Kiểm tra Thêm tất cả phần tử null")
    public void testInsertNull() throws SQLException {
        ChuyenXe cxTest = new ChuyenXe(null, null, null, null, null, null);
        Assertions.assertThrows(NullPointerException.class, () -> {
            kbcx.insertCx(cxTest);
        });
    }

    @Test
    @DisplayName("Kiểm tra Thêm chuyến xe với giá tiền là null")
    public void testInsertPriceIsNull() throws SQLException {
        ChuyenXe cxTest = new ChuyenXe("AAAAA", LocalDateTime.now(), null, "1", "1", "1");
        Assertions.assertThrows(NullPointerException.class, () -> {
            kbcx.insertCx(cxTest);
        });
    }

    @ParameterizedTest
    @DisplayName("Kiểm tra Thêm tất cả phần tử Thành ")
    @CsvSource({
        "1MOTTEST,CX10166A1,2023-04-19 00:00:00,450000,9f3d347b-7c22-4d85-a3b8-f95f902844d6, d0a26db3-bfcc-4b32-8257-833c89fe75a7, 8a7f6cc3-5ccb-4170-84e8-ecfc2ea2b54f",
        "2HAITEST,CX10266A1,2023-04-19 00:00:00,350000,9f3d347b-7c22-4d85-a3b8-f95f902844d6, d0a26db3-bfcc-4b32-8257-833c89fe75a7, 8a7f6cc3-5ccb-4170-84e8-ecfc2ea2b54f",
        "3BATEST,CX10366A1,2023-04-19 00:00:00,250000,9f3d347b-7c22-4d85-a3b8-f95f902844d6, d0a26db3-bfcc-4b32-8257-833c89fe75a7, 8a7f6cc3-5ccb-4170-84e8-ecfc2ea2b54f",
        "4BONTES,CX10466A1,2023-04-19 00:00:00,150000,9f3d347b-7c22-4d85-a3b8-f95f902844d6, d0a26db3-bfcc-4b32-8257-833c89fe75a7, 8a7f6cc3-5ccb-4170-84e8-ecfc2ea2b54f"
    })

    public void testInsert(String id, String name, String ngaykhoihanhStr, double giave, String xekhach_id, String benxedi_id, String benxeden_id) throws SQLException {
        LocalDateTime ngaykhoihanh = LocalDateTime.parse(ngaykhoihanhStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ChuyenXe chuyenXe = new ChuyenXe(id, name, ngaykhoihanh, giave, xekhach_id, benxedi_id, benxeden_id);
        boolean result = kbcx.insertCx(chuyenXe);
        Assertions.assertTrue(result);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM Chuyenxe WHERE id=?");
        stm.setString(1, chuyenXe.getId());

        ResultSet rs = stm.executeQuery();
        Assertions.assertNotNull(rs.next());
        Assertions.assertEquals(chuyenXe.getName(), rs.getString("name"));
        Assertions.assertEquals(chuyenXe.getId(), rs.getString("id"));
    }
    @ParameterizedTest
      @DisplayName("Kiểm tra Search THÀNH CÔNG ")
    @ValueSource(strings = {"CX10166A1",
        "CX10266A1",
        "CX10366A1", 
        "CX10466A1"})
    public void testSearchKw(String kw) throws SQLException {
        List<ChuyenXe> cxs = kbcx.getChuyenXe(kw);
        Assertions.assertEquals(1, cxs.size());
        for (ChuyenXe cx : cxs) {
            Assertions.assertTrue(cx.getName().contains(kw));
        }
    }
    @ParameterizedTest
        @DisplayName("Kiểm tra Update tất cả phần tử Thành Công")
    @CsvSource({
        "1MOTTEST,CX101UPDATE,2023-04-19 00:00:00,450000,9f3d347b-7c22-4d85-a3b8-f95f902844d6, d0a26db3-bfcc-4b32-8257-833c89fe75a7, 8a7f6cc3-5ccb-4170-84e8-ecfc2ea2b54f",
        "2HAITEST,CX102UPDATE,2023-04-19 00:00:00,350000,9f3d347b-7c22-4d85-a3b8-f95f902844d6, d0a26db3-bfcc-4b32-8257-833c89fe75a7, 8a7f6cc3-5ccb-4170-84e8-ecfc2ea2b54f",
        "3BATEST,CX103UPDATE,2023-04-19 00:00:00,250000,9f3d347b-7c22-4d85-a3b8-f95f902844d6, d0a26db3-bfcc-4b32-8257-833c89fe75a7, 8a7f6cc3-5ccb-4170-84e8-ecfc2ea2b54f",
        "4BONTES,CX104UPDATE,2023-04-19 00:00:00,150000,9f3d347b-7c22-4d85-a3b8-f95f902844d6, d0a26db3-bfcc-4b32-8257-833c89fe75a7, 8a7f6cc3-5ccb-4170-84e8-ecfc2ea2b54f"
    })
    public void testUpdate(String id, String name, String ngaykhoihanhStr, double giave, String xekhach_id, String benxedi_id, String benxeden_id) throws SQLException {
        LocalDateTime ngaykhoihanh = LocalDateTime.parse(ngaykhoihanhStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        ChuyenXe chuyenXe = new ChuyenXe(id, name, ngaykhoihanh, giave, xekhach_id, benxedi_id, benxeden_id);
        boolean actual = kbcx.updateCx(chuyenXe);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM Chuyenxe WHERE id=?");
        stm.setString(1, chuyenXe.getId());

        ResultSet rs = stm.executeQuery();
        Assertions.assertNotNull(rs.next());
        Assertions.assertEquals(chuyenXe.getName(), rs.getString("name"));
        Assertions.assertEquals(chuyenXe.getId(), rs.getString("id"));
    }
    
    @ParameterizedTest
      @DisplayName("Kiểm tra XÓA THÀNH CÔNG ")
    @ValueSource(strings = {"1MOTTEST",
        "2HAITEST",
        "3BATEST", "4BONTES"})
    public void testDelete(String id) throws SQLException {
        Assertions.assertTrue(kbcx.deleteChuyenXe(id));
        PreparedStatement stm = conn.prepareCall("SELECT * FROM Chuyenxe WHERE id=?");
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        Assertions.assertFalse(rs.next());

    }

    @ParameterizedTest
    @ValueSource(strings = {"Mot",
        "Hai",
        "Ba", "Bon"})
    public void testDeleteFail(String id) throws SQLException {
        Assertions.assertFalse(kbcx.deleteChuyenXe(id));
    }
}
