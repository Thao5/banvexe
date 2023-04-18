/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

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
import javax.xml.transform.Result;

/**
 *
 * @author Chung Vu
 */
public class TuyenDuongServices {
    public List<TuyenDuong> getTuyenDuongs(){
        List<TuyenDuong> listTD = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from tuyenduong");
            while(rs.next()){
                listTD.add(new TuyenDuong(rs.getString("id"), rs.getString("diemdi"), rs.getString("diemden")));
            }
            return listTD;
        } catch (SQLException ex) {
            Logger.getLogger(TuyenDuongServices.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public int themTD(TuyenDuong td){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "insert into tuyenduong values(?,?,?)";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, td.getId());
            stml.setString(2, td.getDiemdi());
            stml.setString(3, td.getDiemden());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TuyenDuongServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int suaTD(TuyenDuong td){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "update tuyenduong set diemdi = ?, diemden = ? where id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, td.getDiemdi());
            stml.setString(2, td.getDiemden());
            stml.setString(3, td.getId());
            
            return stml.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(TuyenDuongServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public int xoaTD(TuyenDuong td){
        int count = 0;
        try(Connection conn = DatabaseConnection.getDBConnection()){
            conn.setAutoCommit(false);
            String sql = "delete from chuyenxethuoctuyenduong where tuyenduong_id = ?";
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, td.getId());
            
            count += stml.executeUpdate();
            
            sql = "delete from tuyenduong where id = ?";
            stml = conn.prepareCall(sql);
            stml.setString(1, td.getId());
            
            count += stml.executeUpdate();
            
            return count;
        } catch (SQLException ex) {
            Logger.getLogger(TuyenDuongServices.class.getName()).log(Level.SEVERE, null, ex);
            return count;
        }
    }
}
