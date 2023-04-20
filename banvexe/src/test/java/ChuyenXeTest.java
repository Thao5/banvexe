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
import com.thao.pojo.TuyenDuong;
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
    @CsvSource({"TP.HCM-Bến Tre"})
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
    @CsvSource({"8481cec7-433b-4432-928e-992c39a60cf6"})
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
    @CsvSource({"8481cec7-433b-4432-928e-992c39a60cf6"})
    public void testDemVeThuocChuyenXeMoreThan0(String cxID){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertNotEquals(cxs.demVeThuocChuyenXe(cxs.getCX(cxID), listVeDaDat), 0);
    }
    
    @ParameterizedTest
    @CsvSource({"f2a85303-f914-4cfd-8a32-6cf9a1fab421"})
    public void testDemVeThuocChuyenXe0(String cxID){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertEquals(cxs.demVeThuocChuyenXe(cxs.getCX(cxID), listVeDaDat), 0);
    }
    
    @ParameterizedTest
    @CsvSource({"TP.HCM-Bến Tre, 2023-04-30 00:00:00, 100000, 9f3d347b-7c22-4d85-a3b8-f95f902844d6, d0a26db3-bfcc-4b32-8257-833c89fe75a7, 8a7f6cc3-5ccb-4170-84e8-ecfc2ea2b54f"})
    public void testThemCXWorked(String name, String ngaykhoihanh, double giave, String xekhach_id, String benxedi_id, String benxeden_id){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertEquals(cxs.themCX(new ChuyenXe(name, LocalDateTime.parse(ngaykhoihanh, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), giave, xekhach_id, benxedi_id, benxeden_id)), 1);
    }
    
    @ParameterizedTest
    @CsvSource({"f2a85303-f914-4cfd-8a32-6cf9a1fab421"})
    public void testXoaCXWorking(String ID){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertNotEquals(cxs.xoaCX(ID), 0);
    }

    @ParameterizedTest
    @CsvSource({"f2a85303-f914-4cfd-8a32-6cf9a1fab421, Hạ Long-TP.HCM, 2023-04-13 00:00:00, 100000,9f3d347b-7c22-4d85-a3b8-f95f902844d6, d0a26db3-bfcc-4b32-8257-833c89fe75a7, 8a7f6cc3-5ccb-4170-84e8-ecfc2ea2b54f, 1"})
    public void testSuaCX(String cxID, String name, String ngaykhoihanh, double giave, String xekhach_id, String benxedi_id, String benxeden_id, int test){
        ChuyenXeServices cxs = new ChuyenXeServices();
        assertEquals(cxs.suaCX(new ChuyenXe(cxID, name, LocalDateTime.parse(ngaykhoihanh, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), giave, xekhach_id, benxedi_id, benxeden_id)), test); 
    }
}