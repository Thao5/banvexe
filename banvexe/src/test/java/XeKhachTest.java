/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thao.Services.ChuyenXeServices;
import com.thao.Services.VeServices;
import com.thao.Services.XeKhachServices;
import com.thao.banvexe.FXMLDatVeController;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.Ve;
import com.thao.pojo.XeKhach;
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
public class XeKhachTest {
    @ParameterizedTest
    @CsvSource({"1","2"})
    public void testGetXKNotNull(String id){
        XeKhachServices xks = new XeKhachServices();
        assertNotNull(xks.getXK(id));
    }
    
    @ParameterizedTest
    @CsvSource({"12354","1592"})
    public void testGetXKNull(String id){
        XeKhachServices xks = new XeKhachServices();
        assertNull(xks.getXK(id));
    }
    
    @ParameterizedTest
    @CsvSource({"30, aaaaa"})
    public void testThemXK(int sochongoi, String bienso){
        XeKhachServices xks = new XeKhachServices();
        assertEquals(1, xks.themXK(new XeKhach(sochongoi, bienso)));
    }
    
    @ParameterizedTest
    @CsvSource({"9d8ab98c-b58c-4e8e-99fc-379d5588ffd7, 50, aaaaa"})
    public void testSuaXK(String id, int sochongoi, String bienso){
        XeKhachServices xks = new XeKhachServices();
        assertEquals(1, xks.suaXK(new XeKhach(id, sochongoi, bienso)));
    }
    
    @ParameterizedTest
    @CsvSource({"9d8ab98c-b58c-4e8e-99fc-379d5588ffd7, 50, aaaaa"})
    public void testXoaXK(String id, int sochongoi, String bienso){
        XeKhachServices xks = new XeKhachServices();
        assertNotEquals(0, xks.xoaXK(new XeKhach(id, sochongoi, bienso)));
    }
}
