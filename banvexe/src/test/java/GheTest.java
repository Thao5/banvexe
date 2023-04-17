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
    @CsvSource({"a13, true, a9f6853c-6859-4975-9bb7-38bc096d1f94, 1"})
    public void testThemGheNotNull(String gheName, boolean trangThai, String veID, String xkID){
        GheServices ghes = new GheServices();
        assertEquals(ghes.themGhe(new Ghe(gheName, trangThai, veID, xkID)), 1);
    }
    
    @ParameterizedTest
    @CsvSource({"a13, true, 8389ce52-e2dc-41e8-b581-1f9659bbce2esada, 1","a13, true, 8389ce52-e2dc-41e8-b581-1f9659bbce2e, 8"})
    public void testThemGheNull(String gheName, boolean trangThai, String veID, String xkID){
        GheServices ghes = new GheServices();
        assertEquals(ghes.themGhe(new Ghe(gheName, trangThai, veID, xkID)), 0);
    }
    
    @ParameterizedTest
    @CsvSource({"c841da72-a052-4ba9-aef2-c40546c0b640, a13, false, a9f6853c-6859-4975-9bb7-38bc096d1f94, 1"})
    public void testSuaGhe(String id, String gheName, boolean trangThai, String veID, String xkID){
        GheServices ghes = new GheServices();
        assertEquals(ghes.suaGhe(new Ghe(id,gheName, trangThai, veID, xkID)), 1);
    }
    
    @ParameterizedTest
    @CsvSource({"c841da72-a052-4ba9-aef2-c40546c0b640"})
    public void testXoaGhe(String id){
        GheServices ghes = new GheServices();
        assertNotEquals(ghes.xoaGhe(id), 0);
    }
}
