
import com.thao.Services.DatabaseConnection;
import com.thao.Services.KhoaBeoBenXeService;
import com.thao.Services.KhoaBeoVeXeService;
import com.thao.pojo.BenXe;
import com.thao.pojo.ChuyenXe;
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author anhkh
 */
public class KhoaBeoBenXeTest {

    private static Connection conn;
    private static KhoaBeoBenXeService kbbx;

    @BeforeAll
    public static void BeforeAll() throws SQLException {
        conn = DatabaseConnection.getDBConnection();
        kbbx = new KhoaBeoBenXeService();
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
        BenXe bx = new BenXe(null, null);
        Throwable exception = Assertions.assertThrows(Throwable.class, () -> {
            kbbx.insertBenXe(bx);
        });
        Assertions.assertTrue(exception instanceof SQLIntegrityConstraintViolationException);
    }

    @Test
    @DisplayName("Kiểm tra insert thành công")
    public void testInsertSuccess() throws SQLException {
        BenXe bx = new BenXe("2088569f-24ff-4f9d-ae2c-2ec2d66adf28","BX66A1567", "Bến xe Hà Nội");
        boolean actual = kbbx.insertBenXe(bx);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM benxe WHERE name=?");
        stm.setString(1, bx.getName());
        ResultSet rs = stm.executeQuery();
        Assertions.assertTrue(rs.next());
         Assertions.assertEquals(bx.getId(), rs.getString("id"));
        Assertions.assertEquals(bx.getName(), rs.getString("name"));
        Assertions.assertEquals(bx.getAddress(), rs.getString("address"));
    }
@Test
@DisplayName("Kiểm tra chức năng tìm kiếm")
public void testSearch() throws SQLException {
     String kw = "BX66A1567";
     List<BenXe> bxs = kbbx.getLBenXe(kw);
     Assertions.assertEquals(1, bxs.size());
        for (BenXe bx : bxs) {
            Assertions.assertTrue(bx.getName().contains(kw));
        }
}
@Test
@DisplayName("Test cập nhật thông tin bến xe thành công")
public void testUpdateBenXeSuccess() throws SQLException {
        BenXe bx = new BenXe("2088569f-24ff-4f9d-ae2c-2ec2d66adf28","BX66A1567UPDATE", "Bến xe Hà NộiUPDATE");
        boolean actual = kbbx.updateBenXe(bx);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM Benxe WHERE id=?");
        stm.setString(1, bx.getId());

        ResultSet rs = stm.executeQuery();
        Assertions.assertNotNull(rs.next());
         Assertions.assertEquals(bx.getId(), rs.getString("id"));
        Assertions.assertEquals(bx.getName(), rs.getString("name"));
        Assertions.assertEquals(bx.getAddress(), rs.getString("address"));
}
    @Test
    @DisplayName("Test xóa bến xe thành công")
   public void testDeleteBenXeSuccess() throws SQLException {
        String id = "2088569f-24ff-4f9d-ae2c-2ec2d66adf28";
        boolean actual = kbbx.deleteBenXe(id);
        Assertions.assertTrue(actual);
        PreparedStatement stm = conn.prepareCall("SELECT * FROM benxe WHERE id=?");
        stm.setString(1, id);
        ResultSet rs = stm.executeQuery();
       Assertions.assertFalse(rs.next());
        
    }
   
}
