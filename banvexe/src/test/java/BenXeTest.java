/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import com.thao.Services.BenXeServices;
import com.thao.Services.ChuyenXeServices;
import com.thao.Services.VeServices;
import com.thao.Services.XeKhachServices;
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
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertNotNull;
/**
 *
 * @author Chung Vu
 */
public class BenXeTest {
    @ParameterizedTest
    @CsvSource({"1","2","3"})
    public void testGetBenXeNotNull(String id){
        BenXeServices bxs = new BenXeServices();
        assertNotNull(bxs.getBX(id));
    }
    
    @ParameterizedTest
    @CsvSource({"11561","22165","31561"})
    public void testGetBenXeNull(String id){
        BenXeServices bxs = new BenXeServices();
        assertNull(bxs.getBX(id));
    }
}