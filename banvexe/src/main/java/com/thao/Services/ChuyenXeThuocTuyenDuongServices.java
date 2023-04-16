/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.ChuyenXeThuocTuyenDuong;
import com.thao.pojo.TuyenDuong;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chung Vu
 */
public class ChuyenXeThuocTuyenDuongServices {
    public List<ChuyenXeThuocTuyenDuong> getListCXThuocTD(){
        List<ChuyenXeThuocTuyenDuong> listCXThuocTD = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from chuyenxethuoctuyenduong");
            while(rs.next()){
                listCXThuocTD.add(new ChuyenXeThuocTuyenDuong(rs.getString("id"), rs.getString("chuyenxe_id"), rs.getString("tuyenduong_id")));
            }
            return listCXThuocTD;
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeThuocTuyenDuongServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int themChuyenXeThuocTuyenDuong(String tdID, String cxID){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "insert into chuyenxethuoctuyenduong values(?,?,?)";
            PreparedStatement stml = conn.prepareCall(sql);
            ChuyenXeThuocTuyenDuong d = new ChuyenXeThuocTuyenDuong(cxID, tdID);
            stml.setString(1, d.getId());
            stml.setString(2, d.getChuyenxe_id());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeThuocTuyenDuongServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int suaChuyenXeThuocTuyenDuong(ChuyenXeThuocTuyenDuong d){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "update chuyenxethuoctuyenduong set chuyenxe_id = ?, tuyenduong_id = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, d.getChuyenxe_id());
            stml.setString(2, d.getTuyenduong_id());
            stml.setString(3, d.getId());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeThuocTuyenDuongServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int xoaChuyenXeThuocTuyenDuong(ChuyenXeThuocTuyenDuong d){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "delete from chuyenxethuoctuyenduong where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            
            stml.setString(1, d.getId());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ChuyenXeThuocTuyenDuongServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
}
