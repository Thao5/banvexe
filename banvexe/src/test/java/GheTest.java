/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thao.Services.ChuyenXeServices;
import com.thao.Services.GheServices;
import com.thao.Services.UserServices;
import com.thao.Services.VeServices;
import com.thao.Services.XeKhachServices;
import com.thao.banvexe.FXMLDatVeController;
import static com.thao.banvexe.FXMLDatVeController.listVeDaDat;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.Ghe;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
/**
 *
 * @author Chung Vu
 */
public class GheTest {
    @ParameterizedTest
    @CsvSource({"a14, true, 8ac466d2-b941-47a1-8193-8ed650b16ddf, 9f3d347b-7c22-4d85-a3b8-f95f902844d6"})
    public void testThemGheNotNull(String gheName, boolean trangThai, String veID, String xkID){
        GheServices ghes = new GheServices();
        assertEquals(ghes.themGhe(new Ghe(gheName, trangThai, veID, xkID)), 1);
    }
    
    @ParameterizedTest
    @CsvSource({"a14, true, 8ac466d2-b941-47a1-8193-8ed650b16ddf, 9f3d347b-7c22-4d85-a3b8-f95f902844d6","a14, true, 8ac466d2-b941-47a1-8193-8ed650b16ddf, 9f3d347b-7c22-4d85-a3b8-f95f902844d6"})
    public void testThemGheNull(String gheName, boolean trangThai, String veID, String xkID){
        GheServices ghes = new GheServices();
        assertEquals(ghes.themGhe(new Ghe(gheName, trangThai, veID, xkID)), 0);
    }
    
    @ParameterizedTest
    @CsvSource({"b2320758-757d-4492-9c0a-efa396b6aefe, a15, false, 8ac466d2-b941-47a1-8193-8ed650b16ddf, 9f3d347b-7c22-4d85-a3b8-f95f902844d6"})
    public void testSuaGhe(String id, String gheName, boolean trangThai, String veID, String xkID){
        GheServices ghes = new GheServices();
        assertEquals(ghes.suaGhe(new Ghe(id,gheName, trangThai, veID, xkID)), 1);
    }
    
    @ParameterizedTest
    @CsvSource({"b2320758-757d-4492-9c0a-efa396b6aefe"})
    public void testXoaGhe(String id){
        GheServices ghes = new GheServices();
        assertNotEquals(ghes.xoaGhe(id), 0);
    }
}
