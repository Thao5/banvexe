/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thao.Services.BenXeServices;
import com.thao.Services.ChuyenXeServices;
import com.thao.Services.TuyenDuongServices;
import com.thao.Services.VeServices;
import com.thao.Services.XeKhachServices;
import com.thao.banvexe.FXMLDatVeController;
import com.thao.pojo.BenXe;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.TuyenDuong;
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
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 *
 * @author Chung Vu
 */
public class TuyenDuongTest {
    @ParameterizedTest
    @CsvSource({"TP.HCM, Hạ Long"})
    public void testThemTD(String diemdi, String diemden){
        TuyenDuongServices tds = new TuyenDuongServices();
        assertEquals(1, tds.themTD(new TuyenDuong(diemdi, diemden)));
    }
    
    @ParameterizedTest
    @CsvSource({"f71402f4-7119-4486-af1f-ac9fcf9185ef, TP.HCM, Vi Thành"})
    public void testSuaTD(String id, String diemdi, String diemden){
        TuyenDuongServices tds = new TuyenDuongServices();
        assertEquals(1, tds.suaTD(new TuyenDuong(id, diemdi, diemden)));
    }
    
    @ParameterizedTest
    @CsvSource({"f71402f4-7119-4486-af1f-ac9fcf9185ef, TP.HCM, Vi Thành"})
    public void testxoaTD(String id, String diemdi, String diemden){
        TuyenDuongServices tds = new TuyenDuongServices();
        assertNotEquals(0, tds.xoaTD(new TuyenDuong(id, diemdi, diemden)));
    }
}
