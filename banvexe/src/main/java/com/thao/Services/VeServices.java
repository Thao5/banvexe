/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.thao.Services;

import com.thao.pojo.ChuyenXe;
import com.thao.pojo.Ve;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Chung Vu
 */
public class VeServices {
    public List<Ve> listVe(){
        List<Ve> listVe = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from ve");
            
            while(rs.next()){
                listVe.add(new Ve(rs.getString("id"), rs.getString("soghe"), rs.getDouble("giave"), rs.getTimestamp("ngayin").toLocalDateTime(), rs.getString("khachhang"), rs.getString("sdt"), rs.getString("user_id"), rs.getString("chuyenxe_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listVe;
    }
    
    public List<Ve> listVe(String kw){
        List<Ve> listVe = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            String sql = "select * from ve";
            if(kw != null && !kw.isEmpty())
                sql += " WHERE khachhang like concat('%', ?, '%')";
            
            PreparedStatement stm = conn.prepareCall(sql);
            if (kw != null && !kw.isEmpty())
                stm.setString(1, kw);
            
            ResultSet rs = stm.executeQuery();
            while(rs.next()){
                listVe.add(new Ve(rs.getString("id"), rs.getString("soghe"), rs.getDouble("giave"), rs.getTimestamp("ngayin").toLocalDateTime(), rs.getString("khachhang"), rs.getString("sdt"), rs.getString("user_id"), rs.getString("chuyenxe_id")));
            }
        } catch (SQLException ex) {
            Logger.getLogger(VeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return listVe;
    }
    
    public int themVe(Ve ve){
        try(Connection conn = DatabaseConnection.getDBConnection()){
            conn.setAutoCommit(false);
            String sql = "insert into ve values(?,?,?,?,?,?,?,?)";
            PreparedStatement stat = conn.prepareCall(sql);
            
            stat.setString(1, ve.getId());
            stat.setString(2, ve.getSoghe());
            stat.setDouble(3, ve.getGiave());
            stat.setString(4, LocalDateTime.now().toString());
            stat.setString(5, ve.getKhachhang());
            stat.setString(6, ve.getSdt());
            stat.setString(7, ve.getUser_id());
            stat.setString(8, ve.getChuyenxe_id());
            
            int result = stat.executeUpdate();
            conn.commit();
            return result;
        } catch (SQLException ex) {
            Logger.getLogger(VeServices.class.getName()).log(Level.SEVERE, null, ex);
            return 0;
        }
    }
    
    public boolean kiemTraVeDat(Ve ve, ChuyenXe cx){
        if(ve.getNgayin().isBefore(cx.getNgaykhoihanh())){
            int ss = (int)Duration.between(ve.getNgayin(), cx.getNgaykhoihanh()).toMinutes();
            return ss >= 60;
        }
        return false;
    }
    
    public boolean kiemTraVeMua(Ve ve, ChuyenXe cx){
        if(ve.getNgayin().isBefore(cx.getNgaykhoihanh())){
            int ss = (int)Duration.between(ve.getNgayin(), cx.getNgaykhoihanh()).toMinutes();
            return ss >= 5;
        }
        return false;
    }
    
    public boolean kiemTraVeDat30(Ve ve, ChuyenXe cx){
        if(ve.getNgayin().isBefore(cx.getNgaykhoihanh())){
            int ss = (int)Duration.between(ve.getNgayin(), cx.getNgaykhoihanh()).toMinutes();
            return ss >= 30;
        }
        return false;
    }
    
    public boolean isChoTrong(Ve ve, List<Ve> listVe){
        Ve tmp = new Ve();
        List<Ve> listVe2 = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            
            String sql = "select * from ve where soghe = ? && chuyenxe_id = ? limit 1";
            
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, ve.getSoghe());
            stml.setString(2, ve.getChuyenxe_id());
            ResultSet rs = stml.executeQuery();
            if(rs.next())
            {
               tmp = new Ve(rs.getString("id"), rs.getString("soghe"), rs.getDouble("giave"), rs.getTimestamp("ngayin").toLocalDateTime(), rs.getString("khachhang"), rs.getString("sdt"), rs.getString("user_id"), rs.getString("chuyenxe_id"));
               listVe2.add(tmp);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listVe.stream().filter(x->x.getSoghe().equals(ve.getSoghe()) && x.getChuyenxe_id().equals(ve.getChuyenxe_id())).findFirst().isEmpty() && listVe2.isEmpty();
    }
    
    public boolean isChoTrong(String soGhe, String chuyenxe_id, List<Ve> listVe){
        Ve tmp = new Ve();
        List<Ve> listVe2 = new ArrayList<>();
        try(Connection conn = DatabaseConnection.getDBConnection()){
            
            String sql = "select * from ve where soghe = ? && chuyenxe_id = ? limit 1";
            
            PreparedStatement stml = conn.prepareCall(sql);
            stml.setString(1, soGhe);
            stml.setString(2, chuyenxe_id);
            ResultSet rs = stml.executeQuery();
            if(rs.next())
            {
               tmp = new Ve(rs.getString("id"), rs.getString("soghe"), rs.getDouble("giave"), rs.getTimestamp("ngayin").toLocalDateTime(), rs.getString("khachhang"), rs.getString("sdt"), rs.getString("user_id"), rs.getString("chuyenxe_id"));
               listVe2.add(tmp);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VeServices.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listVe.stream().filter(x->x.getSoghe().equals(soGhe) && x.getChuyenxe_id().equals(chuyenxe_id)).findFirst().isEmpty() && listVe2.isEmpty();
    }
}