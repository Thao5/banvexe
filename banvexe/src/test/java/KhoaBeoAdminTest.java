
import com.thao.Services.DatabaseConnection;
import com.thao.Services.KhoaBeoChuyenXeService;
import com.thao.pojo.ChuyenXe;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
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
    public void testInsertNull() throws SQLException{
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
}
