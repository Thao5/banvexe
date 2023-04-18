/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thao.Services.ChuyenXeServices;
import com.thao.Services.UserServices;
import com.thao.Services.VeServices;
import com.thao.Services.XeKhachServices;
import com.thao.banvexe.FXMLDatVeController;
import static com.thao.banvexe.FXMLDatVeController.listVeDaDat;
import com.thao.pojo.ChuyenXe;
import com.thao.pojo.User;
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
public class UserTest {
    @ParameterizedTest
    @CsvSource({"thao, 123456"})
    public void testGetUserNotNull(String username, String password){
        UserServices urs = new UserServices();
        assertNotNull(urs.getUser(username, password));
    }
    
    @ParameterizedTest
    @CsvSource({"thao, 1234568", "thao1, 123456", "test1, test1", ","})
    public void testGetUserNull(String username, String password){
        UserServices urs = new UserServices();
        assertNull(urs.getUser(username, password));
    }
    
    @ParameterizedTest
    @CsvSource({"test, test, 123456, test, test, false"})
    public void testThemUser(String ho, String ten, String sdt, String username, String password, boolean admin){
        UserServices urs = new UserServices();
        assertEquals(1, urs.themUser(new User(ho, ten, sdt, username, password, admin)));
    }
    
    @ParameterizedTest
    @CsvSource({"597f93a1-cde4-4055-9770-b0ba5ff19d7e, test, test, 1234567, test, test, false"})
    public void testSuaUser(String id, String ho, String ten, String sdt, String username, String password, boolean admin){
        UserServices urs = new UserServices();
        assertEquals(1, urs.suaUser(new User(id, ho, ten, sdt, username, password, admin)));
    }
    
    @ParameterizedTest
    @CsvSource({"597f93a1-cde4-4055-9770-b0ba5ff19d7e"})
    public void testXoaUser(String id){
        UserServices urs = new UserServices();
        assertNotEquals(0, urs.xoaUser(id));
    }
}
