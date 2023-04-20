

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thao.Services.DatabaseConnection;
//import com.thao.Services.KhoaBeoTimVeService;
import com.thao.Services.KhoaBeoVeXeService;
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

/**
 *
 * @author anhkh
 */
public class KhoaBeoTimVeTest {

    private static Connection conn;
    private static KhoaBeoVeXeService kb;

    @BeforeAll
    public static void BeforeAll() throws SQLException {
        conn = DatabaseConnection.getDBConnection();
        kb = new KhoaBeoVeXeService();
    }

    @AfterAll
    public static void afterAll() throws SQLException {
        if (conn != null) {
            conn.close();
        }
    }

    @ParameterizedTest
    @DisplayName("Kiểm tra Đàu là mã vé")
    @ValueSource(strings = {"c88efa32-d095-4111-978b-33edeffd03ee",
        "d759356a-9816-439f-a35c-d73ef5c992b6",
        "8ac466d2-b941-47a1-8193-8ed650b16ddf"})
    public void TimVeSearch(String kw) throws SQLException {
        List<Ve> ves = kb.getListVe(kw);
        Assertions.assertEquals(1, ves.size());
        for (Ve v : ves) {
            Assertions.assertTrue(v.getId().contains(kw));
        }
    }

    @ParameterizedTest
    @DisplayName("Kiểm tra Đàu vào là sql")
    @ValueSource(strings = {"SELECT", "INSERT", "UPDATE", "DELETE"})
    public void TimVeSearchSQL(String kw) throws SQLException {
        List<Ve> ves = kb.getListVe(kw);
        Assertions.assertEquals(0, ves.size());
        for (Ve v : ves) {
            Assertions.assertTrue(v.getId().contains(kw));
        }
    }

    @ParameterizedTest
    @DisplayName("TEST THÊM VÉ SUCCESS")
    @CsvSource({
        "1,A101,40000,2023-04-18 00:00:00,KH1,1,1,8481cec7-433b-4432-928e-992c39a60cf6",
        "2,B201,50000,2023-04-19 00:00:00,KH2,2,1,8481cec7-433b-4432-928e-992c39a60cf6",
        "3,C301,35000,2023-04-20 00:00:00,KH3,1,1,8481cec7-433b-4432-928e-992c39a60cf6",
        "4,D401,45000,2023-04-21 00:00:00,KH4,1,1,8481cec7-433b-4432-928e-992c39a60cf6",
        "5,E501,60000,2023-04-22 00:00:00,KH5,1,1,8481cec7-433b-4432-928e-992c39a60cf6"
    })
    public void InsertSuccess(String id, String soghe, Double giave, String ngayinS, String khachhang, String sdt, String user_id, String chuyenxe_id) throws SQLException {
        LocalDateTime ngayin = LocalDateTime.parse(ngayinS, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Ve ve = new Ve(id, soghe, giave, ngayin, khachhang, sdt, user_id, chuyenxe_id);
        Boolean actual = kb.InsertVe(ve);

        Assertions.assertEquals(true, actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM ve WHERE id=?");
        stm.setString(1, ve.getId());

        ResultSet rs = stm.executeQuery();
        
        
     

        Assertions.assertNotNull(rs.next());
        Assertions.assertEquals(ve.getSoghe(), rs.getString("soghe"));
        Assertions.assertEquals(ve.getKhachhang(), rs.getString("khachhang"));
        Assertions.assertEquals(ve.getId(), rs.getString("id"));
    }

    @ParameterizedTest
    @DisplayName("TEST XÓA MÃ VÉ TỒN TẠI")
    @ValueSource(strings = {"1",
        "2",
        "3",
        "4",
        "5"})
    public void testDeleteSuccess(String id) throws SQLException {
        Assertions.assertTrue(kb.deleteVe(id));
        PreparedStatement stm = conn.prepareCall("SELECT * FROM ve WHERE id=?");
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
        Assertions.assertFalse(rs.next());

    }

    @ParameterizedTest
    @DisplayName("TEST XÓA MÃ VÉ KHÔNG TỒN TẠI")
    @CsvFileSource(resources = "/data/data.csv", numLinesToSkip = 1)
    public void testDeleteNotSuccessCSV(String id, boolean expected) throws SQLException {
        boolean actual = kb.deleteVe(id);
        Assertions.assertEquals(expected, actual);
    }
}
