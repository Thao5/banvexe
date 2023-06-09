/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thao.Services.BenXeServices;
import com.thao.Services.ChuyenXeServices;
import com.thao.Services.ChuyenXeThuocTuyenDuongServices;
import com.thao.Services.TuyenDuongServices;
import com.thao.Services.VeServices;
import com.thao.Services.XeKhachServices;
import com.thao.banvexe.FXMLDatVeController;
import com.thao.pojo.BenXe;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.ChuyenXeThuocTuyenDuong;
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
public class ChuyenXeThuocTuyenDuongTest {
    @ParameterizedTest
    @CsvSource({"8481cec7-433b-4432-928e-992c39a60cf6, 7eadf1ee-360e-4964-a885-50c7194178b3"})
    public void testThemCXThuocTD(String cxID, String tdID){
        ChuyenXeThuocTuyenDuongServices cxttds = new ChuyenXeThuocTuyenDuongServices();
        assertEquals(1, cxttds.themChuyenXeThuocTuyenDuong(tdID, cxID));
    }
    
    @ParameterizedTest
    @CsvSource({"f7ca393f-cd35-451f-ba7c-e1124989147d, 8481cec7-433b-4432-928e-992c39a60cf6, 7eadf1ee-360e-4964-a885-50c7194178b3"})
    public void testSuaCXThuocTD(String id, String cxID, String tdID){
        ChuyenXeThuocTuyenDuongServices cxttds = new ChuyenXeThuocTuyenDuongServices();
        assertEquals(1, cxttds.suaChuyenXeThuocTuyenDuong(new ChuyenXeThuocTuyenDuong(id, cxID, tdID)));
    }
    
    @ParameterizedTest
    @CsvSource({"f7ca393f-cd35-451f-ba7c-e1124989147d, 8481cec7-433b-4432-928e-992c39a60cf6, 7eadf1ee-360e-4964-a885-50c7194178b3"})
    public void testXoaCXThuocTD(String id, String cxID, String tdID){
        ChuyenXeThuocTuyenDuongServices cxttds = new ChuyenXeThuocTuyenDuongServices();
        assertEquals(1, cxttds.xoaChuyenXeThuocTuyenDuong(new ChuyenXeThuocTuyenDuong(id, cxID, tdID)));
    }
}
