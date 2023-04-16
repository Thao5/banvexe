/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thao.Services.ChuyenXeServices;
import com.thao.Services.VeServices;
import com.thao.Services.XeKhachServices;
import com.thao.banvexe.FXMLDatVeController;
import static com.thao.banvexe.FXMLDatVeController.listVeDaDat;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.Ve;
import java.sql.SQLException;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
/**
 *
 * @author Chung Vu
 */
public class ChuyenXeTest {
    @ParameterizedTest
    @CsvSource({"TPHCM-Vũng Tàu", "TPHCM-Vi Thành"})
    public void testGetCXTheoNameNotNull(String name){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertNotNull(cxs.getCXTheoName(name));
    }
    
    @ParameterizedTest
    @CsvSource({"TPHCM-Vũng Tàusad", "TPHCM-Vi Thànhsada"})
    public void testGetCXTheoNameNull(String name){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertNull(cxs.getCXTheoName(name));
    }
    
    @ParameterizedTest
    @CsvSource({"8", "9"})
    public void testGetCXTheoIDNotNull(String id){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertNotNull(cxs.getCX(id));
    }
    
    @ParameterizedTest
    @CsvSource({"81231", "91561"})
    public void testGetCXTheoIDNull(String id){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertNull(cxs.getCX(id));
    }
    
    @ParameterizedTest
    @CsvSource({"9"})
    public void testDemVeThuocChuyenXeMoreThan0(String cxID){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertNotEquals(cxs.demVeThuocChuyenXe(cxs.getCX(cxID), listVeDaDat), 0);
    }
    
    @ParameterizedTest
    @CsvSource({"8"})
    public void testDemVeThuocChuyenXe0(String cxID){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertEquals(cxs.demVeThuocChuyenXe(cxs.getCX(cxID), listVeDaDat), 0);
    }
    
    @ParameterizedTest
    @CsvSource({"Hạ Long-TP.HCM, 2023-04-13 00:00:00, 100000, 1, 1, 2"})
    public void testThemCXWorked(String name, String ngaykhoihanh, double giave, String xekhach_id, String benxedi_id, String benxeden_id){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertEquals(cxs.themCX(new ChuyenXe(name, LocalDateTime.parse(ngaykhoihanh, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), giave, xekhach_id, benxedi_id, benxeden_id)), 1);
    }
    
    @ParameterizedTest
    @CsvSource({"41ecbb52-b644-45b3-80ff-2fa81cf86997", "52834e15-7cfe-487f-ad22-f4aa2d128970", "sadsad"})
    public void testXoaCX(String ID){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertNotEquals(cxs.xoaCX(ID), 0);
    }
    
    @ParameterizedTest
    @CsvSource({"8, Hạ Long-TP.HCM, 2023-04-13 00:00:00, 100000, 1, 1, 2, 1"})
    public void testSuaCX(String cxID, String name, String ngaykhoihanh, double giave, String xekhach_id, String benxedi_id, String benxeden_id, int test){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertEquals(cxs.suaCX(new ChuyenXe(cxID, name, LocalDateTime.parse(ngaykhoihanh, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), giave, xekhach_id, benxedi_id, benxeden_id)), test); 
    }
}