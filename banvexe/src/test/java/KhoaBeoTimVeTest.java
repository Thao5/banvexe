

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
        "f7b11589-0251-443f-9c10-2c6a7257335d"})
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
    @DisplayName("TEST XÓA MÃ VÉ TỒN TẠI")
    @ValueSource(strings = {"1,A101,40000,2023-04-18 16:07:28,KH1,00001,1,1",
        "2,B201,50000,2023-04-19 10:25:15,KH2,00002,2,2",
        "3,C301,35000,2023-04-20 14:30:00,KH3,00003,3,3",
        "4,D401,45000,2023-04-21 08:45:50,KH4,00004,4,4",
        "5,E501,60000,2023-04-22 17:20:10,KH5,00005,5,5"

    })
    public void InsertSuccess(String id, String soghe, Double giave, String ngayinS, String khachhang, String sdt, String user_id, String chuyenxe_id) throws SQLException {
    LocalDateTime ngayin = LocalDateTime.parse(ngayinS, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        Ve ve = new Ve(id, soghe, giave, ngayin, khachhang, sdt, user_id, chuyenxe_id);
        Boolean actual = kb.InsertVe(ve);
    
    // Kiểm tra kết quả insert
    Assertions.assertEquals(1, actual);
    
}


    @ParameterizedTest
    @DisplayName("TEST XÓA MÃ VÉ TỒN TẠI")
    @ValueSource(strings = {"613d3d8b-348b-4bb5-9a05-6a41675d75d6",
        "7063a868-b825-4faa-bcb6-e97a8e283045",
        "192413a6-7c3c-415a-b947-fb549df67ff1"})
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
