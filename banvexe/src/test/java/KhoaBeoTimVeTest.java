

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thao.Services.DatabaseConnection;
//import com.thao.Services.KhoaBeoTimVeService;
import com.thao.Services.KhoaBeoVeXeService;
import com.thao.pojo.Ve;
import java.sql.Connection;
import java.sql.SQLException;
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
    @ValueSource(strings = {"613d3d8b-348b-4bb5-9a05-6a41675d75d6",
        "7063a868-b825-4faa-bcb6-e97a8e283045",
        "192413a6-7c3c-415a-b947-fb549df67ff1"})
    public void testDeleteSuccess(String id) throws SQLException {
        Assertions.assertTrue(kb.deleteVe(id));

    }

    @ParameterizedTest
    @DisplayName("TEST XÓA MÃ VÉ KHÔNG TỒN TẠI")
    @CsvFileSource(resources = "/data/data.csv", numLinesToSkip = 1)
    public void testDeleteNotSuccessCSV(String id, boolean expected) throws SQLException {
        boolean actual = kb.deleteVe(id);
        Assertions.assertEquals(expected, actual);
    }
}
