/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thao.Services.ChuyenXeServices;
import com.thao.Services.VeServices;
import com.thao.banvexe.FXMLDatVeController;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.Ve;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import static org.junit.jupiter.params.provider.Arguments.arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * @author Chung Vu
 */
public class QuyDinhVeTest {
    @DisplayName("Kiểm tra thời gian đặt vé")
    @ParameterizedTest
    @CsvSource({"2023-04-08 07:35:19, 9, true", "2023-04-30 06:00:00, 9, true", "2023-04-30 07:00:00, 9, false"})
    public void testKiemTraVeDat(String ngayIn, String idCX, boolean  test){
        ChuyenXeServices cxs = new ChuyenXeServices();
        VeServices ves = new VeServices();
        Ve ve = new Ve("a13", cxs.getCX(idCX).getGiave(), LocalDateTime.parse(ngayIn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "test", "0706847756", "1", cxs.getCX(idCX).getId());
        assertEquals(ves.kiemTraVeDat(ve, cxs.getCX(idCX)), test);
    }
    
    @ParameterizedTest
    @CsvSource({"2023-04-30 06:30:00, 9, true", "2023-04-30 07:00:00, 9, false","2023-04-30 05:00:00, 9, true", "2023-04-30 06:31:00, 9, false"})
    public void testKiemTraVeDat30(String ngayIn, String idCX, boolean test){
        ChuyenXeServices cxs = new ChuyenXeServices();
        VeServices ves = new VeServices();
        Ve ve = new Ve("a13", cxs.getCX(idCX).getGiave(), LocalDateTime.parse(ngayIn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "test", "0706847756", "1", cxs.getCX(idCX).getId());
        assertEquals(ves.kiemTraVeDat30(ve, cxs.getCX(idCX)), test);
    }
    
    @ParameterizedTest
    @CsvSource({"2023-04-30 06:55:00, 9, true", "2023-04-30 06:56:00, 9, false", "2023-04-30 07:00:00, 9, false", "2023-04-30 05:00:00, 9, true"})
    public void testKiemTraVeMua(String ngayIn, String idCX, boolean test){
        ChuyenXeServices cxs = new ChuyenXeServices();
        VeServices ves = new VeServices();
        Ve ve = new Ve("a13", cxs.getCX(idCX).getGiave(), LocalDateTime.parse(ngayIn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), "test", "0706847756", "1", cxs.getCX(idCX).getId());
        assertEquals(ves.kiemTraVeMua(ve, cxs.getCX(idCX)), test);
    }
    
    @ParameterizedTest
    @CsvSource({"a14, 9, true", "a15, 9, false"})
    public void testIsChoTrong(String soGhe, String idCX, boolean test){
        VeServices ves = new VeServices();
        assertEquals(ves.isChoTrong(soGhe, idCX, FXMLDatVeController.listVeDaDat), test);
    }
    
    @ParameterizedTest
    @CsvSource({"a13, 100000, 2023-04-11 14:51:00, test, 123456, 1, 9, 1", "a13, 100000, 2023-04-11 14:51:00, test, 123456, 1, 2, 0"})
    public void testThemVe(String soGhe, Double giaVe, String ngayIn, String kh, String sdt, String userID, String cxID, int test){
        VeServices ves = new VeServices();
        assertEquals(ves.themVe(new Ve(soGhe, giaVe, LocalDateTime.parse(ngayIn, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), kh, sdt, userID, cxID)), test);
    }
    
    
}
